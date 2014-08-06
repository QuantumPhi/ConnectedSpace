package space.android.network;

import java.io.IOException;
import java.util.Stack;
import java.util.UUID;
import java.util.Vector;

import space.Game;
import space.entity.Entity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class Peer {
    public static final int REQUEST_ENABLE_BT = 1;
    
    protected Game game;
    
    protected Stack<Entity> queue = new Stack<>();
    
    protected BluetoothServerSocket server;
    protected BluetoothSocket socket;
        
    protected static boolean running = false;
    
    public Peer(Game _game) {
        game = _game;
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if(adapter == null) {
            Log.w("ConnectedSpace", "Onboard hardware does not support Bluetooth");
        }
    }
    
    public void handshake() {
        Thread handshakeThread = new Thread(() -> {
            BluetoothSocket temp = null;
            try {
                server = BluetoothAdapter.getDefaultAdapter().listenUsingRfcommWithServiceRecord("ConnectedSpace", new UUID(10, 10));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                socket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        handshakeThread.start();
    }
    
    public void start() {
        if(running)
            return;
        running = true;
        Thread sendThread = new Thread(() -> {
            while(running) {
                queue.addAll(game.queue);
                ((Vector<Entity>) game.queue).removeAllElements();
                
            }
        });
        sendThread.start();
        
        Thread recvThread = new Thread(() -> {
            while(running) {
                
            }
        });
        recvThread.start();
    }
    
    public void stop() {
        running = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addToQueue(Entity e) {
        synchronized(queue) {
            queue.push(e);
        }
    }
}
