package space.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	protected SpriteBatch batch;
	protected Sprite sprite;
	
	public Entity(Texture img) {
		batch = new SpriteBatch();
		sprite = new Sprite(img);
	}
	
	public abstract void update(int delta);
	
	public abstract void render();
}
