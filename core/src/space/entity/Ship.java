package space.entity;

import java.util.ArrayList;
import java.util.List;

import space.util.SpriteSheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Ship extends Entity {
    private Animation anim;
    private float stateTime = 0f;

    protected int shields, reloadTime;
    protected double regen, speed;

    protected boolean isHit = false;
    protected float flare;

    private int laserTimer;

    private double shipWidth;
    private double shipHeight;

    public List<Projectile> projectiles;

    public static Ship SHIP_VARIANT_1 = new Ship(SpriteSheet.SHIP_VARIANT_1.getAnim(0.166f),Stats.VARIANT_1);
    public static Ship SHIP_VARIANT_2 = new Ship(SpriteSheet.SHIP_VARIANT_2.getAnim(0.166f),Stats.VARIANT_2);
    public static Ship SHIP_VARIANT_4 = new Ship(SpriteSheet.SHIP_VARIANT_4.getAnim(0.166f),Stats.VARIANT_4);
    
    private enum Stats {
        VARIANT_1(1000, 10, 0.5, 1),
        VARIANT_2(1000, 10, 0.5, 1),
        VARIANT_4(1000, 10, 0.5, 1);
        
        public int reloadTime;
        public int shields;
        public double regen;
        public double speed;
        
        Stats(int rt, int sh, double r, double s){
            reloadTime = rt;
            shields = sh;
            regen = r;
            speed = s;  
        }
    }

    private Ship(Animation a, Stats s) {
        super(a.getKeyFrame(0f).getTexture());
        laserTimer = 0;
        anim = a;
        shields = s.shields;
        reloadTime = s.reloadTime;
        regen = s.regen;
        speed = s.speed;

        shipWidth = sprite.getWidth()*4/1080d;
        shipHeight = sprite.getHeight()*12/1920d;

        projectiles = new ArrayList<>();

        sprite.setSize((float)shipWidth*Gdx.graphics.getWidth(),(float)shipHeight*Gdx.graphics.getHeight());
        sprite.setOrigin(sprite.getWidth()/2-1, sprite.getHeight()/2);
    }

    @Override
    public void update() {
    	int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        stateTime += delta;
        stateTime %= anim.animationDuration;
        sprite.setRegion(anim.getKeyFrame(stateTime,true));

        laserTimer += delta;
        if (laserTimer > reloadTime) {
            projectiles.add(new Laser(getCenterX()/Gdx.graphics.getWidth(), getCenterY()/Gdx.graphics.getHeight()));
            laserTimer = 0;
        }

        double dx = speed * delta * Math.cos(Math.toRadians(Gdx.input.getRoll()-90))/1080.0;
        double dy = speed * delta * Math.sin(Math.toRadians(Gdx.input.getPitch()))/1920.0;


        if(x + dx >= 0 && x + shipWidth + dx <= 1)
            x += dx;
        else if (x + shipWidth + dx > 1)
        	x =  1 - shipWidth;
        else if (x + dx < 0)
        	x = 0;
        
        if(y + dy >= 0 && y + shipHeight + dy <= 1)
            y += dy;
        else if (y + shipHeight + dy > 1)
        	y = 1 - shipHeight;
        else if (y + dy < 0)
        	y = 0;

        if(isHit)
            flare = Math.min(flare + 0.5f, 0.75f);
        else
            flare = Math.max(flare - 0.006f, 0);
    }

    public void resolveHit(Projectile p) {
        shields = Math.max(shields - p.damage, 0);
        isHit = true;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setX((float)getScreenX());
        sprite.setY((float)getScreenY());
        //renderShieldFlare();
        sprite.draw(batch);
        isHit = false;
    }

    protected void renderShieldFlare() {
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeType.Filled);
        shape.setColor(1, 1, 1, flare);
        shape.circle(sprite.getX() - sprite.getOriginX(), sprite.getY() - sprite.getOriginY(), 
                (sprite.getHeight() > sprite.getWidth() ? sprite.getHeight() : sprite.getWidth()) / 2);
        shape.end();
    }
}
