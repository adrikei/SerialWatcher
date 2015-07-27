package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
		this.seasons = new ArrayList<Season>();
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

	}

	@Override
	public int getItemCount() {
		return seasons != null ? seasons.size() : 0;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{
		private View view; // -> CardView
		//TODO - itens internos do cardview como titulo e descricao via findViewById

		public ViewHolder(View root){
			super(root);//a mensagem aqui é bem estranha se não chamar o super
			view = root;
		}

		public View view(){
			return view;
		}
	}
}
