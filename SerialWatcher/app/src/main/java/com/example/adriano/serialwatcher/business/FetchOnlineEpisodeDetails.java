package com.example.adriano.serialwatcher.business;

import android.content.Context;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adriano on 7/16/15.
 */
public class FetchOnlineEpisodeDetails {
	private static final String TAG = FetchOnlineEpisodeDetails.class.getSimpleName();

	private String EPISODE_DETAILS_ENDPOINT = "http://...";



//	static class UrlConnectionUtil{
//
//		public static HttpURLConnection configureConnection(Context context, String requestAddress) throws IOException {
//
//			URL url = new URL(requestAddress);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//			connection.setReadTimeout(10000);
//			connection.setConnectTimeout(60000);
//			connection.setRequestMethod("GET");
//			connection.setDoInput(true);
//			connection.setRequestProperty("Content-Type", "application/json");
//			connection.setRequestProperty("trakt-api-version", "2");
//			connection.setRequestProperty("trakt-api-key", "c9de8f677d42a0e0ce640b285762f964f6479abd0e219ca6099c49fafc1db68d");
//
//			return null;
//		}
//	}
}
