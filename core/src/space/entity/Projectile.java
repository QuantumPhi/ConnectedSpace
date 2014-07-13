package space.entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Projectile extends Entity {  
    public double speed;
    public int damage;
    
    public Projectile(Texture t, double px, double py, double s, int d) {
        super(t);
        x = px;
        y = py;
        speed = s;
        damage = d;
        sprite.setX((float)getScreenX());
    }
}
