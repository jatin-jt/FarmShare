package com.noobsquad.farmshare.testfiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.noobsquad.farmshare.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Clos";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    private static final String TAG = "ContentFragment";
    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    public static ContentFragment newInstance(int resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int saplingLevel = sharedPref.getInt("sapling_level",5);
        mImageView = rootView.findViewById(R.id.iv_progress);
        Log.d(TAG, "onCreateView: "+saplingLevel);
        if(saplingLevel == 5) {
            mImageView.setImageResource(R.drawable.ic_sapling4);
        } else {
            mImageView.setImageResource(R.drawable.ic_sapling6);
        }
        ShimmerFrameLayout container2 =
                (ShimmerFrameLayout)rootView.findViewById(R.id.shimmer_view_container2);
        container2.startShimmer();
        ShimmerFrameLayout container1 =
                (ShimmerFrameLayout)rootView.findViewById(R.id.shimmer_view_container1);
        container1.startShimmer();
        return rootView;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}