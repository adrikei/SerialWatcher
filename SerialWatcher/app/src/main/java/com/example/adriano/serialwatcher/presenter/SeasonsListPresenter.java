package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.model.Season;
import com.example.adriano.serialwatcher.service.SeriesInfoRetriever;
import com.example.adriano.serialwatcher.service.contract.CallableForSeasonList;
import com.example.adriano.serialwatcher.view.contract.SeasonListView;

import java.util.List;

public class SeasonsListPresenter implements CallableForSeasonList{

	private final SeasonListView view;
	private final Context context;

	public SeasonsListPresenter(SeasonListView view, Context context){
		this.view = view;
		this.context = context;
	}

	public void fetchSeasonsList(String slug){
		SeriesInfoRetriever sir = new SeriesInfoRetriever(context);
		sir.requestSeasonsList(slug, this);
	}

	@Override
	public void onSeasonsListSuccess(List<Season> seasons) {
		view.displaySeasonList(seasons);
	}
}
