package space;

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
    public void update() {
        sprite.translateX()
    }

    @Override
    public void render() {
        
    }
}
