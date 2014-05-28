package space.entity.ship.modules.interfaces;

import space.entity.Entity;

import com.badlogic.gdx.graphics.Texture;

public class Weapon extends Entity {
    protected int baseDamage;
    protected int shieldBonus;
    protected int hullBonus;
    
    public Weapon(Texture img, int d, int s, int h) {
        super(img);
        baseDamage = d;
        shieldBonus = s;
        hullBonus = h;
    }

    @Override
    public void update(int delta) {
        
    }
}
