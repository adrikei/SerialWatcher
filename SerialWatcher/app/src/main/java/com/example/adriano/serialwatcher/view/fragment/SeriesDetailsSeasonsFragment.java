package com.example.adriano.serialwatcher.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.adapter.RecyclerAdapter;
import com.example.adriano.serialwatcher.view.contract.SeasonListView;
import com.example.adriano.serialwatcher.model.Season;

import java.util.List;

public class SeriesDetailsSeasonsFragment extends Fragment implements SeasonListView{

	private RecyclerAdapter adapter;
	private Context context; //ok?
	private Bundle arguments;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.series_details_fragment_seasons, container, false);

		RecyclerView view = (RecyclerView) root.findViewById(R.id.series_details_fragment_seasons_carousel);
		view.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
		RecyclerAdapter adapter = new RecyclerAdapter(root.getContext(), R.layout.series_details_fragment_seasons_item);
		view.setAdapter(adapter);

		this.adapter = adapter;
		this.context = root.getContext();

		return root;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.arguments = getArguments();
	}

	@Override
	public void displaySeasonList(List<Season> seasons) {
		//TODO - envia lista pro adapter e on dataset changed notification
		Toast.makeText(context, "displaySeasonsList " + seasons.size(), Toast.LENGTH_LONG).show();
	}
}
