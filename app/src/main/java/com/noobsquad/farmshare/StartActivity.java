package com.noobsquad.farmshare;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.noobsquad.farmshare.Models.CropPlan;
import com.noobsquad.farmshare.testfiles.ContentFragment;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

public class StartActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {

    private static final String TAG = "StartActivity";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.ic_launcher_background;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("YourMainActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();
        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();

        contentFragment = ContentFragment.newInstance(R.drawable.ic_launcher_background);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });


        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
        Intent intent = new Intent(this,GroupLandList.class);
        //startActivity(intent);

    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.ic_close_black_24dp);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem("Flipper", R.drawable.ic_launcher_foreground);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem("GroupDetails", R.drawable.ic_group_black_24dp);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem("MarkLand", R.drawable.ic_land_black_24dp);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem("News", R.drawable.ic_launcher_foreground);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem("CropPlan", R.drawable.ic_launcher_foreground);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem("SignOut", R.drawable.ic_launcher_foreground);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem("Progress", R.drawable.ic_launcher_foreground);
        list.add(menuItem7);

    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
        /*if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.drawable.ic_launcher_foreground:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
        Log.d("test",Integer.toString(topPosition));

        this.res = this.res == R.drawable.ic_launcher_background ? R.drawable.ic_launcher_foreground : R.drawable.ic_launcher_background;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        return contentFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        Log.d("test","here");
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case "Flipper":
                Intent intent = new Intent(this,GroupLandList.class);
                startActivity(intent);
                return screenShotable;
            case "GroupDetails":
                Intent intent2 = new Intent(this,GroupDetailsActivity.class);
                startActivity(intent2);
                return screenShotable;
            case "MarkLand":
                Intent intent3 = new Intent(this,MapsActivity.class);
                startActivity(intent3);
                return screenShotable;
            case "SignOut":
                Log.d(TAG, "onSwitch: sign out");
                IdentityManager.getDefaultIdentityManager().signOut();
                startActivity(new Intent(this, AuthActivity.class));
            case "News":
                Intent intent4 = new Intent(this,NewsActivity.class);
                startActivity(intent4);
                return screenShotable;
            case "CropPlan":
                Intent intent5 = new Intent(this,CropPlanActivity.class);
                startActivity(intent5);
                return screenShotable;
            case "Progress":
                Intent intent6 = new Intent(this,ProgressActivity.class);
                startActivity(intent6);
                return screenShotable;
            default:
                return replaceFragment(screenShotable, position);
        }
    }

    @Override
    public void disableHomeButton() {
        //getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        //().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
}
