package com.arduino.robalim.arduino;

//import com.arduino.robalim.UserInputGraph;

import android.content.Context;
import android.util.Log;
import at.abraxas.amarino.Amarino;

public class RobAlimInterfaceOut {
	private String device_address;
	
	private static RobAlimInterfaceOut instance = new RobAlimInterfaceOut();
	
	public static RobAlimInterfaceOut getInstance(){
		return instance;
	}
	
	public void setDeviceAddress(String value){
		device_address=value;
	}
	
	public void setModeManuel(Context context){
		Log.i("MSG OUT", "Mode Manuel");
		Amarino.sendDataToArduino(context, device_address, 'O', 1);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "mode/manuel");
	}
	
	public void setModeAuto(Context context){
		Log.i("MSG OUT", "Mode Auto");
		Amarino.sendDataToArduino(context, device_address, 'O', 0);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "mode/auto");
	}

	public void avancer(Context context){
		Log.i("MSG OUT", "Avancer");
		Amarino.sendDataToArduino(context, device_address, 'A', 5);
	}
	
	public void avancerRapidement(Context context){
		Log.i("MSG OUT", "Avancer Rapidement");
		Amarino.sendDataToArduino(context, device_address, 'A', 2);
	}
	
	public void tournerAGauche(Context context){
		Log.i("MSG OUT", "Tourner a gauche");
		Amarino.sendDataToArduino(context, device_address, 'A', 4);
	}

	public void avancerGauche(Context context){
		Log.i("MSG OUT", "Avancer à gauche");
		Amarino.sendDataToArduino(context, device_address, 'A', 1);
	}
	
	public void tournerADroite(Context context){
		Log.i("MSG OUT", "Tourner a droite");
		Amarino.sendDataToArduino(context, device_address, 'A', 6);
	}
	
	public void avancerDroite(Context context){
		Log.i("MSG OUT", "Avancer à droite");
		Amarino.sendDataToArduino(context, device_address, 'A', 3);
	}
	
	public void stopper(Context context){
		Log.i("MSG OUT", "Stopper");
		Amarino.sendDataToArduino(context, device_address, 'A', 7);
	}
	
	public void envoyerAction(Context context,String action){
		Log.i("MSG OUT", "Envoyer action: "+action);
		Amarino.sendDataToArduino(context, device_address, 'C', action);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "action/"+action);
	}
}
