package com.arduino.robalim.view;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import at.abraxas.amarino.AmarinoIntent;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;


public class MenuDeplacement extends Activity {

	private RobAlimInterfaceOut robot_out;
	private Activity activity;
	private RobAlimInterfaceIn robot_in;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robot_out=RobAlimInterfaceOut.getInstance();
        robot_in=RobAlimInterfaceIn.getInstance();
        
        setContentView(R.layout.menu_deplacement);
        activity=this;
        ImageButton button_up_left = (ImageButton)findViewById(R.id.button_up_left);
        ImageButton button_up_fast = (ImageButton)findViewById(R.id.button_up_fast);
        ImageButton button_up_right = (ImageButton)findViewById(R.id.button_up_right);
        ImageButton button_left = (ImageButton)findViewById(R.id.button_left);
        ImageButton button_up = (ImageButton)findViewById(R.id.button_up);
        ImageButton button_right = (ImageButton)findViewById(R.id.button_right);
        ImageButton button_stop = (ImageButton)findViewById(R.id.button_stop);
        
        button_up_left.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancerGauche(activity);
        	}
        });
        
        button_up_right.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancerDroite(activity);
        	}
        });
        
        button_up_fast.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancerRapidement(activity);
        	}
        });
        
        button_left.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.tournerAGauche(activity);
        	}
        });
        
        button_up.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancer(activity);
        	}
        });
        
        button_right.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.tournerADroite(activity);
        	}
        });
        
        button_stop.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        			robot_out.stopper(activity);
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
