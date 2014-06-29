package space;

import java.util.HashMap;

import space.entity.Entity;
import space.entity.Ship;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
    public String id;
    
    public SpriteBatch batch;
    public HashMap<Integer, Entity> objects = new HashMap<>();
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        Entity player = Ship.SHIP_VARIANT_1;
    }
    
    public void update() {
        for(Entity e : objects.values())
            e.update();
    }

    @Override
    public void render() {
        update();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        batch.end();
    }
}
