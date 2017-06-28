package com.example.subodh.vcare;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



public class SignUp extends AppCompatActivity {
    private RadioGroup feeStatus;
    private RadioButton feeStatusbutton;
    private RadioGroup status;
    private RadioButton statusbutton;
    private Button submit;
    private int year;
    private int month;
    private int day;
    private EditText name,age,phone,username,password;
    int selectedid;
    patientContact c = new patientContact();
    connnection con= new connnection(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        feeStatus = (RadioGroup) findViewById(R.id.feestatus);
        status = (RadioGroup) findViewById(R.id.status);
        submit = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedid = feeStatus.getCheckedRadioButtonId();
                feeStatusbutton = (RadioButton) findViewById(selectedid);
                selectedid = status.getCheckedRadioButtonId();
                statusbutton = (RadioButton) findViewById(selectedid);

                if (!name.getText().toString().equals("") && !age.getText().toString().equals("") && !username.getText().toString().equals("") &&
                        !password.getText().toString().equals("") && feeStatusbutton != null && statusbutton != null && !phone.getText().toString().equals("")) {
                    c.setName(name.getText().toString());
                    c.setAge(Integer.parseInt(age.getText().toString()));
                    c.setUserName(username.getText().toString());
                    c.setPassword(password.getText().toString());
                    c.setFeeStatus(feeStatusbutton.getText().toString());
                    c.setStatus(statusbutton.getText().toString());
                    c.setPhone(Long.parseLong(phone.getText().toString()));
                    con.insertValues(c);
                    Toast.makeText(getApplicationContext(), "Successfully added, Try Login Now", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Fill all the fields!!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}
