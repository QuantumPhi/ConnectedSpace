package space;

import java.util.ArrayList;
import java.util.List;

import space.entity.galaxy.Star;
import space.entity.ship.Ship;
import space.entity.ship.projectile.Projectile;
import space.util.SpriteSheet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game extends ApplicationAdapter {  
    public Ship player;
    public List<Projectile> projectiles;
    public List<Star> stars;
    
    @Override
    public void create() {
        player = new Ship(new SpriteSheet("ship_spritesheet.png", 32, 32).getAnim(0.166f), 100, 0.5, 1.25);
        projectiles = new ArrayList<>();
        stars = new ArrayList<>();
        
        for(int i = 0; i < 200; i++)
            stars.add(new Star(Math.random(), Math.random()));
    }
    
    public void update() {
        player.update();
        for(Projectile p : projectiles)
            p.update();
        for(Star s : stars)
            s.update();
    }

    @Override
    public void render() {
        update();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        for(Star s : stars)
            s.render();
        for(Projectile p : projectiles)
            p.render();
        player.render();
        
    }
}
