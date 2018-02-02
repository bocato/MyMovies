package br.com.zup.mymovies;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import br.com.zup.mymovies.di.app.AppComponent;
import br.com.zup.mymovies.di.app.AppModule;
import br.com.zup.mymovies.di.app.DaggerAppComponent;
import br.com.zup.mymovies.service.APIClient;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public class MyMoviesApplication extends Application {
    private static MyMoviesApplication mInstance;

    public synchronized static MyMoviesApplication getInstance() {
        return mInstance;
    }

    private APIClient mApiClient;
    private AppComponent mAppComponent;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mApiClient = new APIClient(this, getString(R.string.service_base_url));

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public APIClient getApiClient() {
        return mApiClient;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
