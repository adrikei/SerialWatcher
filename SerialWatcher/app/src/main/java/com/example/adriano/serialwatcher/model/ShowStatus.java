package com.example.adriano.serialwatcher.model;

/**
 * Created by Adriano on 7/15/15.
 */
public enum ShowStatus {

	RETURNING("returning series"), PRODUCTION("in production"), CANCELED("canceled"), ENDED("ended");

	private String mStatus;

	ShowStatus(String status) {
		mStatus = status;
	}

	public String status() {
		return mStatus;
	}

}
