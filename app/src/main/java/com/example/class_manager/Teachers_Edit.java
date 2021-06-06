package com.example.class_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teachers_Edit extends AppCompatActivity {
    private EditText course,name,room,ph,mail;
    private Button submit;
    String ID;
    DatabaseReference ref;
    TeachersDb t;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__edit);

        course = (EditText) findViewById(R.id.course);
        name = (EditText) findViewById(R.id.name);
        room = (EditText) findViewById(R.id.room);
        ph = (EditText) findViewById(R.id.ph);
        mail = (EditText) findViewById(R.id.mail);
        submit = (Button) findViewById(R.id.submit);
        Intent i= getIntent();
        final Integer Sl1=i.getIntExtra("Sl",1);
        final Integer edit=i.getIntExtra("Edit",0);
        ID = i.getStringExtra("ID");
        if(edit==1)
        {
            ref = FirebaseDatabase.getInstance().getReference().child("Teachers").child(ID).child(Sl1.toString());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String course1 = snapshot.child("course").getValue().toString();
                    String name1 = snapshot.child("teacherName").getValue().toString();
                    String room1 = snapshot.child("roomNo").getValue().toString();
                    String mail1 = snapshot.child("mail").getValue().toString();
                    String ph1 = snapshot.child("phNo").getValue().toString();
                    course.setText(course1);
                    name.setText(name1);
                    room.setText(room1);
                    ph.setText(ph1);
                    mail.setText(mail1);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
        t = new TeachersDb();
        ref = FirebaseDatabase.getInstance().getReference().child("Teachers").child(ID);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(TextUtils.isEmpty(course.getText()))
                {
                    course.setError("Course is Required!");
                }
                else if(TextUtils.isEmpty(name.getText()))
                {
                    name.setError("Teacher's Name field is Required!");
                }
                else {
                    t.setCourse(course.getText().toString());
                    t.setTeacherName(name.getText().toString());
                    t.setRoomNo(room.getText().toString());
                    t.setPhNo(ph.getText().toString());
                    t.setMail(mail.getText().toString());
                    t.setSlno(""+Sl1);
                    ref.child(""+Sl1).setValue(t);
                    if(edit==1) Toast.makeText(Teachers_Edit.this,"Edited Successfully.", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(Teachers_Edit.this,"Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Teachers_Edit.this, Teachers_Admin.class);
                    intent.putExtra("Sl", ""+Sl1);
                    intent.putExtra("ID", ID);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}