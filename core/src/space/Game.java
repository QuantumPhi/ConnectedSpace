package space;

import java.util.List;

import space.entity.ship.Ship;
import space.entity.ship.projectile.Projectile;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game extends ApplicationAdapter {
	public Ship player;
	public List<Projectile> projectiles;
	
	@Override
	public void create () {
		player = new Ship(new SpriteSheet());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
