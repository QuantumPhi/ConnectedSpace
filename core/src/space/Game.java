package space;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import space.entity.Entity;
import space.entity.Planet;
import space.entity.Ship;
import space.entity.Star;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
    private static Game game;
    
    public String id;
    
    public SpriteBatch batch;
    public List<Entity> objects;
    
    @Override
    public void create() {
        game = this;
        batch = new SpriteBatch();
        objects = Collections.synchronizedList(new ArrayList<Entity>());
        Entity player = Ship.SHIP_VARIANT_1;
        objects.add(player);
        for(int i = 0; i < 20; i++)
            objects.add(new Star());
        objects.add(new Planet());
    }
    
    public void update() {
        for(Entity e : objects)
            e.update();
    }

    @Override
    public void render() {
        update();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        synchronized(objects) {
            Collections.sort(objects);
        }
        
        batch.begin();
        
        for(Entity e : objects)
            e.render(batch);
        
        batch.end();
    }
    
    public static void addObject(Entity e) {
        game.objects.add(e);
    }
}
