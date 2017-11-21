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

package connectivity;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Basic Echo Client Socket
 */
@WebSocket(maxTextMessageSize = 64 * 1024)
public class WSHandler
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
	
	public WSHandler()
    {
        this.closeLatch = new CountDownLatch(1);
    }
//
    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException
    {
        return this.closeLatch.await(duration,unit);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        if (handlerInterface != null) handlerInterface.onSocketClose(statusCode, reason);
        this.session = null;
//        this.closeLatch.countDown(); // trigger latch
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
        if (handlerInterface != null) handlerInterface.onSocketConnect(session);
    }

    @OnWebSocketMessage
    public void onMessage(String msg) { 
    	if (handlerInterface != null) handlerInterface.onSocketMessage(msg);
    }
    
    @OnWebSocketError
    public void onError(Session session, Throwable error) {
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