package space.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
    //private long id;
    
    protected SpriteBatch batch;
    
	protected Sprite sprite;
	protected Animation anim;
	
	public Entity(Texture img) {
	    batch = new SpriteBatch();
		sprite = new Sprite(img);
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public float getX() { return sprite.getX(); }
	public float getY() { return sprite.getY(); }
	
	public void setX(float x) { sprite.setX(x); }
	public void setY(float y) { sprite.setY(y); }
}
