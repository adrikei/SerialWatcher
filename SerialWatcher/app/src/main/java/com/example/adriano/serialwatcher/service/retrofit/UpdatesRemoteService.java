package com.example.adriano.serialwatcher.service.retrofit;

import com.example.adriano.serialwatcher.model.ShowUpdate;

import retrofit.http.GET;

public interface UpdatesRemoteService {
	@GET("/latestUpdate.json")
	ShowUpdate getLatest();
}
