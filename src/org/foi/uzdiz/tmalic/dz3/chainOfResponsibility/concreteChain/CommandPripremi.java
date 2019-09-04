/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import java.util.ArrayList;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.Inoperative;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;
import org.foi.uzdiz.tmalic.dz3.utils.BaseUtils;

/**
 *
 * @author tadij
 */
public class CommandPripremi implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;
    public static ArrayList<Vehicle> sortedVehicles = new ArrayList<>();

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("PRIPREMI")) {
            commandParts = command.split(";|,");
            for (Vehicle currentVehicle : Vehicle.allVehicles) {
                for (int i = 0; i < commandParts.length; i++) {
                    if (currentVehicle.getId().equals(commandParts[i])) {
                        if (currentVehicle.getVehicleState() instanceof Inoperative) {
                            BaseUtils.formatOutput("To be able to get ready for garbage collecting, vehicle needs to go to the landfill.");
                        } else {
                            currentVehicle.setVehicleState(currentVehicle.getReadyToCollect());
                            sortedVehicles.add(currentVehicle);
                        }
                    }
                }
            }

        } else {
            nextInChain.execute(command);
        }
    }

}
