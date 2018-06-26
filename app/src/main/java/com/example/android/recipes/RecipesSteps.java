//package com.example.android.recipes;
//
//import android.content.Context;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v4.view.PagerAdapter;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.widget.TextView;
//
//import com.example.android.recipes.models.Steps;
//import com.google.android.exoplayer2.ExoPlayer;
//import com.google.android.exoplayer2.ExoPlayerFactory;
//import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
//import com.google.android.exoplayer2.extractor.ExtractorsFactory;
//import com.google.android.exoplayer2.source.ExtractorMediaSource;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.source.SingleSampleMediaSource;
//import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
//import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
//import com.google.android.exoplayer2.trackselection.TrackSelector;
//import com.google.android.exoplayer2.ui.PlayerView;
//import com.google.android.exoplayer2.upstream.BandwidthMeter;
//import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
//import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
//
//import java.util.ArrayList;
//
//public class RecipesSteps extends AppCompatActivity {
//
//    /**
//     * The {@link android.support.v4.view.PagerAdapter} that will provide
//     * fragments for each of the sections. We use a
//     * {@link FragmentPagerAdapter} derivative, which will keep every
//     * loaded fragment in memory. If this becomes too memory intensive, it
//     * may be best to switch to a
//     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
//     */
//    private SectionsPagerAdapter mSectionsPagerAdapter;
//
//    /**
//     * The {@link ViewPager} that will host the section contents.
//     */
//    private ViewPager mViewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recipes_steps);
//        ArrayList<Steps> steps=getIntent().getParcelableArrayListExtra("allsteps");
//        int index=getIntent().getIntExtra("index",0);
//        Bundle bundle=new Bundle();
//        bundle.putParcelableArrayList("steps",steps);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(),steps,index);
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//        mViewPager.setCurrentItem(index-1);
//
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_recipes_steps, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
////    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
////        private static final String ARG_SECTION_NUMBER = "section_number";
////
////        public PlaceholderFragment() {
////        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
////        public static PlaceholderFragment newInstance(int sectionNumber) {
////            PlaceholderFragment fragment = new PlaceholderFragment();
////            Bundle args = new Bundle();
////            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
////            fragment.setArguments(args);
////            return fragment;
////        }
////        String mdescription;
////        @Override
////        public void onCreate(@Nullable Bundle savedInstanceState) {
////            super.onCreate(savedInstanceState);
////            Bundle bundle=getArguments();
////            Steps steps= bundle.getParcelable("steps");
////            mdescription=steps.getDescription();
////
////        }
////
////        @Override
////        public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                                 Bundle savedInstanceState) {
////            View rootView = inflater.inflate(R.layout.fragment_recipes_steps, container, false);
////            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
////            textView.setText(mdescription);
////            return rootView;
////        }
////    }
//
//    /**
//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    public class SectionsPagerAdapter extends PagerAdapter {
//        private Context context;
//        private LayoutInflater layoutInflater;
//        private ArrayList<Steps> steps;
//        private int index;
//        private PlayerView mPlayerView;
//        private TextView textView;
//        private ExoPlayer exoPlayer;
//
//        public SectionsPagerAdapter(Context context, ArrayList<Steps> steps, int index){
//            this.context=context;
//            this.steps=steps;
//            this.index=index;
//        }
////        public SectionsPagerAdapter(FragmentManager fm,Bundle data) {
////            super(fm);
////            bundle=data;
////        }
//
////        @Override
////        public Fragment getItem(int position) {
////            // getItem is called to instantiate the fragment for the given page.
////            // Return a PlaceholderFragment (defined as a static inner class below).
////            return PlaceholderFragment.newInstance(position + 1);
////                    PlaceholderFragment placeholderFragment = new PlaceholderFragment();
////                    placeholderFragment.setArguments(bundle);
////                    return placeholderFragment;
////
////        }
//
//        @Override
//        public int getCount() {
//            // Show 3 total pages.
//            return steps.size();
//        }
//
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view=layoutInflater.inflate(R.layout.fragment_recipes_steps,null);
//            textView=view.findViewById(R.id.section_label);
//            mPlayerView=view.findViewById(R.id.player_view);
//            BandwidthMeter bandwidthMeter=new DefaultBandwidthMeter();
//            TrackSelector trackSelector=new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
//            exoPlayer= ExoPlayerFactory.newSimpleInstance(getApplicationContext(),trackSelector);
//            Uri uri=Uri.parse(steps.get(position).getVideoURL());
//            DefaultHttpDataSourceFactory dataSourceFactory=new DefaultHttpDataSourceFactory("Recipes");
//            MediaSource mediaSource= new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//            mPlayerView.setPlayer(exoPlayer);
//            exoPlayer.prepare(mediaSource);
//            exoPlayer.setPlayWhenReady(true);
//            textView.setText(steps.get(position).getDescription());
//            ViewPager vp= (ViewPager)container;
//            vp.addView(view,0);
//            return view;
//        }
//
//        @Override
//        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//            return view==object;
//        }
//
//        @Override
//        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            ViewPager vp= (ViewPager) container;
//            View view=(View) object;
//            vp.removeView(view);
//        }
//    }
//}
package com.example.android.recipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.android.recipes.models.Recipe;
import com.example.android.recipes.models.Steps;
import com.example.android.recipes.fragments.ListFragment;
import com.example.android.recipes.utilities.OnStepClickListener;

import com.example.android.recipes.fragments.VideoFragment;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

public class RecipesSteps extends AppCompatActivity implements OnStepClickListener {


    ArrayList<Steps> steps;
    Recipe chosenRecipe;
    private Bundle bundleIndex;
    private ListFragment listFragment;
    private VideoFragment videoFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_steps);
        chosenRecipe = getIntent().getParcelableExtra("recipe");
        String name = chosenRecipe.getName();
        steps = chosenRecipe.getSteps();
        getSupportActionBar().setTitle(name);
        listFragment = new ListFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("steps1", steps);
        bundle1.putParcelable("chosenrecipe1", chosenRecipe);


        listFragment.setArguments(bundle1);
        fragmentManager = getSupportFragmentManager();
        final int numberOfViews = steps.size();
        bundleIndex = new Bundle();
        bundleIndex.putInt("size", numberOfViews);
        bundleIndex.putParcelableArrayList("steps", steps);
        videoFragment = new VideoFragment();
        videoFragment.setArguments(bundleIndex);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (!this.getResources().getBoolean(R.bool.is_tablet)) {
            fragmentTransaction.add(R.id.main_content, listFragment, "lfragment");
            fragmentTransaction.commit();
        } else {
            RecipesSteps.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            fragmentTransaction.add(R.id.main_content, listFragment, "lfragment");
            fragmentTransaction.add(R.id.video_content, videoFragment, "vfragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void OnStepSelected(int position) {

        if (!this.getResources().getBoolean(R.bool.is_tablet)) {
            Intent intent = new Intent(this, VideoFragmentActivity.class);
            bundleIndex.putInt("index", position);
            intent.putExtras(bundleIndex);
            startActivity(intent);
        } else {
            VideoFragment videoFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag("vfragment");
            videoFragment.setViewPager(position);
        }

    }



    public static class PlaceholderFragment extends Fragment {

        ExoPlayer exoPlayer;
        View rootView;
        private PlayerView mPlayerView;
        private TextView textView;
        private TextView vPlaceHolder;
        private Boolean isStarted = false;
        private Boolean isVisible = false;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;


        public PlaceholderFragment() {
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_recipes_steps, container, false);

            return rootView;
        }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            textView = view.findViewById(R.id.steps_description);
            mPlayerView = view.findViewById(R.id.player_view);
            vPlaceHolder = view.findViewById(R.id.video_place_holder);
            sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

            int orientation = this.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (getArguments().getString("url").equals("")) {
                    vPlaceHolder.setVisibility(View.VISIBLE);
                    mPlayerView.setVisibility(View.GONE);
                    textView.setText(getArguments().getString("description"));


                } else {
                    textView.setText(getArguments().getString("description"));
                }
            } else {

                if (getArguments().getString("url").equals("")) {
                    if (!this.getResources().getBoolean(R.bool.is_tablet)) {
                        vPlaceHolder.setVisibility(View.VISIBLE);
                        mPlayerView.setVisibility(View.GONE);
                    } else {
                        vPlaceHolder.setVisibility(View.VISIBLE);
                        mPlayerView.setVisibility(View.GONE);
                        textView.setText(getArguments().getString("description"));
                    }
                } else {
                    if (this.getResources().getBoolean(R.bool.is_tablet)) {
                        textView.setText(getArguments().getString("description"));
                    }

                }
            }


        }

        @Override
        public void onStart() {
            super.onStart();
            isStarted = true;
            if (isVisible && isStarted && exoPlayer == null) {
                viewDidAppear();
            } else if (exoPlayer != null) {
                exoPlayer.setPlayWhenReady(true);
            }

        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            isVisible = isVisibleToUser;
            if (isStarted && isVisible) {
                viewDidAppear();
            } else if (isStarted && rootView != null && exoPlayer != null) {

                exoPlayer.release();
                exoPlayer.stop();
                exoPlayer = null;
            }
        }

        public void viewDidAppear() {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            Uri uri = Uri.parse(getArguments().getString("url"));
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("Recipes");
            MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            exoPlayer.prepare(mediaSource);
            mPlayerView.setPlayer(exoPlayer);
            exoPlayer.seekTo(sharedPreferences.getLong("exoplayer_current_position", 0));
            exoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            super.onPause();
            isStarted = false;
            if (exoPlayer != null) {
                editor = sharedPreferences.edit();
                editor.putLong("exoplayer_current_position", exoPlayer.getCurrentPosition());
                editor.apply();
            }
        }


        @Override
        public void onStop() {
            super.onStop();
            if (exoPlayer != null) {
                exoPlayer.release();
                exoPlayer.stop();
                exoPlayer = null;
            }
        }
    }


}
