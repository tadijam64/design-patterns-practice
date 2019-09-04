/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.configuration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Helpers.CHARSET;

/**
 *
 * @author tadij
 */
public abstract class InitialConfiguration {

    Logger logger = Logger.getLogger(InitialConfiguration.class.getName());
    public static Properties prop = new Properties();

    public InitialConfiguration() {
    }

    public void setInitialProperties(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, CHARSET));) {
            prop.load(br);
        } catch (IOException ex) {
            logger.log(Level.CONFIG, "Error reading the file - {0}", ex);
        }
    }

}
