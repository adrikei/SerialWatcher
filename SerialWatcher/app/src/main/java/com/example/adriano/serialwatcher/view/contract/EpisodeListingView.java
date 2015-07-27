package com.example.adriano.serialwatcher.view.contract;

import com.example.adriano.serialwatcher.model.Episode;

import java.util.List;

public interface EpisodeListingView {
	void displayEpisodesList(List<Episode> episodes);
	void onEpisodeClick(Episode episode);
}
