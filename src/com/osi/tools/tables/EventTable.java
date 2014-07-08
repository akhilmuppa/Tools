package com.osi.tools.tables;

import java.util.ArrayList;

import com.osi.tools.bean.EventBean;
import com.osi.tools.db.ToolsDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EventTable {
	
	private SQLiteDatabase db;

	public EventTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}
	
	public void deleteEventrow(int id)
	{

		db.delete(ToolsDataBase.TABLE_Events, "id=" + id, null);

	}

	
	
	public void insertlastupdate(String eventname,String description,String startdate,String enddate)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.EVENTNAME,eventname);
		initialValues.put(ToolsDataBase.DESCRIPTION,description);
		initialValues.put(ToolsDataBase.STARTDATE,startdate);
		initialValues.put(ToolsDataBase.ENDDATE,enddate);


		db.insert(ToolsDataBase.TABLE_Events, null, initialValues);

	}
	

	public ArrayList<EventBean> getallDetails()
	{
		
		ArrayList<EventBean> arraybean = new ArrayList<EventBean>();
		
		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_Events, new String[] {ToolsDataBase.ID,
							ToolsDataBase.EVENTNAME,ToolsDataBase.DESCRIPTION,ToolsDataBase.STARTDATE
							,ToolsDataBase.ENDDATE},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

				do
				{
					EventBean bean = new EventBean();

					bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
					bean.setNameofEvent(c.getString(c.getColumnIndex(ToolsDataBase.EVENTNAME)));
					bean.setDescription(c.getString(c.getColumnIndex(ToolsDataBase.DESCRIPTION)));
					bean.setStartdate(c.getString(c.getColumnIndex(ToolsDataBase.STARTDATE)));
					bean.setEnddate(c.getString(c.getColumnIndex(ToolsDataBase.ENDDATE)));


					arraybean.add(bean);

				}while(c.moveToNext());

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return arraybean;
	}
	
	
	public int getcount()
	{
		Cursor c = null;

		try{
			 c =
					db.query(ToolsDataBase.TABLE_Events, new String[] {ToolsDataBase.ID,
							ToolsDataBase.EVENTNAME,ToolsDataBase.DESCRIPTION,ToolsDataBase.STARTDATE
							,ToolsDataBase.ENDDATE},
							null, null, null, null, null);  




		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c.getCount();
	}

	
}
