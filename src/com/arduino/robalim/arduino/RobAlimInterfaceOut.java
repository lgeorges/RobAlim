package com.arduino.robalim.arduino;

//import com.arduino.robalim.UserInputGraph;

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
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'O', 1);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "mode/manuel");
	}
	
	public void setModeAuto(Context context){
		Log.i("MSG OUT", "Mode Auto");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'O', 0);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "mode/auto");
	}

	public void avancer(Context context){
		Log.i("MSG OUT", "Avancer");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'A', 5);
	}
	
	public void avancerRapidement(Context context){
		Log.i("MSG OUT", "Avancer Rapidement");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'A', 2);
	}
	
	public void tournerAGauche(Context context){
		Log.i("MSG OUT", "Tourner a gauche");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'A', 4);
	}

	public void avancerGauche(Context context){
		Log.i("MSG OUT", "Avancer à gauche");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'A', 1);
	}
	
	public void tournerADroite(Context context){
		Log.i("MSG OUT", "Tourner a droite");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'A', 6);
	}
	
	public void avancerDroite(Context context){
		Log.i("MSG OUT", "Avancer à droite");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'A', 3);
	}
	
	public void stopper(Context context){
		Log.i("MSG OUT", "Stopper");
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'A', 7);
	}
	
	public void envoyerAction(Context context,String action){
		Log.i("MSG OUT", "Envoyer action: "+action);
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'C', action);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "action/"+action);
	}
}
