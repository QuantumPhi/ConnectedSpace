package space.android.network;

import java.nio.ByteBuffer;

import space.entity.Entity;
import space.entity.Laser;
import space.entity.Projectile;

public final class DataPacket {
    
    public static final int MAX_SIZE = 24;
    
    private byte[] data;
    
    public static final int ID = 0;
    public static final int X = 8;
    public static final int Y = 16;
        
    public DataPacket(Entity entity) {
        data = new byte[MAX_SIZE];
        addDouble(entity.id,ID);
        addDouble(entity.x,X);
        addDouble(entity.y,Y);
    }
    
    public DataPacket(byte[] data) {
        this.data = data;
    }
    
    public void addDouble(double d, int pos) {
        addDouble(data,d,pos);
    }
    
    public void addInt(int i, int pos) {
        data[pos] = (byte) (i >> 24);
        data[pos+1] = (byte) (i >> 16);
        data[pos+2] = (byte) (i >> 8);
        data[pos+3] = (byte) (i);
    }
    
    public double getDouble(int pos) {
        return ByteBuffer.wrap(data,pos,8).getDouble();
    }
    
    public int getInt(int pos) {
        int n = 0;
        for (int i=0;i<4;i++) {
            n <<= 8;
            n |= (int)data[i+pos] & 0xFF;
        }
        return n;
    }
    
    public static void addDouble(byte[] arr, double d, int pos) {
        ByteBuffer.wrap(arr,pos,8).putDouble(d);
    }
    
    public byte[] getBytes() {
        return data;
    }
    
    public double getClient() {
        return getDouble(ID);
    }
    
    public void update(EnemyShip s) {
        s.setX(getDouble(X));
        s.setY(getDouble(Y));
        s.setAngle(getDouble(DIR));
        s.setSpeed(getDouble(SPEED));
    }
    
    public void update(Projectile p) {
        p.setX(getDouble(X));
        p.setY(getDouble(Y));
        p.setAngle(getDouble(DIR));
        p.setSpeed(getDouble(SPEED));
    }
}
