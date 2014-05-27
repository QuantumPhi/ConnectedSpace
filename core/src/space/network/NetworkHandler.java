package space.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class NetworkHandler {
	
	public NetworkHandler() {
		Socket socket = Gdx.net.newClientSocket(Protocol.TCP, "127.0.0.1", 9999, new SocketHints());
	}
}
