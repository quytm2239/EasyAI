//
//  ========================================================================
//  Copyright (c) 1995-2017 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package connectivity.websocket;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import connectivity.inteface.WSHandlerInterface;

/**
 * Basic Echo Client Socket
 */
@WebSocket(maxTextMessageSize = 64 * 1024)
public class WSSessionEvent
{
    private final CountDownLatch closeLatch;
    private Session session;
    public WSHandlerInterface handlerInterface;
    
    public WSHandlerInterface getHandlerInterface() {
		return handlerInterface;
	}
	public void setHandlerInterface(WSHandlerInterface handlerInterface) {
		this.handlerInterface = handlerInterface;
	}
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	public WSSessionEvent() {
        this.closeLatch = new CountDownLatch(1);
    }
	
	public WSSessionEvent(WSHandlerInterface handlerInterface) {
        this.closeLatch = new CountDownLatch(1);
        this.handlerInterface = handlerInterface;
    }

	public void disconnect() {
		this.session.close();
	}
    
	public void waitForConnect() {
    		try {
			this.closeLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void stopWait() {
		this.closeLatch.countDown();
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    		this.stopWait();
    		this.session = null;
        if (handlerInterface != null) handlerInterface.onSocketClose(statusCode, reason);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
    		this.stopWait();
        this.session = session;
        if (handlerInterface != null) handlerInterface.onSocketConnect(session);
    }

    @OnWebSocketMessage
    public void onMessage(String msg) { 
    	if (handlerInterface != null) handlerInterface.onSocketMessage(msg);
    }
    
    @OnWebSocketError
    public void onError(Session session, Throwable error) {
    		this.stopWait();
    		this.session = null;
    		if (handlerInterface != null) handlerInterface.onSocketError(session, error);
    }
    
    public void sendString(String data) {
    	if (this.session != null && this.session.isOpen()) {
    		// Async Send of a TEXT message to remote endpoint
    		Future<Void> fut = null;
    		try {
    		    fut = session.getRemote().sendStringByFuture(data);
    		    // wait for completion (timeout)
    		    fut.get();
    		} catch (ExecutionException | InterruptedException e) {
    		    // Send failed
    		    e.printStackTrace();
    		}
	    } else {
	    	System.out.println("Socket is closed!");
	    }
    }
    
    public void sendBytes(byte[] data) {
	    	if (this.session != null && this.session.isOpen()) {
	    		// Async Send of a TEXT message to remote endpoint
	    		ByteBuffer buf = ByteBuffer.wrap(data);
	    		Future<Void> fut = null;
	    		try {
	    		    fut = session.getRemote().sendBytesByFuture(buf);
	    		    // wait for completion (timeout)
	    		    fut.get();
	    		} catch (ExecutionException | InterruptedException e) {
	    		    // Send failed
	    		    e.printStackTrace();
	    		}
	    } else {
		    		System.out.println("Socket is closed!");
	    }
    }
}