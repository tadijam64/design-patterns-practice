/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.state;

import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;

/**
 *
 * @author tadij
 */
public class CollectingGarbage implements VehicleState {

    Vehicle vehicle;

    public CollectingGarbage(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void setIsprazni() {
        formatOutput("Vehicle " + vehicle.getName() + "is going to landfill");
        vehicle.setVehicleState(vehicle.getWaiting());
    }

    @Override
    public void setIzlaz() {
        formatOutput("Stoping garbage collection.");
    }

    @Override
    public void setKontrola() {
        formatOutput("Can't call Kontrola command while vehicle is collecting garbage.");
    }

    @Override
    public void setKreni() {
        formatOutput("There is already one active garbage collecting.");
    }

    @Override
    public void setKvar() {
        formatOutput("Vehicles are in un");
    }

    @Override
    public void setObradi() {
        formatOutput("Vehicle " + vehicle.getName() + "is set for its working area.");
    }

    @Override
    public void setPripremi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
