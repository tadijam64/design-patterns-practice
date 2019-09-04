/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.service.ContainerDirector;
import org.foi.uzdiz.tmalic.dz3.service.StreetDirector;
import org.foi.uzdiz.tmalic.dz3.service.VehicleDirector;
import org.foi.uzdiz.tmalic.dz3.service.buider.ContainerBuilder;
import org.foi.uzdiz.tmalic.dz3.service.buider.StreetBuilder;
import org.foi.uzdiz.tmalic.dz3.service.buider.VehicleBuilder;
import org.foi.uzdiz.tmalic.dz3.service.buider.impl.ContainerBuilderImpl;
import org.foi.uzdiz.tmalic.dz3.service.buider.impl.StreetBuilderImpl;
import org.foi.uzdiz.tmalic.dz3.service.buider.impl.VehicleBuilderImpl;
import org.foi.uzdiz.tmalic.dz3.service.composite.AreaComponent;
import org.foi.uzdiz.tmalic.dz3.service.composite.AreaComposite;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Helpers.CHARSET;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Paths.BasePaths.AREA_FILE;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Paths.BasePaths.STREETS_FILE;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Paths.BasePaths.CONTAINERS_FILE;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Paths.BasePaths.VEHICLES_FILE;

/**
 *
 * @author tadij
 */
public class FileWorker {

    private final File streetsFilePath = new File(Paths.get(".").toAbsolutePath().normalize().toString() + STREETS_FILE);
    private final File containersFilePath = new File(Paths.get(".").toAbsolutePath().normalize().toString() + CONTAINERS_FILE);
    private final File vehiclesFilePath = new File(Paths.get(".").toAbsolutePath().normalize().toString() + VEHICLES_FILE);
    private final File areasFilePath = new File(Paths.get(".").toAbsolutePath().normalize().toString() + AREA_FILE);

    StreetBuilder streetBuilder = StreetBuilderImpl.getInstance();
    ContainerBuilder containerBuilder = ContainerBuilderImpl.getInstance();
    VehicleBuilder vehicleBuilder = VehicleBuilderImpl.getInstance();

    StreetDirector streetService = new StreetDirector(streetBuilder);
    ContainerDirector containerService = new ContainerDirector(containerBuilder);
    VehicleDirector vehicleService = new VehicleDirector(vehicleBuilder);

    private String line = null;

    Logger logger = Logger.getLogger(FileWorker.class.getName());

    public FileWorker() {
    }

    public void getStreetsData() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(streetsFilePath), CHARSET));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                streetService.makeStreet(line);
            }
        } catch (IOException ex) {
            logger.log(Level.CONFIG, "Error reading the file - {0}", ex);
        }
    }

    public void getContainersData() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(containersFilePath), CHARSET));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                containerService.makeContainer(line);
            }
        } catch (IOException ex) {
            logger.log(Level.CONFIG, "Error reading the file - {0}", ex);
        }
    }

    public void getVehiclesData() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(vehiclesFilePath), CHARSET));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                vehicleService.makeVehicle(line);
            }
        } catch (IOException ex) {
            logger.log(Level.CONFIG, "Error reading the file - {0}", ex);
        }
    }

    public void getAreasData() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(areasFilePath), CHARSET));
            reader.readLine();
            AreaComponent areaComposite = null;
            while ((line = reader.readLine()) != null) {
                areaComposite = new AreaComposite();
                areaComposite.makeAreas(line);
            }
            areaComposite.setSubAreas();
        } catch (IOException ex) {
            logger.log(Level.CONFIG, "Error reading the file - {0}", ex);
        }
    }

}
