package space.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements Comparable<Entity> {
    public double x;
    public double y;
    public double z;
        
    protected Sprite sprite;
    
    public static enum EntityType {
        SHIP(0),
        PROJECTILE(1);
        
        public int type;
        
        EntityType(int t) {
            type = t;
        }
    }
    
    public Entity(Texture img) {
        sprite = img == null ? null : new Sprite(img);
    }
    
    public abstract void update();
    
    public abstract void render(SpriteBatch batch);
    
    public byte[] getBytes() {
        
    }
    
    public final double getScreenX() { return x*Gdx.graphics.getWidth(); }
    public final double getScreenY() { return y*Gdx.graphics.getHeight(); }
    public final double getCenterX() { return getScreenX()+sprite.getOriginX(); }
    public final double getCenterY() { return getScreenY()+sprite.getOriginY(); }
    
    @Override
    public int compareTo(Entity other) {
        return new Double(z).compareTo(other.z);
    }
}
