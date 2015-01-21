package kostajabot.core;

import configurations.ConfigurationPropertiesLoader;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelnetConnectionHandler implements Runnable {

    private final TelnetClient client = new TelnetClient();
    private final InputStream in;
    private final PrintStream out;
    private ChannelHandler channelHandler;
    private String server;
    private final ConfigurationPropertiesLoader configuration;

    public TelnetConnectionHandler(ConfigurationPropertiesLoader conf) throws Exception {
        configuration = conf;
        channelHandler = new ChannelHandler(this);
        
        client.connect("irc.fi.quakenet.org", 6667);
        in = client.getInputStream();
        out = new PrintStream(client.getOutputStream());
        setup();
    }

    private void setup() throws Exception {
        handleString(readLine());
        // Load user data
        String nick = configuration.getProperty("nick");
        String ident = configuration.getProperty("ident");
        String usermodes = configuration.getProperty("usermodes");
        String realname = configuration.getProperty("realname");
        write("NICK " + nick);
        handleString(readLine());
        write("USER  " + ident + " " + usermodes + " *  : " + realname);
    }

    private String readLine() throws Exception {
        StringBuilder sb = new StringBuilder();
        char ch = (char) in.read();
        while (true) {
            sb.append(ch);
            if (ch == '\n') {
                return sb.toString();
            }
            ch = (char) in.read();
        }
    }

    public void write(String value) throws Exception {
        out.println(value);
        out.flush();
        System.out.println(value);
    }

    public void disconnect() throws Exception {
        client.disconnect();
    }

    private void pingPong(String str) throws Exception{
        if (str.startsWith("PING")) {
            write("PONG" + str.substring(4));
        }
    }

    private void handleString(String str) throws Exception{
        System.out.print(str);
        pingPong(str);
        if (str.contains("End of /MOTD command")) {
            server = str.split(" ")[0].substring(1);
            System.out.println("My server: " + server);
            channelHandler.joiner();
        }
    }

    @Override
    public void run() {
        try {
            handleString(readLine());
        } catch (Exception ex) {
            Logger.getLogger(TelnetConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
