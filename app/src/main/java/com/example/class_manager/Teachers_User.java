package com.example.class_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Teachers_User extends AppCompatActivity {
    private Button next,assignments;
    private TableLayout t1;
    private com.google.android.material.floatingactionbutton.FloatingActionButton add;
    String sub,name,sl,ID;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ProgressDialog pd=new ProgressDialog(Teachers_User.this);
        pd.setTitle("Loading Data...");
        pd.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__user);

        Intent i1 = getIntent();
        ID = i1.getStringExtra("ID");
        t1 = (TableLayout) findViewById(R.id.table);
        ref= FirebaseDatabase.getInstance().getReference().child("Teachers").child(ID);
        Query assignQuery = ref.orderByChild("slno").startAt("1");

        assignQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                pd.dismiss();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final TableRow r1=new TableRow(Teachers_User.this);
                    r1.setClipToPadding(false);
                    final TextView asl= new TextView(Teachers_User.this);
                    final TextView asub= new TextView(Teachers_User.this);
                    final TextView aname= new TextView(Teachers_User.this);
                    sub=snapshot.child("course").getValue().toString();
                    //assign = snapshot.child("assignment").getValue().toString();
                    //date = snapshot.child("date").getValue().toString();
                    name = snapshot.child("teacherName").getValue().toString();
                    sl =snapshot.child("slno").getValue().toString();
                    asub.setText(sub);
                    asl.setText(sl);
                    aname.setText(name);
                    r1.setClickable(true);
                    r1.addView(asl);
                    r1.addView(asub);
                    r1.addView(aname);
                    t1.addView(r1);
                    r1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent5 = new Intent(Teachers_User.this, Teachers_More.class);
                            TableRow row=(TableRow)v;
                            intent5.putExtra("Sl", ((TextView)(row.getChildAt(0))).getText());
                            intent5.putExtra("ID",ID);
                            startActivity(intent5);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }
        });
    }
}