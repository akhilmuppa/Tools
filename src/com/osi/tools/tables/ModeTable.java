package com.osi.tools.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.osi.tools.db.ToolsDataBase;

public class ModeTable {
	
	private SQLiteDatabase db;

	public ModeTable(Context ct)
	{


		db =    ct.openOrCreateDatabase(ToolsDataBase.DATABASE_NAME,ToolsDataBase.DATABASE_VERSION, null);

	}


	public void close() {
		db.close();
	}
	
	public void insertmode(int mode)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.MODE,mode);


		db.insert(ToolsDataBase.TABLE_RINGER, null, initialValues);

	}
	
	public void insertbtnmode(String mode,int usermode,String audiomode)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.MODEBTN,mode);
		initialValues.put(ToolsDataBase.USERMODE,usermode);
		initialValues.put(ToolsDataBase.AUDIO,audiomode);


		db.insert(ToolsDataBase.TABLE_BUTTONMODE, null, initialValues);
		
	}
	
	
	
	public void updateusermode(int id,int mode)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.USERMODE,mode);


		db.update(ToolsDataBase.TABLE_BUTTONMODE,initialValues,"id="+id,null);
	}
	
	public void updatebtnmode(int id,String mode)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.MODEBTN,mode);


		db.update(ToolsDataBase.TABLE_BUTTONMODE,initialValues,"id="+id,null);
	}
	
	public void updateaudiomode(int id,String mode)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.AUDIO,mode);


		db.update(ToolsDataBase.TABLE_BUTTONMODE,initialValues,"id="+id,null);
	}
	
	public int getusermode()
	{
		int mode=0;
		
		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_BUTTONMODE, new String[] {ToolsDataBase.ID,
							ToolsDataBase.USERMODE},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

			mode = c.getInt(c.getColumnIndex(ToolsDataBase.USERMODE));

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mode;
	}
	
	public String getbtnmode()
	{
		String mode="false";
		
		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_BUTTONMODE, new String[] {ToolsDataBase.ID,
							ToolsDataBase.MODEBTN},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

			mode = c.getString(c.getColumnIndex(ToolsDataBase.MODEBTN));

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mode;
	}
	
	public String getaudiomode()
	{
		String mode="false";
		
		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_BUTTONMODE, new String[] {ToolsDataBase.ID,
							ToolsDataBase.AUDIO},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

			mode = c.getString(c.getColumnIndex(ToolsDataBase.AUDIO));

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mode;
	}
	
	public void updatemode(int id,int mode)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(ToolsDataBase.MODE,mode);

        db.update(ToolsDataBase.TABLE_RINGER,initialValues,"id="+id,null);
	    

	}
	
	public int getdetails()
	{
		
		int mode=0;
		
		try{
			Cursor c =
					db.query(ToolsDataBase.TABLE_RINGER, new String[] {ToolsDataBase.ID,
							ToolsDataBase.MODE},
							null, null, null, null, null);  


			if(c.moveToFirst())
			{

			mode = c.getInt(c.getColumnIndex(ToolsDataBase.MODE));

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return mode;
	}
	
	
	public int getmodecount()
	{
		
		Cursor c = null;
		
		try{
			 c =
					db.query(ToolsDataBase.TABLE_RINGER, new String[] {ToolsDataBase.ID,
							ToolsDataBase.MODE},
							null, null, null, null, null);  


					}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return c.getCount();
	}
	
	public int getbtncount()
	{

		Cursor c = null;

		try{
			c =
					db.query(ToolsDataBase.TABLE_BUTTONMODE, new String[] {ToolsDataBase.ID,
							ToolsDataBase.MODEBTN},
							null, null, null, null, null);  


		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return c.getCount();
	}




}
