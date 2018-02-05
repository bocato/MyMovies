package br.com.zup.mymovies.di;

import br.com.zup.mymovies.di.scope.Activity;
import br.com.zup.mymovies.di.scope.Fragment;
import br.com.zup.mymovies.service.APIClient;
import br.com.zup.mymovies.viewmodel.MovieDetailViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafaelneiva on 05/02/18.
 */

@Module
public class MovieDetailModule {

    public MovieDetailModule() {
    }

    @Activity
    @Provides
    MovieDetailViewModel providesMovieDetailViewModel() {
        return new MovieDetailViewModel();
    }

}
