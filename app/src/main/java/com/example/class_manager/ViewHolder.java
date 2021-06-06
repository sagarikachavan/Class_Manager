package com.example.class_manager;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class ViewHolder extends RecyclerView.ViewHolder{

    // public StringCharacterIterator mTitleTv;
    public  TextView mTitleTv,mDescriptionTv;
    public  View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
        // ViewHolder holder = new ViewHolder(itemView);
        //item click

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Try");
//                Throwable e = null;
//                e.printStackTrace();
                //  setOnClickListener();
                if(mClickListener!=null)
                { mClickListener.onItemClick(v,getAdapterPosition());
                }}
        });
        //item long click listener
        itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                // int position = getAdapterPosition();
                // setOnClickListener(mClickListener);
                if(mClickListener!=null)
                { Log.d(TAG,"Try");
                    mClickListener.onItemLongClick(v,getAdapterPosition());}
                return true;
            }
        });
        //initialize views with mode_layout.xml
        // setOnClickListener(clickListener);
        mTitleTv = itemView.findViewById(R.id.rTitleTv);
        mDescriptionTv=itemView.findViewById(R.id.rDescriptionTv);

    }


    public ViewHolder.ClickListener mClickListener;



    //interface for click listener
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);

    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;
    }
}

