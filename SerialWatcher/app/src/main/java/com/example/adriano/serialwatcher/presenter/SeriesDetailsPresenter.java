package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.model.Show;

public class SeriesDetailsPresenter {

	private Context context;

	public SeriesDetailsPresenter(Context context){
		this.context = context;
	}

	public void loadSeriesDetails(Show series){
		throw new RuntimeException("Unimplemented");
	}
}
