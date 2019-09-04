/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;
import org.foi.uzdiz.tmalic.dz3.service.buider.VehicleBuilder;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.isNumeric;

/**
 *
 * @author tadij
 */
public class VehicleDirector {

    Logger logger = Logger.getLogger(StreetDirector.class.getName());

    private VehicleBuilder vehicleBuilder;
    private static VehicleDirector vehicleService = null;

    public VehicleDirector(VehicleBuilder vehicleBuilder) {
        this.vehicleBuilder = vehicleBuilder;
    }

    public Vehicle getVehicle() {
        return this.vehicleBuilder.getVehicle();
    }

    public void makeVehicle(String vehicle) {
        String[] vehicleData = vehicle.split(";|:");
        if (validate(vehicleData)) {
            this.vehicleBuilder.buildVehicleType(Integer.parseInt(vehicleData[3]));
            this.vehicleBuilder.buildId(vehicleData[0]);
            this.vehicleBuilder.buildName(vehicleData[1]);
            this.vehicleBuilder.buildConsumptionType(Integer.parseInt(vehicleData[2]));
            this.vehicleBuilder.buildCapacity(Float.parseFloat(vehicleData[4]));
            this.vehicleBuilder.buildDrivers(vehicleData[5].split(", "));
            this.vehicleBuilder.buildVehicles();
        }
    }

    private boolean validate(String[] containerData) {
        if (containerData.length != 6 || containerData[0].isEmpty() || containerData[4].isEmpty()) {
            if (containerData[0].isEmpty()) {
                return false;
            }
            logger.log(Level.WARNING, "Error in file structure");
            return false;
        }
        for (int i = 2; i <= containerData.length - 2; i++) {
            if (!isNumeric(containerData[i])) {
                logger.log(Level.WARNING, "Error in file structure,numeric values expected");
                return false;
            }
        }
        return true;
    }
}
