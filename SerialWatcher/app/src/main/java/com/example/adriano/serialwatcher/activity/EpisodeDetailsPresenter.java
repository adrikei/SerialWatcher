package com.example.adriano.serialwatcher.activity;

import android.content.Context;
import android.util.Log;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.contract.EpisodeDetailsView;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.service.contract.EpisodeDetailer;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EpisodeDetailsPresenter {

	private EpisodeDetailsView view;
	private Context context;

	public EpisodeDetailsPresenter(EpisodeDetailsView view, Context context){
		this.view = view;
		this.context = context;

		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		EpisodeDetailer service = adapter.create(EpisodeDetailer.class);

		Log.d("Findme", "Episode details presenter");

		service.getEpisodeDetails("futurama", 1l, 1l, new Callback<Episode>() {

			@Override
			public void success(Episode episode, Response response) {
				Log.d("Findme", "success");
				onEpisodeDetailsSuccess(episode);
			}

			@Override
			public void failure(RetrofitError error) { /**/	}
		});
	}

	public void onEpisodeDetailsSuccess(Episode episode) {
		view.displayEpisode(episode);
	}
}
