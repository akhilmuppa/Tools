package com.osi.tools.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;










import com.osi.tools.fragments.BackupFragment;
import com.osi.tools.fragments.ManageFragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


public class CalllogsBackup {


	BackupFragment context;
	ManageFragment mcontext;

	public CalllogsBackup(BackupFragment con)
	{

		this.context = con;
	}

	public CalllogsBackup(ManageFragment mcon)
	{

		this.mcontext = mcon;
	}

	public void writelogstofile(File file,String fname)
	{

		ArrayList<String[]> arry = backupcallogs();

		try {
			CSVWriter cw = new CSVWriter(new FileWriter(file));

			cw.writeAll(arry);
			cw.close();

			context.updatedate(file,fname,"1",0);




		} catch (IOException e) {

			e.printStackTrace();
		}


	}

	public void readlogsfrmfile(File file)
	{

		try {
			CSVReader cr = new CSVReader(new FileReader(file));
			String[] row = null;

			int count=0;
			while((row=cr.readNext())!= null)
			{
				if(count>0)
				{
					restorecalllogs(row);
				}
				count++;
			}

			cr.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}catch (Exception e) {

			e.printStackTrace();
		}


	}

	public void restorecalllogs(String[] row)
	{
		try{

			insertcalllog(row[0],row[3], row[1], row[4], row[2]);

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void insertcalllog(String name,String number,String Date,String Type,String Duration)
	{
		try
		{
			ContentValues values = new ContentValues();
			values.put(CallLog.Calls.NUMBER, number);
			values.put(CallLog.Calls.DATE,Long.valueOf(Date.trim()));
			values.put(CallLog.Calls.DURATION,Long.valueOf(Duration.trim()));
			values.put(CallLog.Calls.TYPE,Integer.parseInt(Type));
			values.put(CallLog.Calls.CACHED_NAME, name);

			mcontext.getActivity().getContentResolver().insert(CallLog.Calls.CONTENT_URI, values);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<String[]> backupcallogs()
	{

		ArrayList<String[]> arry= new ArrayList<String[]>();

		String[] h = initializeheaders();

		arry.add(h);
		Cursor cursor=null;
		try
		{
			Uri   uri = Uri.withAppendedPath(CallLog.Calls.CONTENT_URI,""); 
			cursor = context.getActivity().getContentResolver().query(uri,null,null,null,null);

			if(cursor.moveToFirst())
			{


				do
				{


					String[] det = new String[5];

					det[0] = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
					det[1] = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
					det[2] = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
					det[3] = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
					det[4] = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));

					arry.add(det);

				}while(cursor.moveToNext());





			}


		}catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			cursor.close();
		}



		return arry;
	}

	public String[] initializeheaders()
	{

		String [] h = new String[5];

		h[0] = "Name";
		h[1] = "Date";
		h[2] = "Duration";
		h[3] = "Number";
		h[4] = "Type";

		return h;
	}


}
