package space.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BackgroundEntity extends Entity {
	protected int depth;
	public BackgroundEntity(Texture t) {
		super(t);
	}

	protected abstract void init();
	public abstract void update();

	@Override
	public void render(SpriteBatch batch) {
		sprite.setY((float)(getScreenY()));
		sprite.draw(batch);
	}
}
