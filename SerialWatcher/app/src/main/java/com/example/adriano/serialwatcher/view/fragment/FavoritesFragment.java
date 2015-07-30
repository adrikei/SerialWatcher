package com.example.adriano.serialwatcher.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Favorite;
import com.example.adriano.serialwatcher.presenter.FavoritesListPresenter;
import com.example.adriano.serialwatcher.view.activity.SeriesDetailsActivity;
import com.example.adriano.serialwatcher.view.adapter.FavoritesAdapter;
import com.example.adriano.serialwatcher.view.contract.FavoriteClickListener;
import com.example.adriano.serialwatcher.view.contract.FavoritesListingView;

public class FavoritesFragment extends Fragment implements FavoritesListingView, FavoriteClickListener{

	private FavoritesListPresenter presenter;
	private FavoritesAdapter adapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ListView root = (ListView) inflater.inflate(R.layout.favorite_shows_list, container, false);
		this.adapter = new FavoritesAdapter(getActivity(), this);
		root.setAdapter(adapter);
		return root;
	}

	@Override
	public void onStart() {
		super.onStart();
		this.presenter = new FavoritesListPresenter(this, getActivity());
		presenter.loadFavorites(getLoaderManager());
	}

	@Override
	public void onFavoriteClick(Favorite favorite) {
		Intent intent = new Intent(getActivity(), SeriesDetailsActivity.class);
		intent.putExtra(SeriesDetailsActivity.SLUG, favorite.slug());
		intent.putExtra(SeriesDetailsActivity.TITLE, favorite.title());
		startActivity(intent);
	}

	@Override
	public void onFavoritesLoad(Cursor cursor) {
		adapter.changeCursor(cursor);
	}

}
