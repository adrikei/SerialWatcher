package com.example.adriano.serialwatcher.activity.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.activity.contract.EpisodeDetailsView;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.service.SeriesInfoRetriever;

public class EpisodeDetailsPresenter {

	private EpisodeDetailsView view;
	private Context context;

	public EpisodeDetailsPresenter(EpisodeDetailsView view, Context context){
		this.view = view;
		this.context = context;

		SeriesInfoRetriever sir = new SeriesInfoRetriever(this.context);
	}

	public void onEpisodeDetailsSuccess(Episode episode) {
		view.displayEpisode(episode);
	}
}
