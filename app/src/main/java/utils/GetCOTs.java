package utils;

import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import bean.COT;

import static android.content.ContentValues.TAG;

/**
 * Created by zhanglun on 2017/9/12.
 */

public class GetCOTs {
    public static void getCot(List<COT> list, Context context) {
        Cursor cursor=null;//用cursor指针读入
        try{
            ContentResolver cr = context.getContentResolver();
            cursor=cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while  (cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //list.add(displayName + "\n" + number);
                //COT cot = new COT(displayName, Long.parseLong(number));//long.parse得到数字的返回值
                list.add(new COT(displayName, number));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
        /*Log.d(TAG, "getCot: 123456");
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse("content://cot/");
        //contentresolver.query对内容URI字符串中的第二个变量进行读取。
        Cursor cursor = cr.query(uri,new String[]{"displayname","number"},null,null,null);
        while (cursor.moveToNext()) {
            String displayName = cursor.getString(cursor.getColumnIndex("displayname"));
            String number=cursor.getString(cursor.getColumnIndex("number"));//返回指定列的名称，如果不存在返回-1
            COT cot = new COT(displayName, Long.parseLong(number));//long.parse得到数字的返回值
            list.add(cot);
        }
        cursor.close();
        Log.d(TAG, "getCot: 654321");*/
    }

}
