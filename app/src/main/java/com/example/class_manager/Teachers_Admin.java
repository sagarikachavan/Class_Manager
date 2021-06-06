package com.example.class_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Teachers_Admin extends AppCompatActivity {
    private int number = 0,num=0;
    private TableLayout t1;
    private com.google.android.material.floatingactionbutton.FloatingActionButton add;
    String sub,name,room,ph,mail,sl,ID;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ProgressDialog pd=new ProgressDialog(Teachers_Admin.this);
        pd.setTitle("Loading Data...");
        pd.show();
        super.onCreate(savedInstanceState);

        Intent i1 = getIntent();
        ID = i1.getStringExtra("ID");
        setContentView(R.layout.activity_teachers__admin);
        add = (com.google.android.material.floatingactionbutton.FloatingActionButton)findViewById(R.id.b1);
        t1 = (TableLayout) findViewById(R.id.table);
        ref= FirebaseDatabase.getInstance().getReference().child("Teachers").child(ID);
        Query assignQuery = ref.orderByChild("slno").startAt("1");

        assignQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                pd.dismiss();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final TableRow r1=new TableRow(Teachers_Admin.this);
                    r1.setClipToPadding(false);
                    num++;
                    final TextView asl= new TextView(Teachers_Admin.this);
                    final TextView asub= new TextView(Teachers_Admin.this);
                    final TextView aname= new TextView(Teachers_Admin.this);
                    sub=snapshot.child("course").getValue().toString();
                    name = snapshot.child("teacherName").getValue().toString();
                    sl =snapshot.child("slno").getValue().toString();
                    asub.setText(sub);
                    aname.setText(name);
                    asl.setText(sl);
                    r1.setClickable(true);
                    r1.addView(asl);
                    r1.addView(asub);
                    r1.addView(aname);
                    final com.google.android.material.floatingactionbutton.FloatingActionButton edit = new com.google.android.material.floatingactionbutton.FloatingActionButton(Teachers_Admin.this);
                    edit.setClickable(true);
                    edit.setId(Integer.parseInt(sl));
                    edit.setImageDrawable(getResources().getDrawable(R.drawable.edit1));
                    edit.setClickable(true);
                    final com.google.android.material.floatingactionbutton.FloatingActionButton delete = new com.google.android.material.floatingactionbutton.FloatingActionButton(Teachers_Admin.this);
                    delete.setClickable(true);
                    delete.setId(Integer.parseInt(sl));
                    delete.setImageDrawable(getResources().getDrawable(R.drawable.delete));
                    delete.setClickable(true);
                    r1.addView(edit);
                    r1.addView(delete);
                    t1.addView(r1);
                    r1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent5 = new Intent(Teachers_Admin.this, Teachers_More.class);
                            TableRow row=(TableRow)v;
                            intent5.putExtra("Sl", ((TextView)(row.getChildAt(0))).getText());
                            intent5.putExtra("ID", ID);
                            startActivity(intent5);
                        }
                    });
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent6 = new Intent(Teachers_Admin.this, Teachers_Edit.class);
                            com.google.android.material.floatingactionbutton.FloatingActionButton button=(com.google.android.material.floatingactionbutton.FloatingActionButton)v;
                            Integer ab=v.getId();
                            intent6.putExtra("Sl", ab);
                            intent6.putExtra("Edit", 1);
                            intent6.putExtra("ID", ID);
                            startActivity(intent6);
                            finish();
                        }
                    });
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Teachers_Admin.this,"Deleted Successfully.", Toast.LENGTH_SHORT).show();
                            com.google.android.material.floatingactionbutton.FloatingActionButton button=(com.google.android.material.floatingactionbutton.FloatingActionButton)v;
                            Integer ab=v.getId();
                            DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("Teachers").child(ID).child(""+ab);
                            ref1.removeValue();
                            Intent intent7 = new Intent(Teachers_Admin.this, Teachers_Admin.class);
                            intent7.putExtra("Sl",""+1);
                            intent7.putExtra("ID",ID);
                            startActivity(intent7);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
            }

        });
        /*Intent i = getIntent();
        String m =i.getStringExtra("Sl");
        if(m!=null)
        {
            number=Integer.parseInt(m);
            if (lastsl<number) lastsl=number;
            int n=1;
            while(n<=lastsl)
            {
                ref= FirebaseDatabase.getInstance().getReference().child("Teachers").child(String.valueOf(n));
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final TableRow r1=new TableRow(TeachersAdmin.this);
                        r1.setClipToPadding(false);
                        final TextView acourse= new TextView(TeachersAdmin.this);
                        final TextView aname= new TextView(TeachersAdmin.this);
                        final TextView aroom= new TextView(TeachersAdmin.this);
                        final TextView asl= new TextView(TeachersAdmin.this);
                        //final TextView amail= new TextView(TeachersAdmin.this);
                        serialNo = snapshot.child("slno").getValue().toString();
                        course=snapshot.child("course").getValue().toString();
                        name = snapshot.child("teacherName").getValue().toString();
                        room = snapshot.child("roomNo").getValue().toString();
                        ph = snapshot.child("phNo").getValue().toString();
                        mail = snapshot.child("mail").getValue().toString();
                        acourse.setText(course);
                        asl.setText(serialNo);
                        aname.setText(name);
                        aroom.setText(room);
                        r1.setClickable(true);
                        r1.addView(asl);
                        r1.addView(acourse);
                        r1.addView(aname);
                        //r1.addView(aroom);
                        final com.google.android.material.floatingactionbutton.FloatingActionButton edit = new com.google.android.material.floatingactionbutton.FloatingActionButton(TeachersAdmin.this);
                        edit.setClickable(true);
                        edit.setId(Integer.parseInt(serialNo));
                        edit.setImageDrawable(getResources().getDrawable(R.drawable.edit1));
                        edit.setClickable(true);
                        final com.google.android.material.floatingactionbutton.FloatingActionButton delete = new com.google.android.material.floatingactionbutton.FloatingActionButton(TeachersAdmin.this);
                        delete.setClickable(true);
                        edit.setId(Integer.parseInt(serialNo));
                        delete.setImageDrawable(getResources().getDrawable(R.drawable.delete));
                        delete.setClickable(true);
                        r1.addView(edit);
                        r1.addView(delete);
                        t1.addView(r1);
                        r1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent5 = new Intent(TeachersAdmin.this, TeachersMore.class);
                                TableRow row=(TableRow)v;
                                intent5.putExtra("Sl", ((TextView)(row.getChildAt(0))).getText());
                                startActivity(intent5);
                            }
                        });
                        edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent6 = new Intent(TeachersAdmin.this, TeachersEdit.class);
                                com.google.android.material.floatingactionbutton.FloatingActionButton button=(com.google.android.material.floatingactionbutton.FloatingActionButton)v;
                                Integer ab=v.getId();
                                intent6.putExtra("Sl", ab);
                                intent6.putExtra("Edit", 1);
                                startActivity(intent6);
                            }
                        });
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                com.google.android.material.floatingactionbutton.FloatingActionButton button=(com.google.android.material.floatingactionbutton.FloatingActionButton)v;
                                Integer ab=v.getId();
                                DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("StudentBlog").child(""+ab);
                                ref1.removeValue();
                                Intent intent7 = new Intent(TeachersAdmin.this, TeachersAdmin.class);
                                intent7.putExtra("Sl",""+1);
                                startActivity(intent7);
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
                n++;
            }
        }*/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=num+1;
                Intent intent2 = new Intent(Teachers_Admin.this,Teachers_Edit.class);
                intent2.putExtra("Sl",num);
                intent2.putExtra("ID", ID);
                startActivity(intent2);
                finish();
            }
        });
    }
}