package com.arduino.robalim.view;

import com.example.robalim.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MenuActions extends Activity {
	
	private Activity menu_activity;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_view);
        
        menu_activity=this;
        Spinner spinner = (Spinner) findViewById(R.id.select_action);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.actions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
	}
}
