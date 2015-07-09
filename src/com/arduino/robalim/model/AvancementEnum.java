package com.arduino.robalim.model;

public enum AvancementEnum {
	ULTRASON_GAUCHE("Ultrason gauche"), 
	ULTRASON_DROITE("Ultrason droite"), 
	INDUCTIF("inductif"),
	TOURNER_GAUCHE("Tourne à gauche"),
	TOURNER_DROITE("Tourne à droite"),
	HORS_MARCHE("Hors marhe");
	
	private final String name;
	AvancementEnum(String name){
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
	
	public static String[] getList(){
		AvancementEnum[] list = AvancementEnum.values();
		String[] result = new String [list.length];
		
		for(int i=0; i<list.length; i++){
			result[i]=list[i].toString();
		}
		
		return result;
	}
}
