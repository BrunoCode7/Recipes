package com.example.android.recipes;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.android.recipes.fragments.VideoFragment;

public class VideoFragmentActivity extends AppCompatActivity {


    private VideoFragment videoFragment;
    private Bundle bundle;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_fragment);
        bundle=getIntent().getExtras();
        videoFragment=new VideoFragment();
        videoFragment.setArguments(bundle);
        fragmentManager=getSupportFragmentManager();

        if (savedInstanceState==null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.video_fragment_activity, videoFragment, "vfragment");
            fragmentTransaction.commit();}



        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation==Configuration.ORIENTATION_LANDSCAPE){
            getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


    }
}
