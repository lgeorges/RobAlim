package com.arduino.robalim.view;

import java.util.Observable;
import java.util.Observer;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class IndicationsFragmentView extends Fragment implements Observer{
	
	private RobAlimInterfaceOut robot_out;
	private RobAlimInterfaceIn robot_in;
	private Button send_action_button;
	private Button send_alim_button;
	private Spinner spinner;
	private Spinner spinneralim;
	private TextView program_value;
	private TextView alimentation_value;
	private TextView statut_value;
	private TextView variateur_value;
	private TextView ultrason_instant_0;
	private TextView ultrason_mean_0;
	private TextView ultrason_instant_1;
	private TextView ultrason_mean_1;
	private TextView inductif_instant_0;
	private TextView inductif_instant_1;
	private TextView inductif_instant_2;
	private Activity main_activity;
	private boolean view_created = false;
	
	public IndicationsFragmentView() {
		super();
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        
        robot_in.addObserver(this);
        Log.i("IndicationFragmentView","addObserver");
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.indications_view, container, false);
        main_activity=getActivity();
        
        ultrason_instant_0=(TextView)rootView.findViewById(R.id.ultrason_instant_0);
        ultrason_instant_1=(TextView)rootView.findViewById(R.id.ultrason_instant_1);
        inductif_instant_0=(TextView)rootView.findViewById(R.id.inductif_instant_0);
        inductif_instant_1=(TextView)rootView.findViewById(R.id.inductif_instant_1);
        inductif_instant_2=(TextView)rootView.findViewById(R.id.inductif_instant_2);
        ultrason_mean_0=(TextView)rootView.findViewById(R.id.ultrason_mean_0);
        ultrason_mean_1=(TextView)rootView.findViewById(R.id.ultrason_mean_1);
        
        spinner = (Spinner)rootView.findViewById(R.id.select_program);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(main_activity,R.array.actions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        spinneralim = (Spinner)rootView.findViewById(R.id.select_alim);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(main_activity,R.array.alimentations_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneralim.setAdapter(adapter1);
        
        program_value = (TextView)rootView.findViewById(R.id.program_value);
        alimentation_value = (TextView)rootView.findViewById(R.id.alimentation_value);
        variateur_value= (TextView)rootView.findViewById(R.id.variateur_value);
        statut_value= (TextView)rootView.findViewById(R.id.statut_value);
        
        send_action_button = (Button)rootView.findViewById(R.id.send_action_button);
        send_action_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String action=spinner.getSelectedItem().toString();
				robot_out.envoyerAction(main_activity, action);
			}
        });
        
        
		send_alim_button = (Button)rootView.findViewById(R.id.send_alim_button);
	    send_alim_button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String action=spinneralim.getSelectedItem().toString();
					robot_out.envoyerAlimentationValue(main_activity, action);
				}	
		
		});
        view_created=true;
        update(null, null);
        return rootView;
    }
    
	@Override
	public void update(Observable observable, Object data) {
		if(view_created){
		
			String action = robot_in.getAction();
			String alimentation = robot_in.getAlimentationValue();
			String variateur=robot_in.getVariateur();
			String statut=robot_in.getStatut();
			
			program_value.setText(action);
			alimentation_value.setText(alimentation);
			variateur_value.setText(variateur);
			statut_value.setText(statut);
			
			int [] ultrasons = robot_in.getUltrasonValues();
			int [] inductifs = robot_in.getInductifValues();
			int [] ultrasons_mean = robot_in.getUltrasonMeans();
	//		Log.i("RobotALimIn"," "+inductifs_mean[0]+" "+inductifs_mean[1]+" "+inductifs_mean[2]);
	//		Log.i("RobotALimIn"," "+ultrasons_mean[0]+" "+ultrasons_mean[1]);
	
			ultrason_instant_0.setText(""+ultrasons[0]);
			ultrason_instant_1.setText(""+ultrasons[1]);
			
			inductif_instant_0.setText(""+inductifs[0]);
			inductif_instant_1.setText(""+inductifs[1]);
			inductif_instant_2.setText(""+inductifs[2]);
			
			ultrason_mean_0.setText(""+ultrasons_mean[0]);
			ultrason_mean_1.setText(""+ultrasons_mean[1]);
		}
		
	}
}
