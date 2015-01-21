/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kostajabot.core;

/**
 *
 * @author kostersson
 */
public class ChannelHandler {
    private Telnet client;

    public ChannelHandler(Telnet client) {
        client = client;
    }
    
    public void joiner(){
        client.write("JOIN ");
    }
}
