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
public class Inoperative implements VehicleState {

    Vehicle vehicle;

    public Inoperative(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void setIsprazni() {
        formatOutput(vehicle.getName() + " is inoperative.");
    }

    @Override
    public void setIzlaz() {
        formatOutput("the program is shutting down.");
    }

    @Override
    public void setKontrola() {
        formatOutput(vehicle.getName() + " is going on control.");
    }

    @Override
    public void setKreni() {
        formatOutput(vehicle.getName() + " is inoperative.");
    }

    @Override
    public void setKvar() {
        formatOutput(vehicle.getName() + " is already inoperative.");
    }

    @Override
    public void setObradi() {
        formatOutput(vehicle.getName() + " is inoperative.");
    }

    @Override
    public void setPripremi() {
        formatOutput(vehicle.getName() + " is inoperative.");
    }

    @Override
    public void setStatus() {
        formatOutput("");
    }
}
