package com.arduino.robalim.model;

public enum AlimentEnum {
	OFF("Off"), 
	ALIMENT1("Aliment 1"), 
	ALIMENT2("Aliment 2"), 
	ALIMENT3("Aliment 3");
	
	private final String name;
	AlimentEnum(String name){
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
	
	public static String[] getList(){
		AlimentEnum[] list = AlimentEnum.values();
		String[] result = new String [list.length];
		
		for(int i=0; i<list.length; i++){
			result[i]=list[i].toString();
		}
		
		return result;
	}
}
