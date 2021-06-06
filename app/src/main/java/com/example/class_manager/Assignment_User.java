package com.example.class_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Assignment_User extends AppCompatActivity {
    private Button next;
    DatabaseReference ref;
    private TableLayout t1;
    Integer num=0;
    String sub,assign,date,link,sl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ProgressDialog pd=new ProgressDialog(Assignment_User.this);
        pd.setTitle("Loading Data...");
        pd.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment__user);

        Intent i = getIntent();
        final String m = i.getStringExtra("ID");
        next = (Button)findViewById(R.id.b1);
        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Assignments.this, Teachers.class);
                startActivity(intent1);
            }
        });*/
        t1 = (TableLayout) findViewById(R.id.table);
        ref= FirebaseDatabase.getInstance().getReference().child("Assignments").child(m);//.child(String.valueOf(n));
        Query assignQuery = ref.orderByChild("slno").startAt("1");

        assignQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                pd.dismiss();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    num =num + 1;
                    final TableRow r1=new TableRow(Assignment_User.this);
                    r1.setClipToPadding(false);
                    final TextView number=(TextView)findViewById(R.id.number);
                    final TextView asl= new TextView(Assignment_User.this);
                    final TextView asub= new TextView(Assignment_User.this);
                    final TextView aassign= new TextView(Assignment_User.this);
                    final TextView adate= new TextView(Assignment_User.this);
                    final TextView alink= new TextView(Assignment_User.this);
                    sub=snapshot.child("subject").getValue().toString();
                    assign = snapshot.child("assignment").getValue().toString();
                    date = snapshot.child("date").getValue().toString();
                    link = snapshot.child("link1").getValue().toString()+snapshot.child("link2").getValue().toString();
                    sl =snapshot.child("slno").getValue().toString();
                    number.setText("Total Number of assignments="+num);
                    asub.setText(sub);
                    asl.setText(sl);
                    aassign.setText(assign);
                    r1.setClickable(true);
                    adate.setText(date);
                    alink.setText(link);
                    r1.addView(asl);
                    r1.addView(asub);
                    r1.addView(adate);
                    t1.addView(r1);
                    r1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent5 = new Intent(Assignment_User.this, Assignments_More.class);
                            TableRow row=(TableRow)v;
                            intent5.putExtra("Sl", ((TextView)(row.getChildAt(0))).getText());
                            intent5.putExtra("ID", m);
                            startActivity(intent5);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }
        });
        /*
        while(true)
        {
            if(ref!=null)
            {
                final TableRow r1=new TableRow(this);
                r1.setClipToPadding(false);
                final TextView asl= new TextView(this);
                final TextView asub= new TextView(this);
                final TextView aassign= new TextView(this);
                final TextView adate= new TextView(this);
                final TextView alink= new TextView(this);
                asl.setText(String.valueOf(n));
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        sub=snapshot.child("subject").getValue().toString();
                        assign = snapshot.child("assignment").getValue().toString();
                        date = snapshot.child("date").getValue().toString();
                        link = snapshot.child("link1").getValue().toString()+snapshot.child("link2").getValue().toString();
                        asub.setText(sub);
                        aassign.setText(assign);
                        r1.setClickable(true);
                        adate.setText(date);
                        alink.setText(link);
                        r1.addView(asl);
                        r1.addView(asub);
                        r1.addView(adate);
                        t1.addView(r1);
                        r1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent5 = new Intent(Assignments.this, AssignMore.class);
                                TableRow row=(TableRow)v;
                                intent5.putExtra("Sl", ((TextView)(row.getChildAt(0))).getText());
                                startActivity(intent5);
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
                n++;
            }
            else
            {
                break;
            }
        }*/
    }
}