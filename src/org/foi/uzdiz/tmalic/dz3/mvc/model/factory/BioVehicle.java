/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.mvc.model.factory;

import org.foi.uzdiz.tmalic.dz3.state.Vehicle;

/**
 *
 * @author tadij
 */
public class BioVehicle extends Vehicle {

    private Float capacity;
    private Float occupied;
    private Float free = 0f;

    public BioVehicle() {
        this.capacity = 0f;
        this.occupied = 0f;
        this.free = capacity - occupied;
        allVehicles.add(this);
    }

    @Override
    public void setConsumptionCategory(int consumptionCategory) {
        super.setConsumptionCategory(consumptionCategory); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        super.setName(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVehicleCategory(int vehicleCategory) {
        super.setVehicleCategory(vehicleCategory); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float getOccupied() {
        return occupied;
    }

    @Override
    public void addOccupied(Float occupied) {
        this.occupied += occupied;
        setFree();
    }

    @Override
    public Float getFree() {
        return capacity - occupied;
    }

    @Override
    public void setFree() {
        this.free = capacity - occupied;
    }

    @Override
    public Float getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    @Override
    public void setOccupiedToCapacity(Float capacity) {
        this.occupied = capacity;
        this.free = 0f;
    }

    @Override
    public void setOccupied(float f) {
        this.occupied = f;
    }

}
