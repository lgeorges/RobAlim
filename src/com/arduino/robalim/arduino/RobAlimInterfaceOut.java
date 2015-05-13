package com.arduino.robalim.arduino;

import com.arduino.robalim.UserInputGraph;

import android.content.Context;
import android.util.Log;
import at.abraxas.amarino.Amarino;

public class RobAlimInterfaceOut {
	
	private static RobAlimInterfaceOut instance = new RobAlimInterfaceOut();
	
	public static RobAlimInterfaceOut getInstance(){
		return instance;
	}
	public void setModeManuel(Context context){
		Log.i("MSG OUT", "Mode Manuel");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'O', 1);
	}
	
	public void setModeAuto(Context context){
		Log.i("MSG OUT", "Mode Auto");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'O', 0);
	}

	public void avancer(Context context){
		Log.i("MSG OUT", "Avancer");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'A', 5);
	}
	
	public void avancerRapidement(Context context){
		Log.i("MSG OUT", "Avancer Rapidement");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'A', 2);
	}
	
	public void tournerAGauche(Context context){
		Log.i("MSG OUT", "Tourner a gauche");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'A', 4);
	}

	public void tournerAGaucheRapidement(Context context){
		Log.i("MSG OUT", "Tourner a gauche rapidement");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'A', 1);
	}
	
	public void tournerADroite(Context context){
		Log.i("MSG OUT", "Tourner a droite");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'A', 6);
	}
	
	public void tournerADroiteRapidement(Context context){
		Log.i("MSG OUT", "Tourner a droite rapidement");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'A', 3);
	}
	
	public void stopper(Context context){
		Log.i("MSG OUT", "Stopper");
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'A', 7);
	}
	
	public void envoyerAction(Context context,String action){
		Log.i("MSG OUT", "Envoyer action: "+action);
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'C', action);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "action/"+action);
	}
}
