package com.arduino.robalim.view;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.example.robalim.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestsFragmentView extends Fragment {
	private RobAlimInterfaceIn robot_in;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_test, container, false);
        robot_in=RobAlimInterfaceIn.getInstance();
        
        Button button1 = (Button)rootView.findViewById(R.id.test_check);
        Button button2 = (Button)rootView.findViewById(R.id.button2);
        Button button3 = (Button)rootView.findViewById(R.id.button3);
        Button button4 = (Button)rootView.findViewById(R.id.button4);
        Button button5 = (Button)rootView.findViewById(R.id.button5);
        Button button6 = (Button)rootView.findViewById(R.id.button6);
//        Button button_stop = (Button)findViewById(R.id.button_stop);
        
        button1.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "mode/auto");
        	}
        });
        
        button2.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "mode/manuel");
        	}
        });
        
        button3.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "statut/Tests");
        	}
        });
        
        button4.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "ultrasons/i0/205");
        		robot_in.updateData(0, "ultrasons/i1/209");
        		robot_in.updateData(0, "ultrasons/m0/102");
        		robot_in.updateData(0, "ultrasons/m1/101");
        	}
        });
        
        button5.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "inductifs/0/152");
        		robot_in.updateData(0, "inductifs/1/153");
        		robot_in.updateData(0, "inductifs/2/154");
        	}
        });
        
        button6.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		robot_in.updateData(0, "variateur/etat 1");
        	}
        });
        
        return rootView;
    }
	
	
}
