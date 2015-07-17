package com.example.adriano.serialwatcher.activity.asynchronous;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.asynchronous.contract.ImageLoading;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adriano on 7/16/15.
 */
public class AsyncLoadImage extends AsyncTask<String, Object, Bitmap>{

	private static final String tag = AsyncLoadImage.class.getSimpleName();

	private Context context;
	private ImageLoading imageLoading;

	public AsyncLoadImage(Context context, ImageLoading loader){
		this.context = context;
		this.imageLoading = loader;
	}

	@Override
	protected Bitmap doInBackground(String... params){
		try {
			String src = params[0];
			URL url = new URL(src);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setReadTimeout(context.getResources().getInteger(R.integer.api_timeout_read));
			connection.setConnectTimeout(context.getResources().getInteger(R.integer.api_timeout_connect));
			connection.setRequestMethod("GET");
			connection.setRequestProperty("trakt-api-version", context.getString(R.string.api_version));
			connection.setRequestProperty("trakt-api-key", context.getString(R.string.api_key));

			connection.connect();

			InputStream stream = connection.getInputStream();
			Bitmap image = BitmapFactory.decodeStream(stream);

			return image;
		}catch(IndexOutOfBoundsException | IOException e){
			Log.d(tag, e.getMessage());
			throw new RuntimeException("AsyncLoadImage should be called with a valid url.");
		}
	}

	@Override
	protected void onPostExecute(Bitmap image){
		imageLoading.onImageLoaded(image);
	}
}
