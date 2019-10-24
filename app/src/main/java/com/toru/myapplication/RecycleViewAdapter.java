package com.toru.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {


    //private final PublishSubject<String> onClickSubject = PublishSubject.create();
   Context mContext;
   List<Message> mdata;

    public RecycleViewAdapter(Context mContext, List<Message> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvContact.setText(mdata.get(position).getContactNumber());
        holder.tvbody.setText(mdata.get(position).getMessageText());


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContact;
        private TextView tvbody;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContact = itemView.findViewById(R.id.username);
            tvbody = itemView.findViewById(R.id.usermessage);
            img = itemView.findViewById(R.id.photo);


        }
    }
}
