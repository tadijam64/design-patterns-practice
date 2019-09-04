/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.state;

import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.OnControlCheck;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.OnLandfill;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.Waiting;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.Full;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.Inoperative;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.ReadyToCollect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.foi.uzdiz.tmalic.dz3.service.composite.AreaComposite;
import org.foi.uzdiz.tmalic.dz3.state.vehicleStates.Refill;
import org.foi.uzdiz.tmalic.dz3.utils.Constants;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.Vehicles.ConsumptionType;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.Vehicles.GarbageType;

/**
 *
 * @author tadij
 */
public abstract class Vehicle {

    private String id;
    private String name;
    private Map<Integer, ConsumptionType> consumptionCategoryMap = new HashMap<>();
    private String consumptionCategory;
    private Map<Integer, GarbageType> vehicleCategoryMap = new HashMap<>();
    private String vehicleCategory;
    private Float capacity;
    private Float occupied;
    private Float free = 0f;
    private String[] driversNames;
    public static List<Vehicle> allVehicles = new ArrayList<>();
    private int localCounter;
    private int numberOfContainersVehiclePass = 0;
    private int numberOfStreetsVehiclePass = 0;
    private int numberOfTimesVehicleWentToUnloading = 1;
    private int localCounterForRefueling = 0;
    private int waitingOnDriver = 0;
    private int refillingTimes = 0;
    private int timesDriverChanged = 0;
    private Driver mainDriver;

    VehicleState full;
    VehicleState collectingGarbage;
    VehicleState waiting;
    VehicleState inoperative;
    VehicleState onLandfill;
    VehicleState readyToCollect;
    VehicleState onControlCheck;
    VehicleState onRefueling;

    VehicleState vehicleState;
    AreaComposite workingArea;

    ArrayList<Driver> drivers = new ArrayList<>();

    public Vehicle() {
        consumptionCategoryMap.put(1, Constants.Vehicles.ConsumptionType.ELECTRIC);
        consumptionCategoryMap.put(0, Constants.Vehicles.ConsumptionType.DISEL);
        vehicleCategoryMap.put(0, Constants.Vehicles.GarbageType.GLASS);
        vehicleCategoryMap.put(1, Constants.Vehicles.GarbageType.PAPER);
        vehicleCategoryMap.put(2, Constants.Vehicles.GarbageType.METAL);
        vehicleCategoryMap.put(3, Constants.Vehicles.GarbageType.ORGANIC);
        vehicleCategoryMap.put(4, Constants.Vehicles.GarbageType.MIXED);
        this.capacity = 0f;
        this.occupied = 0f;
        this.free = capacity - occupied;
        this.localCounter = 0;
        full = new Full(this);
        collectingGarbage = new CollectingGarbage(this);
        waiting = new Waiting(this);
        inoperative = new Inoperative(this);
        onLandfill = new OnLandfill(this);
        readyToCollect = new ReadyToCollect(this);
        onControlCheck = new OnControlCheck(this);
        onRefueling = new Refill(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsumptionCategory() {
        return consumptionCategory;
    }

    public void setConsumptionCategory(int consumptionCategory) {
        this.consumptionCategory = consumptionCategoryMap.get(consumptionCategory).name();
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(int vehicleCategory) {
        this.vehicleCategory = vehicleCategoryMap.get(vehicleCategory).name();
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    public String[] getDriversNames() {
        return driversNames;
    }

    public void setDriversNames(String[] drivers) {
        this.driversNames = drivers;
    }

    public Map<Integer, ConsumptionType> getConsumptionCategoryMap() {
        return consumptionCategoryMap;
    }

    public void setConsumptionCategoryMap(Map<Integer, ConsumptionType> consumptionCategoryMap) {
        this.consumptionCategoryMap = consumptionCategoryMap;
    }

    public Map<Integer, GarbageType> getVehicleCategoryMap() {
        return vehicleCategoryMap;
    }

    public void setVehicleCategoryMap(Map<Integer, GarbageType> vehicleCategoryMap) {
        this.vehicleCategoryMap = vehicleCategoryMap;
    }

    public Float getOccupied() {
        return occupied;
    }

    public void addOccupied(Float occupied) {
        this.occupied += occupied;
        setFree();
    }

    public Float getFree() {
        return capacity - occupied;
    }

    public void setFree() {
        this.free = this.capacity - this.occupied;
    }

    public void setOccupiedToCapacity(Float capacity) {
        this.occupied = capacity;
        this.free = 0f;
    }

    public void setOccupied(float f) {
        this.occupied = f;
    }

    /**
     * @return the localCounter
     */
    public int getLocalCounter() {
        return localCounter;
    }

    /**
     * @param localCounter the localCounter to set
     */
    public void setLocalCounter(int localCounter) {
        this.localCounter = localCounter;
    }

    public int getNumberPassedCont() {
        return numberOfContainersVehiclePass;
    }

    public void incrementNumberOfContainersVehiclePass() {
        this.numberOfContainersVehiclePass++;
    }

    public int getNumberOfStreetsVehiclePass() {
        return numberOfStreetsVehiclePass;
    }

    public void setNumberOfStreetsVehiclePass(int numberOfStreetsVehiclePass) {
        this.numberOfStreetsVehiclePass = numberOfStreetsVehiclePass;
    }

    public int getNumberOfUnloadings() {
        return numberOfTimesVehicleWentToUnloading;
    }

    public void incrementNumberOfTimesVehicleWentToUnloading() {
        this.numberOfTimesVehicleWentToUnloading++;
    }

    public String getId() {
        return id;
    }

    public int getNumberOfContainersVehiclePass() {
        return numberOfContainersVehiclePass;
    }

    public int getNumberOfTimesVehicleWentToUnloading() {
        return numberOfTimesVehicleWentToUnloading;
    }

    public VehicleState getFull() {
        return full;
    }

    public VehicleState getCollectingGarbage() {
        return collectingGarbage;
    }

    public VehicleState getWaiting() {
        return waiting;
    }

    public VehicleState getInoperative() {
        return inoperative;
    }

    public VehicleState getOnLandfill() {
        return onLandfill;
    }

    public VehicleState getReadyToCollect() {
        return readyToCollect;
    }

    public VehicleState getOnControlCheck() {
        return onControlCheck;
    }

    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsprazni() {
        vehicleState.setIsprazni();
    }

    public void setIzlaz() {
        vehicleState.setIzlaz();
    }

    public void setKontrola() {
        vehicleState.setKontrola();
    }

    public void setKreni() {
        vehicleState.setKreni();
    }

    public void setKvar() {
        vehicleState.setKvar();
    }

    public void setObradi() {
        vehicleState.setObradi();
    }

    public void setPripremi() {
        vehicleState.setPripremi();
    }

    public void setStatus() {
        vehicleState.setStatus();
    }

    public void setVehicleState(VehicleState newVehicleState) {
        this.vehicleState = newVehicleState;
    }

    public AreaComposite getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(AreaComposite workingArea) {
        this.workingArea = workingArea;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
        this.setMainDriver(drivers.get(0));
    }

    public String getVehicleStateString() {
        if (this.getVehicleState() instanceof CollectingGarbage) {
            return "Collecting garbage";
        } else if (this.getVehicleState() instanceof OnLandfill) {
            return "On landfill";
        } else if (this.getVehicleState() instanceof Inoperative) {
            return "Inoperative";
        } else if (this.getVehicleState() instanceof ReadyToCollect) {
            return "Ready to collect";
        } else if (this.getVehicleState() instanceof Waiting) {
            return "Waiting after unloading";
        } else {
            return "No status yet";
        }
    }

    public void increaseLocalCounterForRefueling() {
        localCounterForRefueling++;
    }

    public int getLocalCounterForRefueling() {
        return localCounterForRefueling;
    }

    public void setLocalCounterForRefueling(int localCounterForRefueling) {
        this.localCounterForRefueling = localCounterForRefueling;
    }

    public VehicleState getOnRefueling() {
        return onRefueling;
    }

    public void setOnRefueling(VehicleState onRefueling) {
        this.onRefueling = onRefueling;
    }

    public int getWaitingOnDriver() {
        return waitingOnDriver;
    }

    public void setWaitingOnDriver(int waitingOnDriver) {
        this.waitingOnDriver = waitingOnDriver;
    }

    public void increaseTimeWaitingOnDriver() {
        this.waitingOnDriver++;
    }

    public int getRefillingTimes() {
        return refillingTimes;
    }

    public void setRefillingTimes(int refillingTimes) {
        this.refillingTimes = refillingTimes;
    }

    public int getTimesDriverChanged() {
        return timesDriverChanged;
    }

    public void setTimesDriverChanged(int timesDriverChanged) {
        this.timesDriverChanged = timesDriverChanged;
    }

    public void increaseRefillingTimes() {
        this.refillingTimes++;
    }

    public void increaseTimesDriverChanged() {
        this.timesDriverChanged++;
    }

    public Driver getMainDriver() {
        return mainDriver;
    }

    public void setMainDriver(Driver mainDriver) {
        this.mainDriver = mainDriver;
    }

}
