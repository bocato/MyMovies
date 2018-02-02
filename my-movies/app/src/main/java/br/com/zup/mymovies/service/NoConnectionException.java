package br.com.zup.mymovies.service;

import java.io.IOException;

import br.com.zup.mymovies.MyMoviesApplication;
import br.com.zup.mymovies.R;

/**
 * Created by rafaelneiva on 28/11/17.
 */

class NoConnectionException extends IOException {

    @Override
    public String getMessage() {
        return MyMoviesApplication.getInstance().getString(R.string.no_connection_exception_msg);
    }
}
