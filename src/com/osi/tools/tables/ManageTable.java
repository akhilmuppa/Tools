package com.osi.tools.tables;

import java.util.ArrayList;

import com.osi.tools.bean.ManageBean;
import com.osi.tools.db.ToolsDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class ManageTable {


	private SQLiteDatabase db;

	public ManageTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}

	public void delete(int id)
	{

		db.delete(ToolsDataBase.TABLE_MANAGE, "id=" + id, null);

	}

	public void insertfiledetails(String type,String name,String date,String path,int icon)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.FILETYPE, type);
		initialValues.put(ToolsDataBase.FILENAME, name);
		initialValues.put(ToolsDataBase.FILEDATE, date);
		initialValues.put(ToolsDataBase.PATH, path);
		initialValues.put(ToolsDataBase.ICONTAG,icon);


		db.insert(ToolsDataBase.TABLE_MANAGE, null, initialValues);

	}

	public void updatefiledetails(int id,String type,String name,String date,String path,int icon)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.FILETYPE, type);
		initialValues.put(ToolsDataBase.FILENAME, name);
		initialValues.put(ToolsDataBase.FILEDATE, date);
		initialValues.put(ToolsDataBase.PATH, path);
		initialValues.put(ToolsDataBase.ICONTAG,icon);


		db.update(ToolsDataBase.TABLE_MANAGE, initialValues, "id=" +id, null);

	}


	public ArrayList<ManageBean> getalldetails()
	{

		ArrayList<ManageBean> arraybean = new ArrayList<ManageBean>();

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_MANAGE, new String[] {ToolsDataBase.ID,
							ToolsDataBase.FILETYPE,ToolsDataBase.FILENAME,
							ToolsDataBase.FILEDATE
							,ToolsDataBase.PATH,ToolsDataBase.ICONTAG},
							null, null, null, null, null);  

			if(c.moveToFirst())
			{
				do
				{
					ManageBean bean = new ManageBean();

					bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
					bean.setType(c.getString(c.getColumnIndex(ToolsDataBase.FILETYPE)));
					bean.setName(c.getString(c.getColumnIndex(ToolsDataBase.FILENAME)));
					bean.setDate(c.getString(c.getColumnIndex(ToolsDataBase.FILEDATE)));
					bean.setPath(c.getString(c.getColumnIndex(ToolsDataBase.PATH)));
					bean.setIcontag(c.getInt(c.getColumnIndex(ToolsDataBase.ICONTAG)));

					arraybean.add(bean);

				}while(c.moveToNext());
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return arraybean;
	}

	public ManageBean getlastdetails()
	{

		ManageBean bean = null;

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_MANAGE, new String[] {ToolsDataBase.ID,
							ToolsDataBase.FILETYPE,ToolsDataBase.FILENAME,
							ToolsDataBase.FILEDATE
							,ToolsDataBase.PATH,ToolsDataBase.ICONTAG},
							null, null, null, null, null);  

			if(c.moveToLast())
			{

				bean  = new ManageBean();

				bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
				bean.setType(c.getString(c.getColumnIndex(ToolsDataBase.FILETYPE)));
				bean.setName(c.getString(c.getColumnIndex(ToolsDataBase.FILENAME)));
				bean.setDate(c.getString(c.getColumnIndex(ToolsDataBase.FILEDATE)));
				bean.setPath(c.getString(c.getColumnIndex(ToolsDataBase.PATH)));
				bean.setIcontag(c.getInt(c.getColumnIndex(ToolsDataBase.ICONTAG)));


			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return bean;
	}


}
