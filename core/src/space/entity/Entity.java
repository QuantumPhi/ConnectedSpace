package space.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
    
    public double x;
    public double y;
        
    protected Sprite sprite;
    
    public Entity(Texture img) {
        sprite = img == null ? null : new Sprite(img);
    }
    
    public abstract void update();
    
    public abstract void render(SpriteBatch batch);
    
    public double getScreenX() { return x*Gdx.graphics.getWidth(); }
    public double getScreenY() { return y*Gdx.graphics.getHeight(); }
}
