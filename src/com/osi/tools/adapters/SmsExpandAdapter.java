package com.osi.tools.adapters;



import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;
import com.osi.tools.bean.SmsBean;
import com.osi.tools.fragments.ScheduleSmsMainFrag;
import com.osi.tools.tables.SmsDetailsTable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class SmsExpandAdapter extends BaseExpandableListAdapter {

	ScheduleSmsMainFrag context;
	ArrayList<SmsBean> beanarray;
	public SmsExpandAdapter(ScheduleSmsMainFrag con,ArrayList<SmsBean> beanarray)
	{
		
		this.context = con;
		this.beanarray = beanarray;
		
	}
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return beanarray.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		int size=0;
		  if(beanarray.get(groupPosition).getChildren()!=null)
		   size = beanarray.get(groupPosition).getChildren().size();	
		  return size;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return beanarray.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return beanarray.get(groupPosition).getChildren().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		final SmsBean bean = beanarray.get(groupPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater)context.getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.smsdetailgroup, null);
		}
		
		TextView num = (TextView)convertView.findViewById(R.id.nameitem);
		TextView date = (TextView)convertView.findViewById(R.id.numitem);
		Button delete = (Button)convertView.findViewById(R.id.deletesmsbutton);
		
		
		num.setText(bean.getPhonenumber());
		date.setText(toDate(bean.getTimestamp()));
		
		
		
		
		
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				final Dialog confirmd = new Dialog(context.getActivity());
				
				
				confirmd.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
				confirmd.setContentView(R.layout.deletediaolg);
				
				
				Button yescn = (Button)confirmd.findViewById(R.id.yescld);
				Button nocn = (Button)confirmd.findViewById(R.id.nocld);
				
				yescn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						beanarray.remove(bean);

						SmsDetailsTable sdt = new SmsDetailsTable(context.getActivity());
					     sdt.deleterecord(bean.getId());

                       sdt.close();

						notifyDataSetChanged();
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
				
				
				
				/*AlertDialog.Builder adb = new AlertDialog.Builder(context.getActivity());

				adb.setTitle("Alert");

				adb.setMessage("Are you sure you want to remove ");

				adb.setIcon(android.R.drawable.ic_dialog_alert);
                   
				
				
				
				adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						//removeQGuide(qGuideName);
						beanarray.remove(bean);

						SmsDetailsTable sdt = new SmsDetailsTable(context.getActivity());
					     sdt.deleterecord(bean.getId());

                       sdt.close();

						notifyDataSetChanged();
					} 	
				});

				adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});	    
				adb.show();*/
				
			}
		});
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

          SmsBean bean = beanarray.get(groupPosition);
          String msg = bean.getChildren().get(childPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater)context.getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.smsdetailchild, null);
		}
		
		TextView num = (TextView)convertView.findViewById(R.id.bodyitem);
		
		
		num.setText(msg);
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	public String toDate(long dateobject){
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
		String formattedDate = dateFormat.format(dateobject);
		return formattedDate;
	}
	
}
