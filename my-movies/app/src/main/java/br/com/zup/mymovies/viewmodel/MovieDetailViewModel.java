package br.com.zup.mymovies.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import br.com.zup.mymovies.MyMoviesApplication;
import br.com.zup.mymovies.model.OmdbVideoFull;
import br.com.zup.mymovies.service.APIClient;
import br.com.zup.mymovies.service.OMDBService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rafaelneiva on 05/02/18.
 */

public class MovieDetailViewModel extends BaseViewModel {

    private OMDBService mService;

    public MovieDetailViewModel() {
        this.mService = MyMoviesApplication.getInstance().getApiClient().getRetrofit().create(OMDBService.class);
    }

    public MutableLiveData<OmdbVideoFull> getMovieDetails(String omdbId) {
        MutableLiveData<OmdbVideoFull> data = new MutableLiveData<>();

        showLoading.set(true);
        mService.getMovieById(omdbId).enqueue(new Callback<OmdbVideoFull>() {
            @Override
            public void onResponse(Call<OmdbVideoFull> call, Response<OmdbVideoFull> response) {
                showLoading.set(false);
                if (response.isSuccessful())
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OmdbVideoFull> call, Throwable t) {
                showLoading.set(false);
                data.setValue(null);
            }
        });

        return data;
    }
}
