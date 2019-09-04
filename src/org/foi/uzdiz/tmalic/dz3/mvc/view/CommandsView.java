/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.mvc.view;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.CommandChain;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.Dispatcher;
import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandObradi;
import org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils;
import static org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils.ANSI_ESC;
import static org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils.setCursor;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;

/**
 *
 * @author tadij
 */
public class CommandsView {

    int i = 1;
    int j = 80;
    public static String command = "";
    CommandChain commandObradi = new CommandObradi();
    Logger logger = Logger.getLogger(Dispatcher.class.getName());
    ArrayList<String> commandParts = new ArrayList<>();
    Dispatcher dispatcher = new Dispatcher();

    public void setScreen(int brojRedakaGornji, int brojRedakaDonji) {
        for (j = 80; j > 1; j--) {
            show(brojRedakaGornji + brojRedakaDonji + 2, j, 37, "-");
        }
    }

    public void show(int x, int y, int boja, String tekst) {
        setCursor(x, y);
        System.out.print(ANSI_ESC + boja + "m");
        System.out.print(tekst);
    }

    public void getCommandFromUser(int brojRedakaGornji) {
        formatOutput("Upisi komandu: ");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CommandsView.class.getName()).log(Level.SEVERE, null, ex);
        }
        command = scanner.next();
        formatOutput(command);
        AnsiUtils.setCursor(brojRedakaGornji, 1);
        AnsiUtils.eraseUp();
        AnsiUtils.setCursor(1, 1);
        if (validate(command)) {
            dispatcher.sendCommand(command);
        }
    }

    public void writeUserOutput(String msg) {
        fillCommandPartsList();
        System.out.print(msg);
        Scanner scanner = new Scanner(System.in);
        command = scanner.next();
    }

    private boolean validate(String command) {
        if (command.isEmpty() || !isCommand(command)) {
            logger.log(Level.WARNING, "Error in file structure");
            return false;
        }
        return true;
    }

    private boolean isCommand(String command) {
        boolean contains = false;
        for (String pom : commandParts) {
            if (command.contains(pom)) {
                contains = true;
            }
        }
        return contains;
    }

    private void fillCommandPartsList() {
        commandParts.addAll(Arrays.asList("OBRADI", "PRIPREMI", "KRENI", "KVAR", "KONTROLA", "ISPRAZNI", "STATUS"));
        commandParts.addAll(Arrays.asList("BOLOVANJE", "OTKAZ", "PREUZMI", "NOVI", "VOZAČI", "IZLAZ", "GODIŠNJI ODMOR"));
    }

}
