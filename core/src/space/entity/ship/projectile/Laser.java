package space.entity.ship.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Laser extends Projectile {
    
    public Laser() {
        super(new Texture("laser.png"), 5);
    }

    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        sprite.translateY(20 * delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
