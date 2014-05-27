package com.example.space.entity.ship;

import com.badlogic.gdx.graphics.Texture;
import com.example.space.entity.Entity;
import com.example.space.entity.ship.modules.Frame;
import com.example.space.entity.ship.modules.interfaces.Weapon;

public abstract class Ship extends Entity {
	protected Frame frame;
	protected Weapon[] weapons;
	protected float speed;
	
	public Ship(Texture t, Frame frame) {
		super(t);
	}
}
