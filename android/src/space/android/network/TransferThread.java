package space.android.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class TransferThread extends Thread {
    protected boolean running = false;
    
    private final BluetoothSocket socket;
    private final InputStream in;
    private final OutputStream out;
    
    public TransferThread(BluetoothSocket _socket) {
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
    
    public void run() {
        running = true;
        
        byte[] buffer = new byte[1024];
        int bytes = 0;
        
        while(running) {
            try {
                bytes = in.read(buffer);
            } catch (IOException e) {
                Log.e("ConnectedSpace", "Error while reading from input stream");
            }
            if(bytes != DataPacket.MAX_SIZE)
                Log.e("ConnectedSpace", "Corrupt or falsified data received");
        }
    }
    
    public void write(byte[] bytes) {
        try {
            out.write(bytes);
        } catch(IOException e) {
            Log.e("ConnectedSpace", "Error while sending byte data");
        }
    }
    
    public void close() {
        running = false;
        
        synchronized(socket) {
            try {
                socket.close();
            } catch(IOException e) {
                Log.e("ConnectedSpace", "Error while closing socket");
            }
        }
    }
}
