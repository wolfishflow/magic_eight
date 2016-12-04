package codebusters.magic_eight.utility;

import android.os.AsyncTask;

/**
 * Created by greg on 12/11/16.
 */

public class FortuneTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        FortuneUtility util = new FortuneUtility();
        if (params[0] != null || !params[0].equals("")) {
            return util.getFortune(params[0].toLowerCase());
        }
        return "We need your sign in order to find your horoscope.";
    }
}
