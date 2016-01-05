package com.arduino.robalim.arduino;

import com.arduino.robalim.MainMenu;

import android.app.Activity;
import android.content.IntentFilter;
import android.util.Log;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class ConnectionManager {
	private static ConnectionManager instance = new ConnectionManager();
	private ArduinoReceiver arduino_receiver;
	private ArduinoConnectionTester arduino_connection_tester;
	private String device_address;
	
	public ConnectionManager(){
		super();
		arduino_receiver= new ArduinoReceiver();
		arduino_connection_tester = new ArduinoConnectionTester();
	}
	public static ConnectionManager getInstance(){
		return instance;
	}
	
	public ArduinoReceiver getArduinoReceiver(){
		return arduino_receiver;
	}
	
	public String getAddress(){
		return device_address;
	}
	
	public void connectDevice(Activity activity, String address) {
		Log.d("ConnectionManager","connect: "+address);
		device_address=address;
		activity.registerReceiver(arduino_receiver, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
		activity.registerReceiver(arduino_connection_tester, new IntentFilter(AmarinoIntent.ACTION_CONNECTED));
		activity.registerReceiver(new ArduinoConnectionTester(), new IntentFilter(AmarinoIntent.ACTION_DISCONNECTED));

		Amarino.connect(activity, address);
		((MainMenu)activity).updateConnection(true);
	}
	
	public void disconnectDevices(Activity activity){
		if(device_address!=null){
//			Log.d("ConnectionManager","disconnect");
			Amarino.disconnect(activity, device_address);
			activity.unregisterReceiver(arduino_receiver);
		}
	}
}
