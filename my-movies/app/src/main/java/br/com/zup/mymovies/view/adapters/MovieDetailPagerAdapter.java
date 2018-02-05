package br.com.zup.mymovies.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import br.com.zup.mymovies.view.fragments.MovieDetailFragment;

/**
 * Created by rafaelneiva on 05/02/18.
 */

public class MovieDetailPagerAdapter extends FragmentPagerAdapter {
    List<String> mItems;

    public MovieDetailPagerAdapter(FragmentManager fm, List<String> idList) {
        super(fm);
        mItems = idList;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieDetailFragment.newInstance(mItems.get(position));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}
