package br.com.zup.mymovies.view.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import br.com.zup.mymovies.R;
import br.com.zup.mymovies.databinding.ActMyMoviesBinding;
import br.com.zup.mymovies.view.BaseActivity;
import br.com.zup.mymovies.viewmodel.MyMoviesViewModel;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public class MyMoviesActivity extends BaseActivity {

    ActMyMoviesBinding bind;

    @Inject
    MyMoviesViewModel viewModel;

    @Override
    protected int getContentLayoutId() {
        return R.layout.act_my_movies;
    }

    @Override
    protected void initInjectors() {

    }

    @Override
    protected void initBinding() {
        bind = (ActMyMoviesBinding) getBinding();
        bind.setMyMovie(viewModel);

        setSupportActionBar(bind.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_my_movies, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Transition transition = new AutoTransition();
                transition.setDuration(100);
                TransitionManager.beginDelayedTransition(bind.toolbar, transition);
                item.expandActionView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
