package codebusters.magic_eight.utility;

import android.os.AsyncTask;

/**
 * Created by greg on 12/11/16.
 */

public class FortuneTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... params) {
        FortuneUtility util = new FortuneUtility();
        return util.getFortune();
    }
}
