package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.model.Show;
import com.example.adriano.serialwatcher.util.FormatUtil;
import com.example.adriano.serialwatcher.view.fragment.SeriesInfoFragment;
import com.example.adriano.serialwatcher.view.fragment.SeasonsFragment;

public class SeriesDetailsAdapter extends FragmentPagerAdapter {

	private static final int SERIES_INFO_FRAGMENT_POSITION = 0;
	private static final int SEASONS_LIST_FRAGMENT_POSITION = 1;

	private Context context;
	private Show fragmentShow;

	public SeriesDetailsAdapter(FragmentManager manager, Context context, Show show){
		super(manager);
		this.context = context;
		this.fragmentShow = show;
	}

	@Override
	public Fragment getItem(int position){
		switch(position){
			case SERIES_INFO_FRAGMENT_POSITION:
				SeriesInfoFragment sif = new SeriesInfoFragment();
				Bundle args = new Bundle();
				String genres = "";
				for(String genre: fragmentShow.genres()){
					genres += ", " + genre;
				}
				genres = genres.replaceFirst(", ", "");
				args.putString(SeriesInfoFragment.SUMMARY, fragmentShow.overview());
				args.putString(SeriesInfoFragment.GENRES, genres);

				String details = "";
				details += "Broadcasting: " + FormatUtil.formatDate(fragmentShow.firstAired()) + "\n";
				details += "Status: " + fragmentShow.status() + "\n";
				details += "Seasons: " + fragmentShow.airedEpisodes() + "\n";
				details += "Started in: " + fragmentShow.year() + "\n";
				details += "Country: " + fragmentShow.country() + "\n";
				details += "Homepage: " + fragmentShow.network() + "\n";

				args.putString(SeriesInfoFragment.DETAILS, details);
				sif.setArguments(args);
				return sif;
			case SEASONS_LIST_FRAGMENT_POSITION:
				return new SeasonsFragment();
			default:
				return null;
		}
	}

	@Override
	public int getCount(){
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position){
			case SERIES_INFO_FRAGMENT_POSITION:
				return this.context.getString(R.string.series_details_series_info);
			case SEASONS_LIST_FRAGMENT_POSITION:
				return this.context.getString(R.string.series_details_series_seasons);
			default:
				return null;
		}
	}
}
