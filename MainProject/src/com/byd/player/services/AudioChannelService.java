package com.byd.player.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.IBinder;
import android.os.IPlayerService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.widget.Toast;
import android.os.IPlayerService;
import android.os.ServiceManager;
import android.os.RemoteException;


public class AudioChannelService extends Activity{
//
//    private int recBufSize = 0;
//    private int playBufSize = 0;
//    /**
//     * 采样率  441
//     * */
//    private int sampleRateInHz = 44100;
//    /**
//     * 声道,双声道
//     * */
//    private int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
//    /**
//     * 编码率
//     * */
//    private int encodingBitrate = AudioFormat.ENCODING_PCM_16BIT;
//
//    private static AudioRecord audioRecord;
//
//    private static AudioTrack audioTrack;
//
//    /**
//     * 是否录音
//     * */
//    private boolean blnRecord = false;
//    /**
//     * 是否播放
//     * */
//    private boolean blnPlay = false;
//    /**
//     * 即时播放
//     * */
//    private static volatile boolean blnInstantPlay = false;
//
//    private volatile static String currentChannelString = "none";
//
//    private static final int RECORDER_BPP = 16;
//
//    private ThreadInstantPlay playThread;
//
//    /**
//     * 0: bluetooth
//     * 1: fm
//     * 2: cmmb
//     * 3: aux
//     */
//
//    public void selectChannel(String service_tag) {
//        Log.d("AudioChannelService", "service tag is " + service_tag);
//        File select = new File("sys/kernel/debug/esai/esai_reg");
//        FileWriter fr;
//        try {
//            /**
//             * 0: bluetooth 1: fm 2: cmmb 3: aux
//             */
//            fr = new FileWriter(select);
//            Log.v(service_tag, "select the audio channel");
//            String cmd = "";
//            cmd += "ff ";
//            if (service_tag.equalsIgnoreCase("audiochannel-bt")) {
//                cmd += "0";
//                Log.d("AudioChannelService", "select bt!");
//            } else if (service_tag.equalsIgnoreCase("audiochannel-fm")) {
//                cmd += "1";
//            } else if (service_tag.equalsIgnoreCase("audiochannel-cmmb")) {
//                cmd += "2";
//            } else if (service_tag.equalsIgnoreCase("audiochannel-aux")) {
//                cmd += "3";
//                Log.d("AudioChannelService", "select aux!");
//            }
//
//            fr.write(cmd);
//            fr.close();
//            Log.d("BTChannelService", "select bt(ff0): " + cmd);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 播放录音线程
//     * */
//
//    public class LocalBinderPlayer extends Binder {
//        public AudioChannelService getService() {
//            return AudioChannelService.this;
//        }
//    }
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        // TODO Auto-generated method stub
//        Toast.makeText(this, "playerservice is create", Toast.LENGTH_SHORT).show();
//        super.onCreate();
//    }
//
//    public class AudioChannelBinder extends Binder {
//        public AudioChannelService getService() {
//            return AudioChannelService.this;
//        }
//    }
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // TODO Auto-generated method stub
//
//        //startPlayAudio();
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    public void startPlayAudio(String channel) throws InterruptedException
//    {
//        // 开始即时播放
//        //String service_tag = intent.getStringExtra("service_tag");
//
//        if(currentChannelString != channel)
//        {
//            //    		if(audioRecord != null)
//            //            {
//            //    			audioRecord.release();
//            //            }
//            //    		if(audioTrack != null)
//            //            {
//            //    			audioTrack.release();
//            //            }
//
//            blnInstantPlay = false;
//            recBufSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, encodingBitrate);
//            playBufSize = AudioTrack.getMinBufferSize(sampleRateInHz, channelConfig, encodingBitrate);
//            if (audioRecord == null) {
//                audioRecord = new AudioRecord(MediaRecorder.AudioSource.VOICE_COMMUNICATION,
//                        sampleRateInHz, channelConfig, encodingBitrate, recBufSize);
//            } else {
//                if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {
//                    audioRecord.stop();
//                }
//                Thread.sleep(500);
//            }
//            if (audioTrack == null) {
//                audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz,
//                        channelConfig, encodingBitrate, playBufSize,
//                        AudioTrack.MODE_STREAM);
//            } else {
//                if (audioTrack.getState() == AudioRecord.STATE_INITIALIZED){
//                    audioTrack.stop();
//                }
//                Thread.sleep(500);
//            }
//            blnInstantPlay = true;
//
//            if (blnInstantPlay || blnPlay) {
//                audioTrack.setPlaybackRate(sampleRateInHz);
//            }
//            selectChannel(channel);
//            if (playThread != null && playThread.isAlive()) {
//                playThread.interrupt();
//                playThread = null;
//            }
//            playThread = new ThreadInstantPlay();
//            playThread.start();
//        }
//        currentChannelString = channel;
//    }
//
//    /**
//     * 即时播放线程
//     */
//    class ThreadInstantPlay extends Thread {
//        @Override
//        public void run() {
//            byte[] bsBuffer = new byte[recBufSize];
//            /*
//			recBufSize = AudioRecord.getMinBufferSize(sampleRateInHz,
//					channelConfig, encodingBitrate);
//			audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
//					sampleRateInHz, channelConfig, encodingBitrate, recBufSize);*/
//            try {
//                audioRecord.startRecording();//
//                audioTrack.play();
//                while (blnInstantPlay && !isInterrupted()) {
//                    int line = audioRecord.read(bsBuffer, 0, recBufSize);
//                    /*byte[] tmpBuf = new byte[line];
//				System.arraycopy(bsBuffer, 0, tmpBuf, 0, line);*/
//                    audioTrack.write(bsBuffer, 0, bsBuffer.length);
//                }
//                audioTrack.stop();
//                audioRecord.stop();
//            } catch (Exception e) {
//                // TODO: handle exception
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        // TODO Auto-generated method stub
//        blnInstantPlay = false;
//        audioRecord.release();
//        audioTrack.release();
//        audioRecord = null;
//        audioTrack = null;
//        // android.os.Process.killProcess(android.os.Process.myPid());
//        super.onDestroy();
//
//    }
//    /*
//	Handler handler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			//Toast.makeText(PlayerService.this, "播放结束", 2000).show();
//		}
//	};
//     */
	

	public void startPlaybackService(String channel){
		if(false == isChannelValid(channel))
			return;
		IPlayerService playerService = IPlayerService.Stub.asInterface(ServiceManager.getService("PlayerService"));
		try {
			playerService.startPlayer(getPackageName(), channel);
			//playing = true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void stopPlaybackService(String channel){
		if(false == isChannelValid(channel))
			return;
		IPlayerService playerService = IPlayerService.Stub.asInterface(ServiceManager.getService("PlayerService"));
		try {
			playerService.stopPlayer(getPackageName(), channel);
			//playing = false;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void pausePlaybackService(String channel){
		if(false == isChannelValid(channel))
			return;
		IPlayerService playerService = IPlayerService.Stub.asInterface(ServiceManager.getService("PlayerService"));
		try {
			playerService.pausePlayer(getPackageName(), channel);
			//playing = false;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isChannelValid(String channel){
		if(channel.equals("0") || channel.equals("1") || channel.equals("2") || channel.equals("3"))
		{
			return true;
		}else{
			return false;
		}
	}
}
