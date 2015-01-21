/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kostajabot.core;

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

    public String getChannelName() {
        return channelName;
    }

    public String getNetwork() {
        return network;
    }

    
}
