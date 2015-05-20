package com.arduino.robalim.view;

import java.util.Locale;

import com.arduino.robalim.MainMenu;
import com.arduino.robalim.arduino.ArduinoReceiver;
import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import at.abraxas.amarino.Amarino;

public class ConnectionFragmentView extends Fragment {
	
	private RobAlimInterfaceIn robot_in;
	private RobAlimInterfaceOut robot_out;
	private EditText idField;
	private Button button;
	private String device_address;
	private Activity main_activity;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.input, container, false);
        robot_in=RobAlimInterfaceIn.getInstance();
        robot_out=RobAlimInterfaceOut.getInstance();

        idField = (EditText)rootView.findViewById(R.id.deviceIDField);
        button = (Button)rootView.findViewById(R.id.okButton);
        main_activity=getActivity();
        // register listeners
        button.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				device_address = idField.getText().toString();
//				PreferenceManager.getDefaultSharedPreferences(this)
//					.edit()
//						.putString("device", DEVICE_ADDRESS)
//							.commit();
				Amarino.connect(main_activity, device_address);
				robot_out.setDeviceAddress(device_address);
//				robot_in.setArduinoReceiver(new ArduinoReceiver());
				
				((MainMenu)main_activity).updateConnection(true);
//				Intent i = new Intent(this, MainActivity.class);
//				Intent i = new Intent(this, Menu.class);
//		    	startActivity(i);
				
			}
		});
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        DEVICE_ADDRESS = prefs.getString("device", "20:14:12:23:10:40");
        idField.setText("20:14:12:23:10:40");
        return rootView;
    }
}
