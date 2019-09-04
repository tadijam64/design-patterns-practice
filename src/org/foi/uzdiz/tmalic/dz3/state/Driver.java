/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.state;

import org.foi.uzdiz.tmalic.dz3.state.driverStates.DriverReady;
import org.foi.uzdiz.tmalic.dz3.state.driverStates.DriverFired;
import org.foi.uzdiz.tmalic.dz3.state.driverStates.DriverOnSickLeave;
import org.foi.uzdiz.tmalic.dz3.state.driverStates.DriverWorking;
import org.foi.uzdiz.tmalic.dz3.state.driverStates.DriverOnVacation;
import java.util.ArrayList;

/**
 *
 * @author tadij
 */
public class Driver {

    private String name;
    private Boolean employed = true;
    private Vehicle vehicle;

    DriverState ready;
    DriverState working;
    DriverState onVacation;
    DriverState sick;
    DriverState fired;

    DriverState driverState;

    public static ArrayList<Driver> drivers = new ArrayList<>();

    public Driver(String name, Vehicle vehicle) {
        this.name = name;
        this.vehicle = vehicle;
        ready = new DriverReady(this);
        working = new DriverWorking(this);
        onVacation = new DriverOnVacation(this);
        sick = new DriverOnSickLeave(this);
        fired = new DriverFired(this);
        drivers.add(this);
    }

    public Driver(String ime) {
        this.name = ime;
        drivers.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEmployed() {
        return employed;
    }

    public void setEmployed(Boolean employed) {
        this.employed = employed;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DriverState getDriverState() {
        return driverState;
    }

    public void setDriverState(DriverState driverState) {
        this.driverState = driverState;
    }

    public void setGodisnjiOdmor() {
        driverState.setGodisnjiOdmor();
    }

    public void setBolovanje() {
        driverState.setBolovanje();
    }

    public void setOtkaz() {
        driverState.setOtkaz();
    }

    public void setNovi() {
        driverState.setNovi();
    }

    public DriverState getReady() {
        return ready;
    }

    public DriverState getWorking() {
        return working;
    }

    public DriverState getOnVacation() {
        return onVacation;
    }

    public DriverState getSick() {
        return sick;
    }

    public DriverState getFired() {
        return fired;
    }

    public String getDriverStateString() {
        if (this.getDriverState() instanceof DriverFired) {
            return "Fired";
        } else if (this.getDriverState() instanceof DriverOnSickLeave) {
            return "Sickleave";
        } else if (this.getDriverState() instanceof DriverOnVacation) {
            return "Vacation";
        } else if (this.getDriverState() instanceof DriverReady) {
            return "Ready to drive";
        } else if (this.getDriverState() instanceof DriverWorking) {
            return "Currently working";
        } else {
            return "No status yet";
        }
    }

}
