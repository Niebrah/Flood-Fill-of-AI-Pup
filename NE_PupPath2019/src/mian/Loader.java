package mian;

import java.util.HashMap;

public class Loader {

	
	public static HashMap<Integer, String> legend = new HashMap<Integer, String>();
	
	public static HashMap<Integer, String> abbrev = new HashMap<Integer, String>();
	
	public static String[] dirmap = {"WEST","EAST","NORTH","SOUTH"};
	
	public static void pupulateLeg() {
		legend.put(0,"ground");
		legend.put(1,"wall");
		legend.put(2,"food");
		legend.put(3,"pup");
		legend.put(4,"ground");
		legend.put(5,"mutt");
		
		abbrev.put(0," ");
		abbrev.put(1,"=");
		abbrev.put(2,"$");
		abbrev.put(3,"P");
		abbrev.put(4," ");
		abbrev.put(5,"D");
		
		
		
	}
	
	
	
	
}
