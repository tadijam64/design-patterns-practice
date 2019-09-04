/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.state.Driver;
import org.foi.uzdiz.tmalic.dz3.state.driverStates.DriverFired;

/**
 *
 * @author tadij
 */
public class CommandGodisnjiOdmor implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("GODIŠNJI ODMOR")) {
            commandParts = command.split(";|,");
            for (String driversName : commandParts) {
                for (Driver driver : Driver.drivers) {
                    if (driver.getName().equals(driversName)) {
                        if (!(driver.getDriverState() instanceof DriverFired)) {
                            driver.setDriverState(driver.getOnVacation());
                        }
                    }
                }
            }
        } else {
            nextInChain.execute(command);
        }
    }

}
