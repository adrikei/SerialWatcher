package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Show;

import java.util.ArrayList;
import java.util.List;

public class SeriesListGridAdapter extends ArrayAdapter<Show>{

	List<Show> shows;

	public SeriesListGridAdapter(Context context){
		super(context, 0); //TODO - 0?
		this.shows = new ArrayList<Show>();
	}

	public int getCount(){
		return shows != null ? shows.size() : 0;
	}

	public Show getItem(int position){
		return shows != null && shows.size() >= position ? shows.get(position) : null;
	}

	public int getItemId(int position){
		return position;
	}

	public View getView(int position, View view, ViewGroup parent){
		ViewHolder holder;
		if(view == null){
			int resource = R.layout.series_listing_grid_item;
			view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		//populateViewFromHolder(holder, position)
		return view;
	}

	static class ViewHolder{
		private View view;

		public ViewHolder(View view){
			this.view = view;
		}

		public View getView(){
			return this.view;
		}
	}
}
