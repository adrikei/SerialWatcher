package com.example.adriano.serialwatcher.database;

import android.provider.BaseColumns;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = SerialWatcherDatabase.NAME)
public class FavoriteEntity extends BaseModel {

	public FavoriteEntity(){}

	public FavoriteEntity(String slug, String title){
		this.slug = slug;
		this.title = title;
	}

	@Column(name = BaseColumns._ID)
	@PrimaryKey(autoincrement = true)
	Long id;

	@Column
	public String slug;

	@Column
	public String title;
}
