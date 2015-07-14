package com.arduino.robalim.model;

public class PassageModel {
	short aliment;
	int vitesse;
	
	
	public PassageModel(){
		this((short)0,0);
	}
	
	public PassageModel(short aliment, int vitesse){
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
