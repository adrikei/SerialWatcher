package com.example.adriano.serialwatcher.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.contract.EpisodeDetailsView;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.util.FormatUtil;


public class EpisodeDetailsActivity extends AppCompatActivity implements EpisodeDetailsView{

	private EpisodeDetailsPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.episode_details_activity);

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
