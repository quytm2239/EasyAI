package connectivity.websocket;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import connectivity.configuration.*;
import connectivity.inteface.PingSenderInterface;
import connectivity.inteface.WSConnectionInterface;
import connectivity.inteface.WSSessionEventInterface;
import connectivity.ping.PingSender;

public class WSConnection implements WSSessionEventInterface,PingSenderInterface {
	
	final static Logger logger = Logger.getLogger(WSConnection.class);
	
	private void writeLog(String log) {
//		if(logger.isDebugEnabled()){
//		    logger.debug(log);
		    System.out.println(log);
//		}
	}
	
	private static WSConnection instance = null;
	private WSConnection() {
	}
	public static WSConnection getInstance() {
		if (instance == null) {
			instance = new WSConnection();
		}
		return instance;
	}
	
	public static WSConnection getInstance(WSConnectionInterface wsConnectionInterface) {
		if (instance == null) {
			instance = new WSConnection();
		}
		instance.setWsConnectionInterface(wsConnectionInterface);
		return instance;
	}
	
	private WSConnectionInterface wsConnectionInterface = null;
	private boolean isManualDisconnect = false;
	private boolean isReady = false;
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
	public WSConnectionInterface getWsConnectionInterface() {
		return wsConnectionInterface;
	}
	public void setWsConnectionInterface(WSConnectionInterface wsConnectionInterface) {
		this.wsConnectionInterface = wsConnectionInterface;
	}

	public boolean isManualDisconnect() {
		return isManualDisconnect;
	}
	public void setManualDisconnect(boolean isManualDisconnect) {
		this.isManualDisconnect = isManualDisconnect;
	}
	public boolean isReady() {
		return isReady;
	}
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	public PingSender getPingSender() {
		return pingSender;
	}
	
	public void connect() {
		if (this.getStatus() != WSConnectionStatus.DISCONNECTED) return;
		this.setStatus(WSConnectionStatus.CONNECTING);
		this.writeLog("[START] call connect() - " + this.getStatus());
        try {
            client.start();
            URI echoUri = new URI(WSConfiguration.WEBSOCKET_HOST);
            client.connect(socket,echoUri,request);
            this.setManualDisconnect(false);
            this.setReady(true);
            
            this.writeLog("[START] call waitForConnect() - " + this.getStatus());
            socket.waitForConnect();
            
        } catch (IOException e) {
            e.printStackTrace();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void disconnect() {
		this.writeLog("[START] call disconnect()");
		if (this.getStatus() == WSConnectionStatus.DISCONNECTED) return;
		try {
			this.socket.disconnect();
			this.setManualDisconnect(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	public void reconnect() {
//		this.writeLog("[START] call reconnect()");
//		if (this.getStatus() != WSConnectionStatus.DISCONNECTED) return;
//		this.connect();
//	}
	 
	@Override
	public void onPing() {
		if (this.getStatus() == WSConnectionStatus.CONNECTED && WSConfiguration.PING_WITH_MSG) {
			this.sendString("PING!");
		}
		//This below line will make new connect if not ManualDisconnect and current status is DISCONNECTED
		if (!this.isManualDisconnect() && this.getStatus() == WSConnectionStatus.DISCONNECTED && this.isReady()) this.connect();
	}
	
	@Override
	public void onSocketConnect(Session session) {
//        System.out.printf("Connected to: %s%n",session);
        this.setStatus(WSConnectionStatus.CONNECTED);
        if (this.getWsConnectionInterface() != null) this.getWsConnectionInterface().onConnect();
        this.writeLog("[CONNECTED] - " + session.getRemoteAddress());
        // This below line will register to be online on TRANSFER WS SERVER
        this.sendString(WSConfiguration.AI_REQUEST_STATEMENT);
        
	}
	@Override
	public void onSocketClose(int statusCode, String reason) {
		this.writeLog("[DISCONNECTED] - statusCode: " + statusCode + " - " + "reason: " + reason);
		if (!this.isManualDisconnect() && this.getStatus() == WSConnectionStatus.CONNECTED) this.connect();
		this.setStatus(WSConnectionStatus.DISCONNECTED);
		if (this.getWsConnectionInterface() != null) this.getWsConnectionInterface().onDisconnected();
	}
	@Override
	public void onSocketMessage(String data) {
		// Passing data to MainLoop
		if (this.getWsConnectionInterface() != null) this.getWsConnectionInterface().onIncomingData(data);
	}
	@Override
	public void onSocketError(Session session, Throwable error) {
		this.writeLog("[DISCONNECTED] - error: " + error.getMessage());
		if (!this.isManualDisconnect() && this.getStatus() == WSConnectionStatus.CONNECTED) this.connect();
		this.setStatus(WSConnectionStatus.DISCONNECTED);
		if (this.getWsConnectionInterface() != null) this.getWsConnectionInterface().onDisconnected();
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
