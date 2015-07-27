package com.example.adriano.serialwatcher.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.base.BaseNavigationToolbarActivity;
import com.example.adriano.serialwatcher.view.contract.EpisodeClickListener;
import com.example.adriano.serialwatcher.view.contract.EpisodeListingView;
import com.example.adriano.serialwatcher.presenter.EpisodesListPresenter;
import com.example.adriano.serialwatcher.view.adapter.EpisodeListAdapter;
import com.example.adriano.serialwatcher.model.Episode;

import java.util.List;

public class EpisodeListingActivity extends BaseNavigationToolbarActivity implements EpisodeListingView, EpisodeClickListener {

	private EpisodesListPresenter presenter;
	private EpisodeListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.season_details_activity);
		configureToolbar();

		this.presenter = new EpisodesListPresenter(this, this);

		ListView view = (ListView) findViewById(R.id.season_details_list_view);

		//TODO
//		getView.addHeaderView(null, null, false);
//		getView.setEmptyView(null);
//		getView.addFooterView(null, null, false);

		this.adapter = new EpisodeListAdapter(this, this); //presenter deveria conhecer o adapter?
		view.setAdapter(adapter);
	}

	@Override
	public void displayEpisodesList(List<Episode> episodes) {
		adapter.setEpisodes(episodes);
	}

	@Override
	public void onEpisodeClick(Episode episode){
		Intent intent = new Intent(this, EpisodeDetailsActivity.class);
		intent.putExtra(EpisodeDetailsActivity.SHOW, "futurama"); //TODO
		intent.putExtra(EpisodeDetailsActivity.SEASON, episode.season());
		intent.putExtra(EpisodeDetailsActivity.EPISODE, episode.number());
		startActivity(intent);
	}
}