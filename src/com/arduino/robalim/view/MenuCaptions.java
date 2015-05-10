package com.arduino.robalim.view;

import java.util.Observable;
import java.util.Observer;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.example.robalim.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuCaptions extends Activity implements Observer {

	private Activity menu_activity;
	private TextView ultrason_0;
	private TextView ultrason_1;
	private TextView inductif_0;
	private TextView inductif_1;
	private TextView inductif_2;
	private RobAlimInterfaceIn robot_in;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caption_view);

        robot_in= RobAlimInterfaceIn.getInstance();
        robot_in.addObserver(this);
        
        ultrason_0=(TextView)findViewById(R.id.ultrason_value_0);
        ultrason_1=(TextView)findViewById(R.id.ultrason_value_1);
        inductif_0=(TextView)findViewById(R.id.inductif_value_0);
        inductif_1=(TextView)findViewById(R.id.inductif_value_1);
        inductif_2=(TextView)findViewById(R.id.inductif_value_2);
        
        int test[]=robot_in.getUltrasonValues();
        this.update(null, null);
        Log.i("Test","onCreate "+test[0]);
//        ultrason_0.setText("106");
        
	}

	@Override
	public void update(Observable observable, Object data) {
		int [] ultrasons = robot_in.getUltrasonValues();
		int [] inductifs = robot_in.getInductifValues();
		Log.i("Capteurs","update "+ultrasons[0]+" "+ultrasons[1]);
		ultrason_0.setText(""+ultrasons[0]);
		ultrason_1.setText(""+ultrasons[1]);
		
		inductif_0.setText(""+inductifs[0]);
		inductif_1.setText(""+inductifs[1]);
		inductif_2.setText(""+inductifs[2]);
	}
}
