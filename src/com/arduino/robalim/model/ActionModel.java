package com.arduino.robalim.model;

import java.util.ArrayList;

public class ActionModel {
	private AvancementEnum avancement;
	private FinActionEnum fin_action;
	private short distance;
	private AlimentationModel alimentation;

	
	public ActionModel() {
		avancement= AvancementEnum.HORS_MARCHE;
		fin_action= FinActionEnum.PERTE_BANDE;
		distance=0;
		alimentation = new AlimentationModel();
	}

	public ActionModel(AvancementEnum a, FinActionEnum fa, short d) {
		avancement= a;
		fin_action= fa;
		distance=d;
	}
		
	public AvancementEnum getAvancement() {
		return avancement;
	}
	
	public void setAvancement( AvancementEnum a){
		avancement= a;
	}

	public FinActionEnum getFinAction() {
		return fin_action;
	}
	
	public void setFinAction( FinActionEnum fa){
		fin_action = fa;
	}

	public short getDistance() {
		return distance;
	}
	
	public void setDistance(short distance) {
		this.distance = distance;
	}
	
	public void setPassage(short passage, short aliment, int vitesse ){
		alimentation.setPassage(passage, aliment, vitesse);
	}
	
	public ArrayList<AlimentationModel.Passage> getPassages(){
		return alimentation.getPassages();
	}
		
}
