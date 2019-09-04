/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.buider;

import java.util.List;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;

/**
 *
 * @author tadij
 */
public interface VehicleBuilder {

    public void buildName(String name);

    public void buildConsumptionType(int type);

    public void buildVehicleType(int type);

    public void buildCapacity(Float capacity);

    public void buildDrivers(String[] drivers);

    public Vehicle getVehicle();

    public List<Vehicle> getVehicles();

    public void buildVehicles();

    public void buildId(String id);
}
