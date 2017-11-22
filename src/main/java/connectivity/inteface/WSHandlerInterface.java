/**
 * 
 */
package connectivity.inteface;

import org.eclipse.jetty.websocket.api.Session;

/**
 * @author QuyTM239
 *
 */
public interface WSHandlerInterface {
	public void onSocketConnect(Session session);
	public void onSocketClose(int statusCode, String reason);
	public void onSocketMessage(String msg);
	public void onSocketError(Session session, Throwable error);
}
