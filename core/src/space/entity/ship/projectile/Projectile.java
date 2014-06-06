package space.entity.ship.projectile;

import space.entity.Entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Projectile extends Entity {
    protected int damage;
    
    public Projectile(Texture t, int d) {
        super(t);
        damage = d;
    }
    
    public int getDamage() { return damage; }
}
