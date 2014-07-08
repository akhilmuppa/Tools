package com.osi.tools.tables;

import java.util.ArrayList;

import com.osi.tools.bean.SmsBean;
import com.osi.tools.db.ToolsDataBase;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class SmsDetailsTable {
	
	private SQLiteDatabase db;

	public SmsDetailsTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}
	
	public long insertsmsdetails(String phonenumber,String message,long date)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.PHONE, phonenumber);
		initialValues.put(ToolsDataBase.MESSAGE, message);
		initialValues.put(ToolsDataBase.TIMESTAMP, date);


	long iid=db.insert(ToolsDataBase.TABLE_SMSDETAILS, null, initialValues);
return iid;
	}

	public ArrayList<SmsBean> getdetails()
	{
		ArrayList< SmsBean> list=new ArrayList<SmsBean>();

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_SMSDETAILS, new String[] {ToolsDataBase.ID,
							ToolsDataBase.PHONE,ToolsDataBase.MESSAGE,ToolsDataBase.TIMESTAMP},
							null, null, null, null, null);  
	

			if(c.moveToFirst())
			{
				do{
					SmsBean bean=new SmsBean();
				bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
				bean.setPhonenumber(c.getString(c.getColumnIndex(ToolsDataBase.PHONE)));
				bean.setMessage(c.getString(c.getColumnIndex(ToolsDataBase.MESSAGE)));
				bean.setTimestamp(c.getLong(c.getColumnIndex(ToolsDataBase.TIMESTAMP)));
				bean.setChildren(new ArrayList<String>());
				bean.getChildren().add(c.getString(c.getColumnIndex(ToolsDataBase.MESSAGE)));
				list.add(bean);
				
				}while(c.moveToNext());
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return list;
	}
	
	public SmsBean getdetails(long id)
	{
			SmsBean bean=null;

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_SMSDETAILS, new String[] {ToolsDataBase.ID,
							ToolsDataBase.PHONE,ToolsDataBase.MESSAGE,ToolsDataBase.TIMESTAMP},
							"id="+id, null, null, null, null);  


			if(c.moveToFirst())
			{
				bean=new SmsBean();
				bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
				bean.setPhonenumber(c.getString(c.getColumnIndex(ToolsDataBase.PHONE)));
				bean.setMessage(c.getString(c.getColumnIndex(ToolsDataBase.MESSAGE)));
				bean.setTimestamp(c.getLong(c.getColumnIndex(ToolsDataBase.TIMESTAMP)));

			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return bean;
	}

	
	
	public int getcount()
	{
		Cursor c = null;

		try{
			c =
					db.query(ToolsDataBase.TABLE_SMSDETAILS, new String[] {ToolsDataBase.ID},
							null, null, null, null, null);  




		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return c.getCount();
	}
public void deleterecord(long id){
	db.delete(ToolsDataBase.TABLE_SMSDETAILS, ToolsDataBase.ID + "=" + id, null);
}

}
