package space;

import java.util.ArrayList;
import java.util.List;

import space.entity.ship.Ship;
import space.entity.ship.projectile.Laser;
import space.entity.ship.projectile.Projectile;
import space.util.SpriteSheet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class Game extends ApplicationAdapter {
	public Ship player;
	public List<Projectile> projectiles;
	public Texture t;
	
	@Override
	public void create () {
	    SpriteSheet s = new SpriteSheet("ship_spritesheet.png", 32, 32);
		player = new Ship(s.getAnim(0.166f), 100, 0.5, 0.5, new Laser());
		projectiles = new ArrayList<>();
	}
	
	public void update() {
	    player.update();
	    for(Projectile p : projectiles)
	        p.update();
	}

	@Override
	public void render () {
	    update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		player.render();
		for(Projectile p : projectiles)
		    p.render();
	}
}
