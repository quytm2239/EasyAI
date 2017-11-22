package connectivity.websocket;

import java.io.IOException;
import java.net.URI;
import java.util.Date;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import connectivity.*;
import connectivity.inteface.PingSenderInterface;
import connectivity.inteface.WSHandlerInterface;
import connectivity.ping.PingSender;

public class WSConnection implements WSHandlerInterface,PingSenderInterface {
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
	private WSConnectionStatus status = WSConnectionStatus.DISCONNECTED;
	private final WebSocketClient client = new WebSocketClient();
	private final WSSessionEvent socket = new WSSessionEvent(this);
	private final ClientUpgradeRequest request = new ClientUpgradeRequest();
	private final PingSender pingSender = new PingSender(this);	
	
	public WSConnectionStatus getStatus() {
		return status;
	}
	public void setStatus(WSConnectionStatus status) {
		this.status = status;
	}
	
	public void connect() {
		if (this.getStatus() != WSConnectionStatus.DISCONNECTED) return;
		this.setStatus(WSConnectionStatus.CONNECTING);
        try {
            client.start();
            URI echoUri = new URI(WSConfiguration.WEBSOCKET_HOST);
            client.connect(socket,echoUri,request);
            isManualDisconnect = false;
            socket.waitForConnect();
            
        } catch (IOException e) {
            e.printStackTrace();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		if (this.getStatus() == WSConnectionStatus.DISCONNECTED) return;
		try {
			this.socket.disconnect();
			this.isManualDisconnect = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void reconnect() {
		if (this.getStatus() == WSConnectionStatus.CONNECTED) return;
		System.out.println("Reconnect");
		this.connect();
	}
	 
	// PING SERVER TO CHECK CONNECT
	@Override
	public void onPing() {
		System.out.println(new Date() + ": PING!" + ", status: " + this.getStatus());
		if (this.getStatus() == WSConnectionStatus.CONNECTED) {
			this.sendString("PING!");
		}
		if (this.getStatus() == WSConnectionStatus.DISCONNECTED) {
			this.connect();
		}
	}
	
	@Override
	public void onSocketConnect(Session session) {
        System.out.printf("Connected to: %s%n",session);
        this.setStatus(WSConnectionStatus.CONNECTED);
	}
	@Override
	public void onSocketClose(int statusCode, String reason) {
		System.out.printf("Connection closed: %d - %s%n",statusCode,reason);
		
		// Comment to avoid InterruptException
//		try { this.client.stop(); } catch (Exception e) { e.printStackTrace(); }
		if (!isManualDisconnect && this.getStatus() == WSConnectionStatus.CONNECTED) this.reconnect();
		this.setStatus(WSConnectionStatus.DISCONNECTED);
	}
	@Override
	public void onSocketMessage(String msg) {
		// TODO Auto-generated method stub
		System.out.printf("Got msg: %s%n",msg);
	}
	@Override
	public void onSocketError(Session session, Throwable error) {
		System.out.println("Socket has error");
		System.out.println(error.getMessage());
		// Comment to avoid InterruptException
//		try { this.client.stop(); } catch (Exception e) { e.printStackTrace(); }
		if (!isManualDisconnect && this.getStatus() == WSConnectionStatus.CONNECTED) this.reconnect();
		this.setStatus(WSConnectionStatus.DISCONNECTED);
	}
	
	// METHODS TO SEND DATA
	public void sendString(String data) {
		if (this.getStatus() == WSConnectionStatus.CONNECTED) {
			this.socket.sendString(data);
		}
	}
	public void sendBytes(byte[] data) {
		if (this.getStatus() == WSConnectionStatus.CONNECTED) {
			this.socket.sendBytes(data);
		}
	}
}
