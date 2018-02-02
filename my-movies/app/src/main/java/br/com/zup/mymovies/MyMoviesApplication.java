package br.com.zup.mymovies;

import android.app.Application;
import android.databinding.Bindable;

import br.com.zup.mymovies.service.APIClient;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public class MyMoviesApplication extends Application {

    private APIClient mApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiClient = new APIClient(this, "");
    }

    @Bindable
    public APIClient getApiClient() {
        return mApiClient;
    }
}
