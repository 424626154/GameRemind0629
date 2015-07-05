package com.base;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart(getClassName()); //ͳ��ҳ��(����Activity��Ӧ����SDK�Զ����ã�����Ҫ����д)
	    MobclickAgent.onResume(this);          //ͳ��ʱ��
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd(getClassName()); // ������Activity��Ӧ����SDK�Զ����ã�����Ҫ����д����֤ onPageEnd ��onPause ֮ǰ����,��Ϊ onPause �лᱣ����Ϣ 
	    MobclickAgent.onPause(this);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
    public String getClassName(){
    	String  class_name = "BaseActivity";
    	try{
    		class_name = this.getClass().getSimpleName();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return class_name;
    }
}
