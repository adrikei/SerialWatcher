package com.example.adriano.serialwatcher.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.adapter.fragment.RecyclerAdapter;

public class SeriesSeasonsFragment extends Fragment {

	RecyclerAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.series_details_fragment_seasons, container, false);

		RecyclerView view = (RecyclerView) root.findViewById(R.id.series_details_fragment_seasons_carousel);
		view.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
		RecyclerAdapter adapter = new RecyclerAdapter(root.getContext());
		view.setAdapter(adapter);

		this.adapter = adapter;

		return root;
	}
}
