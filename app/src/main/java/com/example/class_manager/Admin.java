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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Admin extends AppCompatActivity {

    EditText Namee;
    Button create,classEnter;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String classname;
    DatabaseReference reff;
    boolean valid=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        classEnter=findViewById(R.id.classEnter);
        Namee=findViewById(R.id.Namee);
        create=findViewById(R.id.create);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        create.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(Namee.getText()))
                {
                    Namee.setError("Class Name is Required!");
                }
                else
                {
                    classname=Namee.getText().toString().trim();
                    //function call to upload data
                    upload(classname);
                }
            }
        });
        classEnter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Map<String,Object> x=new HashMap<>();
                final FirebaseUser user=fAuth.getCurrentUser();
                DocumentReference ID = fStore.collection("Users").document(user.getUid());
                ID.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot docu = task.getResult();
                            if (docu.exists()) {
                                if(docu.getString("id") != null){
                                    Log.d("TAG", "id exists");
                                    if(valid){
                                        fStore.collection("Users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                Toast.makeText(Admin.this, "Class Entered.", Toast.LENGTH_SHORT).show();
                                                /*Intent i=new Intent(Admin.this,Admin_class.class);
                                                i.putExtra("ClassName",classname);
                                                startActivity(i);*/
                                                startActivity(new Intent(getApplicationContext(), Admin_class.class));

                                                finish();
                                            }


                                        }).addOnFailureListener(new OnFailureListener() {

                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                                else {
                                    Toast.makeText(Admin.this, "Havent created class yet!!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                // Log.d(TAG,"What");
                                Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


    }
    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
    private void upload(String classname) {

        Log.d("TAG",classname);
        String id= UUID.randomUUID().toString();
        String classes;
//        classes = String.format("")
        Map<String,Object> doc=new HashMap<>();
        doc.put("id",id);
        reff= FirebaseDatabase.getInstance().getReference().child("Table").child(id);
        String a="No class";
        String r="-";
        Table table=new Table();
        table.setS1(a);
        table.setS2(a);
        table.setS3(a);
        table.setS4(a);
        table.setS5(a);
        table.setS6(a);
        table.setR1(r);
        table.setR2(r);
        table.setR3(r);
        table.setR4(r);
        table.setR5(r);
        table.setR6(r);
        reff.child("Friday").setValue(table);
        reff.child("Monday").setValue(table);
        reff.child("Tuesday").setValue(table);
        reff.child("Wednesday").setValue(table);
        reff.child("Thursday").setValue(table);
        doc.put("ClassName",classname);
        FirebaseUser user=fAuth.getCurrentUser();
        fStore.collection("Users").document(user.getUid()).update(doc)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        //this will be called when data is added successfully

                        Toast.makeText(Admin.this,"Done creating",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Admin_class.class));
                        finish();

                    }

                }).addOnFailureListener(new OnFailureListener(){

            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e("TAG", "Message");
                Toast.makeText(Admin.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Admin.class));
                finish();
            }
        });
    }
    public void logoutAdmin(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}