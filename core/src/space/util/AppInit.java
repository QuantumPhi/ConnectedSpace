package space.util;

import space.Game;

public class AppInit {
    public static void assignID(Game g) {
        g.id = Double.doubleToLongBits(getDevicePriority());
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
