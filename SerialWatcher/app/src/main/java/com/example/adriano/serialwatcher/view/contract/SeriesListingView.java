package com.example.adriano.serialwatcher.view.contract;

import com.example.adriano.serialwatcher.model.Show;

import java.util.List;

public interface SeriesListingView {
	void displaySeries(List<Show> shows);
	void onSeriesClick(Show show);
}
