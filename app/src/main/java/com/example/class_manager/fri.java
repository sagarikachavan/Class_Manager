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

public class fri extends AppCompatActivity {

    private EditText s1,s2,s3,s4,s5,s6,r1,r2,r3,r4,r5,r6;
    private Button btn;
    static String a1,a2,a3,a4,a5,a6,b1,b2,b3,b4,b5,b6,ID;
    DatabaseReference reff;
    Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fri);

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
        String y = prefs1.getString("Fri", "No class");
        s1.setText(y);
        String y1 = prefs1.getString("Fri1", "No class");
        s2.setText(y1);
        String y2 = prefs1.getString("Fri2", "No class");
        s3.setText(y2);
        String y3 = prefs1.getString("Fri3", "No class");
        s4.setText(y3);
        String y4 = prefs1.getString("Fri4", "No class");
        s5.setText(y4);
        String y5 = prefs1.getString("Fri5", "No class");
        s6.setText(y5);
        String z = prefs1.getString("FriRoom", "-");
        r1.setText(z);
        String z1 = prefs1.getString("FriRoom1", "-");
        r2.setText(z1);
        String z2 = prefs1.getString("FriRoom2", "-");
        r3.setText(z2);
        String z3 = prefs1.getString("FriRoom3", "-");
        r4.setText(z3);
        String z4= prefs1.getString("FriRoom4", "-");
        r5.setText(z4);
        String z5= prefs1.getString("FriRoom5", "-");
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
                reff.child("Friday").setValue(table);

                Toast.makeText(fri.this,"data inserted succssfully",Toast.LENGTH_LONG).show();

                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(fri.this);
                SharedPreferences.Editor editor = prefs1.edit();

                editor.putString("Fri", a1);
                editor.putString("Fri1", a2);
                editor.putString("Fri2", a3);
                editor.putString("Fri3", a4);
                editor.putString("Fri4", a5);
                editor.putString("Fri5", a6);
                editor.putString("FriRoom", b1);
                editor.putString("FriRoom1", b2);
                editor.putString("FriRoom2", b3);
                editor.putString("FriRoom3", b4);
                editor.putString("FriRoom4", b5);
                editor.putString("FriRoom5", b6);
                editor.apply();

            }
        });

    }@Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Fri", a1);
        savedInstanceState.putString("Fri1", a2);
        savedInstanceState.putString("Fri2", a3);
        savedInstanceState.putString("Fri3", a4);
        savedInstanceState.putString("Fri4", a5);
        savedInstanceState.putString("Fri5", a6);
        savedInstanceState.putString("FriRoom", b1);
        savedInstanceState.putString("FriRoom1", b2);
        savedInstanceState.putString("FriRoom2", b3);
        savedInstanceState.putString("FriRoom3", b4);
        savedInstanceState.putString("FriRoom4", b5);
        savedInstanceState.putString("FriRoom5", b6);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String Fri = savedInstanceState.getString("Fri");
        String Fri1 = savedInstanceState.getString("Fri1");
        String Fri2 = savedInstanceState.getString("Fri2");
        String Fri3 = savedInstanceState.getString("Fri3");
        String Fri4 = savedInstanceState.getString("Fri4");
        String Fri5 = savedInstanceState.getString("Fri5");
        String FriRoom = savedInstanceState.getString("FriRoom");
        String FriRoom1 = savedInstanceState.getString("FriRoom1");
        String FriRoom2 = savedInstanceState.getString("FriRoom2");
        String FriRoom3 = savedInstanceState.getString("FriRoom3");
        String FriRoom4 = savedInstanceState.getString("FriRoom4");
        String FriRoom5 = savedInstanceState.getString("FriRoom5");
    }
}