package com.smart.ludoclassic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

// activity for side menu.
@SuppressWarnings("All")
public class SideMenuActivity extends AppCompatActivity {

    // declaring layouts
    RelativeLayout mLeftDrawer,profileLayout;
    private DrawerLayout mDrawerLayout;
    public RelativeLayout mForwardLayout;
    ListView mSidemenuListview;
    private Activity currentActivity;
    int handlerDuration = 280;  // duration to navigate to other screen as menu closes.
    MenuListViewAdapter menuListViewAdapter;
    ImageView notification;
    TextView nameTxt,mobileTxt;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        Utilites.getSharedInstance().currentActivityDetails = this;
        // intializing current activity.
        currentActivity = this;

        // intializing items.
        mLeftDrawer       =  findViewById(R.id.left_drawer);
        mDrawerLayout     =  findViewById(R.id.drawer_layout);
        //inflating listview to show locations list
        mSidemenuListview = findViewById(R.id.menu_listview);
        notification = findViewById(R.id.notification);

        profileLayout       = findViewById(R.id.profile_layout);
        nameTxt       = findViewById(R.id.nameTxt);
        mobileTxt       = findViewById(R.id.mobileTxt);

        // to stop the opening of dlayout on swipe gesture.
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        sharedpreferences = getSharedPreferences("Userdetails",
                Context.MODE_PRIVATE);

        nameTxt.setText(""+sharedpreferences.getString("username",""));
        mobileTxt.setText(""+sharedpreferences.getString("mobile",""));
        // setting adapter to listview
        menuListViewAdapter = new MenuListViewAdapter(this,0);
        mSidemenuListview.setAdapter(menuListViewAdapter);


       /* profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),UpdateProfile.class);
                startActivity(i);
            }
        });*/

/*
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to close drawer before navigating
                handlerCloseDrawer();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!(currentActivity instanceof Notifications)) {
                            Intent navToSync = new Intent(currentActivity, Notifications.class);

                            if(!(currentActivity instanceof HomeActivity)) {
                                navToSync.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                            }
                            Utilities.getSharedInstance().moveForwardTransition(currentActivity);
                            startActivity(navToSync);
                        }

                    }
                },handlerDuration);
            }
        });
*/

       /* findViewById(R.id.footerlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to close drawer before navigating
                handlerCloseDrawer();
                // navigating to signout alert
                Utilites.getSharedInstance().showSignOutDialog(currentActivity);
            }
        });*/
        // listener method on drawer layout. to notify on various actions
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // setting x for transition of the screen when side menu is opened.
//                mForwardLayout.setX(slideOffset * drawerView.getWidth());
                // to bring the child to front.
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mSidemenuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {


                // to close side menu.
                mDrawerLayout.closeDrawers();

                // checking if old selected position is equal to current selected position
                // intialting the current selected
                Utilites.getSharedInstance().menuSelectedPosition = position;


                switch (position) {

                    case 0:
                        // to close drawer before navigating
                        handlerCloseDrawer();
                        // intializing oldmenu position
                        Utilites.getSharedInstance().oldmenuSelectedPosition = position;

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!(currentActivity instanceof LudoMainActivity)) {
                                        Intent navToHome = new Intent(currentActivity, LudoMainActivity.class);
                                        navToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(navToHome);
                                        finish();
                                        Utilites.getSharedInstance().moveForwardTransition(currentActivity);
                                    }

                                }
                            }, handlerDuration);

                        break;
                    case 1:
                        // to close drawer before navigating
                        handlerCloseDrawer();
                        // intializing oldmenu position
                        Utilites.getSharedInstance().oldmenuSelectedPosition = position;

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!(currentActivity instanceof UserProfileActivity)) {
                                        Intent navToHome = new Intent(currentActivity, UserProfileActivity.class);
                                        navToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(navToHome);
                                        finish();
                                        Utilites.getSharedInstance().moveForwardTransition(currentActivity);
                                    }

                                }
                            }, handlerDuration);

                        break;

                    /*case 2:
                        // to close drawer before navigating
                        handlerCloseDrawer();
                        Utilites.getSharedInstance().oldmenuSelectedPosition = position;

                              new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!(currentActivity instanceof NOCMapsActivity)) {
                                        Intent navToSync = new Intent(currentActivity, NOCMapsActivity.class);

                                        if (!(currentActivity instanceof MainActivity)) {
                                            navToSync.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            finish();
                                        }
                                        Utilites.getSharedInstance().moveForwardTransition(currentActivity);
                                        startActivity(navToSync);
                                    }

                                }
                            }, handlerDuration);


                        break;*/

                    default:
                        // to close side menu
                        mDrawerLayout.closeDrawers();

                        break;
                }
            }

        });
    }

    // method to be called when clicked on button.
    public  void openCloseMenu()
    {
        // hide keyboard if opened
        hideSoftKeyBoard();
        // check if drawer is opened
        if (mDrawerLayout.isDrawerOpen(mLeftDrawer))
        {
            // close drawers(menu)
            mDrawerLayout.closeDrawers();
        }
        else {
            // open drawer
            mDrawerLayout.openDrawer(mLeftDrawer);
        }
    }

    private void handlerCloseDrawer(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utilites.getSharedInstance().moveForwardTransition(currentActivity);
            }
        }, 0);
    }
    // method to hide keyboard
    private void hideSoftKeyBoard(){
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
