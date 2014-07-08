package com.osi.tools.fragments;

import java.io.File;


import com.osi.tools.R;
import com.osi.tools.adapters.BackupListAdapter;
import com.osi.tools.bean.ManageBean;
import com.osi.tools.bean.SettingBean;
import com.osi.tools.listeners.BackUpClickListner;
import com.osi.tools.tables.BackupTable;
import com.osi.tools.tables.ManageTable;
import com.osi.tools.tables.SettingTable;
import com.osi.tools.utils.CalllogsBackup;
import com.osi.tools.utils.ContactsBackup;
import com.osi.tools.utils.DateUtils;
import com.osi.tools.utils.FileStorage;
import com.osi.tools.utils.SmsBackup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BackupFragment extends Fragment{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public TextView headertext;
	public ListView backuplistview;
	public LinearLayout backuplyt;
	public com.osi.tools.adapters.BackupListAdapter bAdpter;
	public boolean one,two,three;
	public BackupTable bt;
	public com.osi.tools.utils.FileStorage fstorage;
	public CalllogsBackup logbackup;
	public DateUtils dutils;
	public ManageBean mbean;
	public ManageTable mtable;
	public SettingTable stable;
	public SmsBackup smsbackup;
	public ContactsBackup contactbackup;
	
	public int[] drawbles = new int[]{R.drawable.icon_calllog,R.drawable.icon_sms,R.drawable.icon_contacts};
	String[] values = {"Call Logs","Sms","Contacts"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_backup, container, false);
		
		initializeVariables(view);
		backuplyt.setOnClickListener(new BackUpClickListner(BackupFragment.this));

		bt = new BackupTable(getActivity());

		String hval = bt.getdetails();

		headertext.append(hval);


		initAdapter();
		
		
	return view;	
	}
	
	public void initializeVariables(View view)
	{

		headertext = (TextView)view.findViewById(R.id.headertext);
		backuplistview = (ListView)view.findViewById(R.id.blackuplist);
		backuplyt = (LinearLayout)view.findViewById(R.id.Backuplyt);


	}
	

	public void initAdapter()
	{

		bAdpter = new BackupListAdapter(BackupFragment.this, drawbles, values);
		//backuplistview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		backuplistview.setAdapter(bAdpter);

	}

	public void getSelecteditem()
	{

		stable = new SettingTable(getActivity());
		SettingBean sbe = stable.getalldetails();

		dutils = new DateUtils();
		String tt =dutils.gettime();
		String filename = "";
		String file2 = "";
		String file3 = "";

		if(one==true)
		{
			filename = tt+"_calllogsbackup.csv";
		}
		if(two==true)
		{
			file2 = tt+"_smsbackup.csv"; 
		}
		if(three==true)
		{
			file3 = tt+"_contactbackup.csv";	 
		}

		com.osi.tools.utils.CustomDialog cdi = new com.osi.tools.utils.CustomDialog(getActivity());
		cdi.setdialog(filename, file2, file3,"Backup into File","BackUp");

		if(one == true)
		{


			fstorage = new FileStorage(getActivity());




			File ff;
			if(sbe.getIntstorage().equalsIgnoreCase("true"))
			{
				ff = fstorage.getinternalStorage("TelephonySettingsBackUp", filename);
			}else
			{

				ff=fstorage.getexternalStorage("TelephonySettingsBackUp",filename);

			}
			// File ff = fstorage.getinternalStorage("TelephonySettingsBackUp", filename);

			logbackup = new CalllogsBackup(BackupFragment.this);
			logbackup.writelogstofile(ff,filename);

		}

		if(two==true)
		{


			fstorage = new FileStorage(getActivity());

			File ff;
			if(sbe.getIntstorage().equalsIgnoreCase("true"))
			{
				ff = fstorage.getinternalStorage("TelephonySettingsBackUp", file2);
			}else
			{

				ff=fstorage.getexternalStorage("TelephonySettingsBackUp",file2);

			}

			smsbackup = new SmsBackup(BackupFragment.this);
			smsbackup.writesmstofile(ff, file2);


		}

		if(three == true)
		{


			fstorage = new FileStorage(getActivity());


			File ff;
			if(sbe.getIntstorage().equalsIgnoreCase("true"))
			{
				ff = fstorage.getinternalStorage("TelephonySettingsBackUp", file3);
			}else
			{

				ff=fstorage.getexternalStorage("TelephonySettingsBackUp",file3);

			}

			contactbackup = new ContactsBackup(BackupFragment.this);
			contactbackup.writecontactstoFile(ff, file3);


		}



		stable.close();
		//  Toast.makeText(getApplicationContext(),"Backup file is created",Toast.LENGTH_SHORT).show();


	}

	public void updatedate(File ff,String fname,String type,int tag)
	{

		bt = new BackupTable(getActivity());

		int coun =bt.getcount();
		dutils = new DateUtils();

		if(coun>0)
		{
			String dd = dutils.getpresentDate();
			String tt= dutils.gettimeval();
			bt.updatelastupdate(1,dd);
			headertext.setText("Last Backup Date: "+dd);

			mtable = new ManageTable(getActivity());
			mtable.insertfiledetails(type, fname,dd+" "+tt, ff.getAbsolutePath(),tag);
			mtable.close();

		}

		bt.close();


	}

	
}
