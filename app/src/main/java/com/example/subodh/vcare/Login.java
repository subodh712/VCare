package com.example.subodh.vcare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {
    connnection con;
    Button loginbutton;
    EditText username;
    EditText password;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbutton  = (Button) findViewById(R.id.loginbutton);
        con = new connnection(this);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        signup= (Button) findViewById(R.id.signup);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass =con.readvalue(username.getText().toString());
                if(password.getText().toString().equals(pass) && !pass.equals("do not match"))
                {
                    Cursor c=  con.readRecord(username.getText().toString());
                    SharedPreferences shar = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor edit = shar.edit();
                    edit.putInt("id",c.getInt(c.getColumnIndex("id")));
                    edit.putString("username",c.getString(c.getColumnIndex("userName")));
                    edit.commit();
                    Intent i = new Intent(getApplicationContext(),userprofile.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
                finish();
            }
        });
    }
}
