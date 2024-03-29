package com.arduino.robalim.arduino;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import at.abraxas.amarino.AmarinoIntent;

public class ArduinoReceiver extends BroadcastReceiver{
	
	private RobAlimInterfaceIn robot_in;
	private ConnectionManager connectionManager;
	
	public ArduinoReceiver(ConnectionManager connection_manager, RobAlimInterfaceIn rob_alim_in) {
		super();
		robot_in=rob_alim_in;
		connectionManager=connection_manager;
		Log.v("ArduinoReceiver","constructor "+robot_in);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		//Log.i("ArduinoReceiver","onReceive");
		// TODO Auto-generated method stub
		String data = null;
		// the device address from which the data was sent, we don't need it here but to demonstrate how you retrieve it
		final String address = intent.getStringExtra(AmarinoIntent.EXTRA_DEVICE_ADDRESS);
		
		//Log.i("ArduinoReceiver","DEvice adress: "+AmarinoIntent.EXTRA_DEVICE_ADDRESS);
		// the type of data which is added to the intent
		final int data_type = intent.getIntExtra(AmarinoIntent.EXTRA_DATA_TYPE, -1);
		Log.v("ArduinoReceiver","Data type: "+data_type);
		// we only expect String data though, but it is better to check if really string was sent
		// later Amarino will support differnt data types, so far data comes always as string and
		// you have to parse the data to the type you have sent from Arduino, like it is shown below
		if (data_type == AmarinoIntent.STRING_EXTRA){
			data = intent.getStringExtra(AmarinoIntent.EXTRA_DATA);
			Log.v("ArduinoReceiver","Data: "+data);			
			if (data!=null){
				Log.v("ArduinoReceiver","data not null");
				Log.v("ArduinoReceiver","robot_in: " +robot_in);
				robot_in.updateData(data_type, data);
				connectionManager.updateConnection(true);
				
//				mValueTV.setText(data);
//				try {
					// since we know that our string value is an int number we can parse it to an integer
//					final int sensorReading = Integer.parseInt(data);
//					mGraph.addDataPoint(sensorReading);
//				} 
//				catch (NumberFormatException e) { /* oh data was not an integer */ }
			}
		}
	}

}
