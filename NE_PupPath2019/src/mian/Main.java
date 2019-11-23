package mian;

import java.util.HashSet;
import java.util.Scanner;
// 11/9/19 for next week we need to work on iterative generation of the pup
public class Main {

	public static Mutt pup;
	
	public static Node[][] world = new Node[12][17];
	
	
	public static void main(String[] args) {
		System.out.print("Welcome to pup path!");
		init();
		
		Mutt tester = new Mutt(pup.row, pup.col, "Aladdin", pup.mem);
		System.out.println("\n\n the mutt's abbreviation is D");
		world[pup.row][pup.col] = tester;
		
		tester.walkRecursive();
		
		
		
		/*
	while (pup.eaten < pup.maxFood && !pup.stucker) {
		
		try {
			Thread.sleep(100);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
			// player version Scanner sc = new Scanner(System.in);
			
			System.out.println("");
			
			System.out.println("Put a number in as 0-3 with 0:east 1: north etc");
			
			pup.displayCPupPos();
			
			//int direction = sc.nextInt();
			
			int direction = (int) (4 * Math.random());
			
			System.out.print("You chose the direction of " + Loader.dirmap[direction]);
			
			pup.walkRecursive();
			
			
			displayWorld();
		
	
	}
		*/
	}
	
	
	public static int countFood() {
		
		int counter = 0;
		
		for (int row=0; row < world.length; row++) {
			for (int col =0; col < world[0].length;col++) {
				if (world[row][col].contains == 2) counter ++;
			}
		}
		
		System.out.println("THIS IS THE NUMBER OF UNEATEN FOODS IN THE MAZE: " + counter );
		
		return counter;
	}
	
	public static void init() {
		Loader.pupulateLeg();
		pupulateMaze();
		displayWorld();
		//pup.copyWorld(world);
		
	}
	
	public static void pupulateMaze() {
		
		
		for (int row=0; row < world.length; row++) {
			for (int col =0; col < world[0].length;col++) {
				int rand = (int) (Math.random() * 3);
				Node node = new Node(row, col, Loader.legend.get(rand));
				
				world[row][col] = node;
			}
		}
		
		int testRow = (int) (Math.random() * world.length);
		int testCol = (int) (Math.random() * world[0].length);
		
		 pup = new Mutt(testRow, testCol, "mutt", new HashSet<Post>());
		
		world[testRow][testCol] = pup;
		
	}
	
	
	public static void displayWorld() {
		System.out.print("***** THE WORLD *******");
		for (int row=0; row < world.length; row++) {
			System.out.println("");
			for (int col =0; col < world[0].length;col++) {
				
				System.out.print(world[row][col]);
				
			}
			
		}
		
		System.out.println("\n the pup's row is " + pup.row + " and its col is " + pup.col);

	}
	
}
