package space.entity;

import space.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Ship extends Entity {
    
    private Animation anim;
    private float stateTime = 0f;
    
    protected int shields;
    protected double regen, speed;
    
    protected boolean isHit = false;
    protected float flare;
    
    private int laserTimer = 0;
    private int RELOAD_TIME = 1000;
    
    public Ship(Animation a, int sh, double r, double s) {
        super(a.getKeyFrame(0f).getTexture());
        anim = a;
        shields = sh;
        regen = r;
        speed = s;
        
        sprite.setSize(sprite.getWidth() * 4 * Gdx.graphics.getWidth() / 1080f, sprite.getHeight() * 12 * Gdx.graphics.getHeight() / 1920f);
        sprite.setOrigin(sprite.getWidth() / 2-1, sprite.getHeight() / 2);
    }
    
    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        stateTime += delta;
        sprite.setRegion(anim.getKeyFrame(stateTime, true));
        
        laserTimer+=delta;
        if (laserTimer>RELOAD_TIME) {
            Game.projectiles.add(new Laser());
        }
        
        double dx = 0;
        double dy = 0;
        
        dx = speed * delta * Math.cos(Math.toRadians(Gdx.input.getRoll() - 90));
        dy = speed * delta * Math.sin(Math.toRadians(Gdx.input.getPitch()));
        
        if(sprite.getX() + sprite.getOriginX() + dx >= 0 && sprite.getX() + sprite.getOriginX() + dx <= Gdx.graphics.getWidth())
            sprite.translateX((float) dx);
        if(sprite.getY() + sprite.getOriginY() + dy >= 0 && sprite.getY() + sprite.getOriginY() + dy <= Gdx.graphics.getHeight())
            sprite.translateY((float) dy);
        
        x += dx;
        y += dy;
        
        if(isHit)
            flare = Math.min(flare + 0.5f, 0.75f);
        else
            flare = Math.max(flare - 0.006f, 0);
    }
    
    public void resolveHit(Projectile p) {
        shields = Math.max(shields - p.getDamage(), 0);
        isHit = true;
    }
    
    @Override
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
        shape.circle(sprite.getX(), sprite.getY(), sprite.getHeight() / 2);
        shape.end();
    }
}