package com.osi.tools.adapters;

import java.util.ArrayList;










import com.osi.tools.R;
import com.osi.tools.bean.BlackListBean;
import com.osi.tools.fragments.BlackListFragment;
import com.osi.tools.tables.BlackListTable;
import com.osi.tools.tables.SmsDetailsTable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BlackListAdapter extends BaseAdapter {


	ArrayList<BlackListBean> blacklistdata;
	BlackListFragment context;
	private LayoutInflater inflater;


	public BlackListAdapter(ArrayList<BlackListBean> blacklistdata,BlackListFragment con)
	{

		this.blacklistdata = blacklistdata;
		this.context = con;

	}


	@Override
	public int getCount() {

		return blacklistdata.size();
	}

	@Override
	public Object getItem(int position) {

		return blacklistdata.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ListViewHolder vh;
		if(convertView == null)
		{
			vh=new ListViewHolder();
			inflater = LayoutInflater.from(context.getActivity());
			convertView = inflater.inflate(R.layout.blacklistitem,parent,false);


			vh.sms = (ImageView)convertView.findViewById(R.id.smsimage);
			vh.call = (ImageView)convertView.findViewById(R.id.callimage);
			vh.name = (TextView)convertView.findViewById(R.id.nameitem);
			vh.num =  (TextView)convertView.findViewById(R.id.numitem);
			vh.delete = (Button)convertView.findViewById(R.id.deleteblackbutton);

			convertView.setTag(vh);
		}else{
			vh = (ListViewHolder)convertView.getTag();
		}

		final BlackListBean blb = blacklistdata.get(position);

		vh.name.setText(blb.getName());
		vh.num.setText(blb.getNumber());

		if(blb.getSms().equalsIgnoreCase("true"))
		{
			vh.sms.setImageResource(R.drawable.sms);
		}else
		{
			vh.sms.setImageResource(R.drawable.smsno);
		}

		if(blb.getCall().equalsIgnoreCase("true"))
		{

			vh.call.setImageResource(R.drawable.call);

		}else
		{
			vh.call.setImageResource(R.drawable.callno);
		}


		vh.delete.setOnClickListener(new OnClickListener() {

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
						
						blacklistdata.remove(blb);

						BlackListTable bt = new BlackListTable(context.getActivity());
						bt.deleteblacklistitem(blb.getId());

                      bt.close();

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
				
				
				
			/*	AlertDialog.Builder adb = new AlertDialog.Builder(context.getActivity());

				adb.setTitle("Alert");

				adb.setMessage("Are you sure you want to remove ");

				adb.setIcon(android.R.drawable.ic_dialog_alert);

				adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						//removeQGuide(qGuideName);
						blacklistdata.remove(blb);

						BlackListTable bt = new BlackListTable(context.getActivity());
						bt.deleteblacklistitem(blb.getId());



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

	public class ListViewHolder{
		ImageView sms,call;
		TextView name,num;
		Button delete;

	}

}
