package com.example.class_manager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class User_Class extends AppCompatActivity {

    Button aight;
    final StringBuilder fields = new StringBuilder("");
    final StringBuilder name = new StringBuilder("");
    final StringBuilder classname = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__class);

        final TextView welcome=(TextView)findViewById(R.id.Welcome);
        //final VideoView video=(VideoView) findViewById(R.id.vid);
       // Uri url=Uri.parse("android.resource://com.example.class_manager/raw/Vid");
        //video.setVideoPath("android.resource://com.example.class_manager/raw/Vid");
        FirebaseFirestore db=FirebaseFirestore.getInstance();;
        FirebaseAuth fAuth;
        fAuth=FirebaseAuth.getInstance();
        FirebaseUser user=fAuth.getCurrentUser();
        DocumentReference ID = db.collection("Users").document(user.getUid());
        ID.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docu = task.getResult();
                    // StringBuilder fields = new StringBuilder("");
                    fields.append(docu.get("id"));
                    Object OO=docu.getString("id");
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Log.d(TAG,"What");
                    }
                });

        ID = db.collection("Users").document(user.getUid());
        ID.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docu = task.getResult();
                    // StringBuilder fields = new StringBuilder("");
                    name.append(docu.get("FullName"));
                    classname.append(docu.get("ClassName"));
                    Object OO=docu.getString("FullName");
                    welcome.setText(" Welcome "+name.toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Log.d(TAG,"What");
                    }
                });
        Button logout = findViewById(R.id.LOGOUT);
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(User_Class.this, Teachers_User.class);
                intent1.putExtra("ID",fields.toString());
                startActivity(intent1);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(User_Class.this, Assignment_User.class);
                intent2.putExtra("ID",fields.toString());
                startActivity(intent2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(User_Class.this, Timetable_User.class);
                intent3.putExtra("ID",fields.toString());
                startActivity(intent3);
            }
        });
        aight = (Button) findViewById(R.id.adminannouncement);

        aight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */Intent intent = new Intent(User_Class.this,
                        User_Announcement.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
//      aight.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),User_Announcement.class));
//                finish();
//
//            }
//        });
    }
}