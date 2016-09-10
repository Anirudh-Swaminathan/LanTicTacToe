package com.anirudh.anirudhswami.ticgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ip;
    Button conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = (EditText) findViewById(R.id.editText);
        conn = (Button) findViewById(R.id.conBtn);

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAdd = ip.getText().toString();
                if(ipAdd.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter IP Address to connect",Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this,Tic.class);
                    i.putExtra("IP",ipAdd);
                    startActivity(i);
                }
            }
        });
    }
}
