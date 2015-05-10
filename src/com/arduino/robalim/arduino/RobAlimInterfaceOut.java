package com.arduino.robalim.arduino;

import android.util.Log;

public class RobAlimInterfaceOut {
	
	private static RobAlimInterfaceOut instance = new RobAlimInterfaceOut();
	
	public static RobAlimInterfaceOut getInstance(){
		return instance;
	}
	public void setModeManuel(){
		Log.i("MSG OUT", "Mode Manuel");
	}
	
	public void setModeAuto(){
		Log.i("MSG OUT", "Mode Auto");
	}

	public void avancer(){
		Log.i("MSG OUT", "Avancer");
	}
	
	public void avancerRapidement(){
		Log.i("MSG OUT", "Avancer Rapidement");
	}
	
	public void tournerAGauche(){
		Log.i("MSG OUT", "Tourner a gauche");
	}

	public void tournerAGaucheRapidement(){
		Log.i("MSG OUT", "Tourner a gauche rapidement");
	}
	
	public void tournerADroite(){
		Log.i("MSG OUT", "Tourner a droite");
	}
	
	public void tournerADroiteRapidement(){
		Log.i("MSG OUT", "Tourner a droite rapidement");
	}
	
	public void stopper(){
		Log.i("MSG OUT", "Stopper");
	}
	
	public void envoyerAction(String action){
		Log.i("MSG OUT", "Envoyer action: "+action);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "action/"+action);
	}
}
