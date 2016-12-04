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
import codebusters.magic_eight.dao.DatabaseConnector;
import codebusters.magic_eight.settings.SettingsFragment;
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

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_horoscopes) {
                    DatabaseConnector dbConnector = new DatabaseConnector(getApplicationContext());
                    Boolean hasStuff = dbConnector.containsSomething();
                    if (!hasStuff) {
                        //Do database generate here.
                        //after take them to user fragment.
                        Log.d(TAG, "b4 frag");
                        fr = new CreateUserFragment();
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.frlt_fragment_container_home, fr);
                        ft.commit();
                    } else {
                        Log.d(TAG, "not first time");
                        fr = new HomeFragment();
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.frlt_fragment_container_home, fr);
                        ft.commit();
                    }
                } else if (tabId == R.id.tab_magic_eight) {
                }  else if (tabId == R.id.tab_settings) {
                    fr = new SettingsFragment();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frlt_fragment_container_home, fr);
                    ft.commit();
                }
            }
        });

    }
}
