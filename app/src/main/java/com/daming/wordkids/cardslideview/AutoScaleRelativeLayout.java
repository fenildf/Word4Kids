package com.daming.wordkids.cardslideview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.daming.wordkids.R;

/**
 * Created by dashu on 2016/12/27.
 */

public class AutoScaleRelativeLayout extends RelativeLayout {

    private static final String TAG = "Dashu AutoScaleRelativeLayout";
    private float widthHeightRate = 0.35f;

    public AutoScaleRelativeLayout(Context context) {
        this(context, null);
    }

    public AutoScaleRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScaleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.card, 0, 0);
        widthHeightRate = a.getFloat(R.styleable.card_widthHeightRate, widthHeightRate);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 调整高度
        int width = getMeasuredWidth();
        int height = (int) (width * widthHeightRate);
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = height;
        setLayoutParams(lp);
        setMeasuredDimension(width, height);
    }
}
