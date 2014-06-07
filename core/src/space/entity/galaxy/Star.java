package space.entity.galaxy;

import space.entity.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Star extends Entity {
    
    protected int depth;
    
    public Star() {
        super(new Texture("star.png"));
        
        sprite.setSize(sprite.getWidth()  * 2 * Gdx.graphics.getWidth()  / 1080f,
                       sprite.getHeight() * 2 * Gdx.graphics.getHeight() / 1920f);
        
        init();
    }
    
    private void init() {
        x = Math.random();
        y = 1;
        depth = (int)(1+Math.random()*5);
        sprite.setX((float)getScreenX());
    }

    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        
        y -= delta*depth/5000.0f;
        
        if(y < 0)
            init();
    }
    
    @Override
    public void render(SpriteBatch batch) {
        sprite.setY((float)(getScreenY()));
        sprite.draw(batch);
    }
}
