package com.toru.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class show_one extends AppCompatActivity {
    private EditText userName,messageText;
    private RecyclerView Usermessages;
    private ImageButton send;
    private List<Message> oneuser  = new ArrayList<>();
    String contact;

   //private List<Message> dumy = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one);
        userName =(EditText) findViewById(R.id.userid);
        messageText = (EditText) findViewById(R.id.textmessage);
        Usermessages = (RecyclerView) findViewById(R.id.show_recycleview);
        send = (ImageButton) findViewById(R.id.sendButton);

        Bundle bundle = getIntent().getExtras();

//Extract the data…
         contact = bundle.getString("stuf");
        userName.setText(contact);
        userName.setEnabled(false);

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




        showRecycleviewAdapter adapter = new showRecycleviewAdapter(oneuser);
        Usermessages.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Usermessages.setAdapter(adapter);

       refreshInbox();





    }

    public void refreshInbox(){

        ContentResolver cResolver = getContentResolver();
        Cursor smsInboxCursor = cResolver.query(Uri.parse("content://sms/inbox"),
                null, null, null, null);

        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");

        if(indexBody < 0 || !smsInboxCursor.moveToFirst()) return;

        do{
            String ContactNumber=  smsInboxCursor.getString(indexAddress) ;
            String MessageText = smsInboxCursor.getString(indexBody);
            //char t = ContactNumber.charAt(0);
            if(ContactNumber.equals(contact))
            {
                oneuser.add(new Message(ContactNumber,MessageText));
            }



        }while (smsInboxCursor.moveToNext());
    }
}

