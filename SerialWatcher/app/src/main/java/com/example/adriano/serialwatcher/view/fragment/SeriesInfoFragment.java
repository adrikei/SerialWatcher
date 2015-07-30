package com.example.adriano.serialwatcher.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adriano.serialwatcher.R;

public class SeriesInfoFragment extends Fragment {

	private Bundle arguments;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.series_details_fragment_info, container, false);

		return root;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.arguments = getArguments(); //TODO - dados
	}
}
