package com.example.adriano.serialwatcher.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.adapter.SeriesDetailsAdapter;
import com.example.adriano.serialwatcher.view.base.BaseNavigationToolbarActivity;

public class SeriesDetailsActivity extends BaseNavigationToolbarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.series_details_activity);

		ViewPager vPager = (ViewPager) findViewById(R.id.series_details_view_pager);
		vPager.setAdapter(new SeriesDetailsAdapter(getSupportFragmentManager(), this));

		configureToolbar();
	}
}
