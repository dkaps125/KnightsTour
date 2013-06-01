package knightstour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 * A class that demonstrates the knight's tour 1, utilizing the grid and knight classes
 * @author Daniel Kapit
 *
 */
public class KTour1 {
	Random r = new Random();
	Knight k;
	Timer t = new Timer(500, new Tour());
	int roof = 64;
	int reps = 0;
	
	/**
	 * The constructor for the experiment
	 * @param The knight to be used in the experiment
	 */
	public KTour1(Knight j) {
		this.k = j;
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
		Location decision = k_locs[r.nextInt(k_locs.length)];
		
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
	
	public static void main(String[] args) {
		Grid g = new Grid();
		Knight k = new Knight();
		g.add(k);
		KTour1 to = new KTour1(k);
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
