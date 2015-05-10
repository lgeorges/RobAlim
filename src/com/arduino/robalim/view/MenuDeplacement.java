package com.arduino.robalim.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;

import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;


public class MenuDeplacement extends Activity {

	private RobAlimInterfaceOut robot_out;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robot_out=RobAlimInterfaceOut.getInstance();
        
        setContentView(R.layout.menu_deplacement);
        
        Button button_go = (Button)findViewById(R.id.button_go);
        Button button_left = (Button)findViewById(R.id.button_left);
        Button button_right = (Button)findViewById(R.id.button_right);
        Button button_stop = (Button)findViewById(R.id.button_stop);
        final Switch vitesse_rapide = (Switch)findViewById(R.id.switch_motion);
        
        button_go.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		if(vitesse_rapide.isChecked())
        			robot_out.avancerRapidement();
        		else
        			robot_out.avancer();
        	}
        });
        
        
        button_left.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		if(vitesse_rapide.isChecked())
        			robot_out.tournerAGaucheRapidement();
        		else
        			robot_out.tournerAGauche();
        	}
        });
        
        
        button_right.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		if(vitesse_rapide.isChecked())
        			robot_out.tournerADroiteRapidement();
        		else
        			robot_out.tournerADroite();
        	}
        });
        
        
        button_stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					robot_out.stopper();
			}
		});
       
	}
}
