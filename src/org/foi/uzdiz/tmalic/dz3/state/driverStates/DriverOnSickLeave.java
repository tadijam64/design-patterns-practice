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
public class DriverOnSickLeave implements DriverState {

    Driver driver;

    public DriverOnSickLeave(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void setBolovanje() {
        formatOutput("Employer " + driver.getName() + " is on sickleave");
    }

    @Override
    public void setGodisnjiOdmor() {
        formatOutput("Employer " + driver.getName() + " is on vacation");
    }

    @Override
    public void setOtkaz() {
        formatOutput("Employer " + driver.getName() + " is fired");
    }

    @Override
    public void setPreuzmi() {
        formatOutput("Employer " + driver.getName() + " is on sickleave, so he can't take another vehicle");
    }

    @Override
    public void setNovi() {
        formatOutput("Employer " + driver.getName() + " is set to sickleave");
    }

}
