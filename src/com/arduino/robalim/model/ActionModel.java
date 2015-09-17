package com.arduino.robalim.model;

import java.util.ArrayList;

import com.arduino.robalim.model.AlimentationModel.Passage;

public class ActionModel {
	private AvancementEnum avancement;
	private FinActionEnum fin_action;
	private short distance;
	private ArrayList<PassageModel> passages;

	
	public ActionModel() {
		avancement= AvancementEnum.ULTRASON_GAUCHE;
		fin_action= FinActionEnum.PERTE_BANDE;
		distance=0;
		passages = new ArrayList<PassageModel>();
	}
	
	public ActionModel(AvancementEnum a, FinActionEnum fa, short d, ArrayList<PassageModel> ps) {
		avancement= a;
		fin_action= fa;
		distance=d;
		passages = ps;
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
	
	public void setAlimentPassage(int idx, short aliment){
		PassageModel p = passages.get(idx);
		p.setAliment(aliment);
	}
	
	public void setVitessePassage(int idx, int vitesse){
		PassageModel p = passages.get(idx);
		p.setVitesse(vitesse);
	}
	
	public ArrayList<PassageModel> getPassages(){
		return passages;
	}
		
}
