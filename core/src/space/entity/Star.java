package space.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Star extends BackgroundEntity {
    public Star() {
        super(new Texture("star.png"));
        init();       
        sprite.setSize(sprite.getWidth()  * 2 * Gdx.graphics.getWidth()  / 1080f,
                       sprite.getHeight() * 2 * Gdx.graphics.getHeight() / 1920f);      
        y = Math.random()*2;
    }
    protected void init() {
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
    
}