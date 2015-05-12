package com.arduino.robalim.view;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;
import at.abraxas.amarino.AmarinoIntent;

public class Tests extends Activity {
private RobAlimInterfaceIn robot_in;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robot_in=RobAlimInterfaceIn.getInstance();
        
        setContentView(R.layout.view_test);
        
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
//        Button button_stop = (Button)findViewById(R.id.button_stop);
        
        button1.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "mode/auto");
        	}
        });
        
        button2.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "mode/manuel");
        	}
        });
        
        button3.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "statut/Tests");
        	}
        });
        
        button4.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "ultrasons/i0/205");
        		robot_in.updateData(0, "ultrasons/i1/209");
        		robot_in.updateData(0, "ultrasons/m0/102");
        		robot_in.updateData(0, "ultrasons/m1/101");
        	}
        });
        
        button5.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "inductifs/i0/152");
        		robot_in.updateData(0, "inductifs/i1/153");
        		robot_in.updateData(0, "inductifs/i2/154");
        		robot_in.updateData(0, "inductifs/m0/82");
        		robot_in.updateData(0, "inductifs/m1/83");
        		robot_in.updateData(0, "inductifs/m2/84");
        	}
        });
	}

	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(robot_in.getArduinoReceiver(), new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
	};
	
	@Override 
	protected void onStop() {
		super.onStop();
		unregisterReceiver(robot_in.getArduinoReceiver());
	};
	
}
