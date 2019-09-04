/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.GarbageCollector;
import org.foi.uzdiz.tmalic.dz3.utils.BaseUtils;

/**
 *
 * @author tadij
 */
public class CommandKreni implements CommandChain {

    private CommandChain nextInChain;
    String[] commandParts;

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("KRENI")) {
            GarbageCollector garbageCollector = new GarbageCollector();
            commandParts = command.split(" ");
            if (commandParts.length > 1) {
                String cycleNumber = commandParts[1].substring(0, commandParts[1].length() - 1);
                if (BaseUtils.isNumeric(cycleNumber)) {
                    int finalCycleNumber = Integer.parseInt(cycleNumber);
                    GarbageCollector.finalCycleNumber = finalCycleNumber;
                    garbageCollector.startCollecting();
                }
            } else {
                garbageCollector.startCollecting();
            }
        } else {
            nextInChain.execute(command);
        }
    }

}
