package com.osi.tools.adapters;

import java.util.List;

import com.osi.tools.R;
import com.osi.tools.fragments.ScheduleAppFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class applistAdapter extends ArrayAdapter<ResolveInfo> {

	Context context;
	PackageManager pm;
	List<ResolveInfo> apps;
	private LayoutInflater inflater;
	ScheduleAppFragment frag;

	public applistAdapter(Context con, PackageManager pm,
			List<ResolveInfo> apps, ScheduleAppFragment frag) {
		super(con, R.layout.row, apps);

		this.context = con;
		this.pm = pm;
		this.apps = apps;
		this.frag = frag;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = newView(parent);
		}

		bindView(position, convertView);

		return (convertView);
	}

	private View newView(ViewGroup parent) {

		return (frag.getActivity().getLayoutInflater().inflate(R.layout.row,
				parent, false));
	}

	private void bindView(int position, View row) {
		TextView label = (TextView) row.findViewById(R.id.label);

		label.setText(getItem(position).loadLabel(pm));

		ImageView icon = (ImageView) row.findViewById(R.id.icon);

		icon.setImageDrawable(getItem(position).loadIcon(pm));
	}

}
