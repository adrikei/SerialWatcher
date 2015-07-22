package com.example.adriano.serialwatcher.activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.view.base.BaseNavigationToolbarActivity;
import com.example.adriano.serialwatcher.activity.contract.EpisodeListerView;
import com.example.adriano.serialwatcher.activity.presenter.SeasonDetailsPresenter;
import com.example.adriano.serialwatcher.activity.adapter.view.SeasonDetailsAdapter;
import com.example.adriano.serialwatcher.model.Episode;

import java.util.List;

public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeListerView {

	private SeasonDetailsPresenter presenter;
	private SeasonDetailsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.season_details_activity);
		configureToolbar();

		this.presenter = new SeasonDetailsPresenter(this, this);

		ListView view = (ListView) findViewById(R.id.season_details_list_view);

//		view.addHeaderView(null, null, false);
//		view.setEmptyView(null);
//		view.addFooterView(null, null, false);

		this.adapter = new SeasonDetailsAdapter(this/*, this [EpisodeListerView ou outra interface]*/); //Adapter deveria conhecer o presenter?
		view.setAdapter(adapter);
	}

	@Override
	public void displayEpisodesList(List<Episode> episodes) {
		adapter.setItems(episodes);
	}

	@Override
	public void onEpisodeClick(Episode episode){
		Intent intent = new Intent(this, EpisodeDetailsActivity.class);
		intent.putExtra(EpisodeDetailsActivity.SHOW, "futurama");
		intent.putExtra(EpisodeDetailsActivity.SEASON, "1");
		intent.putExtra(EpisodeDetailsActivity.EPISODE, "1");
		startActivity(intent);
	}
}