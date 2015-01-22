/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kostajabot.core;

import java.util.ArrayList;

/**
 *
 * @author kostersson
 */
public class ChannelHandler {
    private TelnetConnectionHandler client;
    private ArrayList<Channel> channels;

    public ChannelHandler(TelnetConnectionHandler telnetClient) {
        client = telnetClient;
        channels = new ArrayList<>();
        // tester
        addChannelToChannels("#kostajabot", "Quakenet");
    }
    
    public void addChannelToChannels(String name, String network){
        Channel channel = new Channel(name, network);
        channels.add(channel);
    }
    
    public void joiner() throws Exception{
        for (Channel channel : channels) {
            client.write("JOIN " + channel.getChannelName());
        }
        
    }
}
