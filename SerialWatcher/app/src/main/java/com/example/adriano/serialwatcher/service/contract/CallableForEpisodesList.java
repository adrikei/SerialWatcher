package com.example.adriano.serialwatcher.service.contract;

import com.example.adriano.serialwatcher.model.Episode;

import java.util.List;

public interface CallableForEpisodesList {
	void onEpisodesListSuccess(List<Episode> episodes);
}
