package codebusters.magic_eight.user;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import codebusters.magic_eight.R;
import codebusters.magic_eight.dao.DatabaseConnector;
import codebusters.magic_eight.home.HomeFragment;

/**
 * Created by alansimon on 2016-12-04.
 */

public class CreateUserFragment extends Fragment {

    public final String TAG = "CreateFragment";
    private Fragment fr;
    private FragmentManager fm;
    private FragmentTransaction ft;

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

                DatabaseConnector dbConnect = new DatabaseConnector(getContext());

                try {
                    int intDay = Integer.parseInt(strDay);
                    String strMonth = sprMonth.getSelectedItem().toString();
                    String sign = "";
                    Calendar birthDate = Calendar.getInstance();
                    Boolean valid = true;
                    switch (strMonth) {
                        case "January":
                            if (intDay>31 || intDay == 0){
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 0);
                            break;
                        case "February":
                            if (intDay>29 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 1);
                            break;
                        case "March":
                            if (intDay>31 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;

                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 2);
                            break;
                        case "April":
                            if (intDay>30 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 3);
                            break;
                        case "May":
                            if (intDay>31 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 4);
                            break;
                        case "June":
                            if (intDay>30 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 5);
                            break;
                        case "July":
                            if (intDay>31 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 6);
                            break;
                        case "August":
                            if (intDay>31 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 7);
                            break;
                        case "September":
                            if (intDay>30) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 8);
                            break;
                        case "October":
                            if (intDay>31 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 9);
                            break;
                        case "November":
                            if (intDay>30 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 10);
                            break;
                        case "December":
                            if (intDay>31 || intDay == 0) {
                                tilDate.setError("Invalid day!");
                                valid = false;
                                break;
                            }
                            valid = true;
                            birthDate.set(Calendar.MONTH, 11);
                            break;
                    }

                    if (valid) {

                        birthDate.set(Calendar.DATE, intDay);
                        sign = dbConnect.getSign(birthDate);
                        dbConnect.insert(strName, sign);

                        fr = new HomeFragment();
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.frlt_fragment_container_home, fr);
                        ft.commit();
                    }

                }catch (NumberFormatException e)
                {
                    tilDate.setError("Day is Required!");
                }
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
