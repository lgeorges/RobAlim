package com.arduino.robalim.view;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.example.robalim.R;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import at.abraxas.amarino.AmarinoIntent;

public class MenuTest extends Activity {
	
	private Activity menu_activity;
	private RobAlimInterfaceIn robot_in;

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        menu_activity=this;
	        robot_in = RobAlimInterfaceIn.getInstance();
	        
	        setContentView(R.layout.menu_test);
	        Button move_button=(Button)findViewById(R.id.move_button);
	        Button action_button=(Button)findViewById(R.id.action_button);
	        Button caption_button=(Button)findViewById(R.id.caption_button);

	        move_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i("click", "onClick");
					Intent i = new Intent(menu_activity, MenuDeplacement.class);
			    	startActivity(i);
//					setContentView(R.layout.menu_test);
					
				}
			});
	        
	        action_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i("click", "onClick");
					Intent i = new Intent(menu_activity, MenuActions.class);
			    	startActivity(i);
//					setContentView(R.layout.action_view);
					
				}
			});
	       
	        caption_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i("click", "onClick");
					Intent i = new Intent(menu_activity, MenuCaptions.class);
			    	startActivity(i);
//					setContentView(R.layout.action_view);
					
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
