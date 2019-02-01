package com.a13rain.unodir.waterlife;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class AddAquarium  extends Fragment {
    private Button btnSet;
    private View  mDatePickerDlg;
    private boolean mCanceled = false;
    private long mlSettingdate = 0;
    private EditText mEdtSettingDate ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


         View v = inflater.inflate(R.layout.add_aquarium, container, false);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEdtSettingDate = (EditText) getView().findViewById(R.id.edtSettingDate);
        btnSet = (Button) getView().findViewById(R.id.btnSetDate);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDatePicketDialog();
            }
        });

        getActivity().setTitle("Add Aquarium ...");
    }

    private void ShowDatePicketDialog() {
        Calendar date = Calendar.getInstance();
        DatePickerDialog dlg = new DatePickerDialog(getActivity() );
        dlg.setTitle("날짜 선택");
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        dlg.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mEdtSettingDate.setText(Integer.toString(i) + "-" + Integer.toString(i1+1) + "-" + Integer.toString(i2));
            }
        });
        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mCanceled = true;
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strSettingDate = transFormat.format(mlSettingdate);
                mEdtSettingDate.setText(strSettingDate);
            }
        });
        dlg.show();
    }
}

/*
{
    private Button btnSet;
    private View  mDatePickerDlg;
    private boolean mCanceled = false;
    private long mlSettingdate = 0;
    private EditText mEdtSettingDate ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_aquarium);
        setTitle("Add Aquarium");


        mEdtSettingDate = (EditText) findViewById(R.id.edtSettingDate);
        btnSet = (Button) findViewById(R.id.btnSetDate);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ShowDatePicketDialog();
            }
        });
    }

    private void ShowDatePicketDialog() {
        Calendar date = Calendar.getInstance();
        DatePickerDialog dlg = new DatePickerDialog(AddAquarium.this );
        dlg.setTitle("날짜 선택");
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        dlg.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mEdtSettingDate.setText(Integer.toString(i) + "-" + Integer.toString(i1+1) + "-" + Integer.toString(i2));
            }
        });
        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mCanceled = true;
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strSettingDate = transFormat.format(mlSettingdate);
                mEdtSettingDate.setText(strSettingDate);
            }
        });
        dlg.show();
    }
}
*/