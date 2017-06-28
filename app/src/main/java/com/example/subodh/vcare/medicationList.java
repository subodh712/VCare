package com.example.subodh.vcare;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class medicationList extends AppCompatActivity {
    private Button add;
    private ListView lv ;
    private EditText ed;
    int id;
    connnection con = new connnection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_list);
        lv= (ListView) findViewById(R.id.medicationList);
        add= (Button) findViewById(R.id.addNew);
        ed= (EditText) findViewById(R.id.medicine);
        refresh();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ed.getText().equals("")) {
                    con.insertMedication(id, ed.getText().toString());
                    ed.setText("");
                    Toast.makeText(getApplicationContext(), "Medicine added", Toast.LENGTH_LONG).show();
                    refresh();
                }
                else
                    Toast.makeText(getApplicationContext(),"Enter the Medicine first",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void refresh()
    {
        SharedPreferences shar = getSharedPreferences("user",MODE_PRIVATE);
        id = shar.getInt("id",0);
        String str[]=con.readMedication(id);
        int[] image = new int[str.length];
        for(int i = 0; i<str.length;i++)
        {
            image[i]=R.drawable.ic_done_black_24dp;
        }
        cu c = new cu(this,image,str);
        lv.setAdapter(c);
    }
}
