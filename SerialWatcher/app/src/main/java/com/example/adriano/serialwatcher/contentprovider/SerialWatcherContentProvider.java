package com.example.adriano.serialwatcher.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.adriano.serialwatcher.helper.ProviderUriHelper;
import com.example.adriano.serialwatcher.helper.SerialWatcherDBHelper;

public class SerialWatcherContentProvider extends ContentProvider {
	private SerialWatcherDBHelper mDbHelper;
	private ProviderUriHelper mUriHelper;

	@Override
	public boolean onCreate() {
		mDbHelper = new SerialWatcherDBHelper(getContext());
		mUriHelper = new ProviderUriHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase database = mDbHelper.getReadableDatabase();
		String table = mUriHelper.table(uri);

		Cursor cursor;
		if (mUriHelper.isSingle(uri)) {
			cursor = database.query(table, null, BaseColumns._ID + " = ?", new String[]{uri.getLastPathSegment()}, null, null, null);
		} else {
			cursor = database.query(table, projection, selection, selectionArgs, null, null, sortOrder);
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return mUriHelper.type(uri);
	}

	@Override
	public Uri insert(Uri uri, ContentValues contentValues) {
		SQLiteDatabase database = mDbHelper.getWritableDatabase();
		String table = mUriHelper.table(uri);

		long id = database.insert(table, null, contentValues);
		if (id == -1) {
			return null;
		}
		return mUriHelper.mountSingleUri(table, id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		String table = mUriHelper.table(uri);
		return mDbHelper.getWritableDatabase().delete(table, selection, selectionArgs);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		String table = mUriHelper.table(uri);
		return mDbHelper.getWritableDatabase().update(table, values, selection, selectionArgs);
	}
}
