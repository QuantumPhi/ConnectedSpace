package space.entity.ship.modules;

import space.entity.ship.modules.interfaces.Projectile;
import space.entity.ship.modules.interfaces.Shell;

public class Shield extends Shell {
    protected float recharge;
    
    public Shield(int health, float r) {
        super(health);
        recharge = r;
    }
    
	public void resolveHit(Projectile p) {
		
	}
	
	public void renderFlare() {
		
	}
}
