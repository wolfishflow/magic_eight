package codebusters.magic_eight.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import codebusters.magic_eight.R;
import codebusters.magic_eight.dao.DatabaseConnector;
import codebusters.magic_eight.settings.SettingsFragment;
import codebusters.magic_eight.user.CreateUserFragment;

/**
 * Created by alansimon on 2016-12-04.
 */

public class HomeActivity extends AppCompatActivity implements SensorEventListener {

    public final String TAG = "HomeActivity";

    private Fragment fr;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private long lastUpdate;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void getAccelerometer(SensorEvent event) {
        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelarationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();
        if (accelarationSquareRoot >= 8 && actualTime > lastUpdate + 2000) {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;

            //switch to Magic8 ball tab here
            String[] magic = getResources().getStringArray(R.array.responses);
            String response = magic[(int)(Math.random() * magic.length)];
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
