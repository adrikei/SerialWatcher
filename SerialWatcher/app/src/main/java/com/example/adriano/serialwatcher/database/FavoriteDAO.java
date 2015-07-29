package com.example.adriano.serialwatcher.database;

import android.content.Context;
import android.database.Cursor;

import com.example.adriano.serialwatcher.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;


//TODO - organizar pacote
public class FavoriteDAO {
	private Context mContext;

	public FavoriteDAO(Context context) {
		mContext = context;
	}

	public void save(Favorite favorite) {
		new FavoriteEntity(favorite.slug(), favorite.title()).save();
	}

	public Cursor all() {
		return new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
	}

	public Favorite query(String slug) {
		FavoriteEntity fav = new Select().from(FavoriteEntity.class).where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug)).querySingle();
		return fav != null ? new Favorite(fav.slug, fav.title) : null;
	}

	public void delete(String slug) {
		new Delete().from(FavoriteEntity.class).where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug)).queryClose();
	}
}
