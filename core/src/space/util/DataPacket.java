package space.util;

import java.nio.ByteBuffer;

import space.entity.Entity;
import space.entity.Projectile;
import space.entity.Ship;

public final class DataPacket {
    
    public static final int MAX_SIZE = 16;
    
    private byte[] data;
    
    public static final int X = 0;
    public static final int Y = 8;
        
    public DataPacket(Entity e) {
        data = new byte[MAX_SIZE];
        addDouble(e.x,X);
        addDouble(e.y,Y);
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
    
    public void update(Ship s) {
        s.x = getDouble(X);
        s.y = getDouble(Y);
    }
    
    public void update(Projectile p) {
        p.x = getDouble(X);
        p.y = getDouble(Y);
    }
}