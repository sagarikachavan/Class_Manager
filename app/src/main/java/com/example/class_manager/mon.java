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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class mon extends AppCompatActivity {

    private EditText s1,s2,s3,s4,s5,s6,r1,r2,r3,r4,r5,r6;
    private Button btn;
    static String a1,a2,a3,a4,a5,a6,b1,b2,b3,b4,b5,b6,ID;
    DatabaseReference reff;
    com.example.class_manager.Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);

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
        String y = prefs1.getString("Mon", "No class");
        s1.setText(y);
        String y1 = prefs1.getString("Mon1", "No class");
        s2.setText(y1);
        String y2 = prefs1.getString("Mon2", "No class");
        s3.setText(y2);
        String y3 = prefs1.getString("Mon3", "No class");
        s4.setText(y3);
        String y4 = prefs1.getString("Mon4", "No class");
        s5.setText(y4);
        String y5 = prefs1.getString("Mon5", "No class");
        s6.setText(y5);
        String z = prefs1.getString("MonRoom", "-");
        r1.setText(z);
        String z1 = prefs1.getString("MonRoom1", "-");
        r2.setText(z1);
        String z2 = prefs1.getString("MonRoom2", "-");
        r3.setText(z2);
        String z3 = prefs1.getString("MonRoom3", "-");
        r4.setText(z3);
        String z4= prefs1.getString("MonRoom4", "-");
        r5.setText(z4);
        String z5= prefs1.getString("MonRoom5", "-");
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
                reff.child("Monday").setValue(table);

                Toast.makeText(mon.this,"data inserted succssfully",Toast.LENGTH_LONG).show();

                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(mon.this);
                SharedPreferences.Editor editor = prefs1.edit();

                editor.putString("Mon", a1);
                editor.putString("Mon1", a2);
                editor.putString("Mon2", a3);
                editor.putString("Mon3", a4);
                editor.putString("Mon4", a5);
                editor.putString("Mon5", a6);
                editor.putString("MonRoom", b1);
                editor.putString("MonRoom1", b2);
                editor.putString("MonRoom2", b3);
                editor.putString("MonRoom3", b4);
                editor.putString("MonRoom4", b5);
                editor.putString("MonRoom5", b6);
                editor.apply();

            }
        });

    }@Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Mon", a1);
        savedInstanceState.putString("Mon1", a2);
        savedInstanceState.putString("Mon2", a3);
        savedInstanceState.putString("Mon3", a4);
        savedInstanceState.putString("Mon4", a5);
        savedInstanceState.putString("Mon5", a6);
        savedInstanceState.putString("MonRoom", b1);
        savedInstanceState.putString("MonRoom1", b2);
        savedInstanceState.putString("MonRoom2", b3);
        savedInstanceState.putString("MonRoom3", b4);
        savedInstanceState.putString("MonRoom4", b5);
        savedInstanceState.putString("MonRoom5", b6);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String Mon = savedInstanceState.getString("Mon");
        String Mon1 = savedInstanceState.getString("Mon1");
        String Mon2 = savedInstanceState.getString("Mon2");
        String Mon3 = savedInstanceState.getString("Mon3");
        String Mon4 = savedInstanceState.getString("Mon4");
        String Mon5 = savedInstanceState.getString("Mon5");
        String MonRoom = savedInstanceState.getString("MonRoom");
        String MonRoom1 = savedInstanceState.getString("MonRoom1");
        String MonRoom2 = savedInstanceState.getString("MonRoom2");
        String MonRoom3 = savedInstanceState.getString("MonRoom3");
        String MonRoom4 = savedInstanceState.getString("MonRoom4");
        String MonRoom5 = savedInstanceState.getString("MonRoom5");
    }
}