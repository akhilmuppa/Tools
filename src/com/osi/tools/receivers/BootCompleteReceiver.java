package com.osi.tools.receivers;

import com.osi.tools.services.EventListnerService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {


		Intent service1 = new Intent(context,EventListnerService.class);

		context.startService(service1);

	}

}
