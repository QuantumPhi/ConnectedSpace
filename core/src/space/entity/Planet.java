package space.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Planet extends BackgroundEntity{

	public Planet(){
		 super(new Texture("star.png"));
		 init();
		 sprite.setSize(sprite.getWidth()  * 8 * Gdx.graphics.getWidth()  / 1080f,
                 sprite.getHeight() * 8 * Gdx.graphics.getHeight() / 1920f);      
		 y = Math.random()*2;		
	}
	protected void init() {
		x = Math.random();
		y = .9;
		depth =  1;
		sprite.setX((float)getScreenX());
	}

	@Override
	public void update() {
		int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
		y -= .0005;
		if(y < 0)
			init();
	}
}
