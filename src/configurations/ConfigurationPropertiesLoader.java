/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configurations;

import configurations.exceptions.NoPropertyFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Kostersson
 */
public class ConfigurationPropertiesLoader {

    Properties properties;

    public ConfigurationPropertiesLoader() {
        properties = new Properties();
        InputStream input = null;

        try {
            input = this.getClass().getResourceAsStream("resources/configuration.properties");
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperty(String name)throws NoPropertyFoundException {
        String property = properties.getProperty(name);
        if(property.isEmpty()){
            throw new NoPropertyFoundException();
        }
        return property;
    }
}
