package com.arduino.robalim.arduino;

import java.util.Observable;

import android.content.IntentFilter;
import android.util.Log;
import at.abraxas.amarino.AmarinoIntent;

public class RobAlimInterfaceIn extends Observable {
	
	private static RobAlimInterfaceIn instance = new RobAlimInterfaceIn();
	private String mode;
	private String statut;
	private String action;
	private int[] inductif_values = {0,0,0};
	private int[] ultrason_values = {0,0};
	private ArduinoReceiver arduino_receiver;
	
	public RobAlimInterfaceIn(){
		mode="manuel";
//		registerReceiver(arduino_receiver, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
	}
	
	public ArduinoReceiver getArduinoReceiver() {
		return arduino_receiver;
	}

	public static RobAlimInterfaceIn getInstance(){
		return instance;
	}
	public String getMode() {
		return mode;
	}
	public String getStatut() {
		return statut;
	}
	public String getAction() {
		return action;
	}
	public int[] getInductifValues() {
		return inductif_values;
	}
	public int[] getUltrasonValues() {
		return ultrason_values;
	}
	
	public void setArduinoReceiver(ArduinoReceiver r){
		this.arduino_receiver=r;
		Log.i("RobAlimIn","set arduino receiver "+r);
	}
	public void updateData(int data_type, String data){
//		action=data;
		String[] parts = data.split("/");
		String identifiant = parts[0]; // 004
		
		Log.i("RobAlimIn","id: "+parts[0]+" data"+parts[1]);
		
		if(identifiant.equalsIgnoreCase("mode"))
			mode = parts[1];
		
		else if(identifiant.equalsIgnoreCase("action"))
			action = parts[1];
		
		else if(identifiant.equalsIgnoreCase("statut"))
			statut = parts[1];
		
		else if(identifiant.equalsIgnoreCase("ultrasons")){
			if(parts[1].equals("0"))
				ultrason_values[0] = Integer.parseInt(parts[2]);
			if(parts[1].equals("1"))
				ultrason_values[1] = Integer.parseInt(parts[2]);
		}
		else if(identifiant.equalsIgnoreCase("inductifs")){
			if(parts[1].equals("0"))
				inductif_values[0] = Integer.parseInt(parts[2]);
			if(parts[1].equals("1"))
				inductif_values[1] = Integer.parseInt(parts[2]);
			if(parts[1].equals("2"))
				inductif_values[2] = Integer.parseInt(parts[2]);
		}
		
		Log.i("RobAlimInfoIn",data);
		setChanged();
		notifyObservers();
	}


}
