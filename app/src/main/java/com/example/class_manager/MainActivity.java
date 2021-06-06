package com.example.class_manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    EditText Classid;
    Button UserClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //User Login Enters the Class ID

        //Text field
        Classid=findViewById(R.id.Classid);
        //Button
        UserClass=findViewById(R.id.UserClass);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        Button logout=findViewById(R.id.LogoutBtn);
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("TAG","Helloooooooooooooo");
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        UserClass.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String classid=Classid.getText().toString().trim();
                if(TextUtils.isEmpty(Classid.getText()))
                {
                    Classid.setError("Enter Class ID");
                }
                //function call to upload data
                else upload(classid);

            }
        });

    }
    private void upload(String classid) {


        // String id= UUID.randomUUID().toString();
        //String classes;
//        classes = String.format("")
        FirebaseUser user=fAuth.getCurrentUser();
        Map<String,Object> doc=new HashMap<>();
        doc.put("id",classid);


        fStore.collection("Users").document(user.getUid()).update(doc)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        //this will be called when data is added successfully

                        Toast.makeText(MainActivity.this,"Entering...",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),User_Class.class));
                        finish();

                    }

                }).addOnFailureListener(new OnFailureListener(){

            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e("TAG", "Message");
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}