package com.osi.tools;





import com.osi.tools.adapters.DrawerListAdapter;
import com.osi.tools.db.ToolsDataBase;
import com.osi.tools.fragments.AgeCalculatorfragment;
import com.osi.tools.fragments.BackupFragment;
import com.osi.tools.fragments.BlackListFragment;
import com.osi.tools.fragments.CalendarEventfragment;
import com.osi.tools.fragments.CurrencyConversionFragment;
import com.osi.tools.fragments.InterestCaluculator;
import com.osi.tools.fragments.ManageFragment;
import com.osi.tools.fragments.ScheduleAppMainFrag;
import com.osi.tools.fragments.ScheduleSmsMainFrag;
import com.osi.tools.fragments.SettingsFragment;
import com.osi.tools.tables.BackupTable;
import com.osi.tools.tables.EventTable;
import com.osi.tools.tables.ModeTable;
import com.osi.tools.tables.SettingTable;
import com.osi.tools.tables.TemplateTable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;




public class ToolsActivity extends FragmentActivity {

 
	
	

	public EventTable etable;
	private DrawerLayout mDrawerLayout;
	private LinearLayout mDrawer,fragmentholder;
	private ListView mDrawerList;
	private ImageView drawerbtn;
	public TextView titletxt;
	public DrawerListAdapter drawerlistadapter;
	public String[] values = { "Schedule Sms", "Schedule App", "Smart Profile",
			"Convertors", "Age Calculator", "Interest Calculator","Black List","Back Up","Manage Backup",
			"Settings"};
	private FragmentTransaction ft;
	public Fragment calendareventfragment,schedulesms,currencyconvertorfrag,scheduleappFrag;
	private static ToolsActivity instance;
	public int fragpos=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeVariables();
		instance = this;

		ToolsDataBase td = new ToolsDataBase(ToolsActivity.this);

		setDrawerList();
		setbtnmode();
		checkTemplates();
		checksetting();
		checkbackup();
		Log.d("fragpos", ""+fragpos);
		if(fragpos==0)
		{
		titletxt.setText(values[0]);

		ChangeFragment(0);
		}

		drawerbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(mDrawerLayout.isDrawerOpen(mDrawer)){
					mDrawerLayout.closeDrawer(mDrawer);
				}else{
					mDrawerLayout.openDrawer(mDrawer);
				}

			}
		});


		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				fragpos=position;
				titletxt.setText(values[position]);
				Log.d("fragposa", ""+fragpos);
				ChangeFragment(position);

				mDrawerLayout.closeDrawer(mDrawer);

			}
		});





	}
	
	public void setbtnmode()
	{
		ModeTable mtable  = new ModeTable(ToolsActivity.this);
		int count = mtable.getbtncount();
		if(count==0)
		{
		mtable.insertbtnmode("false",0,"false");
		}
		mtable.close();
		}

	public static ToolsActivity getInstance(){
		return instance;
	}

	public void initializeVariables()
	{


		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawer = (LinearLayout)findViewById(R.id.drawer);
		mDrawerList = (ListView)findViewById(R.id.drawer_list);
		drawerbtn = (ImageView)findViewById(R.id.titlebar_iv_menu);
		fragmentholder = (LinearLayout)findViewById(R.id.fragmentholder);
		titletxt = (TextView)findViewById(R.id.title);

	}

	public void setDrawerList()
	{


		drawerlistadapter = new DrawerListAdapter(ToolsActivity.this, values);
		mDrawerList.setAdapter(drawerlistadapter);


	}


	public void ChangeFragment(int pos) {

		switch (pos) {
		case 0:

			/*
			 * schedulesms = new ScheduleSmsFragment();
			 * pushFragment(schedulesms);
			 */
			Fragment sm = new ScheduleSmsMainFrag();
			pushFragment(sm);

			break;
		case 1:

			/*
			 * scheduleappFrag = new ScheduleAppFragment();
			 * pushFragment(scheduleappFrag);
			 */

			Fragment am = new ScheduleAppMainFrag();
			pushFragment(am);

			break;

		case 2:

			calendareventfragment = new CalendarEventfragment();
			pushFragment(calendareventfragment);

			break;

		case 3:

			currencyconvertorfrag = new CurrencyConversionFragment();
			pushFragment(currencyconvertorfrag);

			break;

		case 4:

			Fragment agecal = new AgeCalculatorfragment();
			pushFragment(agecal);

			break;
		case 5:

			Fragment interfrag = new InterestCaluculator();
			pushFragment(interfrag);

			break;

		case 6:

			Fragment blacF = new BlackListFragment();
			pushFragment(blacF);
			
			break;
			
		case 7:

			Fragment bacF = new BackupFragment();
			pushFragment(bacF);
			
			break;
			
		case 8:

			Fragment mafrag = new ManageFragment();
			pushFragment(mafrag);

			break;
			
		case 9:

			Fragment setfrag = new SettingsFragment();
			pushFragment(setfrag);

			break;
		

		default:
			break;
		}

	}


	public void pushFragment(Fragment fragment)
	{

		ft = getSupportFragmentManager().beginTransaction();

		ft.replace(R.id.fragmentholder,fragment);

		ft.commit();


	}

	public void pushFragmentwithbackstact(Fragment fragment)
	{

		ft = getSupportFragmentManager().beginTransaction();

		ft.replace(R.id.fragmentholder,fragment);
		ft.addToBackStack(null);
		ft.commit();


	}

	public void removeFragment(Fragment fragment)
	{

		ft = getSupportFragmentManager().beginTransaction();

		ft.remove(fragment);

		ft.commit();


	}

	public String[] val = {"I'm busy at the moment, i'll call you back later.","I'am in a metting at the moment, i'll call you back later"
			,"I cannot talk now, please send me a message","i'am driving at the momemt, i'll call you back later","Don't call me again"};

	
	public void checkTemplates()
	{
		try
		{

			TemplateTable tt = new TemplateTable(ToolsActivity.this);

			int count = tt.gettemplateCount();

			if(count == 0)
			{

				for(int i=0;i<val.length;i++)
				{

					tt.inserttemplate(val[i]);

				}

			}

			tt.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void checksetting()
	{

		try
		{
			SettingTable st = new SettingTable(ToolsActivity.this);

			int count = st.getsettingCount();

			if(count==0)
			{

				st.inserttemplate("on","false","false");
			}

			st.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void checkbackup()
	{

		try
		{

			BackupTable bt  = new BackupTable(ToolsActivity.this);

			int count = bt.getcount();

			if(count==0)
			{

				bt.insertlastupdate(" ");
			}

			bt.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	 public boolean onKeyDown(int keyCode, KeyEvent event) {
		    if (keyCode == KeyEvent.KEYCODE_BACK) {
		        exitByBackKey();

		        //moveTaskToBack(false);

		        return true;
		    }
		    return super.onKeyDown(keyCode, event);
		}

		protected void exitByBackKey() {
			
			
			final Dialog confirmd = new Dialog(ToolsActivity.this);
			
			
			confirmd.requestWindowFeature(Window.FEATURE_NO_TITLE);
			
			confirmd.setContentView(R.layout.confirmwindow);
			
			
			Button yescn = (Button)confirmd.findViewById(R.id.yescl);
			Button nocn = (Button)confirmd.findViewById(R.id.nocl);
			
			yescn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					 finish();
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

		 

		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
