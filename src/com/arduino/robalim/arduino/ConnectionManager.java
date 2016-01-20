package com.arduino.robalim.arduino;

import java.util.Timer;
import java.util.TimerTask;

import com.arduino.robalim.MainMenu;

import android.app.Activity;
import android.content.IntentFilter;
import android.content.PeriodicSync;
import android.util.Log;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class ConnectionManager {
	private static ConnectionManager instance = new ConnectionManager();
	private ArduinoReceiver arduino_receiver;
	private ArduinoConnectionTester arduino_connection_tester;
	private String device_address;
	private Activity main_activity;
	private Timer connection_timer;
	private boolean is_connected;
	
	public ConnectionManager(){
		super();
		arduino_receiver= new ArduinoReceiver(this, RobAlimInterfaceIn.getInstance());
		arduino_connection_tester = new ArduinoConnectionTester(this);
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

		Amarino.connect(activity, address);
		main_activity=activity;
		this.checkConnection();
	}
	
	public void disconnectDevices(Activity activity){
		if(device_address!=null){
//			Log.d("ConnectionManager","disconnect");
			Amarino.disconnect(activity, device_address);
			activity.unregisterReceiver(arduino_receiver);
		}
	}
	
	private void checkConnection(){
		
		if(connection_timer != null)
			connection_timer.cancel();
		
		connection_timer = new Timer();
		TimerTask task = new TimerTask(){

			@Override
			public void run() {
				main_activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						updateConnectionView();
			//			Log.d("Update Connection", "boolean "+is_connected);
						is_connected=false;
					}
				});
			}};
			
		connection_timer.schedule(task, 0, 500);
	}
	
	public void updateConnection(boolean connected){
		this.is_connected=connected;
	}
	
	private void updateConnectionView(){
		//Log.d("Update Connection", "view");
		((MainMenu)main_activity).updateConnectionView(is_connected);
	}
	
	
}
