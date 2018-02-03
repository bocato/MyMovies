package br.com.zup.mymovies.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.zup.mymovies.BR;

/**
 * Created by rafaelneiva on 03/02/18.
 */

public class SearchResult extends BaseObservable {

    @SerializedName("Search")
    private List<OmdbVideoBasic> results;

    @SerializedName("totalResults")
    private int totalResults;

    @Bindable
    public List<OmdbVideoBasic> getResults() {
        return results;
    }

    public void setResults(List<OmdbVideoBasic> results) {
        this.results = results;
        notifyPropertyChanged(BR.results);
    }

    @Bindable
    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
        notifyPropertyChanged(BR.totalResults);
    }
}
