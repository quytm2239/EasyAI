package connectivity;

import java.io.IOException;
import java.net.URI;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class WSConnection implements WSHandlerInterface {
	private static WSConnection instance = null;
	private WSConnection() {
		
	}
	public static WSConnection getInstance() {
		if (instance == null) {
			instance = new WSConnection();
		}
		return instance;
	}
	
	private boolean isManualDisconnect = false;
	private final String destUri = "ws://localhost:8080";
	private final WebSocketClient client = new WebSocketClient();
	private WSHandler socket = new WSHandler();
	socket.handlerInterface = this;
	
	public void connect() {
        try {
            client.start();
            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket,echoUri,request);
            isManualDisconnect = false;
        } catch (IOException e) {
            e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			this.client.stop();
			this.isManualDisconnect = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reconnect() {
		try {
			this.disconnect();
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void sendString(String data) {
		this.socket.sendString(data);
	}
	
	public void sendBytes(byte[] data) {
		this.socket.sendBytes(data);
	}
	
	@Override
	public void onSocketConnect(Session session) {
		// TODO Auto-generated method stub
        System.out.printf("Connected to: %s%n",session);
	}
	@Override
	public void onSocketClose(int statusCode, String reason) {
		// TODO Auto-generated method stub
		System.out.printf("Connection closed: %d - %s%n",statusCode,reason);
		if (!isManualDisconnect) this.reconnect();
	}
	@Override
	public void onSocketMessage(String msg) {
		// TODO Auto-generated method stub
		System.out.printf("Got msg: %s%n",msg);
	}
	@Override
	public void onSocketError(Session session, Throwable error) {
		// TODO Auto-generated method stub
		System.out.printf("Socket has error");
		error.printStackTrace();
		if (!isManualDisconnect) this.reconnect();
	}
}
