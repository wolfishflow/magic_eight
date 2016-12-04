package codebusters.magic_eight.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alansimon on 2016-12-04.
 */

public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        Boolean firstTime = true;
        sharedPref.getBoolean("new", firstTime);
        if (firstTime){
            //Do database generate here.
            //after take them to user fragment.
        }else{
            //ignore db generate here
            //head to main fragment
        }


    }
}
