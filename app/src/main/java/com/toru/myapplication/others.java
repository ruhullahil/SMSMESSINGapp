package com.toru.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class others extends Fragment {
    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;
    private final static int REQUEST_CODE_PERMISSION_READ_SMS = 456;


   View v;
    private RecyclerView myRecycleView;
    private List<Message> dumy;



    public others() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_others, container, false);
        myRecycleView = v.findViewById(R.id.oRccycleView);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(),dumy);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapter);
        ItemClickSupport.addTo(myRecycleView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Message message = dumy.get(position);
                String s = message.getContactNumber()+"  "+message.getMessageText();
                Toast.makeText(getContext().getApplicationContext(),s,Toast.LENGTH_SHORT).show();

            }
        });
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dumy = new ArrayList<>();
        refreshInbox();
    }

    public void refreshInbox(){
        ContentResolver cResolver = getActivity().getContentResolver();
        Cursor smsInboxCursor = cResolver.query(Uri.parse("content://sms/inbox"),
                null, null, null, null);

        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");

        if(indexBody < 0 || !smsInboxCursor.moveToFirst()) return;

        do{
            String ContactNumber=  smsInboxCursor.getString(indexAddress) ;
            String MessageText = smsInboxCursor.getString(indexBody);
            //char t = ContactNumber.charAt(0);
            if(!(ContactNumber.charAt(0)=='+'))
            {
                dumy.add(new Message(ContactNumber,MessageText));
            }



        }while (smsInboxCursor.moveToNext());
    }

}
