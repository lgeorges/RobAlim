package com.arduino.robalim.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;


public class ReglagesFragmentView extends Fragment implements Observer {
	private RobAlimInterfaceIn robot_in;
	private RobAlimInterfaceOut robot_out; 
	private Activity main_activity;
	private Button mode_button;
	private SeekBar seekbar_distribution;
	private SeekBar seekbar_border;
	private TextView border_value;
	private TextView distrib_value;
	private boolean view_created =false;
	
	public ReglagesFragmentView(){
		super();
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        
        robot_in.addObserver(this);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reglages_view, container, false);

        main_activity=getActivity();
        
        seekbar_border=(SeekBar)rootView.findViewById(R.id.seekbar_border);
        seekbar_distribution=(SeekBar)rootView.findViewById(R.id.seekbar_distribution);
        
        border_value=(TextView)rootView.findViewById(R.id.border_value);
        distrib_value=(TextView)rootView.findViewById(R.id.distrib_value);
        
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
        
        seekbar_border.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
        	
        	private int adaptProgressValue(int progress){
				int max = getResources().getInteger(R.integer.max_seekbar_border);
				int min = getResources().getInteger(R.integer.min_seekbar_border);
				int value = progress*(max-min)/100 + min;
				
				return value;
        	}
        	
        	@Override
        	public void onStopTrackingTouch(SeekBar seekBar) {
        		int value = adaptProgressValue(seekBar.getProgress());
        		Log.d("Reglages","value: " +value);
        		robot_out.reglageBordure(main_activity, value);
        	}
        	@Override
        	public void onStartTrackingTouch(SeekBar seekBar) {
        	}
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		border_value.setText(""+adaptProgressValue(progress));
        	}
        });
        
        seekbar_distribution.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
        	private int adaptProgressValue(int progress){
				int max = getResources().getInteger(R.integer.max_seekbar_distrib);
				int min = getResources().getInteger(R.integer.min_seekbar_distrib);
				int value = progress*(max-min)/100 + min;
				
				return value;
        	}
        	
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {        		
				int value = adaptProgressValue(seekBar.getProgress());
				Log.d("Reglages","value: " +value);
				robot_out.reglageVitesseDistribution(main_activity, value);
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				distrib_value.setText(""+adaptProgressValue(progress));
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
			int border = robot_in.getBorderValue();
			int distrib = robot_in.getDistributionValue();
			String statut = robot_in.getStatut();

			if(mode_manuel)
				mode_button.setText("Manuel");
//				mode_button.setChecked(true);
			else
				mode_button.setText("Automatique");
//				mode_button.setChecked(false);
			
			border_value.setText(""+border);
			distrib_value.setText(""+distrib);
		}
	}
}
