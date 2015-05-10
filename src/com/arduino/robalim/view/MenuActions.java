package com.arduino.robalim.view;

import java.util.Observable;
import java.util.Observer;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.example.robalim.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuActions extends Activity implements Observer{
	
	private Activity activity;
	private RobAlimInterfaceOut robot_out;
	private RobAlimInterfaceIn robot_in;
	private Button send_action_button;
	private TextView action_status;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_view);
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        robot_in.addObserver(this);

        activity=this;

        final Spinner spinner = (Spinner) findViewById(R.id.select_action);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.actions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        action_status = (TextView)findViewById(R.id.action_status);
        
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
	public void update(Observable observable, Object data) {
		String action = robot_in.getAction();
		action_status.setText("Action: "+action+" en cours");
	}
}
