package com.smart.ludoclassic;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class Utilites {

    // Singleton
    private static Utilites sharedInstance = null;

    public Activity currentActivityDetails;
    public int menuSelectedPosition = 0;
    public int oldmenuSelectedPosition = 0;
    public final String VEHICLE = "Vehicle";
    public final String EQUIPMENT = "Equipment";


    public Utilites() {
    }

    public static Utilites getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Utilites();
        }
        return sharedInstance;
    }

    /*To display the app version on the screen */
    public String getAppVersion(Context context) {
        // Initializing Variable with null.
        PackageInfo pInfo;
        // Block for Handling Exception.
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            // Returning Version Name Of Package.
            return pInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            // Printing Exception that was handled.
            e.printStackTrace();
        }

        return "0.1";
    }

    // The public static function which can be called from other classes
    public  void darkenStatusBar(Activity activity, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            activity.getWindow().setStatusBarColor(
                    darkenColor(
                            ContextCompat.getColor(activity, color)));
        }

    }


    // Code to darken the color supplied (mostly color of toolbar)
    private static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }


    /*This method is used to set hover effects for buttons */
    public void setHoverForButtons(final View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                try {
                    // when button touched
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        view.setAlpha(0.6f);
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        //when finger was lifted
                        view.setAlpha(1f);
                    }
                }
                catch (Exception ignored)
                {
                }
                return false;
            }
        });
    }

    public  boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int)px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;

        } else {
            return false;
        }

    }

    public void showSpinner(RelativeLayout spinnerLyt){
            spinnerLyt.setVisibility(View.VISIBLE);
            spinnerLyt.setClickable(false);
    }

    public void hideSpinner(RelativeLayout spinnerLyt){
        spinnerLyt.setVisibility(View.GONE);
        spinnerLyt.setClickable(true);
    }

    //    forward intent transition for screens
    public void moveForwardTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.pull_from_right, R.anim.push_to_left);
    }
  //    forward intent transition for screens
    public void slideup(Activity activity) {
        activity.overridePendingTransition(R.anim.slidefromdown_anim, R.anim.slideup);
    }

    //    backward intent transition for screens
    public void moveBackTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.pull_from_left, R.anim.push_to_right);
    }

    // list open transition
    public void openListTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.pull_from_right, R.anim.push_to_left);
    }

    // list open transition
    public void InternalScreenTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.pull_from_right, R.anim.push_to_left);
    }

    public boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
