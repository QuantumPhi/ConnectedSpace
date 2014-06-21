package space.android.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationView extends View {
    private Drawable draw;
    private Animation anim;
    
    public AnimationView(Context context, Animation a) {
        super(context);
        anim = a;
    }

    public AnimationView(Context context, AttributeSet attrs, Animation a) {
        super(context, attrs);
        anim = a;
    }

    public AnimationView(Context context, AttributeSet attrs, int defStyleAttr, Animation a) {
        super(context, attrs, defStyleAttr);
        anim = a;
    }
    
    @Override
    protected void onDraw(Canvas c) {
        
    }
}
