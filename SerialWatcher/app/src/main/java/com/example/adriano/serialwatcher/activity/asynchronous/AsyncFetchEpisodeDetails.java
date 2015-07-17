package com.example.adriano.serialwatcher.activity.asynchronous;

import android.content.Context;
import android.os.AsyncTask;

import com.example.adriano.serialwatcher.activity.asynchronous.contract.EpisodeDetailsFetching;
import com.example.adriano.serialwatcher.business.FetchLocalEpisodeDetails;
import com.example.adriano.serialwatcher.model.Episode;

/**
 * Created by Adriano on 7/16/15.
 */
public class AsyncFetchEpisodeDetails extends AsyncTask<String, Object, Episode> {

	private EpisodeDetailsFetching fetchingListener;
	private Context context;

	public AsyncFetchEpisodeDetails(EpisodeDetailsFetching listener, Context context){
		this.fetchingListener = listener;
		this.context = context;
	}

	@Override
	protected Episode doInBackground(String... params) {
		FetchLocalEpisodeDetails fled = new FetchLocalEpisodeDetails();
		return fled.get(context);
	}

	@Override
	protected void onPostExecute(Episode episode){
		fetchingListener.onDetailsFetched(episode);
	}
}
