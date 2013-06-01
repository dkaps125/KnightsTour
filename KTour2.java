package knightstour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 * A class that demonstrates the knight's tour 2, utilizing the grid and knight classes
 * @author Daniel Kapit
 *
 */
public class KTour2 {
	Random dice = new Random();
	Knight k;
	Timer t = new Timer(500, new Tour());
	int roof = 1000;
	int reps = 0;
	int[][] access;
	
	/**
	 * The constructor for the experiment
	 * @param The knight to be used in the experiment
	 */
	public KTour2(Knight j) {
		this.k = j;
		access = setAccess();
	}
	
	/**
	 * Reads the access values from a folder, constructing an array containing them
	 * @return the array of access values
	 */
	public static int[][] setAccess() {
		int[][] a = new int[8][8];
		int i = 0;
		int j = 0;
		try {
			Scanner in = new Scanner(new File("H:\\access.txt"));
			//in.useDelimiter("\n");
			while (in.hasNext() && i < 8) {
				j = 0;
				Scanner line = new Scanner(in.nextLine());
				line.useDelimiter(" ");
				while (line.hasNext() && j < 8) {
					a[i][j] = Integer.parseInt(line.next().substring(0, 1));
					j++;
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	/**
	 * Outputs the result of the program as an 8 x 8 grid
	 */
	public void printTour() {
		//Prints the column labels
		System.out.println("     1     2     3     4     5     6     7     8");
		System.out.println();
		
		for (int i = 1; i <= k.g.grid.length; i++) {
			//Prints each row label
			System.out.print(i);
			for (int j = 0; j < k.g.grid.length; j++) {
				String s = "0";
				if (k.g.grid[i-1][j].getText().equals(""))
					s = "    0 ";
				//This is to deal with two-digit numbers
				else if (Integer.parseInt(k.g.grid[i-1][j].getText()) >= 10) {
					s = "   " + k.g.grid[i-1][j].getText() + " ";
				}
				else
					s = "    " + k.g.grid[i-1][j].getText() + " ";
				System.out.print("" + s + "");
			}
			System.out.println();
		}
		int hit = 0;
		//prints the number of squares hit in the program
		for (JButton[] jarray : k.g.grid) {
			for (JButton j : jarray) {
				if (!j.getText().equals(""))
					hit++;
			}
		}
		System.out.println(hit + " squares were hit.");
	}
	
	/**
	 * Steps once through the program, moving the knight
	 */
	public void step() {
		//Finds a valid location to move the knight
		Location[] k_locs = k.getMoveLocs();
		Location decision = this.analyze(k_locs);
		
		//Changes the position of the knight and adds to the variable that determines if the knight is 
		//stuck, having moved 64 times
		k.g.grid[k.loc.x][k.loc.y].setEnabled(true);
		if (!k.g.grid[decision.x][decision.y].getText().equals("")) {
			reps++;
		}		
		else if (k.g.grid[decision.x][decision.y].getText().equals("")) {
			k.g.grid[decision.x][decision.y].setText(k.curr_num + "");
			k.curr_num++;
		}
		k.g.grid[decision.x][decision.y].setEnabled(false);
		k.loc = decision;
		
		//stops the program, if being run from a GUI
		if (reps == roof)
			t.stop();
	}
	
	/**
	 * Analyzes the best squares to move to, based on their access values
	 * @param l the list of available locations
	 * @return the decided location
	 */
	public Location analyze(Location[] l) {
		Location res = null;
		int prev_a = 20;
		for (Location loc : l) {
			if (access[loc.x][loc.y] < prev_a) {
				res = loc;
				prev_a = access[loc.x][loc.y];
				access[loc.x][loc.y] = 10;
			}
			else if (access[loc.x][loc.y] == prev_a) {
				int r = dice.nextInt(2);
				prev_a = access[loc.x][loc.y];
				if (r == 1 && this.k.g.grid[loc.x][loc.y].getText().equals("")) {
					res = loc;
					access[loc.x][loc.y] = 10;
				}
				else {
					res = res;
				}
					
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		Grid g = new Grid();
		Knight k = new Knight();
		g.add(k);
		KTour2 to = new KTour2(k);
		while (to.reps < to.roof) {
			to.step();
		}
		to.printTour();
	}
	
	/**
	 * An actionlistener for the timer that runs the GUI
	 * @author Daniel Kapit
	 *
	 */
	class Tour implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			step();
		}
	}
}
