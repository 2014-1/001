package com.crtb.tunnelmonitor.utils;

import java.util.Arrays;
import java.util.List;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.crtb.tunnelmonitor.dao.impl.v2.SubsidenceCrossSectionIndexDao;
import com.crtb.tunnelmonitor.dao.impl.v2.SubsidenceTotalDataDao;
import com.crtb.tunnelmonitor.dao.impl.v2.TunnelCrossSectionIndexDao;
import com.crtb.tunnelmonitor.dao.impl.v2.TunnelSettlementTotalDataDao;
import com.crtb.tunnelmonitor.entity.SubsidenceCrossSectionIndex;
import com.crtb.tunnelmonitor.entity.SubsidenceTotalData;
import com.crtb.tunnelmonitor.entity.TunnelCrossSectionIndex;
import com.crtb.tunnelmonitor.entity.TunnelSettlementTotalData;
import com.crtb.tunnelmonitor.network.CrtbWebService;
import com.crtb.tunnelmonitor.network.DataCounter;
import com.crtb.tunnelmonitor.network.DataCounter.CounterListener;
import com.crtb.tunnelmonitor.network.RpcCallback;
import com.crtb.tunnelmonitor.network.SectionStatus;


public class DataDownloadManager {
	private static final String LOG_TAG = "DataDownloadManager";
	
	public interface DownloadListener {
		/**
		 * 
		 * @param success
		 */
		public void done(boolean success);
	}

	private DownloadListener mListener;
	private Handler mHandler;
	
	public DataDownloadManager() {
		mHandler = new Handler(Looper.getMainLooper());
	}
	
	public void downloadData(DownloadListener listener) {
		mListener = listener;
		downloadSectionCodeList(SectionStatus.VALID);
	}
	
	//TODO: 下载工点数据
	public void downloadWorkSites(final DownloadListener listener) {
		CrtbWebService.getInstance().getZoneAndSiteCode(new RpcCallback() {
			
			@Override
			public void onSuccess(Object[] data) {
				String zoneCode = (String) data[0];
				String zoneName = (String) data[1];
				String siteCode = (String) data[2];
				String siteName = (String) data[3];
			}
			
			@Override
			public void onFailed(String reason) {
				if (listener != null) {
					listener.done(false);
				}
			}
		});
	}
	
	
    //下载断面编码数据
    private void downloadSectionCodeList(SectionStatus status) {
    	//3 = 断面编码下载 + 断面下载 
    	final DataCounter downloadCounter = new DataCounter("DownloadCounter)", 2, new CounterListener() {
			@Override
			public void done(final boolean success) {
				if (mListener != null) {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mListener.done(success);
						}
					});
				}
			}
		});
        CrtbWebService.getInstance().getSectionCodeList(status, new RpcCallback() {

            @Override
            public void onSuccess(Object[] data) {
                Log.d(LOG_TAG, "download section code list success.");
                List<String> sectionCodeList = Arrays.asList((String[])data);
                if (sectionCodeList != null && sectionCodeList.size() > 0) {
                	//标识断面编码列表下载完毕
                	downloadCounter.increase(true, "SecitonCodeList");
                	DataCounter sectionDownloadCounter = new DataCounter("SectionDownloadCounter", sectionCodeList.size(), new CounterListener() {
						@Override
						public void done(boolean success) {
							//标识断面数据下载完毕
							downloadCounter.increase(success, "SectionList");
						}
					});
                	for(String sectionCode : sectionCodeList) {
                        downloadSection(sectionCode, sectionDownloadCounter);
                    }
                } else {
                	downloadCounter.finish("section code list is empty");
                }
            }

            @Override
            public void onFailed(String reason) {
                Log.d(LOG_TAG, "download section code list failed.");
                downloadCounter.finish(reason);
            }
        });
    }
    
    private void downloadSection(final String sectionCode, final DataCounter sectionDownloadCounter) {
        CrtbWebService.getInstance().getSectionInfo(sectionCode, new RpcCallback() {

            @Override
            public void onSuccess(Object[] data) {
                TunnelCrossSectionIndex[] sectionInfo = (TunnelCrossSectionIndex[])data;
                final TunnelCrossSectionIndex section = sectionInfo[0];
                storeTunnelSection(section);
                List<String> pointCodeList = Arrays.asList(section.getSurveyPntName().split(","));
                if (pointCodeList != null && pointCodeList.size() > 0) {
                	DataCounter pointDownloadCounter = new DataCounter("PointDownloadCounter", pointCodeList.size(), new CounterListener() {
						@Override
						public void done(boolean success) {
							sectionDownloadCounter.increase(success, "SectionList");
						}
					});
                	 for(String pointCode : pointCodeList) {
                         downloadPoint(sectionCode, pointCode, pointDownloadCounter);
                     }
                } else {
                	sectionDownloadCounter.increase(true, "SectionList");
                }
            }

            @Override
            public void onFailed(String reason) {
                Log.d(LOG_TAG, "downloadSection failed: " + reason);
                sectionDownloadCounter.increase(false, "SectionList");
            }
        });
    }
    
    private void downloadPoint(final String sectionCode, final String pointCode, final DataCounter pointDownloadCounter) {
        CrtbWebService.getInstance().getPointInfo(pointCode, new RpcCallback() {

            @Override
            public void onSuccess(Object[] data) {
                final List<TunnelSettlementTotalData> pointTestDataList = Arrays.asList((TunnelSettlementTotalData[])data);
                storeTunnelPoints(pointTestDataList);
                pointDownloadCounter.increase(true, sectionCode);
                Log.d(LOG_TAG, "download point success.");
            }

            @Override
            public void onFailed(String reason) {
                Log.d(LOG_TAG, "download point failed: " + reason);
                pointDownloadCounter.increase(false, sectionCode);
            }
        });
    }
    
    private void storeTunnelSection(final TunnelCrossSectionIndex section) {
    	 new Thread(new Runnable() {
             @Override
             public void run() {
                 TunnelCrossSectionIndexDao dao = TunnelCrossSectionIndexDao.defaultDao();
                 dao.insert(section);
             }
         }).start();
    }
    
    private void storeTunnelPoints(final List<TunnelSettlementTotalData> pointTestDataList) {
    	new Thread(new Runnable() {
            @Override
            public void run() {
                TunnelSettlementTotalDataDao dao = TunnelSettlementTotalDataDao.defaultDao();
                for(TunnelSettlementTotalData testPointData : pointTestDataList) {
                    dao.insert(testPointData);
                }
            }
        }).start();
    }
    
    private void storeSubsidenceSection(final SubsidenceCrossSectionIndex section) {
   	 new Thread(new Runnable() {
            @Override
            public void run() {
            	SubsidenceCrossSectionIndexDao dao = SubsidenceCrossSectionIndexDao.defaultDao();
                dao.insert(section);
            }
        }).start();
   }
    
    private void storeSubsidencePoints(final List<SubsidenceTotalData> pointTestDataList) {
    	new Thread(new Runnable() {
            @Override
            public void run() {
            	SubsidenceTotalDataDao dao = SubsidenceTotalDataDao.defaultDao();
                for(SubsidenceTotalData testPointData : pointTestDataList) {
                    dao.insert(testPointData);
                }
            }
        }).start();
    }
}
