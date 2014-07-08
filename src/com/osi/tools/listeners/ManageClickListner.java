package com.osi.tools.listeners;

import java.io.File;













import com.osi.tools.R;
import com.osi.tools.fragments.ManageFragment;
import com.osi.tools.tables.ManageTable;
import com.osi.tools.tables.SmsDetailsTable;
import com.osi.tools.utils.CalllogsBackup;
import com.osi.tools.utils.ContactsBackup;
import com.osi.tools.utils.CustomDialog;
import com.osi.tools.utils.SmsBackup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ManageClickListner implements OnClickListener{


	ManageFragment context;

	public ManageClickListner(ManageFragment con)
	{

		this.context = con;

	}


	@Override
	public void onClick(View v) {


		if(v.getId()==R.id.deletefilebt)
		{

			final Dialog confirmd = new Dialog(context.getActivity());
			
			
			confirmd.requestWindowFeature(Window.FEATURE_NO_TITLE);
			
			confirmd.setContentView(R.layout.deletediaolg);
			
			
			Button yescn = (Button)confirmd.findViewById(R.id.yescld);
			Button nocn = (Button)confirmd.findViewById(R.id.nocld);
			
			yescn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					if(context.clickbean!=null)
					{
						File f = new File(context.clickbean.getPath().trim());
						boolean ch = f.delete();

						if(ch==true)
						{
							context.mtable = new ManageTable(context.getActivity());
							context.mtable.delete(context.clickbean.getId());
							context.mtable.close();
							context.initAdapter();
							Toast.makeText(context.getActivity(), "Deleted Successfully",Toast.LENGTH_SHORT).show();
						}
					}

					context.visiblenormalloyt();
					 confirmd.dismiss();
					
				}
			});
			
			nocn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					 confirmd.dismiss();
					
				}
			});
			
			confirmd.show();
			
			

		/*	AlertDialog.Builder adb = new AlertDialog.Builder(context.getActivity());

			adb.setTitle("Alert");

			adb.setMessage("Are you sure you want to remove ");

			adb.setIcon(android.R.drawable.ic_dialog_alert);

			adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					if(context.clickbean!=null)
					{
						File f = new File(context.clickbean.getPath().trim());
						boolean ch = f.delete();

						if(ch==true)
						{
							context.mtable = new ManageTable(context.getActivity());
							context.mtable.delete(context.clickbean.getId());
							context.mtable.close();
							context.initAdapter();
							Toast.makeText(context.getActivity(), "Deleted Successfully",Toast.LENGTH_SHORT).show();
						}
					}

					context.visiblenormalloyt();



				} 	
			});

			adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					context.visiblenormalloyt();
				}
			});	    
			adb.show();*/



		}else if(v.getId()==R.id.emailbt)
		{

			context.visiblenormalloyt();

			if(context.clickbean!=null)
			{

				File f = new File(context.clickbean.getPath().trim());
				Uri u1  =   null;
				u1  =   Uri.fromFile(f);
				Intent i = new Intent(Intent.ACTION_SEND);
				//i.setType("message/rfc822");
				i.setType("text/plain");
				//	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"To"});
				i.putExtra(Intent.EXTRA_STREAM, u1);
				i.putExtra(Intent.EXTRA_SUBJECT, context.clickbean.getName()+" Generated from Telephony Settings Application");
				i.putExtra(Intent.EXTRA_TEXT   , "");
				context.startActivity(Intent.createChooser(i, "Send mail..."));

			}

		}else if(v.getId() == R.id.restorebt)
		{

			//	context.pdialog = ProgressDialog.show(context, "Restoring Call Logs","");


			if(context.clickbean!=null)
			{
				File f = new File(context.clickbean.getPath().trim());

				CustomDialog cdi = new CustomDialog(context.getActivity());
				cdi.setdialog("","","","Restoring into File","Restore");



				if(context.clickbean.getType().equalsIgnoreCase("1"))
				{
					context.cbackup = new CalllogsBackup(context);
					context.cbackup.readlogsfrmfile(f);
				}

				if(context.clickbean.getType().equalsIgnoreCase("2"))
				{

					context.smsbackup = new SmsBackup(context);
					context.smsbackup.readsmsfrmfile(f);
				}

				if(context.clickbean.getType().equalsIgnoreCase("3"))
				{

					context.contactbackup = new ContactsBackup(context);
					context.contactbackup.readcontactfrmfile(f);
				}

			}


			//Toast.makeText(context, "Restore succesfully", Toast.LENGTH_SHORT).show();

			context.visiblenormalloyt();

			//context.pdialog.dismiss();


		}else if(v.getId() == R.id.backarrowbt)
		{
			context.visiblenormalloyt();
		}

	}

}
