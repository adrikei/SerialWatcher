package com.example.adriano.serialwatcher.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.adapter.SeriesListGridAdapter;
import com.example.adriano.serialwatcher.view.presenter.SeriesListingPresenter;
import com.example.adriano.serialwatcher.view.contract.SeriesListingView;
import com.example.adriano.serialwatcher.model.Show;

import java.util.List;

public class SeriesListingActivity extends AppCompatActivity implements SeriesListingView {

	private SeriesListingPresenter presenter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.series_listing_activity);

		GridView gridview = (GridView) findViewById(R.id.series_listing_grid_view);
		SeriesListGridAdapter adapter = new SeriesListGridAdapter(gridview.getContext());
		gridview.setAdapter(adapter);

	}

	@Override
	public void displaySeries(List<Show> shows) {
		//TODO - presente.set -> adapter.set...
	}

	@Override
	public void onSeriesClick(Show show) {
		//TODO - call explicit intent bla bla
	}
}
