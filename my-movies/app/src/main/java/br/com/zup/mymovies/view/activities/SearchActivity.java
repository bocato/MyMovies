package br.com.zup.mymovies.view.activities;

import android.app.SearchManager;
import android.content.Intent;

import br.com.zup.mymovies.R;
import br.com.zup.mymovies.databinding.ActSearchResultsBinding;
import br.com.zup.mymovies.di.DaggerSearchComponent;
import br.com.zup.mymovies.view.BaseActivity;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public class SearchActivity extends BaseActivity {

    ActSearchResultsBinding bind;

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

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            bind.text.setText(query);
        }
    }

}
