package space.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Laser extends Projectile {
    public enum LaserType {
        LASER_RED("laser_red.png", 7.5f, 5, 1/1.75f, 1.1f),
        LASER_GREEN("laser_green.png", 7.5f, 5, 1/1.75f, 1.1f),
        LASER_BLUE("laser_blue.png", 7.5f, 5, 1/1.75f, 1.1f);
        
        public String img;
        public float speed;
        public int damage;
        public float scaleX, scaleY;
        
        LaserType(String i, float s, int d, float x, float y) {
            img = i;
            speed = s;
            damage = d;
            scaleX = x;
            scaleY = y;
        }
    }
    
    public Laser(LaserType type, double x, double y) {
        super(new Texture(type.img),x,y,type.speed, type.damage);
        sprite.setSize(sprite.getWidth()*type.scaleX, sprite.getHeight()*type.scaleY);
        sprite.setX((float)getScreenX() - sprite.getWidth() / 2);
    }

    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        y += delta*speed/8000.0;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setY((float)getScreenY());
        sprite.draw(batch);
    }
}
