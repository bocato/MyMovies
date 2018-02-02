package br.com.zup.mymovies.di.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import br.com.zup.mymovies.service.APIClient;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application application();

    Context context();

    SharedPreferences sharedPreferences();

    Gson gson();

    APIClient apiClient();

}
