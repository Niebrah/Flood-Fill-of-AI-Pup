package mian;

//pojo = plain old java object

import java.util.HashMap;


//tracks row and col positions of already checked spots


public class Post {
	
	//position has row an col
	public static HashMap<Integer, String> positions = new HashMap<Integer, String>();
	
	int col, row;
	
	public Post(int row, int col) {
		this.row = row;
		this.col = col;
		
	}
	
	public boolean equals(Post p) {
		
		if (this.row == p.row && this.col == p.col) {
			return true;
		}
		
		
		return false;
	}
	
	
	@Override
	public String toString() {
		return "@MEMORY--> ROW:" + this.row + " and COL:" + this.col;
	}
}


