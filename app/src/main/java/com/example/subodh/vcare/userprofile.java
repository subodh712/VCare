package com.example.subodh.vcare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class userprofile extends Activity {
    private TextView logout;
    private TextView name;
    private TextView welcome;
    private TextView age;
    private TextView phone;
    private TextView disease;
    private TextView adddisease;
    Handler handler;
    int problesmsResourceArray;
    boolean checked[] = {false,false,false,false,false,false};
    int id=0;
    SharedPreferences shar;
    connnection con = new connnection(this);

    public void setDiseasesText(){
        Cursor c=  con.readRecord(shar.getString("username",null));
        if(c!=null)
        {
            name.append(" "+c.getString(c.getColumnIndex("name")));
            age.append(" "+c.getString(c.getColumnIndex("age")));
            phone.append(" "+c.getString(c.getColumnIndex("phone")));
            this.id=c.getInt(c.getColumnIndex("id"));
            disease.setText(con.readPatientProblems(id));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        name= (TextView) findViewById(R.id.pname);
        logout= (TextView) findViewById(R.id.logout);
        phone= (TextView) findViewById(R.id.pphone);
        adddisease= (TextView) findViewById(R.id.addDisease);
        age= (TextView) findViewById(R.id.page);
        welcome= (TextView) findViewById(R.id.welcome);
        disease= (TextView) findViewById(R.id.disease);
        final List<String> problemList = Arrays.asList(AllRawData.AllProblemsList.allProblemsList);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<AllRawData.AllProblemsList.allProblemsList.length;i++){
                    if(disease.getText().toString().contains(AllRawData.AllProblemsList.allProblemsList[i])){
                        checked[i] = true;
                    }
                }
            }
        });
        final Handler hand = new Handler();
        hand.post(new Runnable() {
            @Override
            public void run() {
                con.insertAllProblems();
            }
        });

        shar = getSharedPreferences("user",MODE_PRIVATE);
        setDiseasesText();
        adddisease.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        problesmsResourceArray = R.array.problems;
                        final AlertDialog.Builder alert = new AlertDialog.Builder(userprofile.this);
                        alert.setMultiChoiceItems(problesmsResourceArray, checked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checked[which] = isChecked;
                            }
                        });
                        alert.setCancelable(true);
                        alert.setTitle("Select Disease");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                con.truncatePatientProblems();
                                for(int i=0; i<checked.length; i++){
                                    boolean isItChecked = checked[i];
                                    if(isItChecked){
                                        String problem_name = problemList.get(i);
                                        id = con.fetchProblemId(problem_name);
                                        con.insertPatientProblems(shar.getInt("id",0),id,problem_name);
                                    }
                                }
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Cursor c=  con.readRecord(shar.getString("username",null));
                                if(c!=null)
                                {
                                    id=c.getInt(c.getColumnIndex("id"));
                                    disease.setText(con.readPatientProblems(id));
                                }                            }
                        });
                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                    }
                });
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });



        if(shar.getString("username",null)!=null)
        {
            welcome.append(" "+shar.getString("username",null));
        }
        //list View
        int[] image = {R.drawable.medication,R.drawable.patientobservation,R.drawable.dos,R.drawable.donts};
        final String[] str= {"Medication List","Last Doctor Visit","Do\'s","Don\'ts"};
        ListView lv = (ListView) findViewById(R.id.list);
        final cu adapter = new cu(this,image,str);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= adapter.getItem(position).toString();
                if(s.equals("Last Doctor Visit"))
                {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                else if(s.equals("Medication List"))
                {
                    Intent i = new Intent(getApplicationContext(),medicationList.class);
                    startActivity(i);
                }
                else if(s.equals("Do\'s"))
                {
                    Intent i = new Intent(getApplicationContext(),dos.class);
                    startActivity(i);
                }
                else if(s.equals("Don\'ts"))
                {
                    Intent i = new Intent(getApplicationContext(),donts.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });


    }

}

