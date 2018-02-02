package br.com.zup.mymovies.di;

import br.com.zup.mymovies.di.scope.Activity;
import br.com.zup.mymovies.service.APIClient;
import br.com.zup.mymovies.viewmodel.SearchViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafaelneiva on 02/02/18.
 */

@Module
public class SearchModule {

    public SearchModule() {
    }

    @Activity
    @Provides
    SearchViewModel providesSearchViewModel(APIClient client) {
        return new SearchViewModel(client);
    }
}
