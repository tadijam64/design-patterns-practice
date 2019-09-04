/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.state.driverStates;

import org.foi.uzdiz.tmalic.dz3.state.Driver;
import org.foi.uzdiz.tmalic.dz3.state.DriverState;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;

/**
 *
 * @author tadij
 */
public class DriverWorking implements DriverState {

    Driver driver;

    public DriverWorking(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void setBolovanje() {
        formatOutput(driver.getName() + " is going to sickleave.");
    }

    @Override
    public void setGodisnjiOdmor() {
        formatOutput(driver.getName() + " is going to vacation.");
    }

    @Override
    public void setOtkaz() {
        formatOutput(driver.getName() + " is fired.");
    }

    @Override
    public void setPreuzmi() {
        formatOutput(driver.getName() + " is already working with another vehicle.");
    }

    @Override
    public void setNovi() {
        formatOutput(driver.getName() + " is already employed.");
    }

}
