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

public class tue extends AppCompatActivity {

    private EditText s1,s2,s3,s4,s5,s6,r1,r2,r3,r4,r5,r6;
    private Button btn;
    static String a1,a2,a3,a4,a5,a6,b1,b2,b3,b4,b5,b6,ID;
    DatabaseReference reff;
    Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tue);

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
        String y = prefs1.getString("Subject", "No class");
        s1.setText(y);
        String y1 = prefs1.getString("Subject1", "No class");
        s2.setText(y1);
        String y2 = prefs1.getString("Subject2", "No class");
        s3.setText(y2);
        String y3 = prefs1.getString("Subject3", "No class");
        s4.setText(y3);
        String y4 = prefs1.getString("Subject4", "No class");
        s5.setText(y4);
        String y5 = prefs1.getString("Subject5", "No class");
        s6.setText(y5);
        String z = prefs1.getString("Room", "-");
        r1.setText(z);
        String z1 = prefs1.getString("Room1", "-");
        r2.setText(z1);
        String z2 = prefs1.getString("Room2", "-");
        r3.setText(z2);
        String z3 = prefs1.getString("Room3", "-");
        r4.setText(z3);
        String z4= prefs1.getString("Room4", "-");
        r5.setText(z4);
        String z5= prefs1.getString("Room5", "-");
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
                reff.child("Tuesday").setValue(table);

                Toast.makeText(tue.this,"data inserted succssfully",Toast.LENGTH_LONG).show();

                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(tue.this);
                SharedPreferences.Editor editor = prefs1.edit();

                editor.putString("Subject", a1);
                editor.putString("Subject1", a2);
                editor.putString("Subject2", a3);
                editor.putString("Subject3", a4);
                editor.putString("Subject4", a5);
                editor.putString("Subject5", a6);
                editor.putString("Room", b1);
                editor.putString("Room1", b2);
                editor.putString("Room2", b3);
                editor.putString("Room3", b4);
                editor.putString("Room4", b5);
                editor.putString("Room5", b6);
                editor.apply();

            }
        });

    }@Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Subject", a1);
        savedInstanceState.putString("Subject1", a2);
        savedInstanceState.putString("Subject2", a3);
        savedInstanceState.putString("Subject3", a4);
        savedInstanceState.putString("Subject4", a5);
        savedInstanceState.putString("Subject5", a6);
        savedInstanceState.putString("Room", b1);
        savedInstanceState.putString("Room1", b2);
        savedInstanceState.putString("Room2", b3);
        savedInstanceState.putString("Room3", b4);
        savedInstanceState.putString("Room4", b5);
        savedInstanceState.putString("Room5", b6);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String subject = savedInstanceState.getString("Subject");
        String subject1 = savedInstanceState.getString("Subject1");
        String subject2 = savedInstanceState.getString("Subject2");
        String subject3 = savedInstanceState.getString("Subject3");
        String subject4 = savedInstanceState.getString("Subject4");
        String subject5 = savedInstanceState.getString("Subject5");
        String Room = savedInstanceState.getString("Room");
        String Room1 = savedInstanceState.getString("Room1");
        String Room2 = savedInstanceState.getString("Room2");
        String Room3 = savedInstanceState.getString("Room3");
        String Room4 = savedInstanceState.getString("Room4");
        String Room5 = savedInstanceState.getString("Room5");
    }
}