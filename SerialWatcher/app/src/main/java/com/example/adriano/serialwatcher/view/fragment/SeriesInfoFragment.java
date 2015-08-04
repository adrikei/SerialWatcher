package com.example.adriano.serialwatcher.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Show;

public class SeriesInfoFragment extends Fragment {

	public static final String SUMMARY = "summary";
	public static final String GENRES = "genres";
	public static final String DETAILS = "details";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.series_details_fragment_info, container, false);

		TextView summary = (TextView) root.findViewById(R.id.series_details_summary);
		summary.setText(getArguments().getString(SUMMARY));

		TextView genres = (TextView) root.findViewById(R.id.series_details_genres);
		genres.setText(getArguments().getString(GENRES));

		TextView details = (TextView) root.findViewById(R.id.series_details_details);
		details.setText(getArguments().getString(DETAILS));

		return root;
	}
}
