package space.util;
/*	Use:
 * 		-Provide easier implementation of different statistics
 *		-Planning for different unlockable ships/parts (not just new 'skins')
 */


public class ShipStats {
	private int shipType;//Used for special abilities?
	private int reloadTime;//Faster/slower laser bursts
	private double sideSpeedModifier;//How fast the ship can move sideways
	private double frontSpeedModifier;//Same, but forward/backward
	
	//Add as figure out how:
	//	Origin/# of lasers
	//
	
	public ShipStats(){//Since we're not to this point yet, default constructor that does exactly what normal ships have
		shipType = 0;
		reloadTime = 500;
		sideSpeedModifier = 1.0;	
		frontSpeedModifier = 1.0;
	}
	
	public int getType() { return shipType; }
	public int getReloadTime() { return reloadTime; }
	public double getSideSpeedModifier() { return sideSpeedModifier; }
	public double getFrontSpeedModifier() { return frontSpeedModifier; }
	
}
