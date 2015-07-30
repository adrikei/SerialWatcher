package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.model.Show;
import com.example.adriano.serialwatcher.service.SeriesInfoRetriever;
import com.example.adriano.serialwatcher.service.contract.CallableForSeriesDetails;
import com.example.adriano.serialwatcher.view.contract.SeriesDetailsView;

public class SeriesDetailsPresenter implements CallableForSeriesDetails{

	private final SeriesDetailsView view;
	private final Context context;

	public SeriesDetailsPresenter(Context context, SeriesDetailsView view){
		this.context = context;
		this.view = view;
	}

	public void loadSeriesDetails(String slug){
		SeriesInfoRetriever sir = new SeriesInfoRetriever(context);
		sir.requestSeriesDetails(slug, this);
	}

	@Override
	public void onSeriesDetailsSuccess(Show show) {
		view.displaySeries(show);
	}
}
