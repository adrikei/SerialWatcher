package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Images;
import com.example.adriano.serialwatcher.model.Show;
import com.example.adriano.serialwatcher.view.contract.SeriesClickListener;

import java.util.ArrayList;
import java.util.List;

public class SeriesListGridAdapter extends ArrayAdapter<Show>{

	private List<Show> shows;
	private SeriesClickListener scl;

	public SeriesListGridAdapter(Context context, SeriesClickListener scl){
		super(context, R.layout.series_listing_grid_item);
		this.shows = new ArrayList<>();
		this.scl = scl;
	}

	public void setSeries(List<Show> shows){
		this.shows = shows;
		notifyDataSetChanged();
	}

	public int getCount(){
		return shows != null ? shows.size() : 0;
	}

	public Show getItem(int position){
		return shows != null && shows.size() >= position ? shows.get(position) : null;
	}

	@Override
	public long getItemId(int position){
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
		populateViewFromHolder(holder, position);
		return view;
	}

	private void populateViewFromHolder(ViewHolder holder, int position) {
		final Show show = getItem(position);
		ImageView img = (ImageView) holder.getView();

		Glide.with(getContext())
				.load(show.images().poster().get(Images.ImageSize.FULL))
				.placeholder(R.drawable.season_item_placeholder)
				.centerCrop()
				.into(img);

		img.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				scl.onSeriesClick(show);
			}
		});
	}

	static class ViewHolder{
		private View view;

		public ViewHolder(View view){
			this.view = view.findViewById(R.id.series_listing_grid_item_id);
		}

		public View getView(){
			return this.view;
		}
	}
}
