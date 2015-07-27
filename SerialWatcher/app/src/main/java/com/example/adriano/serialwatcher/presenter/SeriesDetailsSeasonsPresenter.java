package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.view.contract.SeasonListView;
import com.example.adriano.serialwatcher.model.Season;
import com.example.adriano.serialwatcher.service.contract.CallableForSeasonList;

import java.util.List;

public class SeriesDetailsSeasonsPresenter implements CallableForSeasonList {

	private final Context context;
	private final SeasonListView view;

	public SeriesDetailsSeasonsPresenter(SeasonListView view, Context context){
		this.context = context;
		this.view = view;
	}

	@Override
	public void onSeasonsListSuccess(List<Season> seasons) {
		this.view.displaySeasonList(seasons);
	}
}
