package space.entity.galaxy;

import space.entity.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Star extends Entity {
    protected int depth;
    protected float posX;
    protected float posY;
    
    public Star(double px, double py) {
        super(new Texture("star.png"));
        
        sprite.setSize(sprite.getWidth() * 3 * Gdx.graphics.getWidth() / 1080f, sprite.getHeight() * 3 * Gdx.graphics.getHeight() / 1920f);
        
        init(px, py);
    }
    
    private void init(double px, double py) {
        depth = (int)(5 + Math.random() * 5);
        
        sprite.setX((float) px * Gdx.graphics.getWidth());
        sprite.setY((float) py * Gdx.graphics.getHeight()); 
    }

    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        sprite.translateY(-(depth / 15.0f) * delta);
        
        if(sprite.getX() < 0 || sprite.getX() > Gdx.graphics.getWidth() ||
                sprite.getY() < 0 || sprite.getY() > Gdx.graphics.getHeight())
            init(Math.random(), 1);
        
        posX = 0;
        posY = 0;
    }
    
    @Override
    public void render() {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}