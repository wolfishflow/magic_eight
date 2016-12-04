package codebusters.magic_eight.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import codebusters.magic_eight.R;

/**
 * Created by alansimon on 2016-12-04.
 */

public class CreateUserFragment extends Fragment {

    public final String TAG = "CreateFragment";


    TextInputLayout tilName;
    TextInputLayout tilDate;
    EditText etName;
    EditText etDay;
    Spinner sprMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_user, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Button btnConfirm = (Button) getActivity().findViewById(R.id.btn_confirm);

        tilName = (TextInputLayout) getActivity().findViewById(R.id.til_name);
        tilDate = (TextInputLayout) getActivity().findViewById(R.id.til_date);
        etName = (EditText) getActivity().findViewById(R.id.et_name);
        etDay = (EditText) getActivity().findViewById(R.id.et_day);
        sprMonth = (Spinner) getActivity().findViewById(R.id.spr_months);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strName = etName.getText().toString();
                String strDay = etDay.getText().toString();

                if (strName.length() == 0) {
                    tilName.setError("Name is Required!");
                } else if (strDay.length() == 0) {
                    tilDate.setError("Day is Required!");
                }

                int intDay = Integer.parseInt(strDay);

                String strMonth = sprMonth.getSelectedItem().toString();
                Log.d(TAG, strMonth);
                switch (strMonth) {
                    case "January":
                        break;
                    case "February":
                        break;
                    case "March":
                        break;
                    case "April":
                        break;
                    case "May":
                        break;
                    case "June":
                        break;
                    case "July":
                        break;
                    case "August":
                        break;
                    case "September":
                        break;
                    case "October":
                        break;
                    case "November":
                        break;
                    case "December":
                        break;
                }
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
