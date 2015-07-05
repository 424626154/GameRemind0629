package com.data;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

public class Remind implements Serializable{
	public long _id ;
	public String appLabel;  
	public Drawable appIcon ;
	public String pkgName ;
	public int hour;
	public int minute;
	public String remarks;
	public String week_json;
	public boolean []b_week = new boolean [7];
	public int on_off = 0;
}
