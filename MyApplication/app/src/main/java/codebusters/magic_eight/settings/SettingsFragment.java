package codebusters.magic_eight.settings;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import codebusters.magic_eight.R;
import codebusters.magic_eight.dao.DatabaseConnector;
import codebusters.magic_eight.dao.User;
import codebusters.magic_eight.user.CreateUserFragment;

/**
 * Created by alansimon on 2016-12-04.
 */

public class SettingsFragment extends Fragment{

    public final String TAG = "SettingsFragment";

    private Fragment fr;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final DatabaseConnector dbConnector = new DatabaseConnector(getContext());

        Button btnDelete = (Button) getActivity().findViewById(R.id.btn_delete);

        ArrayList<User> userArrayList = dbConnector.getUsers();

        if (userArrayList.size()>0)
        {
            final String name = userArrayList.get(0).getName();
            final String sign = userArrayList.get(0).getSign();
            TextView tvName = (TextView) getActivity().findViewById(R.id.tv_settings_name);
            tvName.setText(name);
            TextView tvSign = (TextView) getActivity().findViewById(R.id.tv_settings_sign);
            tvSign.setText(sign);


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbConnector.delete(name, sign);

                    fr = new CreateUserFragment();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frlt_fragment_container_home, fr);
                    ft.commit();

                }});
        }

        super.onActivityCreated(savedInstanceState);
    }
}
