package space;

import space.entity.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Star extends Entity {
    protected int depth;
    protected float posX;
    protected float posY;
    
    public Star(double px, double py) {
        super(new Texture("star.png"));
        depth = (int)(5 + Math.random() * 5);
        
        sprite.setSize(sprite.getWidth() * 3, sprite.getHeight() * 3);
        
        sprite.setX((float) px * Gdx.graphics.getWidth());
        sprite.setY((float) py * Gdx.graphics.getHeight()); 
    }

    @Override
    public void update() {     }
    
    public void renderPos(float cx, float cy) {
        posX = sprite.getX() + Gdx.graphics.getWidth() * depth / 15.0f - Gdx.graphics.getWidth() * depth / 30.0f;
        posY = sprite.getY() + Gdx.graphics.getHeight() * depth / 15.0f - Gdx.graphics.getHeight() * depth / 30.0f;
        
        render();
    }
    
    @Override
    public void render() {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}
