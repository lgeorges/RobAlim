package com.arduino.robalim.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import at.abraxas.amarino.AmarinoIntent;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;


public class IndicationsView extends Activity implements Observer {

	private Activity activity;
	private RobAlimInterfaceOut robot_out;
	private RobAlimInterfaceIn robot_in;
	private Button send_action_button;
	private Spinner spinner;
	private TextView program_value;
	private TextView ultrason_instant_0;
	private TextView ultrason_mean_0;
	private TextView ultrason_instant_1;
	private TextView ultrason_mean_1;
	private TextView inductif_instant_0;
	private TextView inductif_mean_0;
	private TextView inductif_instant_1;
	private TextView inductif_mean_1;
	private TextView inductif_instant_2;
	private TextView inductif_mean_2;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indications_view);
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        robot_in.addObserver(this);
        activity=this;

        ultrason_instant_0=(TextView)findViewById(R.id.ultrason_instant_0);
        ultrason_instant_1=(TextView)findViewById(R.id.ultrason_instant_1);
        inductif_instant_0=(TextView)findViewById(R.id.inductif_instant_0);
        inductif_instant_1=(TextView)findViewById(R.id.inductif_instant_1);
        inductif_instant_2=(TextView)findViewById(R.id.inductif_instant_2);
        ultrason_mean_0=(TextView)findViewById(R.id.ultrason_mean_0);
        ultrason_mean_1=(TextView)findViewById(R.id.ultrason_mean_1);
        inductif_mean_0=(TextView)findViewById(R.id.inductif_mean_0);
        inductif_mean_1=(TextView)findViewById(R.id.inductif_mean_1);
        inductif_mean_2=(TextView)findViewById(R.id.inductif_mean_2);
        
        spinner = (Spinner) findViewById(R.id.select_program);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.actions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        program_value = (TextView)findViewById(R.id.program_value);
        
        send_action_button = (Button) findViewById(R.id.send_action_button);
        send_action_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String action=spinner.getSelectedItem().toString();
				robot_out.envoyerAction(activity, action);
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
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
		String action = robot_in.getAction();
		program_value.setText(action);
		
		int [] ultrasons = robot_in.getUltrasonValues();
		int [] inductifs = robot_in.getInductifValues();
		int [] ultrasons_mean = robot_in.getUltrasonMeans();
		int [] inductifs_mean = robot_in.getInductifMeans();
//		Log.i("RobotALimIn"," "+inductifs_mean[0]+" "+inductifs_mean[1]+" "+inductifs_mean[2]);
//		Log.i("RobotALimIn"," "+ultrasons_mean[0]+" "+ultrasons_mean[1]);

		ultrason_instant_0.setText(""+ultrasons[0]);
		ultrason_instant_1.setText(""+ultrasons[1]);
		
		inductif_instant_0.setText(""+inductifs[0]);
		inductif_instant_1.setText(""+inductifs[1]);
		inductif_instant_2.setText(""+inductifs[2]);
		
		ultrason_mean_0.setText(""+ultrasons_mean[0]);
		ultrason_mean_1.setText(""+ultrasons_mean[1]);
		
		inductif_mean_0.setText(""+inductifs_mean[0]);
		inductif_mean_1.setText(""+inductifs_mean[1]);
		inductif_mean_2.setText(""+inductifs_mean[2]);
	}
}
