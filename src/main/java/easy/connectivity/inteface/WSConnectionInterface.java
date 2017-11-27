/**
 * 
 */
package easy.connectivity.inteface;

/**
 * @author QuyTM239
 *
 */
public interface WSConnectionInterface {
	public void onConnect();
	public void onIncomingData(String data);
	public void onDisconnected();
}
