/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.buider.impl;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.tmalic.dz3.state.Driver;
import org.foi.uzdiz.tmalic.dz3.mvc.model.factory.GlassVehicle;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;
import org.foi.uzdiz.tmalic.dz3.mvc.model.factory.BioVehicle;
import org.foi.uzdiz.tmalic.dz3.mvc.model.factory.MetalVehicle;
import org.foi.uzdiz.tmalic.dz3.mvc.model.factory.MixedVehicle;
import org.foi.uzdiz.tmalic.dz3.mvc.model.factory.PaperVehicle;
import org.foi.uzdiz.tmalic.dz3.service.buider.VehicleBuilder;

/**
 *
 * @author tadij
 */
public class VehicleBuilderImpl implements VehicleBuilder {

    private Vehicle vehicle = null;
    private List<Vehicle> vehicles = new ArrayList<>();
    private static VehicleBuilderImpl vehicleBuilderImpl = null;

    private VehicleBuilderImpl() {
    }

    @Override
    public void buildName(String name) {
        vehicle.setName(name);
    }

    @Override
    public void buildConsumptionType(int type) {
        vehicle.setConsumptionCategory(type);
    }

    @Override
    public void buildVehicleType(int type) {
        setInstance(type);
        vehicle.setVehicleCategory(type);
    }

    @Override
    public void buildCapacity(Float capacity) {
        vehicle.setCapacity(capacity);
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public static VehicleBuilderImpl getInstance() {
        if (vehicleBuilderImpl == null) {
            vehicleBuilderImpl = new VehicleBuilderImpl();
        }
        return vehicleBuilderImpl;
    }

    public void buildVehicles() {
        vehicles.add(vehicle);
        this.vehicle = null;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    private void setInstance(int type) {
        switch (type) {
            case 0:
                this.vehicle = new GlassVehicle();
                break;
            case 1:
                this.vehicle = new PaperVehicle();
                break;
            case 2:
                this.vehicle = new MetalVehicle();
                break;
            case 3:
                this.vehicle = new BioVehicle();
                break;
            case 4:
                this.vehicle = new MixedVehicle();
                break;
            default:
                break;
        }
    }

    @Override
    public void buildId(String id) {
        vehicle.setId(id);
    }

    @Override
    public void buildDrivers(String[] drivers) {
        vehicle.setDriversNames(drivers);
        for (String name : drivers) {
            Driver driver = new Driver(name, vehicle);
            vehicle.addDriver(driver);
        }
    }

}
