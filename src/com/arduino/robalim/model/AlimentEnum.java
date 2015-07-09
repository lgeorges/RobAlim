package com.arduino.robalim.model;

public enum AlimentEnum {
	ALIMENT1 ("Aliment 1"), 
	ALIMENT2("Aliment 2"), 
	ALIMENT3("Aliment 3");
	
	private final String name;
	AlimentEnum(String name){
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
}
