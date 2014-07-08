package com.osi.tools.tables;

import com.osi.tools.db.ToolsDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class BackupTable {

	private SQLiteDatabase db;

	public BackupTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}

	public void insertlastupdate(String lastupdate)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.LASTUPDATE, lastupdate);


		db.insert(ToolsDataBase.TABLE_BACKUP, null, initialValues);

	}

	public void updatelastupdate(int id,String lastupdate)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.LASTUPDATE, lastupdate);


		db.update(ToolsDataBase.TABLE_BACKUP, initialValues, "id=" +id, null);

	}

	public String getdetails()
	{

		String val ="";

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_BACKUP, new String[] {ToolsDataBase.ID,
							ToolsDataBase.LASTUPDATE},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

				val = c.getString(c.getColumnIndex(ToolsDataBase.LASTUPDATE));

			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return val;
	}

	public int getcount()
	{
		Cursor c = null;

		try{
			c =
					db.query(ToolsDataBase.TABLE_BACKUP, new String[] {ToolsDataBase.ID,
							ToolsDataBase.LASTUPDATE},
							null, null, null, null, null);  




		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return c.getCount();
	}


}
