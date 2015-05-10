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
		Amarino.sendDataToArduino(context, UserInputGraph.DEVICE_ADDRESS, 'o', "manuel");
	}
	
	public void setModeAuto(Context context){
		Log.i("MSG OUT", "Mode Auto");
	}

	public void avancer(Context context){
		Log.i("MSG OUT", "Avancer");
	}
	
	public void avancerRapidement(Context context){
		Log.i("MSG OUT", "Avancer Rapidement");
	}
	
	public void tournerAGauche(Context context){
		Log.i("MSG OUT", "Tourner a gauche");
	}

	public void tournerAGaucheRapidement(Context context){
		Log.i("MSG OUT", "Tourner a gauche rapidement");
	}
	
	public void tournerADroite(Context context){
		Log.i("MSG OUT", "Tourner a droite");
	}
	
	public void tournerADroiteRapidement(Context context){
		Log.i("MSG OUT", "Tourner a droite rapidement");
	}
	
	public void stopper(Context context){
		Log.i("MSG OUT", "Stopper");
	}
	
	public void envoyerAction(Context context,String action){
		Log.i("MSG OUT", "Envoyer action: "+action);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "action/"+action);
	}
}
