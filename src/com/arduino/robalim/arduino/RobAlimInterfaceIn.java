package com.arduino.robalim.arduino;

import java.util.ArrayList;
import java.util.Observable;

import com.arduino.robalim.model.ActionModel;
import com.arduino.robalim.model.AlimentationModel;
import com.arduino.robalim.model.AlimentationModel.Passage;
import com.arduino.robalim.model.AvancementEnum;
import com.arduino.robalim.model.FinActionEnum;
import com.arduino.robalim.model.PassageModel;

import android.app.ListActivity;
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
	private String alimentation_value = "No value defined";
	private int border_value = 0;
	private int distribution_value = 0;
	private int current_passage = 0;
	private ArrayList<ActionModel> actions;
	
	public RobAlimInterfaceIn(){
		mode_manuel=false;
		statut="No Statut";
		action="No action";
		variateur="No variateur";
		actions = new ArrayList<ActionModel>();
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
	
	public String getAlimentationValue(){
		return alimentation_value;
	}
	public int getDistributionValue(){
		return distribution_value;
	}
	public int getBorderValue(){
		return border_value;
	}
	public ArrayList<ActionModel> getActions(){
		return actions;
	}
	public int getCurrentPassage(){
		return current_passage;
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
		
		else if(identifiant.equalsIgnoreCase("actions")){
			int num_action = Integer.parseInt(parts[1]);
			AvancementEnum a = AvancementEnum.values()[Integer.parseInt(parts[2])];
			FinActionEnum fa = FinActionEnum.values()[Integer.parseInt(parts[3])];
			short d = Short.parseShort(parts[4]);
			Log.d("MSG IN", a+" "+fa+" "+d);
			ArrayList<PassageModel> passages = new ArrayList<PassageModel>();

			if(a==AvancementEnum.ULTRASON_GAUCHE && parts.length>5){
				String[] passages_txt = parts[5].split("\\|");
				for(String p : passages_txt){
					Log.d("msg in passage:"," "+p);
					String [] p_txt = p.split("_");
					if(p_txt.length>=2){
						passages.add(new PassageModel(Short.parseShort(p_txt[0]), Integer.parseInt(p_txt[1])));
						Log.d("msg in passage:"," "+p_txt[0]+" "+p_txt[1]);
					}
					
				}
			}
			if(num_action<actions.size())
				actions.set(num_action, new ActionModel(a,fa,d,passages));
			else
				actions.add(new ActionModel(a,fa,d,passages));			
			
			
		}
			

		else if(identifiant.equalsIgnoreCase("alimentation"))
			alimentation_value = parts[1];
		
		else if(identifiant.equalsIgnoreCase("statut"))
			statut = parts[1];
		
		else if(identifiant.equalsIgnoreCase("variateur"))
			variateur = parts[1];
		else if(identifiant.equalsIgnoreCase("consigne"))
			border_value = Integer.parseInt(parts[1]);
		else if(identifiant.equalsIgnoreCase("vitessealiment"))
			distribution_value = Integer.parseInt(parts[1]);
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
		else if(identifiant.equalsIgnoreCase("passage")){
			current_passage=Integer.parseInt(parts[2]);
		}
		
		setChanged();
		notifyObservers();
	}


}
