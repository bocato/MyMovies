package br.com.zup.mymovies.service;

import br.com.zup.mymovies.model.OmdbVideoFull;
import br.com.zup.mymovies.model.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rafaelneiva on 02/02/18.
 */

public interface OMDBService {

    @GET(" ")
    Call<SearchResult> searchMovie(@Query("s") String query);

    @GET(" ")
    Call<OmdbVideoFull> getMovieById(@Query("i") String omdbId);

}
