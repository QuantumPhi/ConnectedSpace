package space.android.network;

import java.io.IOException;

import space.android.MainActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class ConnectThread extends Thread {
    private final BluetoothDevice device;
    private final BluetoothSocket socket;
    
    public ConnectThread(BluetoothDevice _device) {
        device = _device;
        BluetoothSocket _socket = null;
        
        try {
            _socket = device.createRfcommSocketToServiceRecord(MainActivity.APP_UUID);
        } catch(IOException e) {
            Log.e("ConnectedSpace", "Failed to create RFCOMM socket");
        }
        
        socket = _socket;
    }
    
    public void run() {
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        
        try {
            socket.connect();
        } catch(IOException e) {
            Log.e("ConnectedSpace", "Error while connecting to socket");
            try {
                socket.close();
            } catch(IOException ex) {
                Log.e("ConnectedSpace", "Error while closing socket");
            }
            cancel();
            return;
        }
        
        manageConnectedSocket(socket);
    }
    
    public void manageConnectedSocket(BluetoothSocket socket) {
        (new Thread(new TransferThread(socket))).start();
    }
    
    public void cancel() {
        synchronized(socket) {
            try {
                socket.close();
            } catch(IOException e) {
                Log.e("ConnectedSpace", "Error while closing socket");
            }
        }
    }
}
