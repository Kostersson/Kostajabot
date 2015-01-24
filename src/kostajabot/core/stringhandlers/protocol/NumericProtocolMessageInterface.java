
package kostajabot.core.stringhandlers.protocol;

/**
 *
 * @author Kostersson
 */
public interface NumericProtocolMessageInterface {
    
    public int getResponseNumber();
    
    public void handleMessage(String str);
}
