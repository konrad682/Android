package com.example.konrad.todolist;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;


import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    public EditText longtext, nametext;
    public String date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        nametext = findViewById(R.id.nametext);
        longtext = findViewById(R.id.longtext);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name",nametext.getText().toString());
                intent.putExtra("desc",longtext.getText().toString());
                intent.putExtra("data", date);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });




        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddTaskActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

               date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }
}
