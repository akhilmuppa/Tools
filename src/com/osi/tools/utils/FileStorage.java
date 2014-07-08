package com.osi.tools.utils;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;

public class FileStorage {


	Context context;
	public FileStorage(Context con)
	{

		this.context = con;

	}


	public File getinternalStorage(String filepath,String filname)
	{

		ContextWrapper contextWrapper = new ContextWrapper(context);
		File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
		File myInternalFile = new File(directory , filname);

		return myInternalFile;
	}

	public File getexternalStorage(String filepath,String filname)
	{

		File  myExternalFile = null;

		if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {  

		} 
		else {

			/*//  myExternalFile = new File(context.getExternalFilesDir(filepath), filname);
			  File mExternalFile = new File("/sdcard/new_dir");
			  mExternalFile.mkdir();

			  myExternalFile = new File(mExternalFile, filname);

			  try {
				myExternalFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+filepath);
			if (!folder.exists()) {
				folder.mkdir();
			}
			myExternalFile = new File(folder,filname);
			if(!myExternalFile.exists()){
				try {
					myExternalFile.createNewFile();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}

		return myExternalFile;
	}
	private static boolean isExternalStorageReadOnly() {  
		String extStorageState = Environment.getExternalStorageState();  
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {  
			return true;  
		}  
		return false;  
	}  

	private static boolean isExternalStorageAvailable() {  
		String extStorageState = Environment.getExternalStorageState();  
		if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {  
			return true;  
		}  
		return false;  
	} 


}
