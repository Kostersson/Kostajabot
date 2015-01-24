/*
* https://www.alien.net.au/irc/irc2numerics.html
* 353 	RPL_NAMREPLY
*/
package kostajabot.core.stringhandlers.protocol;

/**
 *
 * @author Kostersson
 */
public class ChannelNames implements NumericProtocolMessageInterface{
    private int responseNumber;

    public ChannelNames() {
        responseNumber = 353;
    }
    
    @Override
    public int getResponseNumber() {
        return responseNumber;
    }
    
    @Override
    public void handleMessage(String str) {
        System.out.println("Foobar");
    }

}
