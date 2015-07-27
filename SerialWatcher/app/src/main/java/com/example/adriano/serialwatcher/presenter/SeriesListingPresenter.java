package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.view.contract.SeriesListingView;

public class SeriesListingPresenter {

	private final Context context;
	private SeriesListingView view;

	public SeriesListingPresenter(SeriesListingView view, Context context){
		this.view = view;
		this.context = context;
	}
}
