package mian;

import java.awt.image.BufferedImage;

public class Node {
	
	
	int x,y,w,h, col, row;
	
	String abbr;
	
	/*
	 *  contains 0 --> empty or ground
	 *  contains 1 --> wall
	 *  contains 2 --> food
	 *  contains 3 --> automatic dog
	 *  contains 4 --> 
	 *  
	 *  contains 5 --> main dog
	 */
	
	BufferedImage img = null;
	
	String name;
	 
	int contains=0;
	
	
	public Node(int row, int col, String name) {
		
		this.row = row;
		this.col = col;
		this.x = 32 * col;
		this.y = 32 * row;
		
		this.w=32;
		this.h=32;
		
		
		this.name = name;
		
		
		switch (this.name) {
		
		case "wall":
			this.contains = 1;
			break;
		case "food":
			this.contains = 2;
			break;
		case "pup":
			this.contains = 3;
			break;
		case "mutt":
			this.contains = 5;
			break;
		default: 
			this.contains =0;
			break;
		
		}
		
		this.abbr = Loader.abbrev.get(this.contains);
	}
	
	@Override
	public String toString() {
		
		return "[" + this.abbr + "]";
	}
	
	
	
	
	
}
