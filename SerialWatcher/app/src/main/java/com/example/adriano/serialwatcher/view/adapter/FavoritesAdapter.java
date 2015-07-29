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

public class FavoritesAdapter extends CursorAdapter {

	public FavoritesAdapter(Context context) {
		super(context, null, 0); //0?
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(R.layout.favorite_shows_list_item, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder;

		if(view.getTag() != null){
			holder = (ViewHolder) view.getTag();
		}else{
			holder = new ViewHolder(view);
		}

		TextView tv = (TextView) holder.getView();
		tv.setText(cursor.getString(2));
		view.setTag(holder);
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
