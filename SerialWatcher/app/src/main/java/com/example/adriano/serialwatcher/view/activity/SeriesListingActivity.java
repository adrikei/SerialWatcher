package com.example.adriano.serialwatcher.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.adapter.SeriesListGridAdapter;
import com.example.adriano.serialwatcher.presenter.SeriesListingPresenter;
import com.example.adriano.serialwatcher.view.contract.SeriesClickListener;
import com.example.adriano.serialwatcher.view.contract.SeriesListingView;
import com.example.adriano.serialwatcher.model.Show;

import java.util.List;

public class SeriesListingActivity extends AppCompatActivity implements SeriesListingView, SeriesClickListener {

	private SeriesListingPresenter presenter;
	private SeriesListGridAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.series_listing_activity);

		GridView gridview = (GridView) findViewById(R.id.series_listing_grid_view);
		SeriesListGridAdapter adapter = new SeriesListGridAdapter(gridview.getContext(), this);
		gridview.setAdapter(adapter);

		this.adapter = adapter;

		this.presenter = new SeriesListingPresenter(this, this);
		presenter.loadSeries();

//		PendingIntent pendingIntent = PendingIntent.getService(this, 0, new Intent(this, FirebaseUpdateService.class), 0);
//		AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//		manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 10*1000, pendingIntent);
	}

	@Override
	public void displaySeries(List<Show> shows) {
		adapter.setSeries(shows);
	}

	@Override
	public void onSeriesClick(Show show) {
		Intent intent = new Intent(this, SeriesDetailsActivity.class);
		intent.putExtra(SeriesDetailsActivity.SHOW, show);
		startActivity(intent);
	}
}
