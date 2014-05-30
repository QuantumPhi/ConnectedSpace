package space.entity.ship;

import space.entity.Entity;
import space.entity.ship.projectile.Projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Ship extends Entity {
    private Animation anim;
    private float stateTime = 0f;
    
	protected int shields;
	protected double regen;
	protected double speed;
	
	protected boolean isHit = false;
	protected float flare;
	
	protected Projectile projectile;
	
	public Ship(Animation a, int sh, double r, double s, Projectile p) {
		super(a.getKeyFrame(0f).getTexture());
		anim = a;
		shields = sh;
		regen = r;
		speed = s;
		projectile = p;
	}
	
	public void update() {
	    int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
	    stateTime += delta;
	    sprite = new Sprite(anim.getKeyFrame(stateTime, true).getTexture());
	    //double dx = speed * delta * Gdx.input.getPitch();
	    //double dy = speed * delta * Gdx.input.getRoll();
	    //sprite.translateX((float) dx);
	    //sprite.translateY((float) dy);
	    if(isHit)
	        flare = Math.min(flare + 0.5f, 0.75f);
	    else
	        flare = Math.max(flare - 0.006f, 0);
	}
	
	public void resolveHit(Projectile p) {
	    shields = Math.max(shields - p.getDamage(), 0);
	    isHit = true;
	}
	
	public void render(SpriteBatch batch) {
	    if(isHit)
	        renderShieldFlare();
	    sprite.draw(batch);
	    isHit = false;
	}
	
	protected void renderShieldFlare() {
	    ShapeRenderer shape = new ShapeRenderer();
	    shape.begin(ShapeType.Filled);
	    shape.setColor(1, 1, 1, flare);
	    shape.circle(sprite.getOriginX(), sprite.getOriginY(), sprite.getHeight() / 2);
	    shape.end();
	}
}
