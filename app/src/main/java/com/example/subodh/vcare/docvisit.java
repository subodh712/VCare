package com.example.subodh.vcare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class docvisit extends AppCompatActivity implements View.OnClickListener {

    Button time, date,submit,view;
    TextView txtdate, txttime;

    private int myear, mmonth, mday, mhour, mminute;
    connection conn=new connection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docvisit);

        submit= (Button) findViewById(R.id.submit);
        time=(Button)findViewById(R.id.time);
        date=(Button)findViewById(R.id.dateb);
        view= (Button) findViewById(R.id.view);

        txtdate=(TextView) findViewById(R.id.et1);
        txttime=(TextView) findViewById(R.id.ed2);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactLastDocVisit c=new ContactLastDocVisit();
                c.setdate(txtdate.getText().toString());
                c.setTime(txttime.getText().toString());
                SharedPreferences shar = getSharedPreferences("user",MODE_PRIVATE);
                int id = shar.getInt("id",0);
                conn.insertdate(c,id);
                Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_LONG).show();
            }
        });
        time.setOnClickListener(this);
        date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == date){
            final Calendar c=Calendar.getInstance();
            myear=c.get(Calendar.YEAR);
            mmonth=c.get(Calendar.MONTH);
            mday=c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpkd=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtdate.setText(dayOfMonth +"/"+ monthOfYear +"/" +year);
                }
            },myear,mmonth,mday);
            dpkd.show();
        }
        if(v==time){

            final Calendar c=Calendar.getInstance();
            mhour=c.get(Calendar.HOUR_OF_DAY);
            mminute=c.get(Calendar.MINUTE);

            TimePickerDialog tpd=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    txttime.setText(hourOfDay +":"+ minute);
                }
            },mhour,mminute,false);
            tpd.show();
        }
    }
}
