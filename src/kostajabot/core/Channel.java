
package kostajabot.core;

import kostajabot.core.exceptions.*;

/**
 *
 * @author Kostersson
 */
public class Channel {
    private final String channelName;
    private final String network;

    public Channel(String channelName, String network) {
        this.channelName = channelName;
        this.network = network;
    }

    public String getChannelName() throws NoChannelNameException{
        if(channelName.isEmpty()){
            throw new NoChannelNameException();
        }
        return channelName;
    }

    public String getNetwork() throws NoNetworkException{
        if(network.isEmpty()){
            throw new NoNetworkException();
        }
        return network;
    }

    
}
