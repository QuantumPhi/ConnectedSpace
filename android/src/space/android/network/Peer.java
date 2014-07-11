 package space.android.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import space.Game;
import space.entity.Entity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class Peer {
    private Game game;
    
    private BluetoothServerSocket server;
    private BluetoothSocket clientSocket;
    
    private volatile boolean running = true;
    
    public void start() {
        final UUID rand = UUID.randomUUID();
        try {
            server = BluetoothAdapter.getDefaultAdapter().listenUsingInsecureRfcommWithServiceRecord("ConnectedSpace", rand);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Thread handshakeThread = new Thread(new Runnable() {
            @Override
            public void run() {
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
                
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        handshakeThread.start();
        
        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream out = null;
                try {
                    out = clientSocket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                while(running) {                    
                    try {
                        for(Entity entity : game.objects)
                            out.write(entity.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        sendThread.start();
        
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream in = null;
                try {
                    in = clientSocket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                byte[] temp = null;
                while(running) {
                    try {
                        in.read(temp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        receiveThread.start();
    }
    
    public void stop() {
        running = false;
    }
}
