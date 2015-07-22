package com.example.adriano.serialwatcher.activity.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.view.base.BaseNavigationToolbarActivity;
import com.example.adriano.serialwatcher.activity.contract.EpisodeDetailsView;
import com.example.adriano.serialwatcher.activity.presenter.EpisodeDetailsPresenter;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.util.FormatUtil;


public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView{

	public static final String SHOW = "show";
	public static final String SEASON = "season";
	public static final String EPISODE = "episode";

	private EpisodeDetailsPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.episode_details_activity);
		configureToolbar();

		this.presenter = new EpisodeDetailsPresenter(this, this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outBundle){
		outBundle.putString("displayTitle", ((TextView) findViewById(R.id.episode_details_displayTitle)).getText().toString());
		outBundle.putString("airTime", ((TextView) findViewById(R.id.episode_details_airTime)).getText().toString());
		outBundle.putString("summary", ((TextView) findViewById(R.id.episode_details_summary)).getText().toString());

		super.onSaveInstanceState(outBundle);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		((TextView) findViewById(R.id.episode_details_displayTitle)).setText(savedInstanceState.getString("displayTitle"));
		((TextView) findViewById(R.id.episode_details_airTime)).setText(savedInstanceState.getString("airTime"));
		((TextView) findViewById(R.id.episode_details_summary)).setText(savedInstanceState.getString("summary"));
	}

	@Override
	public void displayEpisode(Episode episode) {
		((TextView) findViewById(R.id.episode_details_displayTitle)).setText(episode.title());
		((TextView) findViewById(R.id.episode_details_airTime)).setText(FormatUtil.formatDate(episode.firstAired()).toString());
		((TextView) findViewById(R.id.episode_details_summary)).setText(episode.overview());

		Glide
				.with(this)
				.load(episode.images().screenshot().get("full"))
				.placeholder(R.drawable.highlight_placeholder)
				.centerCrop()
				.into((ImageView)findViewById(R.id.episode_details_highlightImage));
	}
}
