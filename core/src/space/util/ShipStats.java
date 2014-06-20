package space.util;
/*	Use:
 * 		-Provide easier implementation of different statistics
 *		-Planning for different unlockable ships/parts (not just new 'skins')
 */


public class ShipStats {
	private int shipType;//Used for special abilities?
	private int reloadTime;//Faster/slower laser bursts
	private int maxSpeed;//How fast the ship can move
	
	//Add as figure out how:
	//	Origin/# of lasers
	//
	
	public ShipStats(){//Since we're not to this point yet, default constructor that does exactly what normal ships have
		shipType = 0;
		reloadTime = 1000;
		maxSpeed = 0;	
	}
	
	public int getType() { return shipType; }
	public int getReloadTime() { return reloadTime; }
	public int getMaxSpeed() { return maxSpeed; }
	
	
}
