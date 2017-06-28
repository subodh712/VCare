package com.example.subodh.vcare;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class dos extends Activity {
    ListView dolist;
    connnection con = new connnection(this);
    SharedPreferences shar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);
        dolist= (ListView) findViewById(R.id.dolist);
        shar= getSharedPreferences("user",MODE_PRIVATE);
        int ids[]= con.readProblemIds(shar.getInt("id",0));
        String str[]=con.readDos(ids);
        int[] image = new int[str.length];
        for(int i = 0; i<str.length;i++)
        {
            image[i]=R.drawable.ic_done_black_24dp;
        }
        cu c = new cu(this,image,str);
        dolist.setAdapter(c);
    }
}
