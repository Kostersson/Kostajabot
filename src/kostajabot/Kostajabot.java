/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kostajabot;

import kostajabot.core.Telnet;

/**
 *
 * @author kostersson
 */
public class Kostajabot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Telnet telnet;
        try {
            telnet = new Telnet();
            while(true){
                telnet.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
