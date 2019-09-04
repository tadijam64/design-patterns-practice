/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.service.composite.AreaComposite;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;

/**
 *
 * @author tadij
 */
public class CommandObradi implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("OBRADI")) {
            commandParts = command.split(";|,");
            for (AreaComposite area : AreaComposite.getMainAreas()) {
                if (area.getId().equals(commandParts[1])) {
                    for (Vehicle currentVehicle : Vehicle.allVehicles) {
                        for (int i = 2; i < commandParts.length; i++) {
                            if (currentVehicle.getId().equals(commandParts[i])) {
                                currentVehicle.setWorkingArea(area);
                                area.addVehicleToCollection(currentVehicle);
                            }
                        }
                    }
                }
            }
        } else {
            nextInChain.execute(command);
        }
    }

}
