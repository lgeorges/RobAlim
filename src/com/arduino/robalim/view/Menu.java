package com.arduino.robalim.view;

import com.example.robalim.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends Activity {
	
	private Activity menu_activity;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menu_activity=this;
        setContentView(R.layout.menu);
        Button test_button=(Button)findViewById(R.id.test_button);
        test_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("click", "onClick");
				Intent i = new Intent(menu_activity, MenuTest.class);
		    	startActivity(i);
//				setContentView(R.layout.menu_test);
				
			}
		});
	}
}
