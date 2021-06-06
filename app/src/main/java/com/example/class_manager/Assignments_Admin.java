package com.example.class_manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Assignments_Admin extends AppCompatActivity {
    private Button next, assignments;
    Integer num=0;
    private TableLayout t1;
    private com.google.android.material.floatingactionbutton.FloatingActionButton add;
    String sub, assign, date, link,serialNo;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ProgressDialog pd=new ProgressDialog(Assignments_Admin.this);
        pd.setTitle("Loading Data...");
        pd.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments__admin);
        next = (Button) findViewById(R.id.b1);
        //assignments = (Button) findViewById(R.id.b6);
        t1= (TableLayout) findViewById(R.id.table);
        Intent i = getIntent();
        final String m = i.getStringExtra("ID");
        final TextView number=(TextView)findViewById(R.id.number);
        add = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.b2);
        ref= FirebaseDatabase.getInstance().getReference().child("Assignments").child(m);
        Query assignQuery = ref.orderByChild("slno").startAt("1");
        assignQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                pd.dismiss();
                number.setText("Total Number of assignments="+num);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    num =num + 1;
                    final TableRow r1=new TableRow(Assignments_Admin.this);
                    r1.setClipToPadding(false);
                    final TextView asl= new TextView(Assignments_Admin.this);
                    final TextView asub= new TextView(Assignments_Admin.this);
                    final TextView aassign= new TextView(Assignments_Admin.this);
                    final TextView adate= new TextView(Assignments_Admin.this);
                    final TextView alink= new TextView(Assignments_Admin.this);
                    number.setText("Total Number of assignments="+num);
                    sub=snapshot.child("subject").getValue().toString();
                    assign = snapshot.child("assignment").getValue().toString();
                    date = snapshot.child("date").getValue().toString();
                    link = snapshot.child("link1").getValue().toString()+snapshot.child("link2").getValue().toString();
                    serialNo =snapshot.child("slno").getValue().toString();
                    asub.setText(sub);
                    asl.setText(serialNo);
                    aassign.setText(assign);
                    r1.setClickable(true);
                    adate.setText(date);
                    alink.setText(link);
                    r1.addView(asl);
                    r1.addView(asub);
                    r1.addView(adate);
                    final com.google.android.material.floatingactionbutton.FloatingActionButton edit = new com.google.android.material.floatingactionbutton.FloatingActionButton(Assignments_Admin.this);
                    edit.setClickable(true);
                    edit.setId(Integer.parseInt(serialNo));
                    edit.setImageDrawable(getResources().getDrawable(R.drawable.edit1));
                    edit.setClickable(true);
                    final com.google.android.material.floatingactionbutton.FloatingActionButton delete = new com.google.android.material.floatingactionbutton.FloatingActionButton(Assignments_Admin.this);
                    delete.setClickable(true);
                    delete.setId(Integer.parseInt(serialNo));
                    delete.setImageDrawable(getResources().getDrawable(R.drawable.delete));
                    delete.setClickable(true);
                    r1.addView(edit);
                    r1.addView(delete);
                    t1.addView(r1);
                    r1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent5 = new Intent(Assignments_Admin.this, Assignments_More.class);
                            TableRow row=(TableRow)v;
                            intent5.putExtra("Sl", ((TextView)(row.getChildAt(0))).getText());
                            intent5.putExtra("ID", m);
                            startActivity(intent5);
                        }
                    });
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent6 = new Intent(Assignments_Admin.this, Assignments_Edit.class);
                            com.google.android.material.floatingactionbutton.FloatingActionButton button=(com.google.android.material.floatingactionbutton.FloatingActionButton)v;
                            Integer ab=v.getId();
                            intent6.putExtra("Sl", ab);
                            intent6.putExtra("ID", m);
                            intent6.putExtra("Edit", 1);
                            startActivity(intent6);
                            finish();
                        }
                    });
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Assignments_Admin.this,"Deleted Successfully.", Toast.LENGTH_SHORT).show();
                            com.google.android.material.floatingactionbutton.FloatingActionButton button=(com.google.android.material.floatingactionbutton.FloatingActionButton)v;
                            Integer ab=v.getId();
                            DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("Assignments").child(m).child(""+ab);
                            ref1.removeValue();
                            Intent intent7 = new Intent(Assignments_Admin.this, Assignments_Admin.class);
                            intent7.putExtra("ID",m);
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
        String m = i.getStringExtra("Sl");
        if (m != null) {
            number = Integer.parseInt(m);
            if (lastsl<number) lastsl=number;
            n=1;
            while (n <= lastsl) {
                ref = FirebaseDatabase.getInstance().getReference().child("StudentBlog").child(String.valueOf(n));
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final TableRow r1 = new TableRow(MainActivity.this);
                        r1.setClipToPadding(false);
                        long count = snapshot.getChildrenCount();
                        if(lastsl!=0 && count>0 )
                        {
                            serialNo = snapshot.child("slno").getValue().toString();
                            sub = snapshot.child("subject").getValue().toString();
                            assign = snapshot.child("assignment").getValue().toString();
                            date = snapshot.child("date").getValue().toString();
                            link1 = snapshot.child("link1").getValue().toString();
                            link2= snapshot.child("link2").getValue().toString();
                            final TextView asub = new TextView(MainActivity.this);
                            final TextView aassign = new TextView(MainActivity.this);
                            final TextView adate = new TextView(MainActivity.this);
                            final TextView asl = new TextView(MainActivity.this);
                            asl.setText(serialNo);
                            asub.setText(sub);
                            aassign.setText(assign);
                            adate.setText(date);
                            r1.setClickable(true);
                            r1.addView(asl);
                            r1.addView(asub);
                            //r1.addView(aassign);
                            r1.addView(adate);
                            final com.google.android.material.floatingactionbutton.FloatingActionButton edit = new com.google.android.material.floatingactionbutton.FloatingActionButton(MainActivity.this);
                            edit.setClickable(true);
                            edit.setId(Integer.parseInt(serialNo));
                            edit.setImageDrawable(getResources().getDrawable(R.drawable.edit1));
                            edit.setClickable(true);
                            final com.google.android.material.floatingactionbutton.FloatingActionButton delete = new com.google.android.material.floatingactionbutton.FloatingActionButton(MainActivity.this);
                            delete.setClickable(true);
                            delete.setId(Integer.parseInt(serialNo));
                            delete.setImageDrawable(getResources().getDrawable(R.drawable.delete));
                            delete.setClickable(true);
                            r1.addView(edit);
                            r1.addView(delete);
                            t1.addView(r1);
                            r1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent5 = new Intent(MainActivity.this, AssignMore.class);
                                    TableRow row=(TableRow)v;
                                    intent5.putExtra("Sl", ((TextView)(row.getChildAt(0))).getText());
                                    startActivity(intent5);
                                }
                            });
                            edit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent6 = new Intent(MainActivity.this, AssignEdit.class);
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
                                    Intent intent7 = new Intent(MainActivity.this, MainActivity.class);
                                    intent7.putExtra("Sl",""+1);
                                    startActivity(intent7);
                                }
                            });
                        }
                        }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
                n++;
            }
        }
*/
        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Assignments_Admin.this, TeachersAdmin.class);
                startActivity(intent1);
            }
        });*/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                Intent intent2 = new Intent(Assignments_Admin.this, Assignments_Edit.class);
                intent2.putExtra("Sl", num);
                intent2.putExtra("ID", m);
                intent2.putExtra("Edit", 0);
                startActivity(intent2);
                finish();
            }
        });
        /*assignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Assignments_Admin.this, Assignments.class);
                startActivity(intent3);
            }
        });*/
    }
}
