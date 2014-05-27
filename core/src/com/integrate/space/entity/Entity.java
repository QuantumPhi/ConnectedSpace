package com.integrate.space.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	private SpriteBatch batch;
	private Texture img;
	private float x, y;
	
	public Entity(Texture i) {
		batch = new SpriteBatch();
		img = i;
	}
	
	public abstract void update(int delta);
	
	public void render() {
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
}
