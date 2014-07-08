package com.osi.tools.fragments;

import java.util.ArrayList;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;
import com.osi.tools.adapters.SmsExpandAdapter;
import com.osi.tools.bean.SmsBean;
import com.osi.tools.tables.SmsDetailsTable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

public class ScheduleSmsMainFrag extends Fragment {

	public ExpandableListView expListView;
	public SmsDetailsTable detailtable;
	public LinearLayout add;
	public LinearLayout tapadd;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.frag_shedulesms_main, container, false);
		initialzeVaribles(view);
		setAdapter();

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ScheduleSmsFragment smsfrag = new ScheduleSmsFragment();


				((ToolsActivity)getActivity()).pushFragment(smsfrag);

			}
		});

		
		tapadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ScheduleSmsFragment smsfrag = new ScheduleSmsFragment();


				((ToolsActivity)getActivity()).pushFragment(smsfrag);
				
			}
		});

		return view;
	}

	public void initialzeVaribles(View view)
	{ 		expListView=(ExpandableListView)view.findViewById(R.id.listView1);
	add = (LinearLayout)view.findViewById(R.id.add);
	tapadd = (LinearLayout)view.findViewById(R.id.tapadd);

	}

	public void setAdapter()
	{

		SmsDetailsTable  table=new SmsDetailsTable(getActivity());
		ArrayList<SmsBean> list=table.getdetails();
        table.close();
		Log.d("si",""+list.size());

		if(list.size()==0)
		{
			tapadd.setVisibility(View.VISIBLE);
			expListView.setVisibility(View.GONE);
		}else
		{
			tapadd.setVisibility(View.GONE);
			expListView.setVisibility(View.VISIBLE);
			
		}
		
		SmsExpandAdapter seadpt = new SmsExpandAdapter(ScheduleSmsMainFrag.this,list);

		expListView.setAdapter(seadpt);

	}


}
