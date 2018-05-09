package com.mmxb.hybridmodule.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

/**
 * 进度条加载动画
 * <p>
 * Created by xueying on 2018/5/9.
 */

public class AnimationUtil {

    /**
     * progressBar 加载动画
     */
    public static void startUploadingAnimation(ProgressBar progressBar, int currentProgress, int newProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", currentProgress, newProgress);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(300);
        animator.start();
    }

    /**
     * progressBar 消失动画
     */
    public static void startDismissingAnimaiton(final ProgressBar progressBar, int currentProgress) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(progressBar, "alpha", 1.0f, 0.0f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(1000);
        // todo 组合

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
