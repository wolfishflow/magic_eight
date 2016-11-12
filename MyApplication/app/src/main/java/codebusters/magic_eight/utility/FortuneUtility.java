package codebusters.magic_eight.utility;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by greg on 12/11/16.
 */

public class FortuneUtility {

    public static String getFortune() {

        try {
            URL url = new URL("http://laddr.xyz:3113/fortune");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;

            while ((cp = br.read()) != -1) {
                sb.append((char) cp);
            }

            JSONObject json = new JSONObject(sb.toString());
            String fortune = json.getString("fortune");

            return fortune;

        } catch (Exception ex) {
            return "Fortune could not be obtained.";
        }
    }
}
