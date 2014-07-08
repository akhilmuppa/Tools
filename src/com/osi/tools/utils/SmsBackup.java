package com.osi.tools.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;




import com.osi.tools.fragments.BackupFragment;
import com.osi.tools.fragments.ManageFragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


public class SmsBackup {


	BackupFragment context;
	ManageFragment mcontext;
	public SmsBackup(BackupFragment con)
	{
		this.context = con;
	}

	public SmsBackup(ManageFragment mcon)
	{
		this.mcontext = mcon;
	}


	public void writesmstofile(File file,String fname)
	{

		ArrayList<String[]> arry = backupsms();

		try
		{
			CSVWriter cw = new CSVWriter(new FileWriter(file));

			cw.writeAll(arry);
			cw.close();

			context.updatedate(file,fname,"2",1);


		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void readsmsfrmfile(File file)
	{

		try {
			CSVReader cr = new CSVReader(new FileReader(file));
			String[] row = null;

			int count=0;
			while((row=cr.readNext())!= null)
			{
				if(count>0)
				{
					restoreSms(row);
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

	public void restoreSms(String[] row)
	{

		try{

			insertSms(row[0],row[1], row[2], row[3], row[4]);

		}catch(Exception e)
		{
			e.printStackTrace();
		}


	}

	public void insertSms(String address,String date,String read,String type,String body)
	{

		try
		{

			Uri uri = Uri.parse("content://sms/");
			ContentValues cv2 = new ContentValues();
			cv2.put("address",address);
			cv2.put("date", Long.valueOf(date));
			cv2.put("read", Integer.parseInt(read));
			cv2.put("type", Integer.parseInt(type));
			cv2.put("body", body);

			mcontext.getActivity().getContentResolver().insert(uri, cv2);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public ArrayList<String[]> backupsms()
	{

		ArrayList<String[]> arry= new ArrayList<String[]>();

		String[] h = initializeheaders();

		arry.add(h);

		Cursor cursor=null;
		try
		{
			Uri uri = Uri.parse("content://sms/");
			cursor = context.getActivity().getContentResolver().query(uri,null,null,null,null);

			if(cursor.moveToFirst())
			{

				do
				{

					String[] det = new String[5];

					det[0] = cursor.getString(cursor.getColumnIndexOrThrow("address"));
					det[1] = cursor.getString(cursor.getColumnIndexOrThrow("date"));
					det[2] = cursor.getString(cursor.getColumnIndexOrThrow("read"));
					det[3] = cursor.getString(cursor.getColumnIndexOrThrow("type"));
					det[4] = cursor.getString(cursor.getColumnIndexOrThrow("body"));

					arry.add(det);

				}while(cursor.moveToNext());
			}

		}catch(Exception e)
		{
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

		h[0] = "Address";
		h[1] = "Date";
		h[2] = "Read";
		h[3] = "Type";
		h[4] = "Body";

		return h;
	}

}
