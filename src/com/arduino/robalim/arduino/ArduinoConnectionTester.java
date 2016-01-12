package com.arduino.robalim.arduino;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import at.abraxas.amarino.AmarinoIntent;

public class ArduinoConnectionTester extends BroadcastReceiver{
	
	private RobAlimInterfaceIn robot_in;
	
	public ArduinoConnectionTester() {
		super();
		robot_in=RobAlimInterfaceIn.getInstance();
		Log.i("ArduinoReceiver","constructor "+robot_in);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.w("ArduinoConnectionTester"," connected " +intent.toString());
		String data = null;
	}

}
