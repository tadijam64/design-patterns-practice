/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.Main;
import static org.foi.uzdiz.tmalic.dz3.mvc.controller.OutputController.showMsg;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.ISPIS_PROPERTY;

/**
 *
 * @author tadij
 */
public class BaseUtils {

    static Logger logger = Logger.getLogger(BaseUtils.class.getName());
    public static PrintStream out = null;

    public static float generateRandom(int seed, float from, float to) {
        Random generator = new Random(seed);
        float result = from + generator.nextFloat() * (to - from);
        return result;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void output(final String msg, PrintStream out1, PrintStream out2) {
        showMsg(msg);
        if (ISPIS_PROPERTY == 0) {
            //out1.println(msg);
            //out2.println(msg);
        } else {
            //out1.println(msg);
        }
    }

    public static PrintStream getOutFileStream() {
        try {
            if (ISPIS_PROPERTY == 0) {
                out = new PrintStream(new FileOutputStream("output.txt", true));
            } else {
                out = new PrintStream(new FileOutputStream("output.txt"));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public static void statisticOutput(final String msg, PrintStream out1, PrintStream out2) {
        out1.println(msg);
        out2.println(msg);
    }

    public static PrintStream getOutFileStreamStatistic() {
        try {
            out = new PrintStream(new FileOutputStream("output.txt", true));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public static String getExtensionByStringHandling(String filename) {
        String reverseFilename = new StringBuilder(new String(filename)).reverse().toString();
        char[] charArrayOfFilename = reverseFilename.toCharArray();
        String extension = "";
        for (char ch : charArrayOfFilename) {
            if (ch == '.') {
                for (int i = new String(charArrayOfFilename).indexOf('.') - 1; i >= 0; i--) {
                    extension = extension + charArrayOfFilename[i];
                    if (i == 0) {
                        return extension;
                    }
                }
            }
        }
        return extension;
    }

    public static void formatOutput(String output) {
        output(output, System.out, getOutFileStream());
    }

    public static void statFormatOutput(String output) {
        output(output, System.out, getOutFileStreamStatistic());
    }

}
