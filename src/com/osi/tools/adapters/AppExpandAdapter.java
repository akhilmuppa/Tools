package com.osi.tools.adapters;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.osi.tools.R;
import com.osi.tools.bean.AppBean;
import com.osi.tools.fragments.ScheduleAppMainFrag;
import com.osi.tools.tables.AppTable;
import com.osi.tools.tables.SmsDetailsTable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AppExpandAdapter extends BaseAdapter{


	ScheduleAppMainFrag context;
	ArrayList<AppBean> beanarray;
	private LayoutInflater inflater;
	public AppExpandAdapter(ScheduleAppMainFrag con,ArrayList<AppBean> beanarray)
	{

		this.context = con;
		this.beanarray = beanarray;

	}


	@Override
	public int getCount() {

		return beanarray.size();
	}

	@Override
	public Object getItem(int position) {

		return beanarray.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final AppBean bean = beanarray.get(position);

		final ListViewHolder vh;
		if(convertView == null)
		{
			vh=new ListViewHolder();
			inflater = LayoutInflater.from(context.getActivity());
			convertView = inflater.inflate(R.layout.appdetailrow,parent,false);

			vh.appname = (TextView)convertView.findViewById(R.id.appnameitem);
			vh.time = (TextView)convertView.findViewById(R.id.timeitem);
			vh.delete = (Button)convertView.findViewById(R.id.deleteappbutton);
			convertView.setTag(vh);
		}else{
			vh = (ListViewHolder)convertView.getTag();
		}


		vh.appname.setText(bean.getAppname());
		vh.time.setText(toDate(bean.getDatetime()));

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

						AppTable atable = new AppTable(context.getActivity());

						beanarray.remove(bean);
						atable.deleterecord(bean.getId());
						atable.close();
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

						AppTable atable = new AppTable(context.getActivity());

						beanarray.remove(bean);
						atable.deleterecord(bean.getId());
						atable.close();
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

	public String toDate(long dateobject){

		Log.d("ddate", ""+dateobject);
		Date date = new Date(dateobject);

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	public class ListViewHolder{

		TextView appname,time;
		Button delete;
	}

}
