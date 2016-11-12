package codebusters.magic_eight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import codebusters.magic_eight.utility.FortuneTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fortune;
        TextView txtFortune = (TextView) findViewById(R.id.fortune_test);
        try {
             fortune = new FortuneTask().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            fortune = "Fortunes can't be read right now.";
        }
        txtFortune.setText(fortune);

    }
}
