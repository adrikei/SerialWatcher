package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.database.FavoriteEntity;
import com.example.adriano.serialwatcher.database.FavoriteEntity$Adapter;
import com.example.adriano.serialwatcher.database.FavoriteEntity$Table;
import com.example.adriano.serialwatcher.model.Favorite;
import com.example.adriano.serialwatcher.view.contract.FavoriteClickListener;

public class FavoritesAdapter extends CursorAdapter {

	private FavoriteClickListener listener;

	public FavoritesAdapter(Context context, FavoriteClickListener listener) {
		super(context, null, 0); //0?
		this.listener = listener;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.favorite_shows_list_item, parent, false);

		ViewHolder holder = new ViewHolder(view);
		view.setTag(holder);

		return view;
	}

	@Override
	public void bindView(View view, Context context, final Cursor cursor) {
		ViewHolder holder;

		FavoriteEntity$Adapter entityAdapter = new FavoriteEntity$Adapter();
		final FavoriteEntity entity = new FavoriteEntity();
		entityAdapter.loadFromCursor(cursor, entity); //O cursor precisa ser consumido

		holder = (ViewHolder) view.getTag();

		TextView tv = (TextView) holder.getView();
		tv.setText(entity.title);

		view.setTag(holder);

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onFavoriteClick(new Favorite(entity.slug, entity.title));
			}
		});
	}

	public static class ViewHolder{
		private View view;

		public ViewHolder(View view){
			this.view = view.findViewById(R.id.favorite_shows_list_item_id);
		}

		public View getView(){
			return view;
		}
	}
}
