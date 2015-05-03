package com.example.robalim;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	private static final String TAG = "SensorGraph";
	
	
	final int DELAY = 150;
	SeekBar redSB;
	TextView seekValue;

	int red = 255;
	
	long lastChange;
	
	private GraphView mGraph; 
	private TextView mValueTV;
	
	private ArduinoReceiver arduinoReceiver = new ArduinoReceiver();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.main);
        redSB = (SeekBar) findViewById(R.id.SeekBarRed);
        
        seekValue = (TextView)findViewById(R.id.seekvalue);

        redSB.setOnSeekBarChangeListener(this);
        
    
        mGraph = (GraphView)findViewById(R.id.graph);
        mValueTV = (TextView) findViewById(R.id.value);
                mGraph.setMaxValue(1024);
    }
    
	@Override
	protected void onStart() {
		super.onStart();
		redSB.setProgress(red);
		// in order to receive broadcasted intents we need to register our receiver
		registerReceiver(arduinoReceiver, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
		  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
	        red = prefs.getInt("red", 0);
	        
	        redSB.setProgress(red);
	        seekValue.setText(String.valueOf(red));
	        
	        
	        new Thread(){
	        	public void run(){
	        		try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {}
					Log.d(TAG, "Hello World, Updating Colors");//logs debug messages
	        		updateAllColors();
	        	}
	        }.start();
	        
		}
		
	


	@Override
	protected void onStop() {
		super.onStop();
		PreferenceManager.getDefaultSharedPreferences(this)
			.edit()
				.putInt("red", red)
					.commit();
		
		Amarino.disconnect(this, UserInputGraph.DEVICE_ADDRESS);
		
	
		unregisterReceiver(arduinoReceiver);
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// do not send to many updates, Arduino can't handle so much
		if (System.currentTimeMillis() - lastChange > DELAY ){
			updateState(seekBar);
			lastChange = System.currentTimeMillis();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		lastChange = System.currentTimeMillis();
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		updateState(seekBar);
	}

	private void updateState(final SeekBar seekBar) {
		
		switch (seekBar.getId()){
			
		case R.id.SeekBarRed:
			red = seekBar.getProgress();
			updateRed();
			seekValue.setText(String.valueOf(red));
							break;
				
		}
		// provide user feedback
	}
	private void updateAllColors() {
		// send state to Arduino
        updateRed();
        
	}
	private void updateRed(){
		
		Amarino.sendDataToArduino(this, UserInputGraph.DEVICE_ADDRESS, 'o', red);
	}
	

	/**
	 * ArduinoReceiver is responsible for catching broadcasted Amarino
	 * events.
	 * 
	 * It extracts data from the intent and updates the graph accordingly.
	 */
	public class ArduinoReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String data = null;
			
			// the device address from which the data was sent, we don't need it here but to demonstrate how you retrieve it
			final String address = intent.getStringExtra(AmarinoIntent.EXTRA_DEVICE_ADDRESS);
			
			// the type of data which is added to the intent
			final int dataType = intent.getIntExtra(AmarinoIntent.EXTRA_DATA_TYPE, -1);
			
			// we only expect String data though, but it is better to check if really string was sent
			// later Amarino will support differnt data types, so far data comes always as string and
			// you have to parse the data to the type you have sent from Arduino, like it is shown below
			if (dataType == AmarinoIntent.STRING_EXTRA){
				data = intent.getStringExtra(AmarinoIntent.EXTRA_DATA);
				
				if (data != null){
					mValueTV.setText(data);
					try {
						// since we know that our string value is an int number we can parse it to an integer
						
						final int sensorReading = Integer.parseInt(data);
						mGraph.addDataPoint(sensorReading);
					} 
					catch (NumberFormatException e) { /* oh data was not an integer */ }
				}
			}
		}
	}

}