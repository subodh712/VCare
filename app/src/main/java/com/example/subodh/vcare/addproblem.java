package com.example.subodh.vcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.subodh.vcare.AllRawData.*;

public class addproblem extends AppCompatActivity {
private AutoCompleteTextView allDisease;
    connnection con=new connnection(this);
    int id;
    Button add;
    String problem_name;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproblem);
        add = (Button) findViewById(R.id.add);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        allDisease= (AutoCompleteTextView) findViewById(R.id.allproblems);
        ArrayAdapter<String>  arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,AllProblemsList.allProblemsList);
        allDisease.setAdapter(arrayAdapter);
        allDisease.setThreshold(2);
    }
    public void onClick(View v)
    {
        problem_name = allDisease.getText().toString();
        id = con.fetchProblemId(problem_name);
        con.insertPatientProblems(sharedPreferences.getInt("id",0),id,problem_name);
        Toast.makeText(addproblem.this, "insertedPatientProblems", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),userprofile.class);
        startActivity(i);
        finish();
    }
}
