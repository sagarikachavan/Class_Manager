package com.example.class_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Timetable_Admin extends AppCompatActivity implements View.OnClickListener {

    public CardView card1, card2, card3,card4,card5;
    String ID;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__admin);

        Intent i = getIntent();
        ID = i.getStringExtra("ID");
        card1 = (CardView) findViewById((R.id.cv1));
        card2 = (CardView) findViewById((R.id.cv2));
        card3 = (CardView) findViewById((R.id.cv3));
        card4 = (CardView) findViewById((R.id.cv4));
        card5 = (CardView) findViewById((R.id.cv5));

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);

//        reff= FirebaseDatabase.getInstance().getReference().child("Table").child(ID);
//        String a="No class";
//        String r="-";
//        Table table=new Table();
//        table.setS1(a);
//        table.setS2(a);
//        table.setS3(a);
//        table.setS4(a);
//        table.setS5(a);
//        table.setS6(a);
//        table.setR1(r);
//        table.setR2(r);
//        table.setR3(r);
//        table.setR4(r);
//        table.setR5(r);
//        table.setR6(r);
//        reff.child("Friday").setValue(table);
//        reff.child("Monday").setValue(table);
//        reff.child("Tuesday").setValue(table);
//        reff.child("Wednesday").setValue(table);
//        reff.child("Thursday").setValue(table);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cv1:
                i = new Intent(this, mon.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv2:
                i = new Intent(this, tue.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv3:
                i = new Intent(this, wed.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv4:
                i = new Intent(this, thurs.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv5:
                i = new Intent(this, fri.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

        }

    }
}
