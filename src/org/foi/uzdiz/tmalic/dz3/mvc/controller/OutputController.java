/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.mvc.controller;

import org.foi.uzdiz.tmalic.dz3.mvc.view.CommandsView;
import static org.foi.uzdiz.tmalic.dz3.mvc.view.CommandsView.command;
import org.foi.uzdiz.tmalic.dz3.mvc.view.OutputView;
import org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils;
import static org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils.eraseDown;
import static org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils.eraseScreen;
import static org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils.eraseUp;

/**
 *
 * @author tadij
 */
public class OutputController {

    static int brojRedakaGornji, brojRedakaDonji = 0;
    static OutputView outputView = new OutputView();
    static CommandsView commandsView = new CommandsView();
    public static int outputScreenRowCounter = 0;
    static int commandScreenRowCounter = 1;
    String savedCursorsPosition;

    public OutputController() {
    }

    public static void prepareScreens(String brojRedakaGornji, String brojRedakaDonji) {
        OutputController.brojRedakaGornji = Integer.parseInt(brojRedakaGornji.substring(brojRedakaGornji.indexOf(' '), brojRedakaGornji.length()).trim());
        OutputController.brojRedakaDonji = Integer.parseInt(brojRedakaDonji.substring(brojRedakaDonji.indexOf(' '), brojRedakaDonji.length()).trim());
        eraseScreen();
        outputView.setScreen(OutputController.brojRedakaGornji, OutputController.brojRedakaDonji);
        commandsView.setScreen(OutputController.brojRedakaGornji, OutputController.brojRedakaDonji);
        AnsiUtils.setCursor(outputScreenRowCounter, 1);
    }

    public static void showMsg(String msg) {
        outputScreenRowCounter++;
        if (commandScreenRowCounter == OutputController.brojRedakaDonji + 1) {
            resetCommandsView();
        }
        if (outputScreenRowCounter >= OutputController.brojRedakaGornji) {
            while (!command.equals("n") && !command.equals("N")) {
                resetOutputView();
            }
            command = "";
            AnsiUtils.setCursor(OutputController.brojRedakaGornji, 100);
            eraseUp();
            outputScreenRowCounter = 0;
            AnsiUtils.setCursor(0, 0);
        }
        outputView.printMsg(msg);
    }

    static void resetCommandsView() {
        AnsiUtils.setCursor(brojRedakaGornji + 2, 1);
        AnsiUtils.eraseLine();
        commandScreenRowCounter = 1;
    }

    static public void isThereEnoughSpaceOnScreen() {
        if (outputScreenRowCounter + 2 >= OutputController.brojRedakaGornji - 2) {
            outputScreenRowCounter += 2;
        }
    }

    static void resetOutputView() {
        AnsiUtils.setCursor(brojRedakaGornji + 2, 1);
        eraseDown();
        commandScreenRowCounter = 1;
        commandsView.writeUserOutput("There is no space in first frame. Send n/N command to erase old data: ");
    }

    public static void waitForCommand() {
        AnsiUtils.setCursor(brojRedakaGornji + 2, 1);
        eraseDown();
        commandsView.getCommandFromUser(brojRedakaGornji);
    }

}
