package com.example.class_manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import java.util.UUID;

public class Announcement extends AppCompatActivity {
    //Views
    EditText mTitleEt,mDescriptionEt;
    Button mSaveBtn,mListBtn;
    //progress dialog
    ProgressDialog pd;
    FirebaseAuth fAuth;
    //FireStore instance
    FirebaseFirestore db;

    String pId,pTitle,pDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        //actionbar and its title
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Add data");
        //initialize views with its xml
        mTitleEt=findViewById(R.id.title);
        mDescriptionEt=findViewById(R.id.description);
        mSaveBtn=findViewById(R.id.saveBtn);
        mListBtn=findViewById(R.id.listBtn);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            //Update data
            actionBar.setTitle("Update Data");
            mSaveBtn.setText("Update");
            //get data
            pId=bundle.getString("pId");
            pTitle=bundle.getString("pTitle");
            pDescription=bundle.getString("pDescription");
            //set data
            mTitleEt.setText(pTitle);
            mDescriptionEt.setText(pDescription);

        } else {
            //New data
            actionBar.setTitle("Add Data");
            mSaveBtn.setText("Save");
        }
        fAuth=FirebaseAuth.getInstance();
        //progress dialog
        pd=new ProgressDialog(this);

        // FirebaseUser user=fAuth.getCurrentUser();
//        DocumentReference docRef = db.collection("Users").document(user.getUid());
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d("TAG", "No such document");
//                    }
//                } else {
//                    Log.d("TAG", "get failed with ", task.getException());
//                }
//            }
//        });

        //Firestore
        db=FirebaseFirestore.getInstance();
        //click button to upload data
        mSaveBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Bundle bundle = getIntent().getExtras();
                if(bundle!=null)
                {
                    //updating
                    //input data
                    String id=pId;
                    String title=mTitleEt.getText().toString().trim();
                    String description=mDescriptionEt.getText().toString().trim();

                    //function call to update data
                    updateData(id,title,description);
                }
                else {
                    //adding new

                    // input data
                    String title=mTitleEt.getText().toString().trim();
                    String description=mDescriptionEt.getText().toString().trim();
                    //function call to upload data
                    uploadData(title,description);
                }
//                String title=mTitleEt.getText().toString().trim();
//                String description=mDescriptionEt.getText().toString().trim();
//                //function call to upload data
//                uploadData(title,description);

            }
        });
        //click btn to start ListActivity
        mListBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Announcement.this,List_Activity.class));
                finish();
            }
        });

    }

    private void updateData(final String id, final String title, final String description) {
        //   set title of the progress bar
        pd.setTitle("Updating Data...");
        //show progress bar when user click save button
        pd.show();
        FirebaseUser user=fAuth.getCurrentUser();
        final StringBuilder fields = new StringBuilder("");
        DocumentReference ID = db.collection("Users").document(user.getUid());
        ID.get().addOnCompleteListener(new OnCompleteListener <DocumentSnapshot> () {
            @Override
            public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docu = task.getResult();
                    // StringBuilder fields = new StringBuilder("");
                    fields.append(docu.get("id"));
                    Object OO=docu.getString("id");
                    Log.d("BARBIE DOLL", String.valueOf(fields));
                    Log.d("Cindrella",OO.toString());

                    //char[] fields;
//String DONE=OO.toString();
                    db.collection("Class").document(String.valueOf(fields)).collection("announcement").document(id)
                            .update("title",title,"description",description)
                            .addOnCompleteListener(new OnCompleteListener<Void>(){

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    //called when updated successfully
                                    pd.dismiss();
                                    Toast.makeText(Announcement.this,"Updated...",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener(){

                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    //called when there is any error
                                    pd.dismiss();
                                    //get and show error message
                                    Toast.makeText(Announcement.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                    //   Log.d("CUTAY", OK);

                    //  collection("announcement").document(id)
                    //add this data

//                    OK[0] =String.valueOf(fields);
//                    Log.d("CUTAY", OK[0]);
                    //return OK;
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Log.d(TAG,"What");
                    }
                });

    }

    private void addOnCompleteListener(OnCompleteListener<Void> voidOnCompleteListener) {
    }

    private void uploadData(String title, String description) {
        //set title of the progress bar
        pd.setTitle("Adding Data to Firestore");
        //show progress bar when user click save button
        pd.show();
        //FirebaseUser user=fAuth.getCurrentUser();
        //random id for each data to be stored
        FirebaseUser user=fAuth.getCurrentUser();
        final StringBuilder fields = new StringBuilder("");
        final String id= UUID.randomUUID().toString();
        final Map<String,Object> doc=new HashMap<>();
        doc.put("id",id);
        doc.put("title",title);
        doc.put("description",description);
        //final String[] OK = {""};
        DocumentReference ID = db.collection("Users").document(user.getUid());
        ID.get().addOnCompleteListener(new OnCompleteListener <DocumentSnapshot> () {
            @Override
            public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docu = task.getResult();
                    // StringBuilder fields = new StringBuilder("");
                    fields.append(docu.get("id"));
                    Object OO=docu.getString("id");
                    Log.d("BARBIE DOLL", String.valueOf(fields));
                    Log.d("Cindrella",OO.toString());

                    //char[] fields;
//String DONE=OO.toString();

                    //   Log.d("CUTAY", OK);

                    //  collection("announcement").document(id)
                    //add this data
                    db.collection("Class").document(String.valueOf(fields)).collection("announcement").document(id).set(doc)
                            .addOnSuccessListener(new OnSuccessListener<Void>(){
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //this will be called when data is added successfully
                                    pd.dismiss();
                                    Toast.makeText(Announcement.this,"Uploaded..",Toast.LENGTH_SHORT).show();
                                }

                            }).addOnFailureListener(new OnFailureListener(){

                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //this will be called if there is any error while uploading
                            pd.dismiss();
                            //get and show error message
                            Log.e("TAG", "Message");
                            Toast.makeText(Announcement.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
//                    OK[0] =String.valueOf(fields);
//                    Log.d("CUTAY", OK[0]);
                    //return OK;
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Log.d(TAG,"What");
                    }
                });


        //code tht is working
//        String id= UUID.randomUUID().toString();
//        //char[] fields;
////String DONE=OO.toString();
//
//     //   Log.d("CUTAY", OK);
//        final Map<String,Object> doc=new HashMap<>();
//        doc.put("id",id);
//        doc.put("title",title);
//        doc.put("description",description);
//      //  collection("announcement").document(id)
//        //add this data
//        db.collection("Class").document(id).set(doc)
//                .addOnSuccessListener(new OnSuccessListener<Void>(){
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        //this will be called when data is added successfully
//                        pd.dismiss();
//                        Toast.makeText(Announcement.this,"Uploaded..",Toast.LENGTH_SHORT).show();
//                    }
//
//                }).addOnFailureListener(new OnFailureListener(){
//
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                //this will be called if there is any error while uploading
//                pd.dismiss();
//                //get and show error message
//                Log.e("TAG", "Message");
//                Toast.makeText(Announcement.this,e.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }


}
