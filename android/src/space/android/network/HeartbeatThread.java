package space.android.network;

import java.io.IOException;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class HeartbeatThread extends DataThread {    
    public HeartbeatThread(BluetoothSocket socket) {
        super(socket);
    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e("ConnectedSpace", "Error at thread sleep");
            }
            
            write("HI".getBytes());
        }
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