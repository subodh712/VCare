package com.example.subodh.vcare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    ListView listView ;
    Button button;
    connection con = new connection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      listView= (ListView) findViewById(R.id.lv);
        SharedPreferences shar = getSharedPreferences("user",MODE_PRIVATE);
        int id = shar.getInt("id",0);
        String str[] = con.read(id);
        int[] image = new int[str.length];
        for(int i = 0; i<str.length;i++)
        {
            image[i]=R.drawable.ic_date_range_black_24dp;
        }
        cu c = new cu(this,image,str);
        listView.setAdapter(c);
        button= (Button) findViewById(R.id.update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),docvisit.class);
                startActivity(i);
                finish();
            }
        });
    }
}
