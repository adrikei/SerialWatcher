package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.view.contract.FavoritesListingView;

public class FavoritesListPresenter {

	private FavoritesListingView view;
	private Context context;

	public FavoritesListPresenter(FavoritesListingView view, Context context){
		this.view = view;
		this.context = context;
	}
}
