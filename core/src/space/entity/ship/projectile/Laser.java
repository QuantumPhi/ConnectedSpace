package space.entity.ship.projectile;

import com.badlogic.gdx.graphics.Texture;

public class Laser extends Projectile {
    public Laser() {
        super(new Texture(""), 5);
    }

    @Override
    public void update(int delta) {
         sprite.translateY(20 * delta);       
    }

    @Override
    public void render() {
        sprite.draw(batch);
    }
}
