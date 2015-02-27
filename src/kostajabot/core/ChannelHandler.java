package kostajabot.core;

import java.util.ArrayList;
import kostajabot.core.exceptions.NoChannelNameException;

/**
 *
 * @author kostersson
 */
public class ChannelHandler {
    private TelnetConnectionHandler client;
    private ArrayList<Channel> channels;
    private boolean connected;

    public ChannelHandler(TelnetConnectionHandler telnetClient) {
        client = telnetClient;
        channels = new ArrayList<>();
        connected = false;
        // tester
        addChannelToChannels("#kostajabot", "quakenet");
    }
    
    public void addChannelToChannels(String name, String network){
        Channel channel = new Channel(name, network);
        channels.add(channel);
    }
    
    public Channel getChannel(String name, String network) throws Exception{
        for(Channel channel : channels){
            if(channel.getChannelName().equals(name) && channel.getNetwork().equals(network)){
                return channel;
            }
        }
        return null;
    }
    
    public void joiner() throws Exception{
        if(connected){
            return;
        }
        connected = true;
        for (Channel channel : channels) {
            client.write("JOIN " + channel.getChannelName());
        }
    }
}
