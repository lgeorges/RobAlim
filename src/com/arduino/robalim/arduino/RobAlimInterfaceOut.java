package com.arduino.robalim.arduino;

//import com.arduino.robalim.UserInputGraph;

import java.util.ArrayList;

import com.arduino.robalim.model.ActionModel;
import com.arduino.robalim.model.PassageModel;
import com.arduino.robalim.model.AlimentationModel.Passage;
import com.arduino.robalim.model.AvancementEnum;

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
	
	public void envoyerAlimentationValue(Context context,String alimentation){
		Log.i("MSG OUT", "Envoyer Alimentation Value: "+alimentation);
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'E', alimentation);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "alimentation/"+alimentation);
	}
	
	public void reglageVitesseDistribution(Context context, int progress_value){
		Log.i("MSG OUT", "Reglage vitesse distribution: "+progress_value);
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'B', progress_value);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "vitessealiment/"+progress_value);
	}
	
	public void reglageBordure(Context context, int progress_value){
		Log.i("MSG OUT", "Reglage bordure: "+progress_value);
		Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'D', progress_value);
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		robot_in.updateData(0, "consigne/"+progress_value);
	}
	
	public void importActions(Context context){
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
//		robot_in.updateData(0, "actions/0/0/0/123/");
//		robot_in.updateData(0, "actions/0/0/0/123/12_12");
		robot_in.updateData(0, "actions/0/0/0/123/12_12|14_156|45_789");
		robot_in.updateData(0, "actions/1/2/2/123");
	}
	
	public void sendActions(Context context){
		
		RobAlimInterfaceIn robot_in = RobAlimInterfaceIn.getInstance();
		ArrayList<ActionModel> actions = robot_in.getActions();
		for(ActionModel a : actions){
			String char_action = actions.indexOf(a)+"/"+a.getAvancement().ordinal()+"/"+a.getFinAction().ordinal()+"/"+a.getDistance();
			if(a.getAvancement()== AvancementEnum.ULTRASON_GAUCHE){
				char_action = char_action+"/";
				for(PassageModel p : a.getPassages())
					char_action=char_action+p.getAliment()+"_"+p.getVitesse()+"|";
			}
				
				
			
			Log.i("MSG OUT", "send actions: "+char_action);
			Amarino.sendDataToArduino(context, ConnectionManager.getInstance().getAddress(), 'D', char_action);
		}
	}
	
	
}
