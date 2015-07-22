package com.example.adriano.serialwatcher.activity.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.activity.contract.EpisodeListerView;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.service.SeriesInfoRetriever;
import com.example.adriano.serialwatcher.service.contract.CallableForSeasonDetails;

import java.util.List;

public class SeasonDetailsPresenter implements CallableForSeasonDetails{

	private EpisodeListerView view;
	private Context context;

	public SeasonDetailsPresenter(EpisodeListerView view, Context context){

		this.view = view;
		this.context = context;

		SeriesInfoRetriever sir = new SeriesInfoRetriever(context);
		sir.requestSeasonDetails("futurama", 1l, this);
	}

	@Override
	public void onSeasonDetailsSuccess(List<Episode> episodes) { view.displayEpisodesList(episodes); }
}
