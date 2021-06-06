package com.example.class_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class wed extends AppCompatActivity {

    private EditText s1,s2,s3,s4,s5,s6,r1,r2,r3,r4,r5,r6;
    private Button btn;
    static String a1,a2,a3,a4,a5,a6,b1,b2,b3,b4,b5,b6,ID;
    DatabaseReference reff;
    Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wed);

        Intent i = getIntent();
        ID = i.getStringExtra("ID");
        s1 = (EditText) findViewById((R.id.S1));
        s3 = (EditText) findViewById((R.id.S3));
        s4 = (EditText) findViewById((R.id.S4));
        s5 = (EditText) findViewById((R.id.S5));
        s6 = (EditText) findViewById((R.id.S6));
        s2 = (EditText) findViewById((R.id.S2));
        r1 = (EditText) findViewById((R.id.R1));
        r2 = (EditText) findViewById((R.id.R2));
        r3 = (EditText) findViewById((R.id.R3));
        r4 = (EditText) findViewById((R.id.R4));
        r5 = (EditText) findViewById((R.id.R5));
        r6 = (EditText) findViewById((R.id.R6));
        btn = (Button) findViewById(R.id.btn2);
        reff= FirebaseDatabase.getInstance().getReference().child("Table").child(ID);
        table=new Table();
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String y = prefs1.getString("Wed", "No class");
        s1.setText(y);
        String y1 = prefs1.getString("Wed1", "No class");
        s2.setText(y1);
        String y2 = prefs1.getString("Wed2", "No class");
        s3.setText(y2);
        String y3 = prefs1.getString("Wed3", "No class");
        s4.setText(y3);
        String y4 = prefs1.getString("Wed4", "No class");
        s5.setText(y4);
        String y5 = prefs1.getString("Wed5", "No class");
        s6.setText(y5);
        String z = prefs1.getString("WedRoom", "-");
        r1.setText(z);
        String z1 = prefs1.getString("WedRoom1", "-");
        r2.setText(z1);
        String z2 = prefs1.getString("WedRoom2", "-");
        r3.setText(z2);
        String z3 = prefs1.getString("WedRoom3", "-");
        r4.setText(z3);
        String z4= prefs1.getString("WedRoom4", "-");
        r5.setText(z4);
        String z5= prefs1.getString("WedRoom5", "-");
        r6.setText(z5);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1 = s1.getText().toString();
                a2 = s2.getText().toString();
                a3 = s3.getText().toString();
                a4 = s4.getText().toString();
                a5 = s5.getText().toString();
                a6 = s6.getText().toString();
                b1 = r1.getText().toString();
                b2= r2.getText().toString();
                b3= r3.getText().toString();
                b4= r4.getText().toString();
                b5= r5.getText().toString();
                b6= r6.getText().toString();
                table.setS1(a1);
                table.setS2(a2);
                table.setS3(a3);
                table.setS4(a4);
                table.setS5(a5);
                table.setS6(a6);
                table.setR1(b1);
                table.setR2(b2);
                table.setR3(b3);
                table.setR4(b4);
                table.setR5(b5);
                table.setR6(b6);
                reff.child("Wednesday").setValue(table);

                Toast.makeText(wed.this,"data inserted succssfully",Toast.LENGTH_LONG).show();

                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(wed.this);
                SharedPreferences.Editor editor = prefs1.edit();

                editor.putString("Wed", a1);
                editor.putString("Wed1", a2);
                editor.putString("Wed2", a3);
                editor.putString("Wed3", a4);
                editor.putString("Wed4", a5);
                editor.putString("Wed5", a6);
                editor.putString("WedRoom", b1);
                editor.putString("WedRoom1", b2);
                editor.putString("WedRoom2", b3);
                editor.putString("WedRoom3", b4);
                editor.putString("WedRoom4", b5);
                editor.putString("WedRoom5", b6);
                editor.apply();

            }
        });

    }@Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Wed", a1);
        savedInstanceState.putString("Wed1", a2);
        savedInstanceState.putString("Wed2", a3);
        savedInstanceState.putString("Wed3", a4);
        savedInstanceState.putString("Wed4", a5);
        savedInstanceState.putString("Wed5", a6);
        savedInstanceState.putString("WedRoom", b1);
        savedInstanceState.putString("WedRoom1", b2);
        savedInstanceState.putString("WedRoom2", b3);
        savedInstanceState.putString("WedRoom3", b4);
        savedInstanceState.putString("WedRoom4", b5);
        savedInstanceState.putString("WedRoom5", b6);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String Wed = savedInstanceState.getString("Wed");
        String Wed1 = savedInstanceState.getString("Wed1");
        String Wed2 = savedInstanceState.getString("Wed2");
        String Wed3 = savedInstanceState.getString("Wed3");
        String Wed4 = savedInstanceState.getString("Wed4");
        String Wed5 = savedInstanceState.getString("Wed5");
        String WedRoom = savedInstanceState.getString("WedRoom");
        String WedRoom1 = savedInstanceState.getString("WedRoom1");
        String WedRoom2 = savedInstanceState.getString("WedRoom2");
        String WedRoom3 = savedInstanceState.getString("WedRoom3");
        String WedRoom4 = savedInstanceState.getString("WedRoom4");
        String WedRoom5 = savedInstanceState.getString("WedRoom5");
    }
}