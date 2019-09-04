/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import static org.foi.uzdiz.tmalic.dz3.mvc.controller.OutputController.isThereEnoughSpaceOnScreen;
import org.foi.uzdiz.tmalic.dz3.state.Driver;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;

/**
 *
 * @author tadij
 */
public class CommandVozaci implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("VOZAČI")) {
            commandParts = command.split(";|,");
            formatOutput("");
            formatOutput("--------------------------------------------------------------------");
            isThereEnoughSpaceOnScreen();
            System.out.format("%17s%29s%10s", "Ime | ", "Status" + " | ", "Employer status");
            formatOutput("");
            formatOutput("--------------------------------------------------------------------");
            for (Driver currentDriver : Driver.drivers) {
                String driverName = currentDriver.getName();
                isThereEnoughSpaceOnScreen();
                System.out.format("%17s%29s%10s", driverName + " | ", currentDriver.getDriverStateString() + " | ", currentDriver.getEmployed());
                formatOutput("");
            }
        } else {
            nextInChain.execute(command);
        }
    }

}
