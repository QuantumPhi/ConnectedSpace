package space.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Bitmap bmpSource = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        //100 is dimension to resizing image size
        //Passing filter = false will result in a blocky, pixellated image.
        //Passing filter = true will give you smoother edges
        Bitmap bmpScaled = Bitmap.createScaledBitmap(bmpSource, 320, 54, false); 
       
        ImageView img = (ImageView) findViewById(R.id.logo_view);
        img.setImageBitmap(bmpScaled);
    }
}
