/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.state.Driver;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;

/**
 *
 * @author tadij
 */
public class CommandPreuzmi implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("PREUZMI")) {
            commandParts = command.split(";|,");
            Vehicle currentVehicle = null;
            for (Vehicle vehicle : Vehicle.allVehicles) {
                if (vehicle.getId().equals(commandParts[2])) {
                    currentVehicle = vehicle;
                    currentVehicle.increaseTimesDriverChanged();
                }
            }
            mainLoop:
            for (Driver driver : Driver.drivers) {
                if (driver.getName().equals(commandParts[1])) {
                    driver.setVehicle(currentVehicle);
                    for (Driver driverForVehicle : currentVehicle.getDrivers()) {
                        if (driverForVehicle.getName().equals(driver.getName())) {
                            break mainLoop;
                        }
                    }
                    currentVehicle.addDriver(driver);
                    driver.setDriverState(driver.getWorking());
                    currentVehicle.setMainDriver(driver);
                }
            }
        } else {
            nextInChain.execute(command);
        }
    }

}
