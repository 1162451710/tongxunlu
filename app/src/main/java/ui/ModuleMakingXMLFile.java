package ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.example.zhanglun.tongxunlu.R;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.String;
import java.lang.StringBuffer;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import bean.COT;
import utils.GetCOTs;

/**
 * Created by zhanglun on 2017/9/12.
 */
public class ModuleMakingXMLFile extends Activity {
    private ArrayList<COT> cott = new ArrayList<COT>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_makingxmlfile);
        GetCOTs.getCot(cott, this);
    }

    public void makingcotxml(View view) {
        //xml序列化
        XmlSerializer serializer = Xml.newSerializer();
        System.out.println("11111111111111111");
        try {
            //设置序列化器的输出路径和编码方式
            File file = new File(Environment.getExternalStorageDirectory(), "cot.xml");
            FileOutputStream os = new FileOutputStream(file);
            serializer.setOutput(os, "utf-8");
            //serializer.startDocument("xml声明的编码", 文档是否独立);
            serializer.startDocument("utf-8", true);
            // 根节点SMSS
            serializer.startTag(null, "COTT");
            for (COT cot : cott) {
                //sms节点
                serializer.startTag(null, "cot");


                serializer.attribute(null, "number", cot.getNumber() + "");

                //body节点
                serializer.startTag(null, "displayName");
                serializer.text(cot.getDisplayName());
                serializer.endTag(null, "displayName");

                serializer.endTag(null, "cot");
            }
            serializer.endTag(null, "COTT");
            //xml文件结束
            serializer.endDocument();
            //关闭资源
            os.close();
            Toast.makeText(this, "备份成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "备份失败", Toast.LENGTH_SHORT).show();
        }
    }



   /*public void makingcotxml(View view) {
        GetCOTs.getCot(cott, this);


        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<COTT>");
        for (int i = 0; i < cott.size(); i++) {
            sb.append("<COT ");

            sb.append("displayName=\"");
            sb.append(cott.get(i).getDisplayName());
            sb.append("\"");

            sb.append(">");

            sb.append("<number=");
            sb.append(cott.get(i).getNumber());
            sb.append(">");


            sb.append("</COT>");
        }
        sb.append("</COTT>");

        String data=sb.toString();

        try {
            File file = new File(Environment.getExternalStorageDirectory(),"cot1.xml");
            System.out.println("111111111");
            System.out.println("sb:"+sb);
            System.out.println("data:"+data);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());//write(byte[])
            fos.close();

            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();

        }
        /*try {
            File file = new File(Environment.getExternalStorageDirectory(),
                "cot1.xml");

            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        }*/
}



