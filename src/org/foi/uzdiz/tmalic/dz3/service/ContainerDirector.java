/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.mvc.model.Container;
import org.foi.uzdiz.tmalic.dz3.service.buider.ContainerBuilder;
import org.foi.uzdiz.tmalic.dz3.utils.BaseUtils;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.isNumeric;

/**
 *
 * @author tadij
 */
public class ContainerDirector {

    Logger logger = Logger.getLogger(StreetDirector.class.getName());

    private ContainerBuilder containerBuilder;

    public ContainerDirector(ContainerBuilder containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

    public Container getContainer() {
        return this.containerBuilder.getContainer();
    }

    public void makeContainer(String container) {
        String[] containerData = container.split(";|:");
        if (validate(containerData)) {
            this.containerBuilder.buildName(containerData[0]);
            this.containerBuilder.buildContainerType(parseToInt(containerData[1]));
            this.containerBuilder.buildShare(parseToInt(containerData[2]), parseToInt(containerData[3]), parseToInt(containerData[4]));
            this.containerBuilder.buildCapacity(parseToInt(containerData[5]));
            this.containerBuilder.buildContainers();
            this.containerBuilder.addContainerToUsers();
        }
    }

    private boolean validate(String[] containerData) {
        if (containerData.length != 6 || containerData[0].isEmpty()) {
            if (containerData[0].isEmpty()) {
                return false;
            }
            logger.log(Level.WARNING, "Error in file structure, wrong number of parameters in file");
            return false;
        }
        for (int i = 1; i <= containerData.length - 1; i++) {
            if (!isNumeric(containerData[i])) {
                logger.log(Level.WARNING, "Error in file structure,numeric values expected");
                return false;
            }
        }
        return true;
    }

    private int parseToInt(String string) {
        return Integer.parseInt(string);
    }

}
