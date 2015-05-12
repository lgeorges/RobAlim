package com.arduino.robalim.view;

import java.util.Observable;
import java.util.Observer;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import at.abraxas.amarino.AmarinoIntent;

public class Menu extends Activity implements Observer{
	
	private Activity menu_activity;
	private RobAlimInterfaceOut robot_out;
	private RobAlimInterfaceIn robot_in;
	private Button indications_button;
	private Button deplacement_button;
	private ToggleButton mode_button;
	private TextView statut_field;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        robot_in.addObserver(this);
        
        menu_activity=this;
        
        setContentView(R.layout.menu);
        
        statut_field=(TextView)findViewById(R.id.statut);
        
        indications_button=(Button)findViewById(R.id.indications_button);
        deplacement_button=(Button)findViewById(R.id.deplacement_button);
        
        indications_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(menu_activity, IndicationsView.class);
		    	startActivity(i);
				
			}
		});
        
        deplacement_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(menu_activity, MenuDeplacement.class);
		    	startActivity(i);
			}
		});
        
        
        mode_button=(ToggleButton)findViewById(R.id.switch_mode);
        mode_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mode_button.isChecked())
					robot_out.setModeAuto(menu_activity);
				else	
					robot_out.setModeManuel(menu_activity);
			}
		});
        
        Button tests=(Button)findViewById(R.id.button_tests);
        tests.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("click", "onClick");
				Intent i = new Intent(menu_activity, Tests.class);
		    	startActivity(i);
				
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("Menu","receiver "+robot_in.getArduinoReceiver());
		registerReceiver(robot_in.getArduinoReceiver(), new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
        this.update(null,null);
	};
	
	@Override 
	protected void onStop() {
		super.onStop();
		robot_in.deleteObserver(this);
		unregisterReceiver(robot_in.getArduinoReceiver());
	};
	
	@Override
	public void update(Observable observable, Object data) {
		String mode = robot_in.getMode();
		String statut = robot_in.getStatut();

		if(mode.equalsIgnoreCase("auto"))
			mode_button.setChecked(true);
		else
			mode_button.setChecked(false);
		
		this.statut_field.setText("Statut: "+statut);
	}
}
