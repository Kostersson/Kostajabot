/*
* https://www.alien.net.au/irc/irc2numerics.html
* 353 	RPL_NAMREPLY
*/
package kostajabot.core.stringhandlers.protocol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kostajabot.core.Channel;
import kostajabot.core.Core;

/**
 *
 * @author Kostersson
 */
public class ChannelNames implements NumericProtocolMessageInterface{
    private int responseNumber;
    private Core core;

    public ChannelNames(Core core) {
        this.core = core;
        this.responseNumber = 353;
    }
    
    @Override
    public int getResponseNumber() {
        return responseNumber;
    }
    
    @Override
    public void handleMessage(String str) throws Exception{
        //353 Kostajabot = #kostajabot :Kostajabot @Kostersson
        str = str.replaceFirst("(?s) " + responseNumber + " " + core.getName() + " = ", "");
        String channelname = null;
        Pattern p = Pattern.compile("(.*) :");
        Matcher matcher = p.matcher(str);
        if (matcher.find()) {
            channelname = matcher.group(1);
        }
        str = str.replaceFirst("(.*) :" , "");
        String[] users = str.split(" ");
        String network = core.getNetwork();
        Channel channel = core.getChannelHandler().getChannel(channelname, network);
        for(int a = 0; a < users.length; a++){
            channel.addUser(users[a]);
        }
    }

}
