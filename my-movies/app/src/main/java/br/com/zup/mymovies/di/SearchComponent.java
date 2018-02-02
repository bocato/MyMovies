package br.com.zup.mymovies.di;

import br.com.zup.mymovies.di.app.AppComponent;
import br.com.zup.mymovies.di.scope.Activity;
import br.com.zup.mymovies.view.activities.SearchActivity;
import dagger.Component;

/**
 * Created by rafaelneiva on 02/02/18.
 */

@Activity
@Component(dependencies = AppComponent.class, modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}
