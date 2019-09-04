/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.buider;

import java.util.List;
import org.foi.uzdiz.tmalic.dz3.mvc.model.Container;

/**
 *
 * @author tadij
 */
public interface ContainerBuilder {

    public void buildName(String name);

    public void buildContainerType(int type);

    public void buildShare(int smallShare, int mediumShare, int bigShare);

    public void buildCapacity(int capacity);

    public Container getContainer();

    public List<Container> getContainers();

    public void buildContainers();

    public void addContainerToUsers();

    public int getNumberOfContainers();

}
