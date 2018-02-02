package br.com.zup.mymovies.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import br.com.zup.mymovies.MyMoviesApplication;

public abstract class BaseActivity extends AppCompatActivity {

    ViewDataBinding dataBinding;

    private static final int PERMISSIONS_REQUEST_CODE = 1001;
    private RequestListener mRequestCallback;

    protected abstract int getContentLayoutId();

    protected abstract void initInjectors();

    protected abstract void initBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getContentLayoutId());
        initInjectors();
        initBinding();
    }

    public MyMoviesApplication getWLApplication() {
        return ((MyMoviesApplication) getApplication());
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    protected ViewDataBinding getBinding() {
        return dataBinding;
    }

    /**
     * @param permission Need Manifest.permission
     * @return true if permission is granted, false otherwise
     */
    public boolean verifyPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Example
     * if (!verifyPermission(Manifest.permission.CAMERA)) {
     * requestPermission(Manifest.permission.CAMERA, new RequestListener() {
     *
     * @param permission Permission to request
     * @param callback   @RequestListener to provide callback for request
     * @Override public void onRequestCallback(boolean granted) {
     * Toast.makeText(MainActivity.this, granted ? "Deu" : "Deu não", Toast.LENGTH_SHORT).show();
     * }
     * });
     * }
     */
    public void requestPermission(String permission, RequestListener callback) {
        this.mRequestCallback = callback;
        ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mRequestCallback.onRequestCallback(true);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    mRequestCallback.onRequestCallback(false);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public interface RequestListener {
        void onRequestCallback(boolean granted);
    }

    public void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}
