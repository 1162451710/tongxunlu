package utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import java.util.List;

import bean.COT;

import static java.security.AccessController.getContext;

/**
 * Created by zhanglun on 2017/9/12.
 */

public class InsertCOTs {
    public static void insertCOTs(List<COT> listCOTs, Context context) {

        for (int i = 0; i < listCOTs.size(); i++) {
            COT cot = listCOTs.get(i);

        /* 往 raw_contacts 中添加数据，并获取添加的id号*/
            Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
            ContentResolver resolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            long contactId = ContentUris.parseId(resolver.insert(uri, values));

        /* 往 data 中添加数据（要根据前面获取的id号） */
            // 添加姓名
            uri = Uri.parse("content://com.android.contacts/data");
            values.put("raw_contact_id", contactId);
            values.put("mimetype", "vnd.android.cursor.item/name");
            values.put("data2", cot.getDisplayName());
            resolver.insert(uri, values);

            // 添加电话
            values.clear();
            values.put("raw_contact_id", contactId);
            values.put("mimetype", "vnd.android.cursor.item/phone_v2");
            values.put("data2", "2");
            values.put("data1", cot.getNumber());
            resolver.insert(uri, values);
        }
    }
}
