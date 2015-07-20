package com.example.adriano.serialwatcher.service.contract;

import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.configuration.ApiConfiguration;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface EpisodeDetailer {

	@Headers({
			"trakt-api-version: " + ApiConfiguration.API_VERSION,
			"trakt-api-key: " + ApiConfiguration.API_KEY
	})
	@GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
	void getEpisodeDetails(@Path("show") String show, @Path("season") Long season,
	                       @Path("episode") Long episode, Callback<Episode> callback);
}
