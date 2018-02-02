package br.com.zup.mymovies.viewmodel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.EditText;

import br.com.zup.multistatelayout.MultiStateLayout;
import br.com.zup.mymovies.R;

/**
 * Created by rafaelneiva on 30/10/17.
 */

public abstract class
BaseViewModel extends ViewModel implements LifecycleOwner {

    public ObservableBoolean showLoading = new ObservableBoolean(false);

    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    protected BaseViewModel() {
        startListening();
    }

    public void stopListening() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    public void startListening() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @BindingAdapter("animatedVisibility")
    public static void setVisibility(final View view, final int visibility) {
        final float initialAlpha = view.getAlpha();
        // Were we animating before? If so, what was the visibility?
        Integer endAnimVisibility = (Integer) view.getTag(R.id.finalVisibility);
        int oldVisibility = endAnimVisibility == null ? view.getVisibility() : endAnimVisibility;

        if (oldVisibility == visibility) {
            // just let it finish any current animation.
            return;
        }

        boolean isVisible = oldVisibility == View.VISIBLE;
        boolean willBeVisible = visibility == View.VISIBLE;

        view.setVisibility(View.VISIBLE);
        float startAlpha = isVisible ? initialAlpha : 0f;
        if (endAnimVisibility != null) {
            startAlpha = view.getAlpha();
        }
        float endAlpha = willBeVisible ? initialAlpha : 0f;

        // Now create an animator
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, startAlpha, endAlpha);
        alpha.setAutoCancel(true);

        alpha.addListener(new AnimatorListenerAdapter() {
            private boolean isCanceled;

            @Override
            public void onAnimationStart(Animator anim) {
                view.setTag(R.id.finalVisibility, visibility);
            }

            @Override
            public void onAnimationCancel(Animator anim) {
                isCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator anim) {
                view.setTag(R.id.finalVisibility, null);
                if (!isCanceled) {
                    view.setAlpha(initialAlpha);
                    view.setVisibility(visibility);
                }
            }
        });
        alpha.start();
    }

    @BindingAdapter("bubbleVisibility")
    public static void setBubbleVisibility(final View view, final int visibility) {
        final float initialAlpha = view.getAlpha();
        // Were we animating before? If so, what was the visibility?
        Integer endAnimVisibility = (Integer) view.getTag(R.id.finalVisibility);
        int oldVisibility = endAnimVisibility == null ? view.getVisibility() : endAnimVisibility;

        if (oldVisibility == visibility) {
            // just let it finish any current animation.
            return;
        }

        boolean isVisible = oldVisibility == View.VISIBLE;
        boolean willBeVisible = visibility == View.VISIBLE;

        view.setVisibility(View.VISIBLE);
        float startAlpha = isVisible ? initialAlpha : 0f;
        if (endAnimVisibility != null) {
            startAlpha = view.getAlpha();
        }
        float endAlpha = willBeVisible ? initialAlpha : 0f;

        // Now create an animator
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, startAlpha, endAlpha);
        alpha.setAutoCancel(true);

        alpha.addListener(new AnimatorListenerAdapter() {
            private boolean isCanceled;

            @Override
            public void onAnimationStart(Animator anim) {
                view.setTag(R.id.finalVisibility, visibility);
            }

            @Override
            public void onAnimationCancel(Animator anim) {
                isCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator anim) {
                view.setTag(R.id.finalVisibility, null);
                if (!isCanceled) {
                    view.setAlpha(initialAlpha);
                    view.setVisibility(visibility);
                }
            }
        });
        alpha.start();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1, 1.5f, 1);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 1, 1.5f, 1);

        anim1.setDuration(400);
        anim1.setInterpolator(new AnticipateOvershootInterpolator());
        anim2.setDuration(400);
        anim2.setInterpolator(new AnticipateOvershootInterpolator());

        anim1.start();
        anim2.start();
    }

    @BindingAdapter("msl_state")
    public static void setState(MultiStateLayout multiStateLayout, MultiStateLayout.State state) {
        multiStateLayout.setState(state);
    }

    @BindingAdapter("focused")
    public static void setFocused(EditText editText, boolean hasFocus) {
        if (hasFocus) {
            editText.requestFocus();
        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
