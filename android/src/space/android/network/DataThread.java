package space.android.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

public abstract class DataThread extends Thread {
    protected final BluetoothSocket socket;
    protected final InputStream in;
    protected final OutputStream out;
    
    public DataThread(BluetoothSocket _socket) {
        socket = _socket;
        
        InputStream _in = null;
        OutputStream _out = null;
        try {
            _in = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            _out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        in = _in;
        out = _out;
    }
    
    public abstract void run();
    
    public void write(byte[] bytes) {
        try {
            out.write(bytes);
        } catch(IOException e) {
            Log.e("ConnectedSpace", "Error while sending byte data");
        }
    }
    
    public int read(byte[] bytes) {
        try {
            return in.read(bytes);
        } catch(IOException e) {
            Log.e("ConnectedSpace", "Error while reading byte data");
        }
        
        return -1;
    }
    
    public void close() {       
        synchronized(socket) {
            try {
                socket.close();
            } catch(IOException e) {
                Log.e("ConnectedSpace", "Error while closing socket");
            }
        }
    }
}
