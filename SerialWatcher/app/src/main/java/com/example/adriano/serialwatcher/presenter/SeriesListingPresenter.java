package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.model.Show;
import com.example.adriano.serialwatcher.service.SeriesInfoRetriever;
import com.example.adriano.serialwatcher.service.contract.CallableForSeriesList;
import com.example.adriano.serialwatcher.view.contract.SeriesListingView;

import java.util.List;

public class SeriesListingPresenter implements CallableForSeriesList{

	private final Context context;
	private SeriesListingView view;

	public SeriesListingPresenter(SeriesListingView view, Context context){
		this.view = view;
		this.context = context;
	}

	@Override
	public void onSeriesListSuccess(List<Show> shows) {
		view.displaySeries(shows);
	}

	public void loadSeries(){
		SeriesInfoRetriever sir = new SeriesInfoRetriever(this.context);
		sir.requestSeriesList(this);
	}
}
