package com.example.class_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teachers_More extends AppCompatActivity {

    private TextView sub,name,ph,mail,room;
    private String Sl1,sub1,name1,ph1,mail1,room1,ID;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__more);

        Intent i = getIntent();
        Sl1 = i.getStringExtra("Sl");
        ID = i.getStringExtra("ID");
        ref = FirebaseDatabase.getInstance().getReference().child("Teachers").child(ID).child(Sl1);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sub1 = snapshot.child("course").getValue().toString();
                name1 = snapshot.child("teacherName").getValue().toString();
                room1 = snapshot.child("roomNo").getValue().toString();
                ph1 = snapshot.child("phNo").getValue().toString();
                mail1=snapshot.child("mail").getValue().toString();
                sub=(TextView)findViewById(R.id.subject);
                name=(TextView)findViewById(R.id.name);
                ph=(TextView)findViewById(R.id.phone);
                mail=(TextView)findViewById(R.id.mail);
                room=(TextView)findViewById(R.id.room);
                sub.setText(sub1);
                name.setText(name1);
                room.setText(room1);
                ph.setText(ph1);
                mail.setText(mail1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}