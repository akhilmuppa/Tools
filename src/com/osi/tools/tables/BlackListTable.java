package com.osi.tools.tables;

import java.util.ArrayList;




import com.osi.tools.bean.BlackListBean;
import com.osi.tools.db.ToolsDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BlackListTable {


	private SQLiteDatabase db;

	public BlackListTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}

	public void deleteblacklistitem(int id)
	{

		db.delete(ToolsDataBase.TABLE_BLACKLIST, "id=" + id, null);

	}


	public void insertblacklist(String name,String number,String sms,String call,String from,String to,int type,String template)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.NAME, name);
		initialValues.put(ToolsDataBase.Phone, number);
		initialValues.put(ToolsDataBase.SMS, sms);
		initialValues.put(ToolsDataBase.CALL, call);
		initialValues.put(ToolsDataBase.FROM,from);
		initialValues.put(ToolsDataBase.TO, to);
		initialValues.put(ToolsDataBase.TYPE, type);
		initialValues.put(ToolsDataBase.TEMPLATE,template);


		db.insert(ToolsDataBase.TABLE_BLACKLIST, null, initialValues);

	}

	public void updateblacklist(int id,String name,String number,String sms,String call,String from,String to,int type,String template)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.NAME, name);
		initialValues.put(ToolsDataBase.Phone, number);
		initialValues.put(ToolsDataBase.SMS, sms);
		initialValues.put(ToolsDataBase.CALL, call);
		initialValues.put(ToolsDataBase.FROM,from);
		initialValues.put(ToolsDataBase.TO, to);
		initialValues.put(ToolsDataBase.TYPE, type);
		initialValues.put(ToolsDataBase.TEMPLATE,template);


		db.update(ToolsDataBase.TABLE_BLACKLIST, initialValues, "id=" +id, null);

	}

	public ArrayList<BlackListBean> getallDetails()
	{

		ArrayList<BlackListBean> dataholder = new ArrayList<BlackListBean>();

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_BLACKLIST, new String[] {ToolsDataBase.ID,
							ToolsDataBase.NAME,ToolsDataBase.Phone,ToolsDataBase.SMS
							,ToolsDataBase.CALL,ToolsDataBase.FROM,ToolsDataBase.TO,
							ToolsDataBase.TYPE,ToolsDataBase.TEMPLATE},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

				do
				{
					BlackListBean bean = new BlackListBean();

					bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
					bean.setName(c.getString((c.getColumnIndex(ToolsDataBase.NAME))));
					bean.setNumber(c.getString((c.getColumnIndex(ToolsDataBase.Phone))));
					bean.setSms(c.getString((c.getColumnIndex(ToolsDataBase.SMS))));
					bean.setFrom(c.getString((c.getColumnIndex(ToolsDataBase.FROM))));
					bean.setTo(c.getString((c.getColumnIndex(ToolsDataBase.TO))));
					bean.setCall(c.getString((c.getColumnIndex(ToolsDataBase.CALL))));
					bean.setType(c.getInt((c.getColumnIndex(ToolsDataBase.TYPE))));
					bean.setTemplate(c.getString((c.getColumnIndex(ToolsDataBase.TEMPLATE))));

					dataholder.add(bean);

				}while(c.moveToNext());

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return dataholder;
	}

	public BlackListBean getdetailfromid(int id)
	{

		BlackListBean bean = new BlackListBean();

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_BLACKLIST, new String[] {ToolsDataBase.ID,
							ToolsDataBase.NAME,ToolsDataBase.Phone,ToolsDataBase.SMS
							,ToolsDataBase.CALL,ToolsDataBase.FROM,ToolsDataBase.TO,
							ToolsDataBase.TYPE,ToolsDataBase.TEMPLATE},
							"id="+id, null, null, null, null);  


			if(c.moveToFirst())
			{

				do
				{



					bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
					bean.setName(c.getString((c.getColumnIndex(ToolsDataBase.NAME))));
					bean.setNumber(c.getString((c.getColumnIndex(ToolsDataBase.Phone))));
					bean.setSms(c.getString((c.getColumnIndex(ToolsDataBase.SMS))));
					bean.setCall(c.getString((c.getColumnIndex(ToolsDataBase.CALL))));
					bean.setType(c.getInt((c.getColumnIndex(ToolsDataBase.TYPE))));
					bean.setTemplate(c.getString((c.getColumnIndex(ToolsDataBase.TEMPLATE))));
					bean.setFrom(c.getString((c.getColumnIndex(ToolsDataBase.FROM))));
					bean.setTo(c.getString((c.getColumnIndex(ToolsDataBase.TO))));


				}while(c.moveToNext());

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return bean;
	}


}
