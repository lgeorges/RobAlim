package com.arduino.robalim.view;

import java.util.Observable;
import java.util.Observer;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class StatutFragmentView extends Fragment implements Observer {
	private RobAlimInterfaceIn robot_in;
	private RobAlimInterfaceOut robot_out; 
	private Activity main_activity;
	private TextView statut_field;
	private Button mode_button;
	private boolean view_created =false;
	
	public StatutFragmentView(){
		super();
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        
        robot_in.addObserver(this);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statut_view, container, false);

        
        main_activity=getActivity();
        
        statut_field=(TextView)rootView.findViewById(R.id.statut);
        
        mode_button=(Button)rootView.findViewById(R.id.mode_button);
        mode_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(robot_in.isInManualMode())
					robot_out.setModeAuto(main_activity);
				else	
					robot_out.setModeManuel(main_activity);
			}
		});
        
        view_created=true;
        update(null,null);
        return rootView;
	}

	@Override
	public void update(Observable observable, Object data) {
		if(view_created){
			boolean mode_manuel = robot_in.isInManualMode();
			String statut = robot_in.getStatut();

			if(mode_manuel)
				mode_button.setText("Manuel");
//				mode_button.setChecked(true);
			else
				mode_button.setText("Auto");
//				mode_button.setChecked(false);
			
			this.statut_field.setText("Statut: "+statut);			
		}
	}
}
