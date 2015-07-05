package com.module;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.base.BaseActivity;
import com.qianghuai.gr.R;
/**
 * º”‘ÿ“≥
 * @author g
 *
 */
public class LodingActivity extends BaseActivity{
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.loding_layout);
			initView();
			initData();
		}
		
		public void initView(){
			
		}
		public void initData(){
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(LodingActivity.this, HomeActivity.class);
					startActivity(intent);
					LodingActivity.this.finish();
				}
			}, 1000);
		}
}
