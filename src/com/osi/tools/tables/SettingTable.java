package com.osi.tools.tables;

import com.osi.tools.bean.SettingBean;
import com.osi.tools.db.ToolsDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SettingTable {


	private SQLiteDatabase db;
	public SettingTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}

	public void inserttemplate(String blockopt,String callopt,String intstorage)
	{

		ContentValues initialValues = new ContentValues();

		initialValues.put(ToolsDataBase.BLOCK,blockopt);
		initialValues.put(ToolsDataBase.CALLOPTION,callopt);
		initialValues.put(ToolsDataBase.STORAGE,intstorage);

		db.insert(ToolsDataBase.TABLE_SETTINGS, null, initialValues);

	}

	public void updatetemplate(int id,String blockopt,String callopt,String intstorage)
	{

		ContentValues initialValues = new ContentValues();

		initialValues.put(ToolsDataBase.BLOCK,blockopt);
		initialValues.put(ToolsDataBase.CALLOPTION,callopt);
		initialValues.put(ToolsDataBase.STORAGE,intstorage);

		db.update(ToolsDataBase.TABLE_SETTINGS, initialValues, "id=" +id, null);

	}

	public SettingBean getalldetails()
	{

		SettingBean bean = new SettingBean();

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_SETTINGS, new String[] {ToolsDataBase.ID,
							ToolsDataBase.BLOCK,ToolsDataBase.CALLOPTION,ToolsDataBase.STORAGE},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

				do
				{



					bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
					bean.setBlockopt(c.getString(c.getColumnIndex(ToolsDataBase.BLOCK)));
					bean.setCallopt(c.getString(c.getColumnIndex(ToolsDataBase.CALLOPTION)));
					bean.setIntstorage(c.getString(c.getColumnIndex(ToolsDataBase.STORAGE)));




				}while(c.moveToNext());

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return bean;
	}

	public int getsettingCount()
	{


		Cursor c = null;

		try{
			c =
					db.query(ToolsDataBase.TABLE_SETTINGS, new String[] {ToolsDataBase.ID,
							ToolsDataBase.BLOCK,ToolsDataBase.CALLOPTION},
							null, null, null, null, null);  




		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return c.getCount();
	}


}
