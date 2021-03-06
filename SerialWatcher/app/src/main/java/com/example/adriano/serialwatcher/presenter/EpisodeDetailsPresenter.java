package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.view.contract.EpisodeDetailsView;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.service.SeriesInfoRetriever;
import com.example.adriano.serialwatcher.service.contract.CallableForEpisodeDetails;

public class EpisodeDetailsPresenter implements CallableForEpisodeDetails{

	private EpisodeDetailsView view;
	private Context context;

	public EpisodeDetailsPresenter(EpisodeDetailsView view, Context context){
		this.view = view;
		this.context = context;
	}

	public void loadEpisodeDetails(String seriesName, long seasonNumber, long episodeNumber){
		SeriesInfoRetriever sir = new SeriesInfoRetriever(this.context);
		sir.requestEpisodeDetails(seriesName, seasonNumber, episodeNumber, this);
	}

	public void onEpisodeDetailsSuccess(Episode episode) {
		view.displayEpisode(episode);
	}

}
