package space.entity.ship.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Laser extends Projectile {
    private Laser() {
        super(new Texture("laser.png"), 5);
    }

    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        sprite.translateY(20 * delta);       
    }

    @Override
    public void render() {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
    
    public static Projectile init() {
        return new Laser();
    }
}
