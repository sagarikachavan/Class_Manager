package com.example.class_manager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class User_Announcement extends AppCompatActivity {

    List<Model> modelList=new ArrayList<>();
    RecyclerView mRecyclerView;
    //layout manager for recyclerview
    RecyclerView.LayoutManager layoutManager;

//Saga do fast
    //Firestore instance

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    CustomAdapter adapter;
    ProgressDialog pd;
    //private AlertDialog actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__announcement);
        //  setContentView(R.layout.activity_list);

        //actionbar and its title
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("List data");

        //init firestore
        db = FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();
        //initialize views
        mRecyclerView = findViewById(R.id.user_view);

        //set recycler view properties
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //init Progress Dialog
        pd = new ProgressDialog(this);

        //show data in recyclerView
        showData();
    }
    private void showData() {
        pd.setTitle("Loading Data....");
        //show progress dialog
        pd.show();
        FirebaseUser user=fAuth.getCurrentUser();
        final StringBuilder fields = new StringBuilder("");
        DocumentReference ID = db.collection("Users").document(user.getUid());
        ID.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docu = task.getResult();
                    // StringBuilder fields = new StringBuilder("");
                    fields.append(docu.get("id"));
                    // Object OO=docu.getString("id");
                    Log.d("BARBIE DOLL", String.valueOf(fields));
                    // Log.d("Cindrella",OO.toString());

                    //char[] fields;
//String DONE=OO.toString();

                    //   Log.d("CUTAY", OK);

                    //  collection("announcement").document(id)
                    //add this data
                    db.collection("Class").document(String.valueOf(fields)).collection("announcement")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    modelList.clear();
                                    //called when data is retrieved
                                    pd.dismiss();
                                    //show data
                                    for(DocumentSnapshot doc: task.getResult()){
                                        Model model=new Model(doc.getString("id"),
                                                doc.getString("title"),
                                                doc.getString("description"));
                                        modelList.add(model);

                                    }
                                    //adapter
                                    Log.d("TAG","Hello");
                                    adapter=new CustomAdapter(User_Announcement.this,modelList);
                                    //set adapter to recyclerview
                                    mRecyclerView.setAdapter(adapter);
                                }
                            }).addOnFailureListener(new OnFailureListener(){

                        @Override
                        public void onFailure(@NonNull Exception e) {

                            //called when there is any error while retireving
                            pd.dismiss();
                            Toast.makeText(User_Announcement.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
    }

}
