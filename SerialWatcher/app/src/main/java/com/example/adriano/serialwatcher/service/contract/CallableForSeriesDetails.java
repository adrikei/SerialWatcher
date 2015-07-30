package com.example.adriano.serialwatcher.service.contract;

import com.example.adriano.serialwatcher.model.Show;

public interface CallableForSeriesDetails {
	void onSeriesDetailsSuccess(Show show);
}
