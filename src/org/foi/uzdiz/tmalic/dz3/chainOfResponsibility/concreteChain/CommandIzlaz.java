/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.GarbageCollector;

/**
 *
 * @author tadij
 */
public class CommandIzlaz implements CommandChain {

    private CommandChain nextInChain;
    private GarbageCollector garbageCollector = new GarbageCollector();

    @Override
    public void setNextChain(CommandChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void execute(String command) {
        if (command.contains("IZLAZ")) {
            garbageCollector.statisticDataAboutGarbage();
            System.exit(0);
        } else {
            nextInChain.execute(command);
        }
    }
}
