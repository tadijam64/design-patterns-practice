/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.chainOfResponsibility;

/**
 *
 * @author tadij
 */
public interface CommandChain {

    public void setNextChain(CommandChain nextChain);

    public void execute(String command);
}
