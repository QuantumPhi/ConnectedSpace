package space.entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Projectile extends Entity {
    
    protected int damage;
    
    public Projectile(Texture t, int d) {
        super(t);
        damage = d;
    }
    
    public int getDamage() { return damage; }
}
