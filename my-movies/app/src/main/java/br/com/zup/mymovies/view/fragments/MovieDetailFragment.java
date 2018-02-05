package br.com.zup.mymovies.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import br.com.zup.mymovies.MyMoviesApplication;
import br.com.zup.mymovies.R;
import br.com.zup.mymovies.databinding.FragMovieDetailBinding;
import br.com.zup.mymovies.di.DaggerMovieDetailComponent;
import br.com.zup.mymovies.model.OmdbVideoFull;
import br.com.zup.mymovies.view.BaseFragment;
import br.com.zup.mymovies.viewmodel.MovieDetailViewModel;

/**
 * Created by rafaelneiva on 05/02/18.
 */

public class MovieDetailFragment extends BaseFragment {

    private static final String OMDB_ID_KEY = "omdb-id-key";

    public static MovieDetailFragment newInstance(String omdbId) {
        Bundle args = new Bundle();
        args.putString(OMDB_ID_KEY, omdbId);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().getString(OMDB_ID_KEY) != null) {
            mOmdbId = getArguments().getString(OMDB_ID_KEY);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(MovieDetailViewModel.class);

        bind = (FragMovieDetailBinding) getDataBinding();
        bind.setMovie(viewModel);

        postponeEnterTransition();
        viewModel.getMovieDetails(mOmdbId).observe(this, omdbVideoFull -> {
            if (omdbVideoFull != null) {
                MyMoviesApplication.getInstance().getApiClient().getPicasso().load(omdbVideoFull.getPoster()).into(bind.ivPoster);
                startPostponedEnterTransition();
            }
        });
    }

    FragMovieDetailBinding bind;

    MovieDetailViewModel viewModel;

    String mOmdbId;

    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_movie_detail;
    }

    @Override
    protected void initInjectors() {
    }

    @Override
    protected void initBinding() {

    }
}
