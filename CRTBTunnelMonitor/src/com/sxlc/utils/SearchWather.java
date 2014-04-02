package com.sxlc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SearchWather implements TextWatcher{

	//�����ı���ı���   
    private EditText editText;   
 
    /** 
     * ���캯�� 
     */   
    public SearchWather(EditText editText){   
        this.editText = editText;   
    }   
 
    @Override   
    public void onTextChanged(CharSequence ss, int start, int before, int count) {   
        String editable = editText.getText().toString();   
        String str = stringFilter(editable.toString()); 
        if(!editable.equals(str)){ 
            editText.setText(str); 
            //�����µĹ������λ�� www.2cto.com    
            editText.setSelection(str.length()); 
        } 
    }   
 
    @Override   
    public void afterTextChanged(Editable s) {   
 
    }   
    @Override   
    public void beforeTextChanged(CharSequence s, int start, int count,int after) {   
 
    }   
 
 
 
public static String stringFilter(String str)throws PatternSyntaxException{      
    // ֻ������ĸ������  �ͺ���    
    String   regEx  =  "^([\u4e00-\u9fa5]+|[a-zA-Z0-9]+)$";                      
    Pattern   p   =   Pattern.compile(regEx);      
    Matcher   m   =   p.matcher(str);      
    return   m.replaceAll("").trim();      
}   

}
