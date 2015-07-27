package com.example.adriano.serialwatcher.view.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.database.dao.FavoriteDAO;
import com.example.adriano.serialwatcher.model.Favorite;
import com.example.adriano.serialwatcher.model.Images;
import com.example.adriano.serialwatcher.model.Show;
import com.example.adriano.serialwatcher.presenter.SeriesDetailsPresenter;
import com.example.adriano.serialwatcher.view.adapter.SeriesDetailsAdapter;
import com.example.adriano.serialwatcher.view.base.BaseNavigationToolbarActivity;
import com.example.adriano.serialwatcher.view.contract.SeriesDetailsView;

public class SeriesDetailsActivity extends BaseNavigationToolbarActivity implements SeriesDetailsView {

	public static final String SHOW = "show";

	private SeriesDetailsPresenter presenter;
	private ImageView seriesDetailsHighlightImage;
	private FloatingActionButton favoriteView;
	private FavoriteDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.series_details_activity);
		configureToolbar();

		seriesDetailsHighlightImage = (ImageView) findViewById(R.id.series_details_highlight_image);
		favoriteView = (FloatingActionButton) findViewById(R.id.series_details_toggle_heart);

		//TODO - através do presenter ↓

		this.dao = new FavoriteDAO(this);
		Favorite fav = this.dao.query(((Show)getIntent().getExtras().get(SHOW)).ids().slug());

		if(fav != null){
			favoriteView.setImageResource(R.drawable.show_details_favorite_on);
			favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.toggle_on));
		}else{
			favoriteView.setImageResource(R.drawable.show_details_favorite_off);
			favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.toggle_off));
		}
		favoriteView.setTag(fav);

		//TODO - fora da main thread
		favoriteView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FloatingActionButton favoriteButton = (FloatingActionButton)v;
				Favorite favorite = new Favorite(((Show)getIntent().getExtras().get(SHOW)).ids().slug(), ((Show)getIntent().getExtras().get(SHOW)).title());
				if(favoriteButton.getTag() == null){
					favoriteButton.setImageResource(R.drawable.show_details_favorite_on);
					favoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.toggle_on));
					favoriteButton.setTag(favorite);
					dao.save(favorite);
				}else{
					favoriteButton.setImageResource(R.drawable.show_details_favorite_off);
					favoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.toggle_off));
					favoriteButton.setTag(null);
					dao.delete(favorite.slug());
				}
			}
		});


		ViewPager vPager = (ViewPager) findViewById(R.id.series_details_view_pager);
		vPager.setAdapter(new SeriesDetailsAdapter(getSupportFragmentManager(), this));

		this.presenter = new SeriesDetailsPresenter(this);
//		presenter.loadSeriesDetails(getIntent().getExtras().getString(SHOW));
		this.displaySeries((Show)getIntent().getExtras().getSerializable(SHOW));
	}

	@Override
	public void displaySeries(Show show) {
		setToolbarTitle(show.title());

		Glide.with(this)
				.load(show.images().fanart().get(Images.ImageSize.FULL))
				.placeholder(R.drawable.highlight_placeholder)
				.centerCrop()
				.into(seriesDetailsHighlightImage);

		//TODO - carregar detalhes nos fragments
	}
}
