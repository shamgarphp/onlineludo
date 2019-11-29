package com.smart.ludoclassic.Fonts;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/*This class is used for to set the font for text views by using the class name*/
public class HelveticaMediumEditText extends EditText {

    public HelveticaMediumEditText(Context context) {
        super(context);
        init();
    }

    public HelveticaMediumEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HelveticaMediumEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*setting font type to the textview */
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/helvmn.ttf");
        setTypeface(tf);
    }

}
