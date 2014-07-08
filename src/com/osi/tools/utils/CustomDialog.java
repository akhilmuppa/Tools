package com.osi.tools.utils;




import com.osi.tools.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CustomDialog {


	Context context;
	public CustomDialog(Context con)
	{
		this.context = con;

	}


	protected static final int TIMER_RUNTIME = 10000; // in ms --> 10s
	protected boolean mbActive;
	Dialog dialog;

	public void setdialog(final String filename,final String file2,final String file3,String header,String title)
	{

		dialog = new Dialog(context);
		dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimations;
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog);

		final ProgressBar pd = (ProgressBar)dialog.findViewById(R.id.adprogress_progressBar);

		final Button but = (Button)dialog.findViewById(R.id.confirm);
		final LinearLayout hl = (LinearLayout)dialog.findViewById(R.id.lay);
		final LinearLayout hl1 = (LinearLayout)dialog.findViewById(R.id.lay1);
		final LinearLayout hl2 = (LinearLayout)dialog.findViewById(R.id.lay2);
		final TextView tvc = (TextView)dialog.findViewById(R.id.filehh);
		final TextView tvc1 = (TextView)dialog.findViewById(R.id.filehh1);
		final TextView tvc2 = (TextView)dialog.findViewById(R.id.filehh2);
		final TextView thh = (TextView)dialog.findViewById(R.id.theaderd);
		final TextView head = (TextView)dialog.findViewById(R.id.headert);
		thh.setVisibility(View.GONE);
		hl.setVisibility(View.GONE);
		hl1.setVisibility(View.GONE);
		hl2.setVisibility(View.GONE);
		but.setVisibility(View.GONE);

		head.setText(header);


	//	dialog.setTitle(title);


		final Thread timerThread = new Thread() {
			@Override
			public void run() {
				mbActive = true;
				try {
					int waited = 0;
					while(mbActive && (waited < TIMER_RUNTIME)) {
						sleep(200);
						if(mbActive) {
							waited += 200;
							if(null != pd) {
								// Ignore rounding error here
								final int progress = pd.getMax() * waited / TIMER_RUNTIME;
								pd.setProgress(progress);
							}
						}
					}

					((Activity) context).runOnUiThread(new Runnable() {
						@Override
						public void run() {


							if(filename.equalsIgnoreCase(""))
							{

							}else
							{
								tvc.setText(filename);
								hl.setVisibility(View.VISIBLE);
							}

							if(file2.equalsIgnoreCase(""))
							{

							}else
							{
								tvc1.setText(file2);
								hl1.setVisibility(View.VISIBLE);
							}

							if(file3.equalsIgnoreCase(""))
							{

							}else
							{
								tvc2.setText(file3);
								hl2.setVisibility(View.VISIBLE);
							}


							// tvc.setText(filename);
							but.setVisibility(View.VISIBLE);
							thh.setVisibility(View.VISIBLE);




						}
					});

				} catch(InterruptedException e) {
					// do nothing
				} finally {


				}


			}
		};
		timerThread.start();


		but.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				dialog.dismiss();

			}
		});


		dialog.show();
	}


}
