package com.example.class_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Admin_class extends AppCompatActivity {

    Button adminannouncement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final StringBuilder fields = new StringBuilder("");
        final StringBuilder name = new StringBuilder("");
        final StringBuilder classname = new StringBuilder("");
        setContentView(R.layout.activity_admin_class);

        final TextView ClassID=(TextView)findViewById(R.id.ClassID);
        final TextView welcome=(TextView)findViewById(R.id.Welcome);
        final FirebaseFirestore db=FirebaseFirestore.getInstance();;
        FirebaseAuth fAuth;
        fAuth=FirebaseAuth.getInstance();
        FirebaseUser user=fAuth.getCurrentUser();
        DocumentReference ID = db.collection("Users").document(user.getUid());
        ID.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docu = task.getResult();
                    // StringBuilder fields = new StringBuilder("");
                    fields.append(docu.get("id"));
//                    classname.append(docu.get("ClassName"));
//                    final Map<String,Object> doc=new HashMap<>();
//                    doc.put("ClassName",classname);
                    //db.collection("Class").document(String.valueOf(fields)).set(doc);
                    Object OO=docu.getString("id");
                    ClassID.setText("Your Class Code: "+fields.toString());
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
                    welcome.setText("Hello "+name.toString()+" Welcome to "+classname.toString());
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
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Admin_class.this, Teachers_Admin.class);
                intent1.putExtra("ID",fields.toString());
                //Toast.makeText(Admin_class.this,"ID:"+fields, Toast.LENGTH_SHORT).show();
                startActivity(intent1);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Admin_class.this, Assignments_Admin.class);
                intent2.putExtra("ID",fields.toString());
                startActivity(intent2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Admin_class.this, Timetable_Admin.class);
                intent3.putExtra("ID",fields.toString());
                startActivity(intent3);
            }
        });
        adminannouncement = (Button) findViewById(R.id.adminannouncement);

        adminannouncement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */Intent intent = new Intent(Admin_class.this,
                        List_Activity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }
}