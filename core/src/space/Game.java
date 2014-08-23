package space;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import space.entity.Entity;
import space.entity.Ship;
import space.entity.Star;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
    public static class Info {
        public static int WINDOW_WIDTH = Gdx.graphics.getWidth();
        public static int WINDOW_HEIGHT = Gdx.graphics.getHeight();
    }
    
    private static Game game;
    
    protected long id;
    
    public SpriteBatch batch;
    public List<Entity> objects;
    
    @Override
    public void create() {
        game = this;
        batch = new SpriteBatch();
        objects = new ArrayList<Entity>();
        Entity player = Ship.SHIP_VARIANT_1;
        addObject(player);
        for(int i = 0; i < 20; i++)
            addObject(new Star());
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
