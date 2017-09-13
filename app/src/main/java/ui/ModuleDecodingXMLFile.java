package ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.zhanglun.tongxunlu.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import bean.COT;
import utils.DecodingXMLfileByPull;
import utils.InsertCOTs;

/**
 * Created by zhanglun on 2017/9/12.
 */

public class ModuleDecodingXMLFile extends Activity {
    private ArrayList<COT> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decodingxmlfile);
    }

    public void decodingXML(View view) throws XmlPullParserException {
        File file = new File(Environment.getExternalStorageDirectory(),"cot.xml");
        FileInputStream fis;//FileInputStream(File file)通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定。
        System.out.println("xiazaixxxxxxxxxxxxxxxxxxxx");
        try {
            fis = new FileInputStream(file);

            DecodingXMLfileByPull decoder = null;
            decoder = new DecodingXMLfileByPull(fis, this);
            fis.close();
            list = decoder.getCotList();
            /*StringBuffer sb = new StringBuffer();
                for (COT cot : list) {
                    sb.append(cot.toString() + "\n");
                    System.out.println(cot);
                }
                tv.setText(sb);*/
            Toast.makeText(this, "恢复成功", Toast.LENGTH_SHORT).show();

            InsertCOTs.insertCOTs(list, this);
            System.out.println(" ");
        } catch (FileNotFoundException e) {
            System.out.println("未找到文件");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
     /*// Cursor cursor=null;//用cursor指针读入
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
    }*/

