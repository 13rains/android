package com.a13rain.unodir.aqualife;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.a13rain.unodir.aqualife.db.AquaDB;

import java.util.Calendar;

public class AddAquarium  extends Fragment {
    private Button btnSet;
    private Button btnAdd;
    private View  mDatePickerDlg;
    private boolean mCanceled = false;
    private long mlSettingdate = 0;
    private EditText medtSettingDate ;

    Spinner mspTanksize;
    SpinnerAdapter sAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

         View v = inflater.inflate(R.layout.add_aquarium, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Add Aquarium ...");

        medtSettingDate = (EditText) getView().findViewById(R.id.edtSettingDate);

        btnSet = (Button) getView().findViewById(R.id.btnSetDate);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDatePicketDialog();
            }
        });
        medtSettingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePicketDialog();
            }
        });

        btnAdd = (Button)getView().findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            static final String strInsertQuery = "INSERT INTO aquarium ( name, date, size_t, x, y, z, summary )" +
                    "VALUES ( 'name1', DATETIME('now'), 0, 30, 30, 30, 'summary');";
            @Override
            public void onClick(View v) {

                try {
                    AquaDB dbmgr = new AquaDB(getActivity());

                    SQLiteDatabase sdb;

                    sdb = dbmgr.getWritableDatabase();

                    sdb.execSQL(strInsertQuery);

//                    ContentValues cv = new ContentValues();
//                    cv.put("name", "name2");
//                    //cv.put("date", "20");
//                    cv.put("size_t", 0);
//                    cv.put("x", 45);
//                    cv.put("y", 45);
//                    cv.put("z", 45);
//                    cv.put("summary", "it's summary");
//                    sdb.insert("aquarium", null, cv);

                    dbmgr.close();
                } catch (SQLiteException e) {
                    Log.e("Aqua:DB", e.getMessage().toString());
                }
            }
        });

        mspTanksize = (Spinner) getView().findViewById(R.id.txt_question_type);
        sAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.tank_size, android.R.layout.simple_spinner_dropdown_item);

        mspTanksize.setAdapter(sAdapter);
        mspTanksize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
                       sAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();

                EditText edtHorizontal = getView().findViewById(R.id.edthorizontal);
                EditText edtVertical = getView().findViewById(R.id.edtVertical);
                EditText edtHeight = getView().findViewById(R.id.edtHeight);
                int x, y, z;
                switch ( position )
                {
                    // 30 큐브
                    case 1:{
                        x = 30; y = 30; z = 30;
                        break;
                    }
                    // 자반
                    case 2: {
                        x = 45; y = 45;  z = 30;
                        break;
                    }
                    // 45 큐브
                    case 3: {
                        x = 45; y = 45;  z = 45;
                        break;
                    }
                    // 두자
                    case 4: {
                        x= 60; y = 30; z = 30;
                        break;
                    }
                    case 5:{
                        x = 60; y = 45; z = 45;
                        break;
                    }
                    default: { x = 0; y = 0; z = 0;
                        break;
                    }

                }
                edtHorizontal.setText(Integer.toString(x));
                edtVertical.setText(Integer.toString(y));
                edtHeight.setText(Integer.toString(z));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                medtSettingDate.setText(Integer.toString(i) + "-" + Integer.toString(i1+1) + "-" + Integer.toString(i2));
            }
        });
        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mCanceled = true;
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strSettingDate = transFormat.format(mlSettingdate);
                medtSettingDate.setText(strSettingDate);
            }
        });
        dlg.show();
    }
}
