package com.example.adriano.serialwatcher;

import android.app.Application;

public class SerialWatcherApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		Stetho.initialize(
				Stetho.newInitializerBuilder(this)
						.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
						.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
						.build());
	}
}
