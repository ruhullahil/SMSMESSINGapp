package com.toru.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class fcous extends Fragment {
    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;
    private final static int REQUEST_CODE_PERMISSION_READ_SMS = 456;
    //String regexStr = "^[0-9&&[+]]*$";
    //String regexStr = "[a-zA-Z_0-9]";

    private  View v;
   private RecyclerView myRecycleView;
   private List<Message> dumy;


    public fcous() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fcous, container, false);
        myRecycleView = v.findViewById(R.id.frecycleview);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(),dumy);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapter);
        ItemClickSupport.addTo(myRecycleView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Message message = dumy.get(position);
                String s = message.getContactNumber()+"  "+message.getMessageText();
                Toast.makeText(getContext().getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),show_one.class);
                String str = message.getContactNumber();
                Bundle bundle = new Bundle();


                bundle.putString("stuf",str);


                i.putExtras(bundle);


                startActivity(i);



            }
        });
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dumy = new ArrayList<>();

        refreshInbox();
        /*
        dumy.add(new Message("01712666125","hi . how are you"));
        dumy.add(new Message("01714800880","hi thre "));
        dumy.add(new Message("37362762187879426","gywgeuigfvbhuvifgygvy hfvbuowv hu b h b hu yuv  b hu vo b uv"));
        dumy.add(new Message("58ty5t8t8y","ijbijibuhbiuw"));
        dumy.add(new Message("01712666125","hi . how are you"));
        dumy.add(new Message("01714800880","hi thre "));
        dumy.add(new Message("37362762187879426","gywgeuigfvbhuvifgygvy hfvbuowv hu b h b hu yuv  b hu vo b uv"));
        dumy.add(new Message("58ty5t8t8y","ijbijibuhbiuw"));
        dumy.add(new Message("01712666125","hi . how are you"));
        dumy.add(new Message("01714800880","hi thre "));
        dumy.add(new Message("37362762187879426","gywgeuigfvbhuvifgygvy hfvbuowv hu b h b hu yuv  b hu vo b uv"));
        dumy.add(new Message("58ty5t8t8y","ijbijibuhbiuw"));
        dumy.add(new Message("01712666125","hi . how are you"));
        dumy.add(new Message("01714800880","hi thre "));
        dumy.add(new Message("37362762187879426","gywgeuigfvbhuvifgygvy hfvbuowv hu b h b hu yuv  b hu vo b uv"));
        dumy.add(new Message("58ty5t8t8y","ijbijibuhbiuw"));
        dumy.add(new Message("01712666125","hi . how are you"));
        dumy.add(new Message("01714800880","hi thre "));
        dumy.add(new Message("37362762187879426","gywgeuigfvbhuvifgygvy hfvbuowv hu b h b hu yuv  b hu vo b uv"));
        dumy.add(new Message("58ty5t8t8y","ijbijibuhbiuw"));
        dumy.add(new Message("01712666125","hi . how are you"));
        dumy.add(new Message("01714800880","hi thre "));
        dumy.add(new Message("37362762187879426","gywgeuigfvbhuvifgygvy hfvbuowv hu b h b hu yuv  b hu vo b uv"));
        dumy.add(new Message("58ty5t8t8y","ijbijibuhbiuw"));
        dumy.add(new Message("01712666125","hi . how are you"));
        dumy.add(new Message("01714800880","hi thre "));
        dumy.add(new Message("37362762187879426","gywgeuigfvbhuvifgygvy hfvbuowv hu b h b hu yuv  b hu vo b uv"));
        dumy.add(new Message("58ty5t8t8y","ijbijibuhbiuw"));



         */
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
            if(ContactNumber.charAt(0)=='+')
            {
                dumy.add(new Message(ContactNumber,MessageText));
            }



        }while (smsInboxCursor.moveToNext());
    }


}
