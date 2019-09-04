/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.mvc.model;

import org.foi.uzdiz.tmalic.dz3.service.composite.StreetLeaf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.Containers.ContainerType;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory;

/**
 *
 * @author tadij
 */
public class Container {

    private static int counter;
    private int id;
    private String name;
    private static final AtomicInteger containersCounter = new AtomicInteger(0);
    private Map<Integer, ContainerType> containerTypeMap = new HashMap<>();
    private String containerType;
    private Map<ShareCategory, Integer> share;
    private int capacity;
    public User user;
    public StreetLeaf street;
    public static List<Container> allContainers = new ArrayList<>();

    public Container() {
        this.id = containersCounter.incrementAndGet();
        containerTypeMap.put(1, ContainerType.CONTAINER);
        containerTypeMap.put(0, ContainerType.TRASH_CAN);
        counter++;
        allContainers.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(int containerType) {
        this.containerType = containerTypeMap.get(containerType).name();
    }

    public Map<ShareCategory, Integer> getShare() {
        return share;
    }

    public void setShare(Map<ShareCategory, Integer> share) {
        this.share = share;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, ContainerType> getContainerTypeMap() {
        return containerTypeMap;
    }

    public static int getCounter() {
        return counter;
    }

}
