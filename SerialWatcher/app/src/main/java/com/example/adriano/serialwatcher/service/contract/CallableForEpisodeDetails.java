package com.example.adriano.serialwatcher.service.contract;

import com.example.adriano.serialwatcher.model.Episode;

public interface CallableForEpisodeDetails {
	void onEpisodeDetailsSuccess(Episode episode);
}
