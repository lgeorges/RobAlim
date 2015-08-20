package com.arduino.robalim.model;

public class VitesseEnum {
	private static int val_max = 100;
	VitesseEnum(){
	}
	
	public static String[] getList(){
		
		String[] result = new String [val_max+1];
		
		for(int i=0; i<=val_max; i++){
			result[i]=i+"";
		}
		
		return result;
	}
}
