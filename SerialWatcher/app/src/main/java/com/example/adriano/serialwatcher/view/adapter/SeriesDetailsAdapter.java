package com.example.adriano.serialwatcher.view.adapter;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.adriano.serialwatcher.R;
import com.example.adriano.serialwatcher.view.fragment.SeriesDetailsInfoFragment;
import com.example.adriano.serialwatcher.view.fragment.SeriesDetailsSeasonsFragment;

public class SeriesDetailsAdapter extends FragmentPagerAdapter {

	private static final int SERIES_INFO_FRAGMENT_POSITION = 0;
	private static final int SEASONS_LIST_FRAGMENT_POSITION = 1;

	private Context context;
	//Não tem lista de itens porque é 2 "hardcoded" //TODO - por enquanto?

	public SeriesDetailsAdapter(FragmentManager manager, Context context){
		super(manager);
		this.context = context;
	}

	@Override
	public Fragment getItem(int position){
		switch(position){
			case SERIES_INFO_FRAGMENT_POSITION:
				return new SeriesDetailsInfoFragment();
			case SEASONS_LIST_FRAGMENT_POSITION:
				return new SeriesDetailsSeasonsFragment();
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
