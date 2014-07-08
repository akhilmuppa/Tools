package com.osi.tools.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotolightTextView extends TextView{

	public RobotolightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotolightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotolightTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Light.ttf");
            setTypeface(tf);
        }
    }


}
