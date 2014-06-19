package space.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {    
    private Texture texture;
    private TextureRegion[] region;
    
    public static SpriteSheet SHIP_VARIANT_1 = new SpriteSheet("ship_spritesheet1.png",23,32);
    public static SpriteSheet SHIP_VARIANT_2 = new SpriteSheet("ship_spritesheet2.png",26,28);
    
    public SpriteSheet(String path, int width, int height) {
        texture = new Texture(path);
        TextureRegion[][] regions = TextureRegion.split(texture, width, height);
        region = new TextureRegion[regions.length * regions[0].length];
        
        for(int i = 0; i < regions.length; i++)
            System.arraycopy(regions[i], 0, region, i * regions[i].length, regions[i].length);
    }
    
    public Animation getAnim(float frameSpeed) {
        return new Animation(frameSpeed, region);
    }
}
