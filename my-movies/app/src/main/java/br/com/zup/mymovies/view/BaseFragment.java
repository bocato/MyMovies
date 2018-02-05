package br.com.zup.mymovies.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    ViewDataBinding dataBinding;

    protected abstract int getFragmentLayout();

    protected abstract void initInjectors();

    protected abstract void initBinding();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false);
        initInjectors();
        initBinding();
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public ViewDataBinding getDataBinding() {
        return dataBinding;
    }
}
