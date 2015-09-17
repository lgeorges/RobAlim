package com.arduino.robalim.model;

import java.util.ArrayList;

public class DistanceEnum {
	private static int val_max = 255;
	private static int pas = 5;
	DistanceEnum(){
	}
	
	public static String[] getList(){
		String[] res = new String [val_max/pas+1];
		int idx=0;
		for(int i=0; i<=val_max; i=i+pas){
			res[idx]=""+i;
			idx++;
		}
		
		return res ;
	}
	public static int getOrdinal(int val){
		int idx_max = val_max/pas+1;
		int[] tmp = new int [idx_max];
		int idx=0;
		for(int i=0; i<=val_max; i=i+pas){
			tmp[idx]=i;
			idx++;
		}
		idx=0;
		while(idx<idx_max && tmp[idx]<val)
			idx++;
		
		if(val>0)
			return idx-1;
		else
			return 0;
	}
}