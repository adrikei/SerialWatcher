package com.example.adriano.serialwatcher.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = SerialWatcherDatabase.NAME, version = SerialWatcherDatabase.VERSION)
public class SerialWatcherDatabase {

	public static final String NAME = "serial_watcher_dbflow";
	public static final int VERSION = 1;
}
