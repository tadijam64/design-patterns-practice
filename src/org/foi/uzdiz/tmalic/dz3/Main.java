/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3;

import org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.Dispatcher;
import org.foi.uzdiz.tmalic.dz3.configuration.ConfigurationSetter;
import org.foi.uzdiz.tmalic.dz3.configuration.ConfigurationSetterWithZipFile;
import org.foi.uzdiz.tmalic.dz3.configuration.InitialConfiguration;
import static org.foi.uzdiz.tmalic.dz3.mvc.controller.OutputController.prepareScreens;
import static org.foi.uzdiz.tmalic.dz3.mvc.controller.OutputController.waitForCommand;
import org.foi.uzdiz.tmalic.dz3.service.composite.AreaComposite;
import org.foi.uzdiz.tmalic.dz3.utils.BaseUtils;
import org.foi.uzdiz.tmalic.dz3.utils.FileWorker;

/**
 *
 * @author tadij
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InitialConfiguration initialConfiguration = null;
        if (args.length == 0) {
            System.out.println("There is no parameters file!");
            System.exit(0);
        } else if (BaseUtils.getExtensionByStringHandling(args[0]).equals("zip")) {
            initialConfiguration = new ConfigurationSetterWithZipFile();
            initialConfiguration.setInitialProperties(args[0]);
        } else {
            initialConfiguration = new ConfigurationSetter();
            initialConfiguration.setInitialProperties(args[0]);
            prepareScreens(args[1] + " " + args[2], args[3] + " " + args[4]);
        }

        FileWorker fileWorker = new FileWorker();
        fileWorker.getStreetsData();
        fileWorker.getContainersData();
        fileWorker.getVehiclesData();
        fileWorker.getAreasData();

        AreaComposite.printAreasData();

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.getDispatcherData();
        while (true) {
            waitForCommand();
        }

    }

}
