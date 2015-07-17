package com.example.adriano.serialwatcher.activity.asynchronous;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.model.converter.ModelConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adriano on 7/16/15.
 */
public class RemoteEpisodeDetailsLoader extends AsyncTaskLoader<Episode> {

	private static final String tag = RemoteEpisodeDetailsLoader.class.getSimpleName();

	private Context context;
	private String url;

	public RemoteEpisodeDetailsLoader(Context context, String url){
		super(context);
		this.context = context;
		this.url = url;
	}

	@Override
	public Episode loadInBackground() {

		Episode episode = null;
		InputStreamReader reader = null;
		HttpURLConnection connection = null;

		Log.d("WOLOLO", "loadInBackground");
		// ?extended=full,images
		connection = configureConnection(context, url);

		try {
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream stream = connection.getInputStream();
				reader = new InputStreamReader(stream);
				episode = new ModelConverter().toEpisode(reader);
			}
		} catch (IOException e) {
			Log.e(tag, "Error loading remote content", e);
		} finally {
//			connection.disconnect();
		}

		return episode;
	}

	private HttpURLConnection configureConnection(Context context, String url) {
		HttpURLConnection connection = null;
		Log.d("WOLOLO", "configureConnection" + url);
		if(url != null && !url.equals("")) {
			try {
				connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setReadTimeout(context.getResources().getInteger(R.integer.api_timeout_read));
				connection.setConnectTimeout(context.getResources().getInteger(R.integer.api_timeout_connect));
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("trakt-api-version", context.getString(R.string.api_version));
				connection.setRequestProperty("trakt-api-key", context.getString(R.string.api_key));

			} catch (IOException e) {
				Log.e(tag, "Error in configured url.", e);
			}
		}

		return connection;
	}

}
