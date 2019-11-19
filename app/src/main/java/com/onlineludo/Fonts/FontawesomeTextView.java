package com.onlineludo.Fonts;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/*This class is used for to set the font for text views by using the class name*/
public class FontawesomeTextView extends TextView {

    public FontawesomeTextView(Context context) {
        super(context);
        init();
    }

    public FontawesomeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontawesomeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*setting font type to the textview */
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/fontawesome.ttf");
        setTypeface(tf);
    }

}
