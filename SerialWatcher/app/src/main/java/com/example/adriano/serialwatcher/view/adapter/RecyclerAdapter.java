package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Season;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

	private Context context;
	private List<Season> seasons;
	private int layoutId;

	public RecyclerAdapter(Context context, int layoutId){
		this.context = context;
		this.layoutId = layoutId;
		this.seasons = new ArrayList<>();
	}

	public void setSeasons(List<Season> seasons){
		this.seasons = seasons;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Season season = seasons.get(position);
		holder.title().setText("Season " + (position + 1));
		holder.numEpisodes().setText(season.episodeCount() + " Episodes");
	}

	@Override
	public int getItemCount() {
		return seasons != null ? seasons.size() : 0;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{
//		private View root; // -> CardView
		private ImageView image;
		private TextView title;
		private TextView numEpisodes;

		public ViewHolder(View root){
			super(root);//a mensagem aqui é bem estranha se não chamar o super
//			this.root = root;
			this.image = (ImageView) root.findViewById(R.id.seasons_list_item_image);
			this.title = (TextView) root.findViewById(R.id.seasons_list_item_title);
			this.numEpisodes = (TextView) root.findViewById(R.id.seasons_list_item_episodeNumber);
		}

//		public View root(){
//			return root;
//		}
		public ImageView image(){
			return image;
		}
		public TextView title(){
			return title;
		}
		public TextView numEpisodes(){
			return numEpisodes;
		}
	}
}
