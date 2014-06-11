package space.entity;

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
        y = Math.random()*2;
    }
    
    private void init() {
        x = Math.random();
        y = 1;
        depth = (int)(4+Math.random()*5);
        sprite.setX((float)getScreenX());
    }

    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        
        y -= delta*depth/16000.0;
        
        if(y < 0)
            init();
    }
    
    @Override
    public void render(SpriteBatch batch) {
        sprite.setY((float)(getScreenY()));
        sprite.draw(batch);
    }
}
