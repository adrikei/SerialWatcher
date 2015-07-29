package com.example.adriano.serialwatcher.view.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.database.FavoriteDAO;
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
			public void onClick(final View v) {

				ObjectAnimator firstScaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.1f);
				ObjectAnimator firstScaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.1f); //1 - 1st
				ObjectAnimator firstScaleXReverse = ObjectAnimator.ofFloat(v, "scaleX", 1f);
				ObjectAnimator firstScaleYReverse = ObjectAnimator.ofFloat(v, "scaleY", 1f); //1 - 2nd
				ObjectAnimator secondScaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.4f);
				ObjectAnimator secondScaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.4f); //3 - 3rd
				ObjectAnimator secondScaleXReverse = ObjectAnimator.ofFloat(v, "scaleX", .7f);
				ObjectAnimator secondScaleYReverse = ObjectAnimator.ofFloat(v, "scaleY", .7f); //3 - 4th
				ObjectAnimator thirdScaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.1f);
				ObjectAnimator thirdScaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.1f); //1 - 5th
				ObjectAnimator normalizationX = ObjectAnimator.ofFloat(v, "scaleX", 1f);
				ObjectAnimator normalizationY = ObjectAnimator.ofFloat(v, "scaleY", 1f); //1 - 6th



				AnimatorSet first = new AnimatorSet();
				first.playTogether(firstScaleX, firstScaleY);
				first.setDuration(150);

				final AnimatorSet second = new AnimatorSet();
				second.playTogether(firstScaleXReverse, firstScaleYReverse);
				second.setDuration(100);

				final AnimatorSet third = new AnimatorSet();
				third.playTogether(secondScaleX, secondScaleY);
				third.setDuration(250);

				final AnimatorSet fourth = new AnimatorSet();
				fourth.playTogether(secondScaleXReverse, secondScaleYReverse);
				fourth.setDuration(200);

				final AnimatorSet fifth = new AnimatorSet();
				fifth.playTogether(thirdScaleX, thirdScaleY);
				fifth.setDuration(150);

				final AnimatorSet sixth = new AnimatorSet();
				sixth.playTogether(normalizationX, normalizationY);
				sixth.setDuration(60);

				AnimatorSet initial = new AnimatorSet();
				initial.playSequentially(first, second, third);

				initial.addListener(new Animator.AnimatorListener() {
					@Override
					public void onAnimationStart(Animator animation) {
					}

					@Override
					public void onAnimationEnd(Animator animation) {


						FloatingActionButton favoriteButton = (FloatingActionButton) v;

						Favorite favorite = new Favorite(((Show) getIntent().getExtras().get(SHOW)).ids().slug(), ((Show) getIntent().getExtras().get(SHOW)).title());
						if (favoriteButton.getTag() == null) {
							favoriteButton.setImageResource(R.drawable.show_details_favorite_on);
							favoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.toggle_on));
							favoriteButton.setTag(favorite);
							dao.save(favorite);
						} else {
							favoriteButton.setImageResource(R.drawable.show_details_favorite_off);
							favoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.toggle_off));
							favoriteButton.setTag(null);
							dao.delete(favorite.slug());
						}

						AnimatorSet beat = new AnimatorSet();
						beat.playSequentially(fourth, fifth, sixth);
						beat.start();

					}

					@Override
					public void onAnimationCancel(Animator animation) {
					}

					@Override
					public void onAnimationRepeat(Animator animation) {
					}
				});
				initial.start();
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
