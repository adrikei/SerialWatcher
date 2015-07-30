package com.example.adriano.serialwatcher.service;

import android.content.Context;
import android.util.Log;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.model.Season;
import com.example.adriano.serialwatcher.model.Show;
import com.example.adriano.serialwatcher.service.contract.CallableForEpisodeDetails;
import com.example.adriano.serialwatcher.service.contract.CallableForEpisodesList;
import com.example.adriano.serialwatcher.service.contract.CallableForSeasonList;
import com.example.adriano.serialwatcher.service.contract.CallableForSeriesDetails;
import com.example.adriano.serialwatcher.service.contract.CallableForSeriesList;
import com.example.adriano.serialwatcher.service.retrofit.SeriesDetailer;

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
	public void requestEpisodesList(String seriesName, long seasonNumber, final CallableForEpisodesList callable){
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		SeriesDetailer service = adapter.create(SeriesDetailer.class);

		service.getEpisodesList(seriesName, seasonNumber, new Callback<List<Episode>>() {
			@Override
			public void success(List<Episode> episodes, Response response) {
				callable.onEpisodesListSuccess(episodes);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(tag, "EpisodesList >>" + error.getCause());
			}
		});
	}

	//TODO - Errors should trigger some behavior on who calls this method, instead of simple logging
	public void requestEpisodeDetails(String seriesName, long seasonNumber, long episodeNumber, final CallableForEpisodeDetails callable){
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		SeriesDetailer service = adapter.create(SeriesDetailer.class);

		service.getEpisodeDetails(seriesName, seasonNumber, episodeNumber, new Callback<Episode>() {

			@Override
			public void success(Episode episode, Response response) {
				callable.onEpisodeDetailsSuccess(episode);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(tag, "EpisodeDetails >>" + error.getCause());
			}
		});
	}

	//TODO - Errors should trigger some behavior on who calls this method, instead of simple logging
	public void requestSeasonsList(String SeriesName, final CallableForSeasonList callable){
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		SeriesDetailer service = adapter.create(SeriesDetailer.class);

		service.getSeasonsList(SeriesName, new Callback<List<Season>>() {
			@Override
			public void success(List<Season> seasons, Response response) {
				callable.onSeasonsListSuccess(seasons);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(tag, "SeasonsList >>" + error.getCause());
			}
		});
	}

	//TODO - Errors should trigger some behavior on who calls this method, instead of simple logging
	public void requestSeriesList(final CallableForSeriesList callable){
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		SeriesDetailer service = adapter.create(SeriesDetailer.class);

		service.getSeriesList(new Callback<List<Show>>() {
			@Override
			public void success(List<Show> shows, Response response) {
				callable.onSeriesListSuccess(shows);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(tag, "SeriesList >>" + error.getCause());
			}
		});
	}

	//TODO - Errors should trigger some behavior on who calls this method, instead of simple logging
	public void requestSeriesDetails(String seriesName, final CallableForSeriesDetails callable){
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.context.getString(R.string.api_url_base)).build();
		SeriesDetailer service = adapter.create(SeriesDetailer.class);

		service.getSeriesDetails(seriesName, new Callback<Show>() {
			@Override
			public void success(Show show, Response response) {
				callable.onSeriesDetailsSuccess(show);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(tag, "seriesDetails >>" + error.getCause());
			}
		});
	}
}
