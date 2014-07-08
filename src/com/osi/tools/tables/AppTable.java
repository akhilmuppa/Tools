package com.osi.tools.tables;

import java.util.ArrayList;

import com.osi.tools.bean.AppBean;
import com.osi.tools.db.ToolsDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AppTable {

	
	private SQLiteDatabase db;
	public AppTable(Context con)
	{
		db =    con.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION,null);
		
	}
	public void close() {
		db.close();
	}
	public void deleteEventrow(int id)
	{

		db.delete(ToolsDataBase.TABLE_APP_INFO, "id=" + id, null);

	}
	public long insertscheduleapp(String packagename,String appinformation,long dateandtime,String appname)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.PACKAGENAME,packagename);
		initialValues.put(ToolsDataBase.APPINFO,appinformation);
		initialValues.put(ToolsDataBase.DATE_TIME,dateandtime);
		initialValues.put(ToolsDataBase.APP_NAME,appname);
		


		long iid =db.insert(ToolsDataBase.TABLE_APP_INFO, null, initialValues);
       return iid;
	}
	
	public AppBean getdetails(long id)
	{
		
		AppBean bean = null;
		
		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_APP_INFO, new String[] {ToolsDataBase.ID,
							ToolsDataBase.PACKAGENAME,ToolsDataBase.APPINFO,ToolsDataBase.DATE_TIME,ToolsDataBase.APP_NAME},
							"id="+id, null, null, null, null);  


			if(c.moveToFirst())
			{
				bean = new AppBean();
				bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
				bean.setPkgname(c.getString(c.getColumnIndex(ToolsDataBase.PACKAGENAME)));
				bean.setAppinfo(c.getString(c.getColumnIndex(ToolsDataBase.APPINFO)));
				bean.setDatetime(c.getLong(c.getColumnIndex(ToolsDataBase.DATE_TIME)));
                bean.setAppname(c.getString(c.getColumnIndex(ToolsDataBase.APP_NAME)));
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
		
		return bean;
	}
	
	public ArrayList<AppBean> getalldetails()
	{
		
		ArrayList<AppBean> beanarray = new ArrayList<AppBean>();
		
		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_APP_INFO, new String[] {ToolsDataBase.ID,
							ToolsDataBase.PACKAGENAME,ToolsDataBase.APPINFO,ToolsDataBase.DATE_TIME,ToolsDataBase.APP_NAME},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{
                do
                {
                	AppBean bean = new AppBean();	
                
				bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
				bean.setPkgname(c.getString(c.getColumnIndex(ToolsDataBase.PACKAGENAME)));
				bean.setAppinfo(c.getString(c.getColumnIndex(ToolsDataBase.APPINFO)));
				bean.setDatetime(c.getLong(c.getColumnIndex(ToolsDataBase.DATE_TIME)));
				 bean.setAppname(c.getString(c.getColumnIndex(ToolsDataBase.APP_NAME)));
                beanarray.add(bean);
                }while(c.moveToNext());
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
		
		return beanarray;
	}
	
	public void deleterecord(long id){
		db.delete(ToolsDataBase.TABLE_APP_INFO, ToolsDataBase.ID + "=" + id, null);
	}
	
}
