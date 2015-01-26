
package kostajabot.core;

import java.util.ArrayList;
import kostajabot.core.exceptions.*;

/**
 *
 * @author Kostersson
 */
public class Channel {
    private final String channelName;
    private final String network;
    private ArrayList<String> users;
    
    public Channel(String channelName, String network) {
        this.channelName = channelName;
        this.network = network;
        this.users = new ArrayList<>();
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
    
    public void addUser(String user){
        users.add(user);
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return channelName + " @ " + network + "\n===============\n" + users + "\n===============\n";
    }
    
    
    
}
