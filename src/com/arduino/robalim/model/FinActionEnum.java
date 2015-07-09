package com.arduino.robalim.model;

public enum FinActionEnum {
	BANDE_PERPENDICULAIRE("Bande perpendiculaire"), 
	BANDE_MILIEU("Bande milieu"), 
	PERTE_BANDE("Perte bande");
	
	private final String name;
	FinActionEnum(String name){
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
	
	public static String[] getList(){
		FinActionEnum[] list = FinActionEnum.values();
		String[] result = new String [list.length];
		
		for(int i=0; i<list.length; i++){
			result[i]=list[i].toString();
		}
		
		return result;
	}
}
