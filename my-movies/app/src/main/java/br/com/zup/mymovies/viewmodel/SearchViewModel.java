package br.com.zup.mymovies.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import br.com.zup.mymovies.model.SearchResult;
import br.com.zup.mymovies.service.APIClient;
import br.com.zup.mymovies.service.OMDBService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public class SearchViewModel extends BaseViewModel {

    private OMDBService mService;

    public SearchViewModel(APIClient client) {
        mService = client.getRetrofit().create(OMDBService.class);
    }

    public MutableLiveData<SearchResult> searchMovie(String query) {
        MutableLiveData<SearchResult> data = new MutableLiveData<>();

        mService.searchMovie(query).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful())
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });

        return data;
    }


}
