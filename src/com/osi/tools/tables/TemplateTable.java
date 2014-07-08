package com.osi.tools.tables;

import java.util.ArrayList;

import com.osi.tools.bean.TemplateBean;
import com.osi.tools.db.ToolsDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class TemplateTable {



	private SQLiteDatabase db;
	public TemplateTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}

	public void inserttemplate(String template)
	{

		ContentValues initialValues = new ContentValues();

		initialValues.put(ToolsDataBase.TEMPLATE,template);


		db.insert(ToolsDataBase.TABLE_TEMPLATES, null, initialValues);

	}

	public void updatetemplate(int id,String template)
	{

		ContentValues initialValues = new ContentValues();

		initialValues.put(ToolsDataBase.TEMPLATE,template);


		db.update(ToolsDataBase.TABLE_TEMPLATES, initialValues, "id=" +id, null);

	}

	public ArrayList<TemplateBean> getalldetails()
	{

		ArrayList<TemplateBean> array = new ArrayList<TemplateBean>();

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_TEMPLATES, new String[] {ToolsDataBase.ID,
							ToolsDataBase.TEMPLATE},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

				do
				{

					TemplateBean bean = new TemplateBean();

					bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
					bean.setTemplatename(c.getString(c.getColumnIndex(ToolsDataBase.TEMPLATE)));

					array.add(bean);


				}while(c.moveToNext());

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return array;
	}


	public TemplateBean getdetailfromid(int id)
	{

		TemplateBean bean=  new TemplateBean();

		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_TEMPLATES, new String[] {ToolsDataBase.ID,
							ToolsDataBase.TEMPLATE},
							"id="+id, null, null, null, null);  


			if(c.moveToFirst())
			{

				do
				{



					bean.setId(c.getInt(c.getColumnIndex(ToolsDataBase.ID)));
					bean.setTemplatename(c.getString(c.getColumnIndex(ToolsDataBase.TEMPLATE)));




				}while(c.moveToNext());

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}



		return bean;
	}

	public int gettemplateCount()
	{


		Cursor c = null;

		try{
			c =
					db.query(ToolsDataBase.TABLE_TEMPLATES, new String[] {ToolsDataBase.ID,
							ToolsDataBase.TEMPLATE},
							null, null, null, null, null);  




		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return c.getCount();
	}


}
