package com.example.adriano.serialwatcher.activity.asynchronous;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

import com.example.adriano.serialwatcher.activity.asynchronous.contract.EpisodeDetailsFetching;
import com.example.adriano.serialwatcher.model.Episode;

/**
 * Created by Adriano on 7/16/15.
 */
public class EpisodeLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {

	private Context context;
	private EpisodeDetailsFetching listener;

	public EpisodeLoaderCallback(Context context, EpisodeDetailsFetching listener){
		this.context = context;
		this.listener = listener;
		Log.d("WOLOLO", "EpisodeLoaderCallback");
	}

	@Override
	public Loader<Episode> onCreateLoader(int id, Bundle args) {
		Log.d("WOLOLO", "onCreateLoader");
		String url = args != null && args.get("url") != null? args.get("url").toString() : "";
		RemoteEpisodeDetailsLoader loader = new RemoteEpisodeDetailsLoader(context, url);

		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Episode> loader, Episode data) {
		listener.onDetailsFetched(data);
	}

	@Override
	public void onLoaderReset(Loader<Episode> loader) { }
}
