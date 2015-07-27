package com.example.adriano.serialwatcher.service.retrofit;

import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.configuration.ApiConfiguration;
import com.example.adriano.serialwatcher.model.Season;
import com.example.adriano.serialwatcher.model.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface SeriesDetailer {

	@Headers({
			"trakt-api-version: " + ApiConfiguration.API_VERSION,
			"trakt-api-key: " + ApiConfiguration.API_KEY
	})
	@GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
	void getEpisodeDetails(@Path("show") String show, @Path("season") Long season,
	                       @Path("episode") Long episode, Callback<Episode> callback);


	@Headers({
			"trakt-api-version: " + ApiConfiguration.API_VERSION,
			"trakt-api-key: " + ApiConfiguration.API_KEY
	})
	@GET("/shows/{show}/seasons/{season}/episodes?extended=full,images")
	void getEpisodesList(@Path("show") String show, @Path("season") Long season,
	                     Callback<List<Episode>> callback);

	@Headers({
			"trakt-api-version: " + ApiConfiguration.API_VERSION,
			"trakt-api-key: " + ApiConfiguration.API_KEY
	})
	@GET("/shows/{show}/seasons?extended=full,images")
	void getSeasonsList(@Path("show") String show, Callback<List<Season>> callback);

	@Headers({
			"trakt-api-version: " + ApiConfiguration.API_VERSION,
			"trakt-api-key: " + ApiConfiguration.API_KEY
	})
	@GET("/shows/popular/?limit=50&extended=full,images")
	void getSeriesList(Callback<List<Show>> callback);
}
