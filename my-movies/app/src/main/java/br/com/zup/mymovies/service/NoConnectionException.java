package br.com.zup.rwwhitelabel.service;

import java.io.IOException;

import br.com.zup.rwwhitelabel.R;
import br.com.zup.rwwhitelabel.WhiteLabelApplication;

/**
 * Created by rafaelneiva on 28/11/17.
 */

class NoConnectionException extends IOException {

    @Override
    public String getMessage() {
        return WhiteLabelApplication.getInstance().getString(R.string.no_connection_exception_msg);
    }
}
