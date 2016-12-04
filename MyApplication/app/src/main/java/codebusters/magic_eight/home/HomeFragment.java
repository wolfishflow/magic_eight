package codebusters.magic_eight.home;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import codebusters.magic_eight.R;
import codebusters.magic_eight.dao.DatabaseConnector;
import codebusters.magic_eight.dao.User;
import codebusters.magic_eight.utility.FortuneTask;

/**
 * Created by greg on 04/12/16.
 */

public class HomeFragment extends Fragment {

    DatabaseConnector db;
    private final String DEBUG_TAG = "magic_eight_debug";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        db = new DatabaseConnector(getContext());
        String sign = "", fortune;

        ArrayList<User> userArrayList = db.getUsers();
        if (userArrayList.size()>0)
        {
            sign = userArrayList.get(0).getSign();
        }
        TextView txtFortune = (TextView) getActivity().findViewById(R.id.tv_horoscope);
        try {
            fortune = new FortuneTask().execute(sign).get();
        } catch (Exception e) {
            Log.e(DEBUG_TAG, e.getMessage());
            fortune = "Fortunes can't be read right now.";
        }
        txtFortune.setText(fortune);

        super.onActivityCreated(savedInstanceState);
    }
}
