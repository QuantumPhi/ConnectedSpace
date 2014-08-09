package space.android.network;

import space.entity.Entity.DataPacket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class TransferThread extends DataThread {   
    public TransferThread(BluetoothSocket socket) {
        super(socket);
    }
    
    public void run() {        
        byte[] buffer = new byte[1024];
        int bytes = 0;
        
        while(true) {
            bytes = read(buffer);
            
            if(bytes != DataPacket.MAX_SIZE)
                Log.e("ConnectedSpace", "Corrupt or falsified data received");
        }
    }
}
