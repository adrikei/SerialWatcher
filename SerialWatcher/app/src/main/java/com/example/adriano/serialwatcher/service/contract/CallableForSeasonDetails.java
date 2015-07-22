package com.example.adriano.serialwatcher.service.contract;

import com.example.adriano.serialwatcher.model.Episode;

import java.util.List;

public interface CallableForSeasonDetails {
	void onSeasonDetailsSuccess(List<Episode> episodes);

}
