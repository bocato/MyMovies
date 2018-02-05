package br.com.zup.mymovies.view.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuInflater;

import javax.inject.Inject;

import br.com.zup.multistatelayout.MultiStateLayout;
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

        getWindow().setExitTransition(new Explode());
        getWindow().setReenterTransition(new Explode());

        setSupportActionBar(bind.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        bind.toolbar.setNavigationOnClickListener(view -> onBackPressed());

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(query + " results");
            }

            viewModel.searchMovie(query).observe(this, searchResult -> {
                if (searchResult != null && searchResult.getResults() != null && searchResult.getResults().size() > 0) {
                    setupListAdapter(searchResult);
                } else {
                    bind.msl.setState(MultiStateLayout.State.EMPTY);
                }
            });
        }
    }

    private void setupListAdapter(SearchResult searchResult) {
        SearchResultAdapter adapter = new SearchResultAdapter(searchResult.getResults());
        bind.rvSearchResults.setAdapter(adapter);
        bind.rvSearchResults.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));

        adapter.setListener((omdbIds, clickedPos, sharedElement) -> {
            Intent intent = new Intent(SearchActivity.this, MovieDetailActivity.class);
            intent.putStringArrayListExtra(MovieDetailActivity.ID_LIST_KEY, omdbIds);
            intent.putExtra(MovieDetailActivity.CLICKED_POSITION_KEY, clickedPos);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SearchActivity.this, sharedElement, getString(R.string.trans_name_movie_banner));
            startActivity(intent, options.toBundle());
        });
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