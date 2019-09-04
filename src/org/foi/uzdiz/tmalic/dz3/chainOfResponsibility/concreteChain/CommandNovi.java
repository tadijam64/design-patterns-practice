/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.state.Driver;

/**
 *
 * @author tadij
 */
public class CommandNovi implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("NOVI")) {
            commandParts = command.split(";|,");
            for (int i = 1; i < commandParts.length; i++) {
                Driver newDriver = new Driver(commandParts[i]);
            }
        } else {
            nextInChain.execute(command);
        }
    }

}
