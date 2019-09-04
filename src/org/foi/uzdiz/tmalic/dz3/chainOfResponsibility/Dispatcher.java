/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandBolovanje;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandGodisnjiOdmor;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandIsprazni;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandIzlaz;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandKontrola;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandKreni;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandKvar;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandNovi;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandObradi;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandOtkaz;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandPreuzmi;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandPripremi;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandStatus;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandVozaci;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Helpers.CHARSET;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Paths.BasePaths.DISPATCHER_FILE;

/**
 *
 * @author tadij
 */
public class Dispatcher {

    private final File dispatcherFilePath = new File(Paths.get(".").toAbsolutePath().normalize().toString() + DISPATCHER_FILE);
    private static Dispatcher instance = null;

    Logger logger = Logger.getLogger(Dispatcher.class.getName());
    String command;
    private String line = null;

    CommandChain commandBolovanje = new CommandBolovanje();
    CommandChain commandGodisnjiOdmor = new CommandGodisnjiOdmor();
    CommandChain commandIsprazni = new CommandIsprazni();
    CommandChain commandIzlaz = new CommandIzlaz();
    CommandChain commandKontrola = new CommandKontrola();
    CommandChain commandKreni = new CommandKreni();
    CommandChain commandKvar = new CommandKvar();
    CommandChain commandNovi = new CommandNovi();
    CommandChain commandObradi = new CommandObradi();
    CommandChain commandOtkaz = new CommandOtkaz();
    CommandChain commandPreuzmi = new CommandPreuzmi();
    CommandChain commandPripremi = new CommandPripremi();
    CommandChain commandStatus = new CommandStatus();
    CommandChain commandVozaci = new CommandVozaci();

    public Dispatcher() {
        commandIzlaz.setNextChain(commandPripremi);
        commandPripremi.setNextChain(commandStatus);
        commandStatus.setNextChain(commandVozaci);
        commandVozaci.setNextChain(commandGodisnjiOdmor);
        commandGodisnjiOdmor.setNextChain(commandOtkaz);
        commandOtkaz.setNextChain(commandBolovanje);
        commandBolovanje.setNextChain(commandNovi);
        commandNovi.setNextChain(commandKreni);
        commandKreni.setNextChain(commandKvar);
        commandKvar.setNextChain(commandKontrola);
        commandKontrola.setNextChain(commandIsprazni);
        commandIsprazni.setNextChain(commandPreuzmi);
        commandPreuzmi.setNextChain(commandObradi);
        commandObradi.setNextChain(commandIzlaz);
    }

    public void getDispatcherData() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dispatcherFilePath), CHARSET));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                sendCommand(line);
            }
        } catch (IOException ex) {
            logger.log(Level.CONFIG, "Error reading the file - {0}", ex);
        }
    }

    public void sendCommand(String command) {
        String[] commandData = command.split(";|:");
        if (validate(commandData)) {
            commandObradi.execute(command);
        }
    }

    private boolean validate(String[] containerData) {
        if (containerData[0].isEmpty()) {
            logger.log(Level.WARNING, "Error in file structure");
            return false;
        }
        return true;
    }

}
