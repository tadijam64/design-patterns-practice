/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.service.buider.StreetBuilder;
import org.foi.uzdiz.tmalic.dz3.service.composite.StreetLeaf;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.isNumeric;

/**
 *
 * @author tadij
 */
public class StreetDirector {

    private StreetBuilder streetBuilder;
    Logger logger = Logger.getLogger(StreetDirector.class.getName());

    public StreetDirector(StreetBuilder streetBuilder) {
        this.streetBuilder = streetBuilder;
    }

    public StreetLeaf getStreet() {
        return this.streetBuilder.getStreet();
    }

    public void makeStreet(String street) {
        String[] streetData = street.split(";|:");
        if (validate(streetData)) {
            this.streetBuilder.buildId(streetData[0]);
            this.streetBuilder.buildName(streetData[1]);
            this.streetBuilder.buildSpotNumber(Integer.parseInt(streetData[2]));
            this.streetBuilder.buildShare(Integer.parseInt(streetData[3]), Integer.parseInt(streetData[4]), Integer.parseInt(streetData[5]));
            this.streetBuilder.buildUsers();
            this.streetBuilder.buildStreets();
        }
    }

    private boolean validate(String[] streetData) {
        if (streetData.length != 6 || streetData[0].isEmpty()) {
            if (streetData[0].isEmpty()) {
                return false;
            }
            logger.log(Level.WARNING, "Error in file structure");
            return false;
        }
        for (int i = 2; i <= streetData.length - 1; i++) {
            if (!isNumeric(streetData[i])) {
                logger.log(Level.WARNING, "Error in file structure,numeric values expected");
                return false;
            }
        }
        return true;
    }

}
