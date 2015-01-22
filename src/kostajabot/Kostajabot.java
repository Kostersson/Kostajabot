
package kostajabot;

import configurations.ConfigurationPropertiesLoader;
import kostajabot.core.TelnetConnectionHandler;

/**
 *
 * @author kostersson
 */
public class Kostajabot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConfigurationPropertiesLoader conf = new ConfigurationPropertiesLoader();
        TelnetConnectionHandler telnet;
        try {
            telnet = new TelnetConnectionHandler(conf);
            while (true) {
                telnet.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
