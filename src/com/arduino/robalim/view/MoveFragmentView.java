package com.arduino.robalim.view;

import java.util.Locale;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MoveFragmentView extends Fragment {
	private RobAlimInterfaceOut robot_out;
	private Activity main_activity;
	private RobAlimInterfaceIn robot_in;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.move_view, container, false);

        robot_out=RobAlimInterfaceOut.getInstance();
        robot_in=RobAlimInterfaceIn.getInstance();
        
        main_activity=getActivity();
        ImageButton button_up_left = (ImageButton)rootView.findViewById(R.id.button_up_left);
        ImageButton button_up_fast = (ImageButton)rootView.findViewById(R.id.button_up_fast);
        ImageButton button_up_right = (ImageButton)rootView.findViewById(R.id.button_up_right);
        ImageButton button_left = (ImageButton)rootView.findViewById(R.id.button_left);
        ImageButton button_up = (ImageButton)rootView.findViewById(R.id.button_up);
        ImageButton button_right = (ImageButton)rootView.findViewById(R.id.button_right);
        ImageButton button_stop = (ImageButton)rootView.findViewById(R.id.button_stop);
        
        button_up_left.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancerGauche(main_activity);
        	}
        });
        
        button_up_right.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancerDroite(main_activity);
        	}
        });
        
        button_up_fast.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancerRapidement(main_activity);
        	}
        });
        
        button_left.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.tournerAGauche(main_activity);
        	}
        });
        
        button_up.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.avancer(main_activity);
        	}
        });
        
        button_right.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		robot_out.tournerADroite(main_activity);
        	}
        });
        
        button_stop.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        			robot_out.stopper(main_activity);
        	}
        });
        
        return rootView;
    }
}
