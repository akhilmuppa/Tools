package com.osi.tools.fragments;

import com.osi.tools.R;

import com.osi.tools.bean.SettingBean;
import com.osi.tools.listeners.SettingClickListner;
import com.osi.tools.tables.SettingTable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SettingsFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public Button save;
	public ToggleButton blockswitch;
	public CheckBox callssetting;
	public SettingTable stable;
	public String bloakval;
	public RadioButton intrd,extrd;
	public TextView extpathtxt;
	public String storageval;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_settings, container, false);
		
		initializeVariables(view);

		addlistnerforSwitch();

		save.setOnClickListener(new SettingClickListner(SettingsFragment.this));
		

		stable = new SettingTable(getActivity());

		SettingBean bean = stable.getalldetails();

		if(bean.getBlockopt().equalsIgnoreCase("on"))
		{

			blockswitch.setChecked(true);

		}else
		{
			blockswitch.setChecked(false);
		}

		if(bean.getCallopt().equalsIgnoreCase("true"))
		{

			callssetting.setChecked(true);

		}else
		{
			callssetting.setChecked(false);
		}

		if(bean.getIntstorage().equalsIgnoreCase("true"))
		{
			intrd.setChecked(true);
			extpathtxt.setVisibility(View.GONE);
			storageval="true";

		}else
		{
			extrd.setChecked(true);
			extpathtxt.setVisibility(View.VISIBLE);
			storageval="false";
		}
		
	return view;	
	}
	

	public void initializeVariables(View view)
	{

		save = (Button)view.findViewById(R.id.savebtn);
	
		blockswitch = (ToggleButton)view.findViewById(R.id.blockswitch);
		callssetting = (CheckBox)view.findViewById(R.id.chkboxall);
		intrd = (RadioButton)view.findViewById(R.id.intradio);
		extrd = (RadioButton)view.findViewById(R.id.extradio);
		extpathtxt = (TextView)view.findViewById(R.id.extpathtxt);


	}
	
	public void addlistnerforSwitch()
	{

		blockswitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				if(isChecked)
				{
					bloakval = "on";
				}else
				{
					bloakval = "off";
				}

			}
		});


	}
	public void radiobuttonClick(View v)
	{

		boolean checked = ((RadioButton)v).isChecked();


		if(v.getId()==R.id.intradio)
		{
			if(checked)
			{
				extpathtxt.setVisibility(View.GONE);
				storageval="true";
			}

		}else if(v.getId()==R.id.extradio)
		{
			extpathtxt.setVisibility(View.VISIBLE);
			storageval="false";
		}

	}


}
