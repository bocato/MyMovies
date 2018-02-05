package br.com.zup.mymovies.view.activities;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.zup.mymovies.MyMoviesApplication;
import br.com.zup.mymovies.R;
import br.com.zup.mymovies.databinding.ActMovieDetailBinding;
import br.com.zup.mymovies.di.DaggerMovieDetailComponent;
import br.com.zup.mymovies.di.MovieDetailModule;
import br.com.zup.mymovies.view.BaseActivity;
import br.com.zup.mymovies.view.adapters.MovieDetailPagerAdapter;
import br.com.zup.mymovies.viewmodel.MovieDetailViewModel;

/**
 * Created by rafaelneiva on 05/02/18.
 */

public class MovieDetailActivity extends BaseActivity {
    public static final String ID_LIST_KEY = "id-list-key";
    public static final String CLICKED_POSITION_KEY = "clicked-position-key";

    private ArrayList<String> mIdList;
    private int mStartPos = 0;

    ActMovieDetailBinding bind;

    @Inject
    MovieDetailViewModel viewModel;

    @Override
    protected int getContentLayoutId() {
        return R.layout.act_movie_detail;
    }

    @Override
    protected void initInjectors() {
        DaggerMovieDetailComponent.builder()
                .appComponent(getWLApplication().getAppComponent())
                .movieDetailModule(new MovieDetailModule())
                .build().inject(this);
    }

    @Override
    protected void initBinding() {
        bind = (ActMovieDetailBinding) getBinding();
        if (getIntent().getExtras() != null) {
            mIdList = getIntent().getStringArrayListExtra(ID_LIST_KEY);
            mStartPos = getIntent().getExtras().getInt(CLICKED_POSITION_KEY);

            postponeEnterTransition();
            viewModel.getMovieDetails(mIdList.get(mStartPos)).observe(this, omdbVideoFull -> {
                if (omdbVideoFull != null) {
                    MyMoviesApplication.getInstance().getApiClient().getPicasso().load(omdbVideoFull.getPoster()).into(bind.ivPoster);
                    startPostponedEnterTransition();
                }
            });

//            MovieDetailPagerAdapter adapter = new MovieDetailPagerAdapter(getSupportFragmentManager(), mIdList);
//            bind.vpMovies.setAdapter(adapter);
//            bind.vpMovies.setCurrentItem(mStartPos);
        }
    }
}