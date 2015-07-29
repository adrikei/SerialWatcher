package com.example.adriano.serialwatcher.view;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.example.adriano.serialwatcher.database.FavoriteDAO;

public class FavoritesLoader extends CursorLoader {

	public FavoritesLoader(Context context) {
		super(context);
	}

	public Cursor loadInBackground(){
		FavoriteDAO dao = new FavoriteDAO(getContext());
		return dao.all();
	}
}
