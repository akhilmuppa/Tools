package com.osi.tools.adapters;

import java.util.ArrayList;







import com.osi.tools.R;
import com.osi.tools.bean.ManageBean;
import com.osi.tools.fragments.ManageFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ManageListAdapter extends BaseAdapter{



	ManageFragment context;
	ArrayList<ManageBean> arraybean;
	private LayoutInflater inflater;
	private static int selectedIndex;
	public boolean ch =false;

	public ManageListAdapter(ManageFragment con,ArrayList<ManageBean> arraybean)
	{

		this.context = con;
		this.arraybean = arraybean;

	}

	public void setSelectedIndex(int ind) {
		selectedIndex = ind;
		ch=true;
	}


	@Override
	public int getCount() {

		return arraybean.size();
	}

	@Override
	public Object getItem(int position) {

		return arraybean.get(position);
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
			convertView = inflater.inflate(R.layout.managelistitem,parent,false);

			vh.iview = (ImageView)convertView.findViewById(R.id.manageicon);
			vh.nametxt = (TextView)convertView.findViewById(R.id.manageheaderone);
			vh.datetxt = (TextView)convertView.findViewById(R.id.manageheadertwo);

			convertView.setTag(vh);
		}else{
			vh = (ListViewHolder)convertView.getTag();
		}

		ManageBean bean = arraybean.get(position);

		//vh.iview.setImageResource(context.drawbles[bean.getIcontag()]);
		vh.iview.setBackgroundResource(context.drawbles[bean.getIcontag()]);
		vh.nametxt.setText(bean.getName());
		vh.datetxt.setText(bean.getDate());




		if(ch==true){
			if (position == selectedIndex) {
				convertView.setBackgroundColor(convertView.getResources().getColor(R.color.lightorange));
			}
			else {
				convertView.setBackgroundColor(convertView.getResources().getColor(R.color.silver));
			}
		}

		return convertView;
	}

	public class ListViewHolder{
		ImageView iview;
		TextView nametxt,datetxt;


	}

}
