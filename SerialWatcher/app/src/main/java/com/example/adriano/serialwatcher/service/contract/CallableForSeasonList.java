package com.example.adriano.serialwatcher.service.contract;

import com.example.adriano.serialwatcher.model.Season;

import java.util.List;

public interface CallableForSeasonList {
	void onSeasonsListSuccess(List<Season> seasons);
}
