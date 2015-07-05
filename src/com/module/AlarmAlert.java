package com.module;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * 实际跳出闹钟的dialog
 * @author CxLong
 *
 */
public class AlarmAlert extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		long _id = getIntent().getExtras().getLong("_id", 0);
		String type = getIntent().getExtras().getString("type");
		new AlertDialog.Builder(AlarmAlert.this)
			.setTitle(type)
			.setMessage(_id+"")
			.setNegativeButton("关掉它", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					AlarmAlert.this.finish();
				}
			}).show();
	}
	
}
