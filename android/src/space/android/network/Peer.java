package space.android.network;

import java.util.Stack;

import space.Game;
import space.entity.Entity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class Peer {
    public static final int REQUEST_ENABLE_BT = 1;
    
    protected Stack<Entity> queue = new Stack<>();
    
    protected BluetoothServerSocket server;
    protected BluetoothSocket socket;
        
    protected static boolean running = false;
    
    public Peer(Game game) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if(adapter == null) {
            Log.w("ConnectedSpace", "Onboard hardware does not support Bluetooth");
        }
    }
    
    public void handshake() {
        Thread handshakeThread = new Thread(new Runnable() {
           @Override
           public void run() {
               final BroadcastReceiver mReceiver = new BroadcastReceiver() {
                   public void onReceive(Context context, Intent intent) {
                       String action = intent.getAction();
                       if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                           BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                           mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                       }
                   }
               };
               IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
               registerReceiver(mReceiver, filter);
            }
        });
    }
    
    public void start() {
        if(running)
            return;
        running = true;
        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    queue.addAll(Game.);
                }
            }
        });
        sendThread.start();
        
        Thread recvThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    
                }
            }
        });
        recvThread.start();
    }
    
    public void stop() {
        running = false;
    }
    
    public void addToQueue(Entity e) {
        synchronized(queue) {
            queue.push(e);
        }
    }
}
