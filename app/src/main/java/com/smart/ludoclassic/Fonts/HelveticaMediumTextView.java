package com.smart.ludoclassic.Fonts;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/*This class is used for to set the font for text views by using the class name*/
public class HelveticaMediumTextView extends TextView {

    public HelveticaMediumTextView(Context context) {
        super(context);
        init();
    }

    public HelveticaMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HelveticaMediumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*setting font type to the textview */
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/helveticabold.ttf");
        setTypeface(tf);
    }

}
