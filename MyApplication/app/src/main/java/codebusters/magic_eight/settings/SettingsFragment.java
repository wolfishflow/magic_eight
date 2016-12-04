package codebusters.magic_eight.settings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import codebusters.magic_eight.R;
import codebusters.magic_eight.dao.DatabaseConnector;
import codebusters.magic_eight.dao.User;

/**
 * Created by alansimon on 2016-12-04.
 */

public class SettingsFragment extends Fragment{

    public final String TAG = "SettingsFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        DatabaseConnector dbConnector = new DatabaseConnector(getContext());


        ArrayList<User> userArrayList = dbConnector.getUsers();

        if (userArrayList.size()>0)
        {
            TextView tvName = (TextView) getActivity().findViewById(R.id.tv_settings_name);
            tvName.setText(userArrayList.get(0).getName());
        }

        super.onActivityCreated(savedInstanceState);
    }
}
