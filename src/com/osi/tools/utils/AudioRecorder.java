package com.osi.tools.utils;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.media.MediaRecorder;

public class AudioRecorder {
	
	
	  public static MediaRecorder myRecorder;
	  
	  
	  
	  public static void StartRecording(String filename,Context context)
	  {
		  String folder = "ToolsRecordings";
		  FileStorage fs = new FileStorage(context);
		 File file = fs.getexternalStorage(folder, filename);
		  
		
		  myRecorder = new MediaRecorder();
	      myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	      myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	      myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
	  /*   String out= Environment.getExternalStorageDirectory().
		  getAbsolutePath() +"/"+folder+"/"+filename;*/
	      myRecorder.setOutputFile(file.getAbsolutePath());
		  
	      
	      try {
	          myRecorder.prepare();
	          myRecorder.start();
	       } catch (IllegalStateException e) {
	          // start:it is called before prepare()
	    	  // prepare: it is called after start() or before setOutputFormat() 
	          e.printStackTrace();
	       } catch (IOException e) {
	           // prepare() fails
	           e.printStackTrace();
	        }
	      
	  }
	  
	  
	  public static void StopRecording()
	  {
		  
		  try {
		      myRecorder.stop();
		      myRecorder.release();
		      myRecorder  = null;
		  } catch (IllegalStateException e) {
				//  it is called before start()
				e.printStackTrace();
		   } catch (RuntimeException e) {
				// no valid audio/video data has been received
				e.printStackTrace();
		   }
	  }

}
