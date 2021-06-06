package com.example.class_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Assignments_More extends AppCompatActivity {
    private TextView sub,assign,date,link;
    private String Sl1,assign1,date1,link1,sub1,ID;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments__more);
        Intent i = getIntent();
        Sl1 = i.getStringExtra("Sl");
        ID = i.getStringExtra("ID");
        ref = FirebaseDatabase.getInstance().getReference().child("Assignments").child(ID).child(Sl1);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sub1 = snapshot.child("subject").getValue().toString();
                assign1 = snapshot.child("assignment").getValue().toString();
                date1 = snapshot.child("date").getValue().toString();
                link1 = snapshot.child("link1").getValue().toString()+ snapshot.child("link2").getValue().toString();
                sub=(TextView)findViewById(R.id.submore);
                assign=(TextView)findViewById(R.id.assignmore);
                date=(TextView)findViewById(R.id.datemore);
                link=(TextView)findViewById(R.id.linkmore);
                sub.setText(sub1);
                assign.setText(assign1);
                date.setText(date1);
                link.setText(link1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}