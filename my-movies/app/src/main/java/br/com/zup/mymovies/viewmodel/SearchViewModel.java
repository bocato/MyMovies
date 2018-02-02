package br.com.zup.mymovies.viewmodel;

import br.com.zup.mymovies.service.APIClient;
import br.com.zup.mymovies.service.OMDBService;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public class SearchViewModel extends BaseViewModel {

    private OMDBService mService;

    public SearchViewModel(APIClient client) {
        mService = client.getRetrofit().create(OMDBService.class);
    }


}
