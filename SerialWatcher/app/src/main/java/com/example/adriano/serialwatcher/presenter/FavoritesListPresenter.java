package com.example.adriano.serialwatcher.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.example.adriano.serialwatcher.model.Show;
import com.example.adriano.serialwatcher.service.contract.CallableForSeriesDetails;
import com.example.adriano.serialwatcher.view.FavoritesLoader;
import com.example.adriano.serialwatcher.view.contract.FavoritesListingView;

public class FavoritesListPresenter implements LoaderManager.LoaderCallbacks<Cursor>{

	private FavoritesListingView view;
	private Context context;

	public FavoritesListPresenter(FavoritesListingView view, Context context){
		this.view = view;
		this.context = context;
	}

	public void loadFavorites(LoaderManager loaderManager) {
		loaderManager.initLoader(0, null, this).forceLoad();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new FavoritesLoader(this.context);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		view.onFavoritesLoad(data); //TODO - deveria transformar o cursor em uma lista de itens?
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		Log.d("Findme", "onLoaderReset!!");
	}
}
