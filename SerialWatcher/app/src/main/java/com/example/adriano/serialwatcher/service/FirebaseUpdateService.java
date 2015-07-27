package com.example.adriano.serialwatcher.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.ShowUpdate;
import com.example.adriano.serialwatcher.service.retrofit.UpdatesRemoteService;
import com.example.adriano.serialwatcher.view.activity.SeriesDetailsActivity;

import retrofit.RestAdapter;

public class FirebaseUpdateService extends IntentService {

	public FirebaseUpdateService(){
		super("FirebaseUpdateService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		RestAdapter adapter = new RestAdapter.Builder().setEndpoint(this.getString(R.string.api_url_updates)).build();
		UpdatesRemoteService service = adapter.create(UpdatesRemoteService.class);

		ShowUpdate su = service.getLatest();
		Log.d("Findme", su.date() + ": " + su.message());

		Intent notificationIntent = new Intent(this, SeriesDetailsActivity.class);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setContentTitle(su.title())
				.setContentText(su.date() + ": " + su.message())
				.setContentIntent(PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT))
				.setAutoCancel(true)
				.setStyle(new NotificationCompat.BigTextStyle().bigText(su.message()));

		Notification notification = builder.build();

		NotificationManager manager =
				(NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		SharedPreferences preferences = this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE);

		String lu = preferences.getString("last_update", null);

		if(lu == null || su.date().compareTo(lu) > 0){
			manager.notify(0, notification);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("last_update", su.date());
			editor.commit();
			Log.d("Findme", su.date() + " inserted");
		}
	}
}
