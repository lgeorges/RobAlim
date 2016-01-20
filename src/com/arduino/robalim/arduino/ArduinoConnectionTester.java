package com.arduino.robalim.arduino;

import java.util.Timer;
import java.util.TimerTask;

import com.arduino.robalim.MainMenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import at.abraxas.amarino.AmarinoIntent;

public class ArduinoConnectionTester extends BroadcastReceiver{
	
	private ConnectionManager connection_manager;
	
	public ArduinoConnectionTester(ConnectionManager connection_manager) {
		super();
		this.connection_manager=connection_manager;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.w("ArduinoConnectionTester"," connected " +intent.toString());
		String data = null;
		connection_manager.updateConnection(true);
	}

}
