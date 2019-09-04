/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.mvc.view;

import static org.foi.uzdiz.tmalic.dz3.mvc.view.CommandsView.command;
import static org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils.ANSI_ESC;
import static org.foi.uzdiz.tmalic.dz3.utils.AnsiUtils.setCursor;

/**
 *
 * @author tadij
 */
public class OutputView {

    int i = 1;
    int j = 80;

    public void setScreen(int brojRedakaGornji, int brojRedakaDonji) {
        for (j = 80; j > 1; j--) {
            show(brojRedakaGornji + 1, j, 37, "-");
        }
        System.out.print(ANSI_ESC + "B");
    }

    public void show(int x, int y, int boja, String tekst) {
        setCursor(x, y);
        System.out.print(ANSI_ESC + boja + "m");
        System.out.print(tekst);
    }

    public void getResults() {
        System.out.print(ANSI_ESC + 37 + "m");
        System.out.print("Primljena komanda: " + command);
    }

    public void printMsg(String msg) {
        System.out.print(ANSI_ESC + 37 + "m");
        System.out.println(msg);
    }
}
