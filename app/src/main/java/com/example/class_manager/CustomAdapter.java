package com.example.class_manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder>{
    List_Activity listActivity;
    List<Model> modelList;
    User_Announcement user_announcement;
    Context context;



    public CustomAdapter(List_Activity listActivity,List<Model> modelList){
        this.listActivity=listActivity;
        this.modelList=modelList;
        //  this.context=context;
    }

    public CustomAdapter(User_Announcement user_announcement, List<Model> modelList) {
        this.user_announcement=user_announcement;
        this.modelList=modelList;
    }



    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //inflate layout

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_layout,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        //handle item clicks here
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {


            @Override
            public void onItemClick(View view, int position) {
                //this will be called when user click item
                Log.d(TAG,"Checking");
                //show data in toast on clicking
                String title=modelList.get(position).getTitle();
                String descr=modelList.get(position).getDescription();
                Toast.makeText(listActivity,title+"\n"+descr,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                //this will be called when user long click item

                //Creating AlertDialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivity);
                //option to display in dialog
                String[] options ={"Update","Delete"};
                builder.setItems(options,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(which == 0)
                        {

                            //update is clicked here
                            //get data
                            String id=modelList.get(position).getId();
                            String title=modelList.get(position).getTitle();
                            String description=modelList.get(position).getDescription();

                            //intent to start activity
                            Intent intent=new Intent(listActivity,Announcement.class);

                            //put data in intent
                            intent.putExtra("pId",id);
                            intent.putExtra("pTitle",title);
                            intent.putExtra("pDescription",description);
                            //start activity
                            listActivity.startActivity(intent);
                        }
                        if(which == 1)
                        {

                            //delete is clicked here
                            listActivity.deleteData(position);
                        }
                    }


                }).create().show();
            }
        });
        return viewHolder;
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//       //made changes here
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.model_layout, parent, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//bind views /set data
        Log.d("TAG",modelList.get(i).getTitle());
        Log.d("TAG",modelList.get(i).getDescription());
        viewHolder.mTitleTv.setText(modelList.get(i).getTitle());
        viewHolder.mDescriptionTv.setText(modelList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
