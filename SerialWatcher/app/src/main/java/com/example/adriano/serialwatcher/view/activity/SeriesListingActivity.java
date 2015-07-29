package com.example.adriano.serialwatcher.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.adapter.SeriesListGridAdapter;
import com.example.adriano.serialwatcher.presenter.SeriesListingPresenter;
import com.example.adriano.serialwatcher.view.base.BaseNavigationDrawerActivity;
import com.example.adriano.serialwatcher.view.contract.SeriesClickListener;
import com.example.adriano.serialwatcher.view.contract.SeriesListingView;
import com.example.adriano.serialwatcher.model.Show;

import java.util.List;

public class SeriesListingActivity extends BaseNavigationDrawerActivity
		implements SeriesListingView, SeriesClickListener {

	private SeriesListingPresenter presenter;
	private SeriesListGridAdapter gridAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.series_listing_activity);
		configureNavigation();

		GridView gridview = (GridView) findViewById(R.id.series_listing_grid_view);
		SeriesListGridAdapter gridAdapter = new SeriesListGridAdapter(gridview.getContext(), this);
		gridview.setAdapter(gridAdapter);
		this.gridAdapter = gridAdapter;

		this.presenter = new SeriesListingPresenter(this, this);
		presenter.loadSeries();
	}

	@Override
	public void displaySeries(List<Show> shows) {
		gridAdapter.setSeries(shows);
	}

	@Override
	public void onSeriesClick(Show show) {
		Intent intent = new Intent(this, SeriesDetailsActivity.class);
		intent.putExtra(SeriesDetailsActivity.SHOW, show);
		startActivity(intent);
	}

}