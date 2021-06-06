package com.example.class_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Assignments_Edit extends AppCompatActivity {

    private EditText sub,assign,link1,link2,date;
    private Button submit;
    DatabaseReference ref;
    StudentBog s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments__edit);

        sub = (EditText) findViewById(R.id.sub);
        assign = (EditText) findViewById(R.id.assign);
        date = (EditText) findViewById(R.id.date);
        link1 = (EditText) findViewById(R.id.link1);
        link2 = (EditText) findViewById(R.id.link2);
        submit = (Button) findViewById(R.id.submit);
        Intent i1= getIntent();
        final Integer Sl1=i1.getIntExtra("Sl",1);
        final Integer edit=i1.getIntExtra("Edit",1);
        final String ID = i1.getStringExtra("ID");
        if(edit==1)
        {
            ref = FirebaseDatabase.getInstance().getReference().child("Assignments").child(ID).child(Sl1.toString());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String sub1 = snapshot.child("subject").getValue().toString();
                    String assign1 = snapshot.child("assignment").getValue().toString();
                    String date1 = snapshot.child("date").getValue().toString();
                    String link11 = snapshot.child("link1").getValue().toString();
                    String link12 = snapshot.child("link2").getValue().toString();
                    sub.setText(sub1);
                    assign.setText(assign1);
                    date.setText(date1);
                    link1.setText(link11);
                    link2.setText(link12);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
        s1 = new StudentBog();
        ref = FirebaseDatabase.getInstance().getReference().child("Assignments");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(TextUtils.isEmpty(sub.getText()))
                {
                    sub.setError("Subject is Required!");
                }
                else if(TextUtils.isEmpty(assign.getText()))
                {
                    assign.setError("Assignment field is Required!");
                }
                else {
                    s1.setDate(date.getText().toString());
                    s1.setSubject(sub.getText().toString());
                    s1.setAssignment(assign.getText().toString());
                    s1.setLink1(link1.getText().toString());
                    s1.setLink2(link2.getText().toString());
                    s1.setSlno(""+Sl1);
                    ref.child(ID).child(""+Sl1).setValue(s1);
                    if(edit==1)Toast.makeText(Assignments_Edit.this,"Edited Successfully.", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(Assignments_Edit.this,"Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Assignments_Edit.this, Assignments_Admin.class);
                    intent.putExtra("ID", ID);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}