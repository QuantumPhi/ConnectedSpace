package space.entity;

import space.Game;
import space.entity.Laser.LaserType;
import space.util.SpriteSheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Ship extends Entity {    
    private Animation anim;
    private float stateTime = 0f;

    Stats stat;

    protected boolean isHit = false;
    protected float flare;

    protected LaserType type;
    private int laserTimer;

    private double shipWidth;
    private double shipHeight;

    public static Ship SHIP_VARIANT_1 = new Ship(SpriteSheet.SHIP_VARIANT_1.getAnim(0.166f),Stats.VARIANT_1, LaserType.LASER_RED);
    public static Ship SHIP_VARIANT_2 = new Ship(SpriteSheet.SHIP_VARIANT_2.getAnim(0.166f),Stats.VARIANT_2, LaserType.LASER_BLUE);
    public static Ship SHIP_VARIANT_3 = new Ship(SpriteSheet.SHIP_VARIANT_3.getAnim(0.166f),Stats.VARIANT_3, LaserType.LASER_PURPLE);
    public static Ship SHIP_VARIANT_4 = new Ship(SpriteSheet.SHIP_VARIANT_4.getAnim(0.166f),Stats.VARIANT_4, LaserType.LASER_RED);
    public static Ship SHIP_VARIANT_5 = new Ship(SpriteSheet.SHIP_VARIANT_5.getAnim(0.166f),Stats.VARIANT_5, LaserType.LASER_GREEN);
    
    private enum Stats {
        VARIANT_1(1000, 10, 1),
        VARIANT_2(1000, 10, 1),
        VARIANT_3(1000, 10, 1),
        VARIANT_4(1000, 10, 1),
        VARIANT_5(1000, 10, 1);
        
        public int reloadTime;
        public int shields;
        public double speed;
        
        Stats(int rt, int sh, double s){
            reloadTime = rt;
            shields = sh;
            speed = s;  
        }
    }

    private Ship(Animation a, Stats s, LaserType t) {
        super(a.getKeyFrame(0f).getTexture());
        z = 1;
        
        laserTimer = 0;
            
        anim = a;
        stat = s;
        type = t;

        shipWidth = sprite.getWidth()*4/1080d;
        shipHeight = sprite.getHeight()*12/1920d;

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
        if (laserTimer > stat.reloadTime) {
            Game.addObject(new Laser(type, getCenterX()/Gdx.graphics.getWidth(), getCenterY()/Gdx.graphics.getHeight()));
            laserTimer = 0;
        }

        double dx = stat.speed * delta * Math.cos(Math.toRadians(Gdx.input.getRoll()-90))/1080.0;
        double dy = stat.speed * delta * Math.sin(Math.toRadians(Gdx.input.getPitch()))/1920.0;


        if(dx < 0)
            x = Math.max(x+dx, 0);
        else
            x = Math.min(x+dx, 1-shipWidth);
        
        if(dy < 0)
            y = Math.max(y+dy, 0);
        else
            y = Math.min(y+dy, 1-shipHeight);

        if(isHit)
            flare = Math.min(flare + 0.5f, 0.75f);
        else
            flare = Math.max(flare - 0.006f, 0);
    }

    public void resolveHit(Projectile p) {
        stat.shields = Math.max(stat.shields - p.damage, 0);
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
