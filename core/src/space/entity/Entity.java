package space.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	protected SpriteBatch batch;
	protected Texture img;
	protected double x, y;
	
	public Entity(Texture i) {
		batch = new SpriteBatch();
		img = i;
	}
	
	public abstract void update(int delta);
	
	public void render() {
		batch.begin();
		batch.draw(img, (float)x, (float)y);
		batch.end();
	}
}
