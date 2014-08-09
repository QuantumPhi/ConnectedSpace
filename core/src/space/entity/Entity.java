package space.entity;

import java.nio.ByteBuffer;

import space.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements Comparable<Entity> {
    public class DataPacket {
        public static final int MAX_SIZE = 24;
        public static final int X = 0; //Double value
        public static final int Y = 8; //Double value
        public static final int Z = 16; //Double value
        public final byte[] bytes = new byte[MAX_SIZE];
        
        private DataPacket(Entity e) {
            addDouble(X, e.x);
            addDouble(Y, e.y);
            addDouble(Z, e.z);
        }
        
        public DataPacket getPacket(Entity e) {
            return new DataPacket(e);
        }
        
        public void addDouble(int pos, double value) {
            ByteBuffer.wrap(bytes, pos, 8).putDouble(value);
        }
        
        public double getDouble(int pos) {
            return ByteBuffer.wrap(bytes, pos, 8).getDouble();
        }
        
        public byte[] getBytes() {
            return bytes;
        }
    }
    
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
    
    public final double getScreenX() { return x*Gdx.graphics.getWidth(); }
    public final double getScreenY() { return y*Gdx.graphics.getHeight(); }
    public final double getCenterX() { return getScreenX()+sprite.getOriginX(); }
    public final double getCenterY() { return getScreenY()+sprite.getOriginY(); }
    
    @Override
    public int compareTo(Entity other) {
        return new Double(z).compareTo(other.z);
    }
}
