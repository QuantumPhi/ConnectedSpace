package space.android;

import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    public static int REQUEST_ENABLE_BT = 1,
                      REQUEST_DISCOVERABLE_BT = 2;
    public static UUID APP_UUID = UUID.fromString("3787E831-57B7-4A33-B249-1ED821612181");
        
    protected Thread discoverThread;
    protected Thread sendThread;
    protected Thread recvThread;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ENABLE_BT) {
            if(resultCode == RESULT_OK) {
                //BT enabled
            }
        } else if(requestCode == REQUEST_DISCOVERABLE_BT) {
            if(resultCode == RESULT_OK) {
                //BT discoverable
            }
        }
    }
    
    protected void startBluetooth() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        
        if(adapter == null)
            Log.w("ConnectedSpace", "Device has no available Bluetooth adapter");
        
        if(!adapter.isEnabled()) {
            Intent btEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(btEnable, REQUEST_ENABLE_BT);
        }
    }
    
    protected void startDiscovery(int seconds) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        
        if(adapter == null)
            Log.w("ConnectedSpace", "Device has no available Bluetooth adapter");
        
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        if(pairedDevices.size() > 0)
            for(BluetoothDevice e : pairedDevices)
                Log.w("ConnectedSpace", e.getName());
        
        if(!adapter.isDiscovering()) {
            Intent btDiscover = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            btDiscover.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, seconds);
            startActivityForResult(btDiscover, REQUEST_DISCOVERABLE_BT);
        }
    }
    
    protected void searchAndConnect() {
        
    }
}
