package com.osi.tools.adapters;




import com.osi.tools.R;
import com.osi.tools.fragments.BackupFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BackupListAdapter extends BaseAdapter{


	BackupFragment context;
	int[] drawbles;
	String[] val;
	private LayoutInflater inflater;

	public BackupListAdapter(BackupFragment con,int[] drawbles,String[] values)
	{

		this.context = con;
		this.drawbles = drawbles;
		this.val = values;

	}


	@Override
	public int getCount() {

		return drawbles.length;
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {


		final ListViewHolder vh;
		if(convertView == null)
		{
			vh=new ListViewHolder();
			inflater = LayoutInflater.from(context.getActivity());
			convertView = inflater.inflate(R.layout.backuplistitem,parent,false);

			vh.iview = (ImageView)convertView.findViewById(R.id.appimage);
			vh.headertxt = (TextView)convertView.findViewById(R.id.apptxtview);
			vh.chk = (CheckBox)convertView.findViewById(R.id.appchkbox);

			convertView.setTag(vh);
		}else{
			vh = (ListViewHolder)convertView.getTag();
		}


		vh.iview.setBackgroundResource(drawbles[position]);
		vh.headertxt.setText(val[position]);

		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT){
			if(position==1)
			{

				vh.chk.setEnabled(false);
				vh.headertxt.setEnabled(false);
				vh.iview.setEnabled(false);

			}

		} 



		vh.chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				if(position==0)
				{
					context.one=isChecked;

				}else if(position==1)
				{

					context.two=isChecked;
				}else if(position==2)
				{

					context.three=isChecked;
				}

			}
		});


		return convertView;
	}

	public class ListViewHolder{
		ImageView iview;
		TextView headertxt;
		CheckBox chk;

	}

}
