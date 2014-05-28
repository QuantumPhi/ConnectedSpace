package space.interfaces;

import space.entity.Entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Projectile extends Entity {
	protected int baseDamage;
	protected int shieldBonus;
	protected int hullBonus;
	
	public Projectile(Texture t, int b, int s, int h) {
	    super(t);
		baseDamage = b;
		shieldBonus = s;
		hullBonus = h;
	}
	
	public int getShieldDamage() { return baseDamage + shieldBonus; }
	public int getHullDamage() { return baseDamage + hullBonus; }
}
