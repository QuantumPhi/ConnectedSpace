package space.entity;

import space.Game;
import space.util.DataPacket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements Comparable<Entity> {    
    public double x;
    public double y;
    public double z;
    
    protected Sprite sprite;
    
    public Entity(Texture img) {
        sprite = img == null ? null : new Sprite(img);
        sprite.setSize(sprite.getWidth()/1080f*Game.Info.WINDOW_WIDTH,
                sprite.getHeight()/1920f*Game.Info.WINDOW_HEIGHT);
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
    }
    
    public abstract void update();
    
    public abstract void render(SpriteBatch batch);
    
    public byte[] getBytes() {
        return DataPacket.getBytes(this);
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
