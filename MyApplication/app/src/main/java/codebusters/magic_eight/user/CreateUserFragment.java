package codebusters.magic_eight.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codebusters.magic_eight.R;

/**
 * Created by alansimon on 2016-12-04.
 */

public class CreateUserFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_user, container, false);
        return rootView;
    }



}
