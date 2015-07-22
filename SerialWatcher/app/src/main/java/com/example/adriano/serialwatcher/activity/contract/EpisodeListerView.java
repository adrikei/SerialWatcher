package com.example.adriano.serialwatcher.activity.contract;

import com.example.adriano.serialwatcher.model.Episode;

import java.util.List;

public interface EpisodeListerView {
	void displayEpisodesList(List<Episode> episodes);
	void onEpisodeClick(Episode episode);
}
