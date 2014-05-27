package com.example.space.entity.ship;

import com.badlogic.gdx.graphics.Texture;
import com.example.space.entity.Entity;
import com.example.space.entity.ship.modules.Frame;

public abstract class Ship extends Entity {
	protected Frame frame;
	protected float speed;
	
	public Ship(Texture t, Frame frame) {
		super(t);
	}
}
