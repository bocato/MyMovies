package br.com.zup.mymovies.view.activities;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import javax.inject.Inject;

import br.com.zup.mymovies.R;
import br.com.zup.mymovies.databinding.ActSearchResultsBinding;
import br.com.zup.mymovies.di.DaggerSearchComponent;
import br.com.zup.mymovies.model.SearchResult;
import br.com.zup.mymovies.view.BaseActivity;
import br.com.zup.mymovies.view.adapters.SearchResultAdapter;
import br.com.zup.mymovies.viewmodel.SearchViewModel;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public class SearchActivity extends BaseActivity {

    ActSearchResultsBinding bind;

    @Inject
    SearchViewModel viewModel;

    @Override
    protected int getContentLayoutId() {
        return R.layout.act_search_results;
    }

    @Override
    protected void initInjectors() {
        DaggerSearchComponent.builder()
                .appComponent(getWLApplication().getAppComponent())
                .build().inject(this);
    }

    @Override
    protected void initBinding() {
        bind = (ActSearchResultsBinding) getBinding();
        bind.setSearch(viewModel);

        setSupportActionBar(bind.toolbar);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(query + " results");

            viewModel.searchMovie(query).observe(this, searchResult -> {
                if (searchResult != null) {
                    bind.rvSearchResults.setAdapter(new SearchResultAdapter(searchResult.getResults()));
                    bind.rvSearchResults.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
                }
            });
        }
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

}
