package com.example.adriano.serialwatcher.model.converter;

import com.google.gson.Gson;

import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.model.Season;
import com.example.adriano.serialwatcher.model.Show;

import java.io.Reader;

public class ModelConverter {

	private Gson mGson;

	public ModelConverter() {
		mGson = new Gson();
	}

	public Episode toEpisode(Reader reader) {
		return mGson.fromJson(reader, Episode.class);
	}

	public Show toShow(Reader reader) {
		return null;
	}

	public Season toSeason(Reader reader) {
		return null;
	}

}
