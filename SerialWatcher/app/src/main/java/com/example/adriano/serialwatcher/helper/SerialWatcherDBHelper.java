package com.example.adriano.serialwatcher.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.adriano.serialwatcher.configuration.DatabaseConfiguration;
import com.example.adriano.serialwatcher.database.entity.FavoriteEntity;

public class SerialWatcherDBHelper extends SQLiteOpenHelper {

	public SerialWatcherDBHelper(Context context) {
		super(context, DatabaseConfiguration.NAME, null, DatabaseConfiguration.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(FavoriteEntity.FavoriteEntityFields.createSql());
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL(FavoriteEntity.FavoriteEntityFields.dropSql());
		onCreate(database);
	}
}
