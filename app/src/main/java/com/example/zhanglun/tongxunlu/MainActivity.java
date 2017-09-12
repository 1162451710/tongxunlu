package com.example.zhanglun.tongxunlu;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ui.ModuleDecodingXMLFile;
import ui.ModuleMakingXMLFile;

public class MainActivity extends AppCompatActivity {

    ListView contactsView;//定义contactview

    ArrayAdapter<String> adapter;

    List<String> contactsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsView=(ListView)findViewById(R.id.contact_view);//获取id
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);
        readContacts();
    }

    private void readContacts(){
        Cursor cursor=null;//用cursor指针读入
        try{
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while  (cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName + "\n" + number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }
    public void sbcontact(View v) {
        startMakingXMLFile();
    }

    public void dlcontact(View v) {
        startDecodingXMLFile();
    }

    private void startDecodingXMLFile() {
        Intent intent = new Intent(MainActivity.this,
                ModuleDecodingXMLFile.class);
        startActivity(intent);
    }

    private void startMakingXMLFile() {
        Intent intent = new Intent(MainActivity.this,
                ModuleMakingXMLFile.class);
        startActivity(intent);
    }
}
