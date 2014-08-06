package space.android.network;

import space.entity.Entity;

public class DataPacket {
    public static final int MAX_SIZE = 32;
    public static final int X = 0; //Double value
    public static final int Y = 8; //Double value
    public static final int Z = 16; //Double value
    public static final int ID = 24; //Long value
    public final byte[] data = new byte[MAX_SIZE];
    
    public DataPacket(Entity e) {
        DataPacket.init(e);
    }
}
