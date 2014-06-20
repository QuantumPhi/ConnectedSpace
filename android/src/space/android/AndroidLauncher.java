package space.android;

import space.Game;
import android.app.Application;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        Game game = new Game();
        AppInit.assignID(this, game);
        initialize(game,config);
    }
    
    static class AppInit {
        public static void assignID(AndroidApplication a, Game g) {
            g.id = "" + android.provider.Settings.Secure.getString(a.getContext().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        }
        
        private static double getDevicePriority() {
            long sTime = System.nanoTime();
            int j;
            for(int i = 0; i < 1000000; i++)
                j = i;
            long eTime = System.nanoTime();
            
            return (eTime - sTime) / 1000000.0;
        }
    }
}
