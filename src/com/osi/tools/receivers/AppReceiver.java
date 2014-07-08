package com.osi.tools.receivers;

import com.osi.tools.bean.AppBean;
import com.osi.tools.tables.AppTable;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class AppReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {


		AppTable atable = new AppTable(context);
		long id = intent.getLongExtra("appid",0);

		AppBean bean = atable.getdetails(id);

		if(bean!=null)
		{
			ComponentName name = new ComponentName(
					bean.getPkgname(), bean.getAppinfo());
			Intent i = new Intent(Intent.ACTION_MAIN);

			i.addCategory(Intent.CATEGORY_LAUNCHER);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
			i.setComponent(name);

			atable.deleterecord(id);
			atable.close();
			context.startActivity(i);
		}

	}


}
