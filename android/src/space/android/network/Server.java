 package space.android.network;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import space.entity.Ship;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class Server {
    private BluetoothServerSocket server;
    private List<Ship> players;
    
    private boolean running = true;
    
    public void start() {
        final UUID rand = UUID.randomUUID();
        try {
            server = BluetoothAdapter.getDefaultAdapter().listenUsingInsecureRfcommWithServiceRecord("ConnectedSpace", rand);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        players = new CopyOnWriteArrayList<>();
        
        Thread handshakeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    BluetoothSocket clientSocket = null;
                    try {
                        clientSocket = server.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    OutputStream out = null;
                    try {
                        out = clientSocket.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    try {
                        out.write(rand.hashCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        handshakeThread.start();
        
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        });
        receiveThread.start();
    }
}
