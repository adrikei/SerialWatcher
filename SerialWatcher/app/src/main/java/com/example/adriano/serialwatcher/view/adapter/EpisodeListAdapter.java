package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.activity.EpisodeListingActivity;
import com.example.adriano.serialwatcher.model.Episode;
import com.example.adriano.serialwatcher.view.contract.EpisodeClickListener;
import com.example.adriano.serialwatcher.view.contract.EpisodeListingView;

import java.util.ArrayList;
import java.util.List;


public class EpisodeListAdapter extends ArrayAdapter<Episode> {

	private List<Episode> episodes;
	private EpisodeClickListener ecl;

	public EpisodeListAdapter(Context context, EpisodeClickListener ecl){
		super(context, R.layout.season_details_list_item);
		this.episodes = new ArrayList<>();
		this.ecl = ecl;
	}

	public int getCount(){
		return episodes != null ? episodes.size() : 0;
	}

	public Episode getItem(int position){
		return episodes != null && episodes.size() >= position ? episodes.get(position) : null;
	}

	public long getItemId(int position){
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
//		int type = getItemViewType(position); //parte do to-do abaixo
		if(view == null){
			int resource = R.layout.season_details_list_item;
			//if type == TBA... //TODO
			view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		populateViewFromHolder(holder, position);

		return view;
	}

	private void populateViewFromHolder(ViewHolder holder, int position){

		final Episode ep = getItem(position);

		TextView view = (TextView) holder.getView();

		view.setText(ep.title());

		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ecl.onEpisodeClick(ep);
			}
		});

	}

	public void setEpisodes(List<Episode> episodes){
		this.episodes = episodes;
		this.notifyDataSetChanged();
	}

	public static class ViewHolder{ //Increases List performance by mantaining references to views
		private View mView;

		public ViewHolder(View root){
			mView = root.findViewById(R.id.season_details_item_id);
		}

		public View getView(){
			return mView;
		}
	}

}
