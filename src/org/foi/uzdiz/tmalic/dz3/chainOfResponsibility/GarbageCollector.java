/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.foi.uzdiz.tmalic.dz3.chainOfResponsibility.concreteChain.CommandPripremi.sortedVehicles;
import org.foi.uzdiz.tmalic.dz3.configuration.InitialConfiguration;
import org.foi.uzdiz.tmalic.dz3.service.composite.StreetLeaf;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;
import org.foi.uzdiz.tmalic.dz3.service.buider.ContainerBuilder;
import org.foi.uzdiz.tmalic.dz3.service.buider.StreetBuilder;
import org.foi.uzdiz.tmalic.dz3.service.buider.VehicleBuilder;
import org.foi.uzdiz.tmalic.dz3.service.buider.impl.ContainerBuilderImpl;
import org.foi.uzdiz.tmalic.dz3.service.buider.impl.StreetBuilderImpl;
import org.foi.uzdiz.tmalic.dz3.service.buider.impl.VehicleBuilderImpl;
import org.foi.uzdiz.tmalic.dz3.service.composite.AreaComposite;
import org.foi.uzdiz.tmalic.dz3.state.Driver;
import org.foi.uzdiz.tmalic.dz3.state.driverStates.DriverReady;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.OnLandfill;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.ReadyToCollect;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.GarbageType.PAPER;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.GarbageType.GLASS;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.GarbageType.METAL;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.GarbageType.MIXED;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.GarbageType.ORGANIC;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.BILO;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.BROJ;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.KAPACITET_DIZEL_VOZILA;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.KAPACITET_ELEKTRO_VOZILA;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.OBISLO;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.OTPADA;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.PRIDRUZENO;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.PUNJENJE_DIZEL_VOZILA;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.PUNJENJE_ELEKTRO_VOZILA;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.SLOBODNO;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.UKUPNO;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.UKUPNO_CONTAINERA;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.VOZILO;

/**
 *
 * @author tadij
 */
public class GarbageCollector {

    private Float garbageQuantityInStreetGlass = 1f;
    private Float garbageQuantityInStreetPaper = 1f;
    private Float garbageQuantityInStreetMetal = 1f;
    private Float garbageQuantityInStreetOrganic = 1f;
    private Float garbageQuantityInStreetMixed = 1f;

    StreetBuilder streetBuilder = StreetBuilderImpl.getInstance();
    ContainerBuilder containerBuilder = ContainerBuilderImpl.getInstance();
    VehicleBuilder vehicleBuilder = VehicleBuilderImpl.getInstance();

    private List<Vehicle> allVehicles = new ArrayList<>();
    private static int cycleCounter = 0;
    public static int finalCycleNumber = 0;

    public void startCollecting() {
        setListOfVehicles();
        if (finalCycleNumber == 0) {
            while (isThereStillGarbage()) {
                doGarbageCollecting();
            }
        } else {
            while (cycleCounter < finalCycleNumber) {
                doGarbageCollecting();
            }
        }
        sendVehiclesToUnload();
    }

    private void setListOfVehicles() {
        allVehicles = this.vehicleBuilder.getVehicles();
        allVehicles.sort(Comparator.comparing(sortedVehicles::indexOf));
    }

    private boolean isThereStillGarbage() {
        Boolean pom = garbageQuantityInStreetGlass > 0 || garbageQuantityInStreetPaper > 0 || garbageQuantityInStreetMetal > 0;
        return pom || garbageQuantityInStreetOrganic > 0 || garbageQuantityInStreetMixed > 0;
    }

    private void doGarbageCollecting() {
        for (Vehicle currentVehicle : allVehicles) {
            if (checkIsVehicleReadyToCollect(currentVehicle)) {
                collectAllGarbage(currentVehicle);
            }
        }
    }

    private void collectAllGarbage(Vehicle currentVehicle) {
        AreaComposite vehiclesWorkingArea = currentVehicle.getWorkingArea();
        for (AreaComposite subarea : vehiclesWorkingArea.getSubAreas()) {
            for (StreetLeaf currentStreet : subarea.getStreets()) {
                getGarbageQuantityFromStreet(currentStreet);
                if (checkIsVehicleReadyToCollect(currentVehicle)) {
                    pickUpGarbageFromCurrentStreet(currentVehicle);
                }
            }
        }
        if (vehiclesWorkingArea.getSubAreas().isEmpty()) {
            for (StreetLeaf currentStreet : vehiclesWorkingArea.getStreets()) {
                getGarbageQuantityFromStreet(currentStreet);
                if (checkIsVehicleReadyToCollect(currentVehicle)) {
                    pickUpGarbageFromCurrentStreet(currentVehicle);
                }
            }
        }

    }

    private boolean checkIsVehicleReadyToCollect(Vehicle currentVehicle) {
        cycleCounter++;
        return currentVehicle.getVehicleState() instanceof ReadyToCollect;
    }

    private void getGarbageQuantityFromStreet(StreetLeaf currentStreet) {
        garbageQuantityInStreetGlass = currentStreet.getGarbageInStreet().get(GLASS);
        garbageQuantityInStreetPaper = currentStreet.getGarbageInStreet().get(PAPER);
        garbageQuantityInStreetMetal = currentStreet.getGarbageInStreet().get(METAL);
        garbageQuantityInStreetOrganic = currentStreet.getGarbageInStreet().get(ORGANIC);
        garbageQuantityInStreetMixed = currentStreet.getGarbageInStreet().get(MIXED);
        streetBuilder.setGarbageForAllStreets();
    }

    private void pickUpGarbageFromCurrentStreet(Vehicle currentVehicle) {
        if (currentVehicle.getVehicleCategory().equals(GLASS)) {
            pickingGarbageAlgorithm(currentVehicle, garbageQuantityInStreetGlass);
        } else if (currentVehicle.getVehicleCategory().equals(PAPER)) {
            pickingGarbageAlgorithm(currentVehicle, garbageQuantityInStreetPaper);
        } else if (currentVehicle.getVehicleCategory().equals(METAL)) {
            pickingGarbageAlgorithm(currentVehicle, garbageQuantityInStreetMetal);
        } else if (currentVehicle.getVehicleCategory().equals(ORGANIC)) {
            pickingGarbageAlgorithm(currentVehicle, garbageQuantityInStreetOrganic);
        } else if (currentVehicle.getVehicleCategory().equals(MIXED)) {
            pickingGarbageAlgorithm(currentVehicle, garbageQuantityInStreetMixed);
        }
    }

    private void pickingGarbageAlgorithm(Vehicle currentVehicle, Float garbageQuantityInStreet) {
        if (garbageQuantityInStreet > 0) {
            currentVehicle.addOccupied(garbageQuantityInStreet);
            currentVehicle.incrementNumberOfContainersVehiclePass();
            if (currentVehicle.getOccupied() < currentVehicle.getCapacity()) {
                formatOutput(PRIDRUZENO + currentVehicle.getVehicleCategory() + ", količine: " + garbageQuantityInStreet);
                formatOutput(VOZILO + currentVehicle.getName() + UKUPNO + currentVehicle.getOccupied() + SLOBODNO + currentVehicle.getFree());
                garbageQuantityInStreet = 0f;
            } else {
                garbageQuantityInStreet = currentVehicle.getOccupied() - currentVehicle.getCapacity();
                currentVehicle.setOccupiedToCapacity(currentVehicle.getCapacity());
                formatOutput(PRIDRUZENO + currentVehicle.getVehicleCategory() + ", količine: " + currentVehicle.getOccupied());
                formatOutput(OTPADA + currentVehicle.getVehicleCategory() + " je ostalo: " + garbageQuantityInStreet);
                formatOutput(VOZILO + currentVehicle.getName() + UKUPNO + currentVehicle.getOccupied() + SLOBODNO + currentVehicle.getFree());
                currentVehicle.setVehicleState(currentVehicle.getOnLandfill());
                currentVehicle.incrementNumberOfTimesVehicleWentToUnloading();
                formatOutput("Vozilo: " + currentVehicle.getName() + " odlazi na mjesto za zbrinjavanje otpada.");
                currentVehicle.setLocalCounter(cycleCounter);
            }
        }
        cycleCounter += 1;
        currentVehicle.increaseLocalCounterForRefueling();
        checkRefillNeeds(currentVehicle);
        setGarbageQuantityToItsType(currentVehicle, garbageQuantityInStreet);
        checkIfVehiclesDidUnloading();
    }

    private void checkRefillNeeds(Vehicle currentVehicle) {
        if (currentVehicle.getVehicleCategory().equals("dizel")) {
            if (currentVehicle.getLocalCounterForRefueling() >= KAPACITET_DIZEL_VOZILA) {
                currentVehicle.setVehicleState(currentVehicle.getOnRefueling());
                currentVehicle.setLocalCounterForRefueling(0);
                if (currentVehicle.getLocalCounterForRefueling() >= PUNJENJE_DIZEL_VOZILA) {
                    currentVehicle.setVehicleState(currentVehicle.getReadyToCollect());
                }
                changeDriver(currentVehicle);
            }
        } else {
            if (currentVehicle.getLocalCounterForRefueling() >= KAPACITET_ELEKTRO_VOZILA) {
                currentVehicle.setVehicleState(currentVehicle.getOnRefueling());
                currentVehicle.setLocalCounterForRefueling(0);
                if (currentVehicle.getLocalCounterForRefueling() >= PUNJENJE_ELEKTRO_VOZILA) {
                    currentVehicle.setVehicleState(currentVehicle.getReadyToCollect());
                }
                changeDriver(currentVehicle);
            }
        }
        currentVehicle.increaseRefillingTimes();
    }

    private void changeDriver(Vehicle currentVehicle) {
        if (currentVehicle.getDrivers().size() > 0) {
            currentVehicle.getDrivers().remove(0);
        } else {
            formatOutput("Vozilo " + currentVehicle.getName() + " čeka na dodjelu vozača");
            currentVehicle.increaseTimeWaitingOnDriver();
        }
        for (Driver driver : currentVehicle.getDrivers()) {
            if (driver.getDriverState().getClass().equals(DriverReady.class)) {
                currentVehicle.setMainDriver(driver);
                currentVehicle.increaseTimesDriverChanged();
            }
        }
    }

    private void setGarbageQuantityToItsType(Vehicle currentVehicle, Float garbageQuantityInStreet) {
        switch (currentVehicle.getVehicleCategory()) {
            case GLASS:
                garbageQuantityInStreetGlass = garbageQuantityInStreet;
                break;
            case PAPER:
                garbageQuantityInStreetPaper = garbageQuantityInStreet;
                break;
            case METAL:
                garbageQuantityInStreetMetal = garbageQuantityInStreet;
                break;
            case ORGANIC:
                garbageQuantityInStreetOrganic = garbageQuantityInStreet;
                break;
            case MIXED:
                garbageQuantityInStreetMixed = garbageQuantityInStreet;
                break;
            default:
                break;
        }
    }

    private void checkIfVehiclesDidUnloading() {
        for (Vehicle currentVehicle : allVehicles) {
            if (currentVehicle.getVehicleState() instanceof OnLandfill) {
                int skippingCycles = Integer.parseInt(InitialConfiguration.prop.get("brojRadnihCiklusaZaOdvoz").toString());
                if (currentVehicle.getLocalCounter() + skippingCycles <= cycleCounter) {
                    currentVehicle.setOccupied(0f);
                    currentVehicle.setVehicleState(currentVehicle.getReadyToCollect());
                }
            }
        }
    }

    private void sendVehiclesToUnload() {
        for (Vehicle currentVehicle : allVehicles) {
            if (currentVehicle.getOccupied() != 0f) {
                currentVehicle.setOccupied(0f);
                currentVehicle.setVehicleState(currentVehicle.getReadyToCollect());
            }
        }
    }

    public void statisticDataAboutGarbage() {
        formatOutput("\n");
        formatOutput("Statistika : ");
        formatOutput("Ukupna količina otpada vrste GLASS na odlagalištu je " + getGarbage(0));
        formatOutput("Ukupna količina otpada vrste PAPER na odlagalištu je " + getGarbage(1));
        formatOutput("Ukupna količina otpada vrste METAL na odlagalištu je " + getGarbage(2));
        formatOutput("Ukupna količina otpada vrste BIO na odlagalištu je " + getGarbage(3));
        formatOutput("Ukupna količina otpada vrste MIXED na odlagalištu je " + getGarbage(4));

        formatOutput("\n");
        Vehicle.allVehicles.forEach((currentVehicle) -> {
            formatOutput(UKUPNO_CONTAINERA + currentVehicle.getName().trim() + " je " + currentVehicle.getNumberPassedCont());
            formatOutput(BROJ + currentVehicle.getName().trim() + BILO + currentVehicle.getNumberOfUnloadings());
        });
        formatOutput("\n");
        formatOutput(OBISLO + StreetLeaf.allStreets.size());
        showStatisticAboutVehicleWaitingTime();
    }

    private Float getGarbage(int type) {
        return streetBuilder.getGarbageForAllStreets()[type];
    }

    private void showStatisticAboutVehicleWaitingTime() {
        for (Vehicle currentVehicle : Vehicle.allVehicles) {
            formatOutput("Vozilo " + currentVehicle.getName() + "je čekalo " + currentVehicle.getWaitingOnDriver() + " puta.");
            formatOutput("Vozilo " + currentVehicle.getName() + "se punilo " + currentVehicle.getRefillingTimes() + " puta.");
            formatOutput("Vozilo " + currentVehicle.getName() + "je mijenjalo vozača " + currentVehicle.getTimesDriverChanged() + " puta.");
        }
    }

}
