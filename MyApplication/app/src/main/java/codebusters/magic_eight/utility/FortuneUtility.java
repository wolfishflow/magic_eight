package codebusters.magic_eight.utility;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by greg on 12/11/16.
 */

public class FortuneUtility {

    public static String getFortune(String sign) {

        try {
//            URL url = new URL("http://laddr.xyz:3113/fortune");
            URL url = new URL("http://widgets.fabulously40.com/horoscope.json?sign=" + sign);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;

            while ((cp = br.read()) != -1) {
                sb.append((char) cp);
            }

            JSONObject json = new JSONObject(sb.toString());
            String fortune = json.getJSONObject("horoscope").getString("horoscope");

            return fortune;

        } catch (Exception ex) {
            return "Fortune could not be obtained.";
        }
    }
}
