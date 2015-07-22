package com.example.adriano.serialwatcher.service;

import android.content.Context;
import android.util.Log;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.service.contract.CallableForEpisodeDetails;
import com.example.adriano.serialwatcher.service.contract.CallableForSeasonDetails;
import com.example.adriano.serialwatcher.service.retrofit.EpisodeDetailer;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SeriesInfoRetriever {

	private static final String tag = SeriesInfoRetriever.class.getSimpleName();
	private Context context;

	public SeriesInfoRetriever(Context context){
		this.context = context;
	}

	//TODO - Errors should trigger some behavior on who calls this method, instead of simple logging
	public void requestSeasonDetails(String seriesName, long seasonNumber, final CallableForSeasonDetails callable){
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		EpisodeDetailer service = adapter.create(EpisodeDetailer.class);

		service.getSeasonDetails(seriesName, seasonNumber, new Callback<List<Episode>>() {
			@Override
			public void success(List<Episode> episodes, Response response) {
				callable.onSeasonDetailsSuccess(episodes);
			}

			@Override
			public void failure(RetrofitError error) { Log.e(tag, error.toString());	}
		});
	}

	//TODO - Errors should trigger some behavior on who calls this method, instead of simple logging
	public void requestEpisodeDetails(String seriesName, long seasonNumber, long episodeNumber, final CallableForEpisodeDetails callable){
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		EpisodeDetailer service = adapter.create(EpisodeDetailer.class);

		service.getEpisodeDetails(seriesName, seasonNumber, episodeNumber, new Callback<Episode>() {

			@Override
			public void success(Episode episode, Response response) {
				callable.onEpisodeDetailsSuccess(episode);
			}

			@Override
			public void failure(RetrofitError error) { Log.e(tag, error.toString() );	}
		});
	}
}
