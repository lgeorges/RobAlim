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
	private String device_address;
	
	public ConnectionManager(){
		super();
		arduino_receiver= new ArduinoReceiver();
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
	
	public void setAddress(String value){
		device_address=value;
	}
	
	public void connectDevice(Activity activity, String address) {
//		Log.d("ConnectionManager","connect");
		device_address=address;
		activity.registerReceiver(arduino_receiver, new IntentFilter(AmarinoIntent.ACTION_CONNECTION_FAILED));
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
