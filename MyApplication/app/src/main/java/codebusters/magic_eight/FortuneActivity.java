package codebusters.magic_eight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import codebusters.magic_eight.utility.FortuneTask;

public class FortuneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fortune;
        String sign = "virgo"; // replace with getting user's sign
        TextView txtFortune = (TextView) findViewById(R.id.fortune_test);
        try {
             fortune = new FortuneTask().execute(sign).get();
        } catch (Exception e) {
            e.printStackTrace();
            fortune = "Fortunes can't be read right now.";
        }
        txtFortune.setText(fortune);

    }
}
