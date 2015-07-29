package com.example.adriano.serialwatcher.presenter;

import android.content.Context;

import com.example.adriano.serialwatcher.view.contract.EpisodeListingView;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.service.SeriesInfoRetriever;
import com.example.adriano.serialwatcher.service.contract.CallableForEpisodesList;

import java.util.List;

public class EpisodesListPresenter implements CallableForEpisodesList {

	private EpisodeListingView view;
	private Context context;

	public EpisodesListPresenter(EpisodeListingView view, Context context){

		this.view = view;
		this.context = context;
	}

	public void fetchEpisodesList(String show, long seasonNumber){
		SeriesInfoRetriever sir = new SeriesInfoRetriever(context);
		sir.requestEpisodesList(show, seasonNumber, this);
	}

	@Override
	public void onEpisodesListSuccess(List<Episode> episodes) { view.displayEpisodesList(episodes); }
}
