package com.osi.tools.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class FileUtils {

	Context context;
	public FileUtils(Context con)
	{
		this.context = con;
	}
	
	
	public void writeFile(String response)
	{
		try
		{
		FileOutputStream fos = context.openFileOutput("responsecache.dat", Context.MODE_PRIVATE);
		
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(response);
		os.close();
		Log.d("fil", "ss");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String ReadFile()
	{
		String response=null;
		try
		{
		FileInputStream fis = context.openFileInput("responsecache.dat");
		ObjectInputStream is = new ObjectInputStream(fis);
		 response = (String) is.readObject();
		is.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
}
