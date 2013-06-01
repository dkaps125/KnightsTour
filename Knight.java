package knightstour;

import java.util.ArrayList;

/**
 * A class that defines a knight object, to be used in the Euler problems
 * @author Daniel Kapit
 *
 */
public class Knight {
	Grid g;
	Location loc;
	int curr_num = 1;
	
	/**
	 * Constructs the knight object, with its starting location
	 */
	public Knight() {
		this.loc = new Location(1, 1);
	}
	
	/**
	 * Finds valid locations that the knight can move to
	 * @return An array of valid locations
	 */
	public Location[] getMoveLocs() {
		ArrayList<Location> l_r = new ArrayList<Location>();
		
		//checks to see which of the knight's possible moves are into valid locations
		if (this.loc.x - 2 >= 0 && this.loc.y + 1 < this.g.grid.length)
			l_r.add(new Location(this.loc.x-2, this.loc.y+1));
		
		if (this.loc.x + 1 < this.g.grid.length && this.loc.y - 2 >= 0)
			l_r.add(new Location(this.loc.x+1, this.loc.y-2));
		
		if (this.loc.x + 2 < this.g.grid.length && this.loc.y - 1 >= 0)
			l_r.add(new Location(this.loc.x+2, this.loc.y-1));
		
		if (this.loc.x + 2 < this.g.grid.length && this.loc.y + 1 < this.g.grid.length)
			l_r.add(new Location(this.loc.x+2, this.loc.y+1));
		
		if (this.loc.x + 1 < this.g.grid.length && this.loc.y + 2 < this.g.grid.length)
			l_r.add(new Location(this.loc.x+1, this.loc.y+2));
		
		if (this.loc.x - 1 >= 0 && this.loc.y + 2 < this.g.grid.length)
			l_r.add(new Location(this.loc.x-1, this.loc.y+2));
		
		if (this.loc.x - 2 >= 0 && this.loc.y - 1 >= 0)
			l_r.add(new Location(this.loc.x-2, this.loc.y-1));
		
		if (this.loc.x - 1 >= 0 && this.loc.y - 2 >= 0)
			l_r.add(new Location(this.loc.x-1, this.loc.y-2));
		
		Location[] ls = new Location[l_r.size()];
		for (Location l : l_r) {
			ls[l_r.indexOf(l)] = l;
		}
		return ls;
	}
}
