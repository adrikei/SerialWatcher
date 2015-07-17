package com.example.adriano.serialwatcher.activity.asynchronous.contract;

import com.example.adriano.serialwatcher.model.Episode;

/**
 * Created by Adriano on 7/16/15.
 */
public interface EpisodeDetailsFetching {
	void onDetailsFetched(Episode episode);
}
