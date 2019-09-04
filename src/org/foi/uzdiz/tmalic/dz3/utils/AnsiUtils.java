/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.utils;

/**
 *
 * @author tadij
 */
public class AnsiUtils {

    public static final String ANSI_ESC = "\033[";

    public static void setCursor(int x, int y) {
        System.out.print(ANSI_ESC + x + ";" + y + "f");
    }

    public static void moveCursorDown() {
        System.out.println(ANSI_ESC + "B");
    }

    public static void moveCursorUp() {
        System.out.println(ANSI_ESC + "A");
    }

    public static void eraseLine() {
        System.out.println(ANSI_ESC + "2K");
    }

    public static void eraseUp() {
        System.out.println(ANSI_ESC + "1J");
    }

    public static void eraseDown() {
        System.out.println(ANSI_ESC + "J");
    }

    public static void eraseEndOfLine() {
        System.out.println(ANSI_ESC + "K");
    }

    public static void eraseScreen() {
        System.out.print(ANSI_ESC + "2J");
    }

    public static void saveCursorPosition() {
        System.out.print(ANSI_ESC + "7");
        System.out.print("\n");
    }

    public static void restoreLastSavedCursorPosition() {
        System.out.print(ANSI_ESC + "78");
    }
}
