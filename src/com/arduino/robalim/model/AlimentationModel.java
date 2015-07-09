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
	}
	
	private ArrayList<Passage> passages;
	
	public AlimentationModel(){
		passages=new ArrayList<AlimentationModel.Passage>();
		for(int i=0;i<6; i++)
			passages.add(new Passage());
	}
	
	public void setPassage(short passage, short aliment, int vitesse ){
		passages.set(passage, new Passage(aliment, vitesse));
	}
	
	public ArrayList<Passage> getPassages(){
		return passages;
	}
}
