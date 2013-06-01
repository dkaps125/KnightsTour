package knightstour;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * A class that defines an object that can be used as a chess board. 
 * @author Daniel Kapit
 *
 */
public class Grid extends JFrame {
	
	JButton[][] grid = new JButton[8][8];
	Knight kni;
	BufferedImage knight;
	
	/**
	 * The constructor for the chess board object
	 */
	public Grid() {
		//initializes all of the settings of the frame
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Knight's Tour");
		setLayout(new GridLayout(9, 9));
		
		//initializes all of the buttons in the grid
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = new JButton();
			}
		}
		
		//adds numbers labeling the columns
		add(new JLabel());
		for (int r = 0; r < 8; r++) {
			int num = r + 1;
			add(new JLabel("        " + num));
		}
		JLabel[] cols = new JLabel[8];
		//adds numbers labeling the rows
		for (int c = 0; c < 8; c++) {
			int num = c + 1;
			cols[c] = new JLabel("        " + num);
		}
		
		//adds all of the spaces of the chess board to the frame\
		int n = 0;
		for (JButton[] jarray : grid) {
			add(cols[n]);
			n++;
			for (JButton j : jarray) {
				add(j);
			}
		}
	}
	
	/**
	 * Checks if the chess board contains a knight
	 * @return true if the array contains a knight, false if it does not
	 */
	public boolean containsKnight() {
		//scans the jbuttons for numbers, which would show a knight has been added
		for (JButton[] jarray : this.grid){
			for (JButton j : jarray) {
				if (j.getText().equals("1"))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * This method adds a knight to a grid, interlinking the properties of each
	 * @param k the knight that is being added to the grid
	 * @return true if it succeeded, false if the grid already contains a knight
	 */
	public boolean add(Knight k) {
		if (this.containsKnight()) 
			return false;
		
		//adds the first number to the grid
		this.grid[k.loc.x][k.loc.y].setText(k.curr_num + "");
		k.curr_num++;
		this.grid[k.loc.x][k.loc.y].setEnabled(false);
		
		//coordinates the variables
		k.g = this;
		this.kni = k;
		
		return true;
	}
	
	public static void main(String[] args) {
		Grid g = new Grid();
		Knight k = new Knight();
		g.add(k);
		KTour2 to = new KTour2(k);
		g.setVisible(true);
		to.t.start();
	}
	
}

/**
 * A class that defines a Location, with x and y variables
 * @author Daniel Kapit
 *
 */
class Location {
	int x; 
	int y;
	
	/**
	 * The constructor for the location object
	 * @param x1 the x variable
	 * @param y1 the y variable
	 */
	public Location(int x1, int y1) {
		this.x = x1;
		this.y = y1;
	}
}
