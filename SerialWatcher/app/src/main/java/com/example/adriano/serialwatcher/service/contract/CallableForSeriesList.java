package com.example.adriano.serialwatcher.service.contract;

import com.example.adriano.serialwatcher.model.Show;

import java.util.List;

public interface CallableForSeriesList {
	void onSeriesListSuccess(List<Show> shows);
}
