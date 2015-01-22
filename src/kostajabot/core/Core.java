package kostajabot.core;

import configurations.ConfigurationPropertiesLoader;
import kostajabot.core.stringhandlers.MessageHandler;

/**
 *
 * @author Kostersson
 */
public class Core {
    private ConfigurationPropertiesLoader conf;
    private TelnetConnectionHandler connectionHandler;
    private ChannelHandler channelHandler;
    private MessageHandler messageHandler;
    
    public Core() {
        conf = new ConfigurationPropertiesLoader();
        try {
            connectionHandler = new TelnetConnectionHandler(this, conf);
            channelHandler = new ChannelHandler(connectionHandler);
            messageHandler = new MessageHandler();
            setup();
            while (true) {
                connectionHandler.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setup() throws Exception {
        // Load user data
        String nick = conf.getProperty("nick");
        String ident = conf.getProperty("ident");
        String usermodes = conf.getProperty("usermodes");
        String realname = conf.getProperty("realname");
        write("NICK " + nick);
        write("USER  " + ident + " " + usermodes + " *  : " + realname);
    }
    
    public void autoJoinChannels() throws Exception{
        channelHandler.joiner();
    }
    
    public void handleString(String str){
        if (str.matches("(?s):(.*) PRIVMSG (.*)")) {
            messageHandler.handleMessage(str);
        }
    }
    
    public void write(String str) throws Exception{
        connectionHandler.write(str);
    }
}
