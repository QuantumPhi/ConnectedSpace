package space.entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Projectile extends Entity {
    
    public int damage;
    public int speed;
    
    public Projectile(Texture t, double nx, double ny, int d, int s) {
        super(t);
        x = nx;
        y = ny;
        damage = d;
        speed = s;
        sprite.setX((float)getScreenX());
    }
}
