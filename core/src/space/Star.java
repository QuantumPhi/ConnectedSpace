package space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import space.entity.Entity;

public class Star extends Entity {
    protected int depth;
    protected float posX;
    protected float posY;
    
    public Star() {
        super(new Texture(""));
        depth = (int)(5 + Math.random() * 5);
        
    }

    @Override
    public void update() {     }
    
    public void renderPos(float cx, float cy) {
        posX = cx * Gdx.graphics.getWidth() * depth / 15.0f;
        posY = cy * Gdx.graphics.getHeight() * depth / 15.0f;
    }
    
    @Override
    public void render() {
        
    }
}
