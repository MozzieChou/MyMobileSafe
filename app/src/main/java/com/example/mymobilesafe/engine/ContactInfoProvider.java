package com.example.mymobilesafe.engine;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.mymobilesafe.domain.ContactInfo;

/**
 * Created by Administrator on 2015/8/21.
 */
public class ContactInfoProvider {
    private Context context;

    public ContactInfoProvider(Context context) {
        this.context = context;
    }

    /**
     * 返回所有的联系人的信息
     *
     * @return
     */
    public List<ContactInfo> getContactInfos() {
        List<ContactInfo> infos = new ArrayList<ContactInfo>();//将所有联系人存入该集合
        //参数二：所要查询的列，即联系人的id。获取一个查询数据库所返回的结果集
        Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {//移动游标
            //用于封装每个联系人的具体信息
            ContactInfo info = new ContactInfo();
            //获得ID
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //获得姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            info.setName(name);
            //得到id后，我们通过该id来查询data表中的联系人的具体数据（data表中的data1中的数据）。参数二：null，会将所有的列返回回来

            Cursor dataCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = " + id, null, null);
            while (dataCursor.moveToNext()) {
                String phonenumber = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                info.setPhone(phonenumber);

                //dataCursor.getString(dataCursor.getColumnIndex("mimetype"))获取data1列中具体数据的数据类型，这里判断的是联系人的姓名
                /*if ("vnd.android.cursor.item/name".equals(dataCursor
                        .getString(dataCursor.getColumnIndex("mimetype")))) {
                    //dataCursor.getString(dataCursor.getColumnIndex("data1"))获取data1列中的联系人的具体数据
                    info.setName(dataCursor.getString(dataCursor
                            .getColumnIndex("data1")));
                } else if ("vnd.android.cursor.item/phone_v2".equals(dataCursor
                        .getString(dataCursor.getColumnIndex("mimetype")))) {//数据类型是否是手机号码
                    info.setPhone(dataCursor.getString(dataCursor
                            .getColumnIndex("data1")));
                }*/

            }
            //每查询一个联系人后就将其添加到集合中
            infos.add(info);
            info = null;
            dataCursor.close();//关闭游标

        }
        cursor.close();
        return infos;
    }
}
