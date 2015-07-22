package com.example.adriano.serialwatcher.activity.adapter.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.activity.view.SeasonDetailsActivity;
import com.example.adriano.serialwatcher.model.Episode;

import java.util.ArrayList;
import java.util.List;


public class SeasonDetailsAdapter extends ArrayAdapter<Episode> {

	private List<Episode> episodes;

	public SeasonDetailsAdapter(Context context){
		super(context, 0);
		this.episodes = new ArrayList<>();
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
//		int type = getItemViewType(position);
		if(view == null){
			int resource = R.layout.season_details_list_item;
			//if type == TBA... //TODO
			view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		populateViewFromHolder(holder, position/*, type*/);

		return view;
	}

	public void populateViewFromHolder(ViewHolder holder, int position){

		final Episode ep = getItem(position);

		TextView view = (TextView) holder.view();

		view.setText(ep.title());

		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				((SeasonDetailsActivity) getContext()).onEpisodeClick(ep);
			}
		});

	}

	public void setItems(List<Episode> episodes){
		this.episodes = episodes;
		this.notifyDataSetChanged();
	}

	public static class ViewHolder{ //Increases List performance by mantaining references to views
		private View mView;

		public ViewHolder(View root){
			mView = root.findViewById(R.id.season_details_item_id);
		}

		public View view(){
			return mView;
		}
	}

}
