package com.example.class_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class wed2 extends AppCompatActivity {

    private TextView a,b,c,f,g,h,i,j,k,l,m;
    private TextView e;
    String ID;

    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wed2);

        Intent i1 = getIntent();
        ID = i1.getStringExtra("ID");

        a = (TextView) findViewById((R.id.S1));
        b = (TextView) findViewById((R.id.S2));
        c = (TextView) findViewById((R.id.S3));
        e = (TextView) findViewById((R.id.S4));
        f = (TextView) findViewById((R.id.S5));
        g = (TextView) findViewById((R.id.S6));
        h = (TextView) findViewById((R.id.R1));
        i= (TextView) findViewById((R.id.R2));
        j = (TextView) findViewById((R.id.R3));
        k = (TextView) findViewById((R.id.R4));
        l = (TextView) findViewById((R.id.R5));
        m = (TextView) findViewById((R.id.R6));

        reff= FirebaseDatabase.getInstance().getReference().child("Table").child(ID).child("Wednesday");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1=snapshot.child("s1").getValue().toString();
                String sub2=snapshot.child("s2").getValue().toString();
                String sub3=snapshot.child("s3").getValue().toString();
                String sub4=snapshot.child("s4").getValue().toString();
                String sub5=snapshot.child("s5").getValue().toString();
                String sub6=snapshot.child("s6").getValue().toString();
                String MonRoom1=snapshot.child("r1").getValue().toString();
                String MonRoom2=snapshot.child("r2").getValue().toString();
                String MonRoom3=snapshot.child("r3").getValue().toString();
                String MonRoom4=snapshot.child("r4").getValue().toString();
                String MonRoom5=snapshot.child("r5").getValue().toString();
                String MonRoom6=snapshot.child("r6").getValue().toString();
                a.setText(sub1);
                b.setText(sub2);
                c.setText(sub3);
                e.setText(sub4);
                f.setText(sub5);
                g.setText(sub6);
                h.setText(MonRoom1);
                i.setText(MonRoom2);
                j.setText(MonRoom3);
                k.setText(MonRoom4);
                l.setText(MonRoom5);
                m.setText(MonRoom6);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}