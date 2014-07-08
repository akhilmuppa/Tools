package com.osi.tools.adapters;

import java.util.ArrayList;








import com.osi.tools.R;
import com.osi.tools.bean.TemplateBean;
import com.osi.tools.fragments.BlockOptionsFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TemplateAdapter extends BaseAdapter{


	BlockOptionsFragment context;
	ArrayList<TemplateBean> arraybean;
	private LayoutInflater inflater;
	private static int selectedIndex;


	public TemplateAdapter(BlockOptionsFragment con,ArrayList<TemplateBean> arraybean)
	{

		this.context = con;
		this.arraybean = arraybean;

	}


	@Override
	public int getCount() {

		return arraybean.size();
	}

	@Override
	public Object getItem(int arg0) {

		return arraybean.get(arg0);
	}

	public static void setSelectedIndex(int ind) {
		selectedIndex = ind;
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
			convertView = inflater.inflate(R.layout.templatelistitem,parent,false);

			vh.tv = (TextView)convertView.findViewById(R.id.templateitem);

			convertView.setTag(vh);
		}else{
			vh = (ListViewHolder)convertView.getTag();
		}

		TemplateBean tb= arraybean.get(position);

		vh.tv.setText(tb.getTemplatename());

		if (position == selectedIndex) {
			convertView.setBackgroundColor(convertView.getResources().getColor(R.color.darkgrey));
		}
		else {
			convertView.setBackgroundColor(convertView.getResources().getColor(R.color.white));
		}


		return convertView;
	}

	public class ListViewHolder{
		public TextView tv;

	}

}
