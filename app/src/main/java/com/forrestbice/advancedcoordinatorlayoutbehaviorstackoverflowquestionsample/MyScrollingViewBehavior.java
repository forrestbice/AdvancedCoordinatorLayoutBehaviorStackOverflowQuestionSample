package com.forrestbice.advancedcoordinatorlayoutbehaviorstackoverflowquestionsample;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

class MyScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
    public static final String TAG = MyScrollingViewBehavior.class.getCanonicalName();

    public MyScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RelativeLayout ||
                super.layoutDependsOn(parent, child, dependency);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof RelativeLayout) {
            RelativeLayout bottomSheet = (RelativeLayout) parent.findViewById(R.id.bottom_sheet);
            int bottomSheetHeight = (bottomSheet.getTop() - child.getBottom());
            ViewCompat.offsetTopAndBottom(child, bottomSheetHeight);
        }
        super.onDependentViewChanged(parent, child, dependency);
        return false;
    }
}
