package com.osi.tools.adapters;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DrawerListAdapter extends BaseAdapter{

	
	ToolsActivity context;
	String[] values;
	private LayoutInflater inflater;
	public static int selectedIndex;
	
	public DrawerListAdapter(ToolsActivity con,String[] values)
	{
		
		this.context = con;
		this.values = values;
		
	}
	
	
	@Override
	public int getCount() {
		
		return values.length;
	}

	@Override
	public Object getItem(int position) {
		
		return values[position];
	}

	@Override
	public long getItemId(int position) {
	
		return 0;
	}
	
	public static void setSelectedIndex(int ind) {
	     selectedIndex = ind;
	 }
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		
		final ListViewHolder vh;
		if(convertView == null)
		{
			vh=new ListViewHolder();
			inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.drawer_layout,parent,false);

          vh.name = (TextView)convertView.findViewById(R.id.MenuItem);

			convertView.setTag(vh);
		}else{
			vh = (ListViewHolder)convertView.getTag();
		}
		
		vh.name.setText(values[position]);
		
		
		return convertView;
	}
	
	public class ListViewHolder{
		
		TextView name;
	

	}
	

}
