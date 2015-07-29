package com.example.adriano.serialwatcher;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;


//TODO - loader em algum lugar
public class SerialWatcherApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		FlowManager.init(this);
	}
}
