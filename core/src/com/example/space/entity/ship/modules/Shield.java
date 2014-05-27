package com.example.space.entity.ship.modules;

import com.example.space.entity.ship.modules.interfaces.Projectile;
import com.example.space.entity.ship.modules.interfaces.Shell;

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
