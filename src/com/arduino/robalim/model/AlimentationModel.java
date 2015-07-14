package com.arduino.robalim.model;

import java.util.ArrayList;

public class AlimentationModel {
	public class Passage {
		short aliment;
		int vitesse;
		
		
		public Passage(){
			this((short)0,0);
		}
		
		public Passage(short aliment, int vitesse){
			this.aliment=aliment;
			this.vitesse=vitesse;
		}
		
		public short getAliment(){
			return aliment;
		}
		
		public void setAliment(short a){
			aliment=a;
		}
		
		public int getVitesse(){
			return vitesse;
		}
		
		public void setVitesse(int v){
			vitesse=v;
		}
	}
	
	private ArrayList<Passage> passages;
	
	public AlimentationModel(){
		passages=new ArrayList<AlimentationModel.Passage>();

	}
	
	public void setAlimentPassage(int idx, short aliment){

	}
	
	public void setVitessePassage(int idx, int vitesse){
		Passage p = passages.get(idx);
		p.setVitesse(vitesse);
	}
	
	public ArrayList<Passage> getPassages(){
		return passages;
	}
}
