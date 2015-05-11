package com.arduino.robalim;

import com.arduino.robalim.arduino.ArduinoReceiver;
import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.view.Menu;
import com.example.robalim.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import at.abraxas.amarino.Amarino;

public class UserInputGraph extends Activity implements OnClickListener {
	
	private static final String TAG = "SensorGraph";
	
	public static String DEVICE_ADDRESS;
	private RobAlimInterfaceIn robot_in;
	EditText idField;
	Button button;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);
        
        Log.d(TAG, "Main onStart");
        robot_in=RobAlimInterfaceIn.getInstance();
        // get references to views defined in our main.xml layout file
        idField = (EditText) findViewById(R.id.deviceIDField);
        button = (Button) findViewById(R.id.okButton);
        // register listeners
        button.setOnClickListener(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        DEVICE_ADDRESS = prefs.getString("device", "20:14:12:23:10:40");
        idField.setText(DEVICE_ADDRESS);
    }
    

	public void onClick(View v) 
	{
		DEVICE_ADDRESS = idField.getText().toString();
		PreferenceManager.getDefaultSharedPreferences(this)
			.edit()
				.putString("device", DEVICE_ADDRESS)
					.commit();
		Amarino.connect(this, DEVICE_ADDRESS);
		robot_in.setArduinoReceiver(new ArduinoReceiver());
//		Intent i = new Intent(this, MainActivity.class);
		Intent i = new Intent(this, Menu.class);
    	startActivity(i);
	}
	
}