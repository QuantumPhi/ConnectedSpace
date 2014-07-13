package space.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends Entity {
	public Planet(){
		 super(new Texture("star.png"));
		 z = -1;
		 
		 sprite.setSize(sprite.getWidth()  * 8 * Gdx.graphics.getWidth()  / 1080f,
                 sprite.getHeight() * 8 * Gdx.graphics.getHeight() / 1920f);
		 
		 y = Math.random()*2;		
	}
	protected void init() {
		x = Math.random();
		y = 1;
		sprite.setX((float)getScreenX());
	}

	@Override
	public void update() {
		int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
		y -= delta * -z / 16000.0;
		if(y < 0 - sprite.getHeight() / Gdx.graphics.getHeight())
			init();
	}
	
	@Override	
	public void render(SpriteBatch batch) {
	    sprite.setY((float)getScreenY());
	    sprite.draw(batch);
	}
}
