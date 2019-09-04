/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.mvc.controller.OutputController;
import static org.foi.uzdiz.tmalic.dz3.mvc.controller.OutputController.isThereEnoughSpaceOnScreen;
import org.foi.uzdiz.tmalic.dz3.state.Driver;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.Inoperative;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;

/**
 *
 * @author tadij
 */
public class CommandStatus implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("STATUS")) {
            commandParts = command.split(";|,");
            formatOutput("");
            formatOutput("--------------------------------------------------------------------");
            OutputController.isThereEnoughSpaceOnScreen();
            System.out.format("%17s%29s%15s", "Vozilo | ", "Stanje" + " | ", "Vozači");
            formatOutput("");
            formatOutput("--------------------------------------------------------------------");
            for (Vehicle currentVehicle : Vehicle.allVehicles) {
                String vehicleName = currentVehicle.getName();
                String formattedDriversNames = formatDriverNames(currentVehicle);
                if (!(currentVehicle.getVehicleState() instanceof Inoperative)) {
                    isThereEnoughSpaceOnScreen();
                    System.out.format("%17s%29s%15s", vehicleName + " | ", currentVehicle.getVehicleStateString() + " | ", formattedDriversNames);
                    formatOutput("");
                }
            }
        } else {
            nextInChain.execute(command);
        }
    }

    private String formatDriverNames(Vehicle currentVehicle) {
        String formattedDriverNames = "";
        if (currentVehicle.getDriversNames() != null) {
            for (Driver driver : currentVehicle.getDrivers()) {
                formattedDriverNames += driver.getName();
                formattedDriverNames += ", ";
            }
        }

        return formattedDriverNames;
    }

}
