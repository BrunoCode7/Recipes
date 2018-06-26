package com.example.android.recipes.fragments;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.recipes.R;
import com.example.android.recipes.RecipesSteps;
import com.example.android.recipes.models.Steps;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Baraa Hesham on 6/18/2018.
 */
public class VideoFragment extends Fragment{

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public VideoFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.video_fragment_layout,container,false);
        mViewPager = view.findViewById(R.id.container);
        Bundle bundle = new Bundle();
        bundle.putInt("size", getArguments().getInt("size"));
        bundle.putParcelableArrayList("steps", getArguments().getParcelableArrayList("steps"));
        RecipesSteps.PlaceholderFragment fragment = new RecipesSteps.PlaceholderFragment();
        fragment.setArguments(bundle);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), bundle);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation== Configuration.ORIENTATION_PORTRAIT){
            mViewPager.setCurrentItem(getArguments().getInt("index"));
        }else {

        }
        return view;
    }

    public void setViewPager(int position){

        mViewPager.setCurrentItem(position);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private Bundle bundle;
        RecipesSteps.PlaceholderFragment fragment;
        SharedPreferences pagerSharedPreferences;
        SharedPreferences.Editor pagerEditor;

        public SectionsPagerAdapter(FragmentManager fm, Bundle data) {
            super(fm);
            this.bundle = data;
        }


        @Override
        public Fragment getItem(int position) {

            fragment = new RecipesSteps.PlaceholderFragment();
            ArrayList<Steps> steps = bundle.getParcelableArrayList("steps");
            String description = steps.get(position).getDescription();
            String thumbnailURL = steps.get(position).getThumbnailURL();
            String shortDescription = steps.get(position).getShortDescription();
            String url = steps.get(position).getVideoURL();
            Bundle data = new Bundle();
            data.putString("short_description",shortDescription);
            data.putString("description", description);
            data.putString("thumbnailURL", thumbnailURL);
            data.putString("url", url);
            data.putInt("page_number",position);
            pagerSharedPreferences= getActivity().getPreferences(MODE_PRIVATE);
            pagerEditor=pagerSharedPreferences.edit();
            pagerEditor.putLong("exoplayer_current_position",0);
            pagerEditor.apply();
            fragment.setArguments(data);
            return fragment;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            fragment=(RecipesSteps.PlaceholderFragment) super.instantiateItem(container, position);
            return fragment;
        }

        @Override
        public int getCount() {
            return bundle.getInt("size");
        }
    }
}
