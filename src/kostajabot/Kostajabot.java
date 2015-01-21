/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            while(true){
                telnet.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
