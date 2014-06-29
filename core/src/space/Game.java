package space;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import space.entity.Entity;
import space.entity.Ship;
import space.entity.Star;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
    private static Game instance;
    
    public String id;
    
    public SpriteBatch batch;
    public List<Entity> objects = new CopyOnWriteArrayList<>();
    
    @Override
    public void create() {
        instance = this;
        batch = new SpriteBatch();
        Entity player = Ship.SHIP_VARIANT_1;
        objects.add(player);
        for(int i = 0; i < 20; i++)
            objects.add(new Star());
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

        Collections.sort(objects);
        
        batch.begin();
        
        for(Entity e : objects)
            e.render(batch);
        
        batch.end();
    }
    
    public static void addObject(Entity e) {
        instance.objects.add(e);
    }
}
