package space;

import java.util.ArrayList;
import java.util.List;

import space.entity.Projectile;
import space.entity.Ship;
import space.entity.Star;
import space.util.SpriteSheet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
    
    public Ship player;
    public List<Projectile> projectiles;
    public List<Star> stars;
    public SpriteBatch batch;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        projectiles = new ArrayList<>();
        player = new Ship(new SpriteSheet("ship_spritesheet2.png",32,32).getAnim(0.166f),100,0.5,0.75,projectiles);
        stars = new ArrayList<>();
        
        for(int i = 0; i < 20; i++)
            stars.add(new Star());
    }
    
    public void update() {
        player.update();
        for(int i=0;i<projectiles.size();i++) {
            if (projectiles.get(i).y < 1) {
                projectiles.get(i).update();
            } else {
                projectiles.remove(i);
                i--;
            }
        }
        for(Star s : stars)
            s.update();
    }

    @Override
    public void render() {
        update();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        for(Star s : stars)
            s.render(batch);
        for(Projectile p : projectiles)
            p.render(batch);
        player.render(batch);
        
        batch.end();
    }
}
