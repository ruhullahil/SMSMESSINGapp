package com.toru.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class show_one extends AppCompatActivity {
    private EditText userName,messageText;
    private RecyclerView Usermessages;
    private ImageButton send;
    private List<Message> oneuser  = new ArrayList<>();
    String contact ,phoneNo ,message;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

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
         Boolean set = bundle.getBoolean("set");
        userName.setText(contact);
        userName.setEnabled(set);

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
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              try {
                  String msg = messageText.getText().toString();
                  String phoneNum = userName.getText().toString();

                  SmsManager smsMan = SmsManager.getDefault();
                  smsMan.sendTextMessage(phoneNum, null, msg, null, null);
                  Toast.makeText(show_one.this,
                          "SMS send to " + phoneNum, Toast.LENGTH_LONG).show();
              } catch (Exception e) {
                  Toast.makeText(show_one.this,
                          e.getMessage(), Toast.LENGTH_LONG).show();

              }


                //sendSMSMessage();

            }
        });





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

    protected void sendSMSMessage() {

        phoneNo = userName.getText().toString();
        message = messageText.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}

