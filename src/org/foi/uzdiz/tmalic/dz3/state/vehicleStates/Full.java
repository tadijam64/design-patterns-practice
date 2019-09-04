/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.state.vehicleStates;

import org.foi.uzdiz.tmalic.dz3.state.Vehicle;
import org.foi.uzdiz.tmalic.dz3.state.VehicleState;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;

/**
 *
 * @author tadij
 */
public class Full implements VehicleState {

    Vehicle vehicle;

    public Full(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void setIsprazni() {
        formatOutput(vehicle.getName() + " is going to landfill.");
    }

    @Override
    public void setIzlaz() {
        formatOutput("The program is shutting down.");
    }

    @Override
    public void setKontrola() {
        formatOutput(vehicle.getName() + " is going to control.");
    }

    @Override
    public void setKreni() {
        formatOutput(vehicle.getName() + " is full.");
    }

    @Override
    public void setKvar() {
        formatOutput(vehicle.getName() + " is full.");
    }

    @Override
    public void setObradi() {
        formatOutput(vehicle.getName() + " is full.");
    }

    @Override
    public void setPripremi() {
        formatOutput(vehicle.getName() + " is full.");
    }

    @Override
    public void setStatus() {
        formatOutput("");
    }

}
