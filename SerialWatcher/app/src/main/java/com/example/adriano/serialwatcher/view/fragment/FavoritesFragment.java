package com.example.adriano.serialwatcher.view.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Favorite;
import com.example.adriano.serialwatcher.presenter.FavoritesListPresenter;
import com.example.adriano.serialwatcher.view.FavoritesLoader;
import com.example.adriano.serialwatcher.view.adapter.FavoritesAdapter;
import com.example.adriano.serialwatcher.view.contract.FavoriteClickListener;
import com.example.adriano.serialwatcher.view.contract.FavoritesListingView;

public class FavoritesFragment extends Fragment implements FavoritesListingView, FavoriteClickListener, LoaderManager.LoaderCallbacks<Cursor>{

//	private FavoritesListPresenter presenter;
	private FavoritesAdapter adapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ListView root = (ListView) inflater.inflate(R.layout.favorite_shows_list, container, false);
//		this.presenter = new FavoritesListPresenter(this, getActivity());
		this.adapter = new FavoritesAdapter(getActivity());
		root.setAdapter(adapter);
//		presenter.loadFavorites(); // linha debaixo vai aqui dentro?
		getLoaderManager().initLoader(0, null, this).forceLoad();
		Log.d("Findme", "FavoritesFragment.onCreateView");
		return root;
	}

	@Override
	public void onFavoriteClick(Favorite favorite) {
		//TODO - via intent
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new FavoritesLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.changeCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		Log.d("Findme", "onLoaderReset!!");
	}
}
