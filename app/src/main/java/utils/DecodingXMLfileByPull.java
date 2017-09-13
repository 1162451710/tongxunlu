package utils;

import android.content.Context;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import bean.COT;

/**
 * Created by zhanglun on 2017/9/12.
 */
//用pull解析xml文件
public class DecodingXMLfileByPull {
    private ArrayList<COT> cotList = new ArrayList<COT>();//定义结果cotlist
    private Context context;

    public DecodingXMLfileByPull(InputStream is, Context context)
            throws XmlPullParserException, IOException {
        this.context = context;

        ////创建解析器XMLParse某个 XML 格式的字符串解析为 ModelMBean 对象
        XmlPullParser pullParser = Xml.newPullParser();//创建pull解析类
        int type = 0;
        try {
            //文件写到解析器
            pullParser.setInput(is, "utf-8");// 设置数据读取格式，解析的数据格式
            type = pullParser.getEventType();;// 获取每个节点的类型
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Toast.makeText(context, "写入失败", Toast.LENGTH_SHORT).show();
            return;
        }
        decoding(type, pullParser);
    }

    private void decoding(int type, XmlPullParser pullParser)
            throws XmlPullParserException, IOException {

        int i = 0;
        COT cot = null;
        while (type != XmlPullParser.END_DOCUMENT) {
            i++;
            switch (type) {
                case XmlPullParser.START_TAG:// 文档开始事件,可以进行数据初始化处理
                    String tagName = pullParser.getName(); //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值

                    if ("COTT".equals(tagName)) {//string.equal将此字符串与指定的对象比较。当且仅当该参数不为 null，并且是与此对象表示相同字符序列的 String 对象时，结果才为 true。
                    }else if ("cot".equals(tagName)) {
                        cot = new COT();
                        String nbStr = pullParser.getAttributeValue(0);//通过getAttributeValue(i)获取属性节点值
                        cot.setNumber(nbStr);

                    }else if ("displayName".equals(tagName)) {
                        String displayName = null;
                        displayName = pullParser.nextText();
                        cot.setDisplayName(displayName);
                        System.out.println(i + " diaplayName:" + displayName + " :"
                                + pullParser.getText());//??????
                    }
                    break;

                case XmlPullParser.END_TAG:
                    String endtagName = pullParser.getName();
                    if ("cot".equals(endtagName)) {
                        cotList.add(cot);
                        System.out.println(cot);
                        cot = null;
                    }
                    break;
            }
            type = pullParser.next();
        }
    }

    public ArrayList<COT> getCotList() {
        return cotList;
    }

}