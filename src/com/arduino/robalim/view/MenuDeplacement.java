package com.arduino.robalim.view;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
        Button button_go = (Button)findViewById(R.id.button_go);
        Button button_left = (Button)findViewById(R.id.button_left);
        Button button_right = (Button)findViewById(R.id.button_right);
        Button button_stop = (Button)findViewById(R.id.button_stop);
        final Switch vitesse_rapide = (Switch)findViewById(R.id.switch_motion);
        
        button_go.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		if(vitesse_rapide.isChecked())
        			robot_out.avancerRapidement(activity);
        		else
        			robot_out.avancer(activity);
        	}
        });
        
        
        button_left.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		if(vitesse_rapide.isChecked())
        			robot_out.tournerAGaucheRapidement(activity);
        		else
        			robot_out.tournerAGauche(activity);
        	}
        });
        
        
        button_right.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		if(vitesse_rapide.isChecked())
        			robot_out.tournerADroiteRapidement(activity);
        		else
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
