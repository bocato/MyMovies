package br.com.zup.mymovies.di;

import br.com.zup.mymovies.di.app.AppComponent;
import br.com.zup.mymovies.di.scope.Activity;
import br.com.zup.mymovies.di.scope.Fragment;
import br.com.zup.mymovies.view.activities.MovieDetailActivity;
import br.com.zup.mymovies.view.fragments.MovieDetailFragment;
import dagger.Component;

/**
 * Created by rafaelneiva on 05/02/18.
 */

@Activity
@Component(dependencies = AppComponent.class, modules = MovieDetailModule.class)
public interface MovieDetailComponent {

    void inject(MovieDetailActivity activity);

}
