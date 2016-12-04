package codebusters.magic_eight.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import codebusters.magic_eight.R;
import codebusters.magic_eight.user.CreateUserFragment;

/**
 * Created by alansimon on 2016-12-04.
 */

public class HomeActivity extends AppCompatActivity {

    public final String TAG = "HomeActivity";

    private Fragment fr;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "");
        Boolean firstTime = true;
        sharedPref.getBoolean("new", firstTime);
        Log.d(TAG, firstTime.toString());
        if (firstTime) {
            //Do database generate here.
            //after take them to user fragment.
            Log.d(TAG, "b4 frag");
            fr = new CreateUserFragment();
            fm = getFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.frlt_fragment_container_home, fr);
            ft.commit();
        } else {
            //ignore db generate here
            //head to main fragment
            //fr = new ();
//            fm = getFragmentManager();
//            ft = fm.beginTransaction();
//            ft.replace(R.id.frlt_fragment_container_home, fr);
        }


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    //Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                    fr = new HomeFragment_();
//                    fm = getFragmentManager();
//                    ft = fm.beginTransaction();
//                    ft.replace(R.id.frlt_fragment_container_home, fr);
//                    ft.commit();
                } else if (tabId == R.id.tab_magic_eight) {
//                    fr = new ProfileFragment_();
//                    fm = getFragmentManager();
//                    ft = fm.beginTransaction();
//                    ft.replace(R.id.frlt_fragment_container_home, fr);
//                    ft.commit();
                } else if (tabId == R.id.tab_horoscopes) {
//                    fr = new ForumsFragment_();
//                    fm = getFragmentManager();
//                    ft = fm.beginTransaction();
//                    ft.replace(R.id.frlt_fragment_container_home, fr);
//                    ft.commit();
                } else if (tabId == R.id.tab_settings) {
//                    fr = new PostingsFragment_();
//                    fm = getFragmentManager();
//                    ft = fm.beginTransaction();
//                    ft.replace(R.id.frlt_fragment_container_home, fr);
//                    ft.commit();
                }
            }
        });

    }
}
