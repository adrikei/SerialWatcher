package com.example.adriano.serialwatcher.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.asynchronous.AsyncFetchEpisodeDetails;
import com.example.adriano.serialwatcher.activity.asynchronous.AsyncLoadImage;
import com.example.adriano.serialwatcher.activity.asynchronous.EpisodeLoaderCallback;
import com.example.adriano.serialwatcher.activity.asynchronous.contract.EpisodeDetailsFetching;
import com.example.adriano.serialwatcher.activity.asynchronous.contract.ImageLoading;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.util.FormatUtil;

import java.text.MessageFormat;


public class EpisodeDetailsActivity extends ActionBarActivity implements EpisodeDetailsFetching, ImageLoading{

    private final String tag = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.episode_details_activity);
		Log.d(tag, "onCreate");
		if(savedInstanceState!= null && savedInstanceState.getString("title") != null){
			Log.d(tag, "\n\t " + savedInstanceState.getString("title"));
			Log.d(tag, "/onCreate");
		}
//		new AsyncFetchEpisodeDetails(this, this).execute();

		Bundle args = new Bundle();
		args.putString("url", this.getString(R.string.api_url_base) + MessageFormat.format(this.getString(R.string.api_url_episode), "futurama", "1", "1") + "?extended=full,images");

		getLoaderManager().initLoader(0, args, new EpisodeLoaderCallback(this, this)).forceLoad();
    }

	@Override
	protected void onStart(){
		super.onStart();
		Log.d(tag, "onStart");
	}

	@Override
	protected void onResume(){
		super.onResume();
		Log.d(tag, "onResume");
	}

	@Override
	protected void onRestart(){
		super.onRestart();
		Log.d(tag, "onRestart");
	}

	@Override
	protected void onPause(){
		super.onPause();
		Log.d(tag, "onPause");
	}

	@Override
	protected void onStop(){
		super.onStop();
		Log.d(tag, "onStop");
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		Log.d(tag, "onDestroy");
	}

	@Override
	protected void onSaveInstanceState(Bundle outBundle){
		outBundle.putString("title", "Bla");

		super.onSaveInstanceState(outBundle);
		Log.d(tag, "onSaveInstanceState");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(tag, "onRestoreInstanceState");

		if(savedInstanceState!= null && savedInstanceState.getString("title") != null) {
			Log.d(tag, "\n\t " + savedInstanceState.getString("title"));
			Log.d(tag, "/onRestoreInstanceState");
		}
	}

	@Override
	public void onDetailsFetched(Episode episode) {
		((TextView) findViewById(R.id.episode_details_displayTitle)).setText(episode.title());
		((TextView)findViewById(R.id.episode_details_airTime)).setText(FormatUtil.formatDate(episode.firstAired()).toString());
		((TextView)findViewById(R.id.episode_details_summary)).setText(episode.overview());
		new AsyncLoadImage(this, this).execute(episode.images().screenshot().get("full"));
	}

	@Override
	public void onImageLoaded(Bitmap image){
		((ImageView)findViewById(R.id.episode_details_highlightImage)).setImageBitmap(image);
	}

}
