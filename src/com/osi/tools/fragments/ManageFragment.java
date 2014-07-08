package com.osi.tools.fragments;

import java.io.File;
import java.util.ArrayList;


import com.osi.tools.R;
import com.osi.tools.adapters.ManageListAdapter;
import com.osi.tools.bean.ManageBean;
import com.osi.tools.listeners.ManageClickListner;
import com.osi.tools.tables.ManageTable;
import com.osi.tools.utils.CalllogsBackup;
import com.osi.tools.utils.ContactsBackup;
import com.osi.tools.utils.SmsBackup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class ManageFragment extends Fragment{
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public TextView filetype,filename,filedate;
	public ListView managelistview;
	public RelativeLayout operationlayout;

	public Button delete,email,restore,back;
	public ManageTable mtable;
	public int[] drawbles = new int[]{R.drawable.icon_calllog,R.drawable.icon_sms,R.drawable.icon_contacts};
	public String[] typearray = {"","Call Logs","SMS","Contacts"};
	public ManageListAdapter madapter;
	public CalllogsBackup cbackup;
	public ManageBean clickbean;
	public boolean checkpress;
	public ProgressDialog pdialog;
	public SmsBackup smsbackup;
	public ContactsBackup contactbackup;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_manage, container, false);
		
		initializeVaribles(view);
		initLastUpdateFile();
		updatedatabase();
		initAdapter();
		visiblenormalloyt();
		addlongpresslistner();

		delete.setOnClickListener(new ManageClickListner(ManageFragment.this));
		restore.setOnClickListener(new ManageClickListner(ManageFragment.this));
		email.setOnClickListener(new ManageClickListner(ManageFragment.this));
		back.setOnClickListener(new ManageClickListner(ManageFragment.this));

		
	return view;	
	}
	
	public void initializeVaribles(View view)
	{

		filetype = (TextView)view.findViewById(R.id.manageapph);
		filename = (TextView)view.findViewById(R.id.managename);
		filedate = (TextView)view.findViewById(R.id.managedate);
		managelistview  = (ListView)view.findViewById(R.id.managelist);
		operationlayout = (RelativeLayout)view.findViewById(R.id.longpresslayout);
		delete = (Button)view.findViewById(R.id.deletefilebt);
		restore = (Button)view.findViewById(R.id.restorebt);
		email = (Button)view.findViewById(R.id.emailbt);
		back = (Button)view.findViewById(R.id.backarrowbt);
	


	}

	public void initAdapter()
	{

		mtable = new ManageTable(getActivity());

		ArrayList<ManageBean> arraybean = mtable.getalldetails();
		mtable.close();



		madapter = new ManageListAdapter(ManageFragment.this, arraybean);

		managelistview.setAdapter(madapter);

	}

	public void initLastUpdateFile()
	{
		mtable = new ManageTable(getActivity());

		ManageBean bean = mtable.getlastdetails();
		mtable.close();

		if(bean!=null)
		{

			filetype.append(typearray[Integer.parseInt(bean.getType())]);

			filename.append(bean.getName());
			filedate.append(bean.getDate());
		}

	}

	public void visiblenormalloyt()
	{

	
		operationlayout.setVisibility(View.GONE);

	}

	public void visibleoperationlyt()
	{

		
		operationlayout.setVisibility(View.VISIBLE);
		Animation anim = AnimationUtils.loadAnimation(getActivity(),R.animator.scale);
		operationlayout.startAnimation(anim);

	}

	public void addlongpresslistner()
	{

		managelistview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {


				visibleoperationlyt();

				clickbean = (ManageBean)madapter.getItem(position);
				madapter.setSelectedIndex(position);
				madapter.notifyDataSetChanged();

				return false;
			}
		});

	}

	public void updatedatabase()
	{
		mtable = new ManageTable(getActivity());

		ArrayList<ManageBean> arraybean = mtable.getalldetails();

		for(int i=0;i<arraybean.size();i++)
		{
			ManageBean mb = arraybean.get(i);

			String path = mb.getPath();
			boolean ch = checkfile(path.trim());
			if(ch==false)
			{
				mtable.delete(mb.getId());
			}

		}

		mtable.close();

	}

	public boolean checkfile(String path)
	{
		boolean che = false;

		File f = new File(path);

		if(f.exists())
		{
			che = true;
		}else
		{
			che= false;
		}

		return che;
	}

	

}
