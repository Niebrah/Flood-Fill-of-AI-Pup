package mian;

import java.util.HashSet;

public class Mutt extends Node {

	//dog can't smell past walls
	
	public Node[][] copyWorld = new Node[12][17];
	int dir, eaten, maxFood, stuckCur, stuckMax;
	boolean stucker;
	HashSet<Post> mem = new HashSet<Post>();
	
	public Mutt(int row, int col, String name, HashSet<Post> mem) {
		
		super(row, col,name);
		
		this.eaten =0;
		this.maxFood = Main.countFood();
		this.stuckMax = 4;
		
	}
	
	public void passingOnKnowledge(HashSet<Post> mem) {
		//this adds the old pup's memory to the new spawned puppy
		for (Post p: mem) {
			this.mem.add(p);
		}
		
	}
	
	public void displayMem() {
		
		System.out.print("\n \n \n PUP'S MEMORY DISPLAYED");
		System.out.print("\n \n \n PUP'S TOTAL FOOD COUNT: "  + this.eaten);
		
		for(Post p: this.mem) {
			System.out.println(p);
		}
	}
	
	
	public Node[][] copyWorld(Node[][] arr) {
		
		//creates copy of world for pup to do flood fill for
		System.out.print("***** THE COPIED WORLD FOR PUP *******");
		for (int row=0; row < arr.length; row++) {
			
			System.out.println("");
			for (int col =0; col < arr[0].length;col++) {
				
				String name = arr[row][col].name;
				
				
				Node tempNode = new Node(row, col, name);
				copyWorld[row][col] = tempNode;
				System.out.print(copyWorld[row][col]);
				
			}
			
		}
		
		return copyWorld;
	}
	
	public void displayCPupPos() {
		
		System.out.print("\n \n \n PUP'S current position" + this.row + ", " + this.col);
	}
	
	
	
	public boolean verifyNotInMem(int dir) {
		//return true iff this attempted position is not in the memory
		
		
		//boundary checks
		int R =this.row;
		int C = this.col;
		
		
		switch(dir) {
		
		case 0:
			C++;
			break;
		case 1:
			R--;
			break;
		case 2: 
			C--;
			break;
		case 3:
			R++;
			
			break;
		default:
			System.out.print("Something is not working in the verifyNotInMem");
		
		
		
		}
		
		
		
		Post check = new Post(R,C);
		
		//for every position in the dog's respective memory
		for (Post p: this.mem) {
			
			this.mem.add(p);
			
			if (p.equals(check)) {
				System.out.println("\n This pup has already been at " + R + ", " + C);
				return false;
			}
		
		}
		
		return true;
	}
	
	
	public boolean canIWalkThere(int dir) {
		
	this.stuckCur = 0;
		
		int cR = this.row;
		int cC = this.col;
		
		switch (dir) {
		
		case 0:
			System.out.print("Pup spawns to the right or EAST");
			
			cC = this.col +1;
			break;
		case 1:
			System.out.print("Pup spawns to the up or NORTH");
			cR = this.row-1;
		
			break;
		case 2:
			System.out.print("Pup spawns to the left or WEST");
	
			cC = this.col -1;
			break;
		case 3:
			System.out.print("Pup spawns to the down or SOUTH");
			cR = this.row+1;
	
			break;
		default:
			System.out.print("It's alright there's a bug.");
			break;
		}
		
		
		System.out.println("\n the row pupppy is at is " + this.row + " and the col is " + this.col);
		
		System.out.println("\n the row the program is checking is " + cR + " and the col is checking " + cC);
		
		
		
		if (cR >= Main.world.length || cR < 0 || cC <0 || cC >= Main.world[0].length) {
			System.out.println("THERE IS NO WORLD AND PUP WILL FALL INTO NOTHINGNESS.");
			
			this.stuckCur++;
			if (this.stuckCur >= this.stuckMax) this.stucker = true; 
			
			
			return false;
		}
		
		
		Node ground = Main.world[cR][cC];
		
		int c = ground.contains;
		System.out.println("We found " + Loader.legend.get(c));
		//end of part 1
		
		

		
	
			if (this.verifyNotInMem(dir) && c!=1 && c!=5 ) {
				System.out.println("We found " + Loader.dirmap[dir]);
				
				return true;
				
			}  else {
				System.out.println(" \n been here! Don't want to go there");
				//this.stuckCur++;
				
				//if (this.stuckCur >= this.stuckMax) this.stucker = true;
				
				return false;
			}
			
		
	}
	
	public void walkRecursive() {
		
		//start off with east
		
		for (int di =0; di<4; di++) {
			
			if (this.canIWalkThere(di)) {
				System.out.print("If I can walk to " + Loader.dirmap[dir] + ", I will respawn.");
				
				int cR = this.row;
				int cC = this.col;
				
				
				switch (di) {
				
				case 0:
					System.out.print("Pup spawns to the right or EAST");
					
					cC = this.col +1;
					break;
				case 1:
					System.out.print("Pup spawns to the up or NORTH");
					cR = this.row-1;
				
					break;
				case 2:
					System.out.print("Pup spawns to the left or WEST");
			
					cC = this.col -1;
					break;
				case 3:
					System.out.print("Pup spawns to the down or SOUTH");
					cR = this.row+1;
			
					break;
				default:
					System.out.print("It's alright there's a bug.");
					break;
				}
				
				Post memory = new Post(cR,cC);
				this.mem.add(memory);
				
				
				//spawning agent
				Mutt dupli = new Mutt(cR,cC, "mutt",this.mem);
				System.out.print(dupli.mem);
				
				
				//slows it down
				try {
					Thread.sleep(180);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				Main.world[cR][cC] = dupli;
				
				System.out.print("the spawn will start walking like the original pup");
				dupli.walkRecursive();
				
				
				Mutt oldPup = new Mutt(this.row,this.col, "mutt", this.mem);
				Main.world[this.row][this.col] = oldPup;
				
			}
			
			Main.displayWorld();
		}
			
			
	/*
	 *public void walk(int dir) {
		
		this.stuckCur = 0;
		
		int cR = this.row;
		int cC = this.col;
		
		switch (dir) {
		
		case 0:
			System.out.print("Pup goes to the right or EAST");
			
			cC = this.col +1;
			break;
		case 1:
			System.out.print("Pup goes to the up or NORTH");
			cR = this.row-1;
		
			break;
		case 2:
			System.out.print("Pup goes to the left or WEST");
	
			cC = this.col -1;
			break;
		case 3:
			System.out.print("Pup goes to the down or SOUTH");
			cR = this.row+1;
	
			break;
		default:
			System.out.print("It's alright there's a bug.");
			break;
		}
		
		
		
		System.out.println("\n the row pupppy is at is " + this.row + " and the col is " + this.col);
		
		System.out.println("\n the row the program is checking is " + cR + " and the col is checking " + cC);
		
		
		
		if (cR >= Main.world.length || cR < 0 || cC <0 || cC >= Main.world[0].length) {
			System.out.println("THERE IS NO WORLD AND PUP WILL FALL INTO NOTHINGNESS.");
			this.stucker = true;
			//empty return
			return;
		}
		
		Node ground = Main.world[cR][cC];
		
		int c = ground.contains;
		System.out.println("We found " + Loader.legend.get(c));
		
		
			
			if (this.verifyNotInMem(dir) && c!=1) {
				System.out.println("We found " + Loader.dirmap[dir]);
			//add data to hash set then walk
				if (Main.world[cR][cC].contains == 2) this.eaten++;
			
				Post  memory = new Post(ground.row, ground.col);
				this.mem.add(memory);
				
				
			//	Mutt respawned = new Mutt(cR, cC, this.name, this.mem);
				
				Main.world[cR][cC]  = this;
				
				this.displayMem();
				
				Node g = new Node(this.row, this.col, "GROUND");
				
				Main.world[this.row][this.col] = g;
				
				//dog's memory is based on these lines to remember that the checked rows have really been checked
				this.row = cR;
				this.col = cC;
				
				
				return;
				
			}  else {
				System.out.println(" \n been here! Don't want to go there");
				this.stuckCur++;
				
				if (this.stuckCur >= this.stuckMax) this.stucker = true;
				
				return;
			}
			
			
			
			
			
		
		
		
	}
			
	 */
			
		
		
		
	}
	
}
