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

    protected int shields;
    protected double regen, speed;

    protected boolean isHit = false;
    protected float flare;

    private int laserTimer;
    private final int RELOAD_TIME = 1000;

    private double shipWidth;
    private double shipHeight;

    public List<Projectile> projectiles;

    public static Ship SHIP_VARIANT_1 = new Ship(SpriteSheet.SHIP_VARIANT_1.getAnim(0.166f),100,0.5,1);
    public static Ship SHIP_VARIANT_2 = new Ship(SpriteSheet.SHIP_VARIANT_2.getAnim(0.166f),100,0.5,1);

    private Ship(Animation a, int sh, double r, double s) {
        super(a.getKeyFrame(0f).getTexture());
        laserTimer = 0;
        anim = a;
        shields = sh;
        regen = r;
        speed = s;

        shipWidth = sprite.getWidth()*4/1080d;
        shipHeight = sprite.getHeight()*12/1920d;

        projectiles = new ArrayList<Projectile>();

        sprite.setSize((float)shipWidth*Gdx.graphics.getWidth(),(float)shipHeight*Gdx.graphics.getHeight());
        sprite.setOrigin(sprite.getWidth()/2-1, sprite.getHeight()/2);
    }

    @Override
    public void update() {
        int delta = (int)(Gdx.graphics.getDeltaTime() * 1000);
        stateTime += delta;
        sprite.setRegion(anim.getKeyFrame(stateTime,true));

        laserTimer += delta;
        if (laserTimer > RELOAD_TIME) {
            projectiles.add(new Laser(getCenterX()/Gdx.graphics.getWidth(), getCenterY()/Gdx.graphics.getHeight()));
            laserTimer = 0;
        }

        double dx = 0;
        double dy = 0;

        dx = speed * delta * Math.cos(Math.toRadians(Gdx.input.getRoll()-90))/1080.0;
        dy = speed * delta * Math.sin(Math.toRadians(Gdx.input.getPitch()))/1920.0;

        if(x + dx >= 0 && x + shipWidth + dx <= 1)
            x += dx;
        if(y + dy >= 0 && y + shipHeight + dy <= 1)
            y += dy;

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
        if(isHit)
            renderShieldFlare();
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
