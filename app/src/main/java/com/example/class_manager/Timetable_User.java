package com.example.class_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class Timetable_User extends AppCompatActivity implements View.OnClickListener {

    public CardView card1, card2, card3,card4,card5;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__user);

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

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cv1:
                i = new Intent(this, mon2.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv2:
                i = new Intent(this, tue2.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv3:
                i = new Intent(this, wed2.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv4:
                i = new Intent(this, thurs2.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

            case R.id.cv5:
                i = new Intent(this, fri2.class);
                i.putExtra("ID", ID);
                startActivity(i);
                break;

        }

    }
}
