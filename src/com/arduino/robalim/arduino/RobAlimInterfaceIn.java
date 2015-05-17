package com.arduino.robalim.arduino;

import java.util.Observable;

import android.content.IntentFilter;
import android.util.Log;
import at.abraxas.amarino.AmarinoIntent;

public class RobAlimInterfaceIn extends Observable {
	
	private static RobAlimInterfaceIn instance = new RobAlimInterfaceIn();
	private boolean mode_manuel;
	private String statut;
	private String action;
	private String variateur;
	private int[] inductif_values = {0,0,0};
	private int[] ultrason_values = {0,0};
	private int[] ultrason_means = {0,0};
	private ArduinoReceiver arduino_receiver;
	
	public RobAlimInterfaceIn(){
		mode_manuel=false;
		statut="No Statut";
		action="No action";
		variateur="No variateur";
	}
	
	public ArduinoReceiver getArduinoReceiver() {
		return arduino_receiver;
	}

	public static RobAlimInterfaceIn getInstance(){
		return instance;
	}
	public boolean isInManualMode() {
		return mode_manuel;
	}
	public String getStatut() {
		return statut;
	}
	public String getAction() {
		return action;
	}
	public String getVariateur() {
		return variateur;
	}
	public int[] getInductifValues() {
		return inductif_values;
	}
	public int[] getUltrasonValues() {
		return ultrason_values;
	}
	public int[] getUltrasonMeans() {
		return ultrason_means;
	}
	
	public void setArduinoReceiver(ArduinoReceiver r){
		this.arduino_receiver=r;
		Log.i("RobAlimIn","set arduino receiver "+r);
	}
	public void updateData(int data_type, String data){
//		action=data;
		String[] parts = data.split("/");
		String identifiant = parts[0]; // 004
		
		Log.i("RobAlimIn","id: "+parts[0]+" data "+parts[1]);
		
		if(identifiant.equalsIgnoreCase("mode")){
			if(parts[1].equals("auto"))
				mode_manuel = false;
			else
				mode_manuel = true;
		}		
		else if(identifiant.equalsIgnoreCase("action"))
			action = parts[1];
		
		else if(identifiant.equalsIgnoreCase("statut"))
			statut = parts[1];
		
		else if(identifiant.equalsIgnoreCase("variateur"))
			variateur = parts[1];
		
		else if(identifiant.equalsIgnoreCase("ultrasons")){
			if(parts[1].equals("i0"))
				ultrason_values[0] = Integer.parseInt(parts[2]);
			if(parts[1].equals("i1"))
				ultrason_values[1] = Integer.parseInt(parts[2]);
			if(parts[1].equals("m0"))
				ultrason_means[0] = Integer.parseInt(parts[2]);
			if(parts[1].equals("m1"))
				ultrason_means[1] = Integer.parseInt(parts[2]);
		}
		else if(identifiant.equalsIgnoreCase("inductifs")){
			if(parts[1].equals("0"))
				inductif_values[0] = Integer.parseInt(parts[2]);
			if(parts[1].equals("1"))
				inductif_values[1] = Integer.parseInt(parts[2]);
			if(parts[1].equals("2"))
				inductif_values[2] = Integer.parseInt(parts[2]);
		}
		
		setChanged();
		notifyObservers();
	}


}
