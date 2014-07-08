package com.osi.tools.fragments;

import java.util.ArrayList;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;
import com.osi.tools.adapters.AppExpandAdapter;
import com.osi.tools.bean.AppBean;
import com.osi.tools.tables.AppTable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ScheduleAppMainFrag extends Fragment{


	public ListView listview;
	public LinearLayout addlyt;
	public LinearLayout taplyt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.frag_sheduleapp_main, container, false);
		initializeVaribales(view);
		setAdapter();

		addlyt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Fragment scheduleappFrag = new ScheduleAppFragment();
				((ToolsActivity)getActivity()).pushFragment(scheduleappFrag);

			}
		});


		taplyt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Fragment scheduleappFrag = new ScheduleAppFragment();
				((ToolsActivity)getActivity()).pushFragment(scheduleappFrag);
				
			}
		});
		
		
		return view;	
	}

	public void initializeVaribales(View view)
	{
		listview=(ListView)view.findViewById(R.id.listViewapp);
		addlyt = (LinearLayout)view.findViewById(R.id.addapp);
		taplyt = (LinearLayout)view.findViewById(R.id.tapapp);

	}

	public void setAdapter()
	{

		AppTable  table=new AppTable(getActivity());
		ArrayList<AppBean> list=table.getalldetails();

		table.close();
		
		if(list.size()==0)
		{
			taplyt.setVisibility(View.VISIBLE);
			listview.setVisibility(View.GONE);
		}else
		{
			taplyt.setVisibility(View.GONE);
			listview.setVisibility(View.VISIBLE);
			
		}

		Log.d("si",""+list.size());

		AppExpandAdapter adapt = new AppExpandAdapter(ScheduleAppMainFrag.this,list);

		listview.setAdapter(adapt);

	}


}
