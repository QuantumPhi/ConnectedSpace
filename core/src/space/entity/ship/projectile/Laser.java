package space.entity.ship.projectile;

import com.badlogic.gdx.graphics.Texture;

public class Laser extends Projectile {
    public Laser() {
        super(new Texture(""), 5);
    }

    @Override
    public void update(int delta) {
         y += 20 * delta;       
    }
}
