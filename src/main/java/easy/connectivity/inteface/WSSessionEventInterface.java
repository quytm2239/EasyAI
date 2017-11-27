/**
 * 
 */
package easy.connectivity.inteface;

import org.eclipse.jetty.websocket.api.Session;

/**
 * @author QuyTM239
 *
 */
public interface WSSessionEventInterface {
	public void onSocketConnect(Session session);
	public void onSocketClose(int statusCode, String reason);
	public void onSocketMessage(String data);
	public void onSocketError(Session session, Throwable error);
}
