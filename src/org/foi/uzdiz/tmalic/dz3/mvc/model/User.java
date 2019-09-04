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
import org.foi.uzdiz.tmalic.dz3.utils.Constants;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.Vehicles.GarbageType;

/**
 *
 * @author tadij
 */
public class User {

    private int id;
    private static final AtomicInteger usersCounter = new AtomicInteger(0);
    private Map<Integer, Constants.ShareCategory> consumerType = new HashMap<>();
    private int consumerTypeKey;
    private int group;
    public List<Container> containers = new ArrayList<>();
    public StreetLeaf street;
    public Map<GarbageType, Float> garbageQuantityMap = new HashMap<>();
    public static List<User> allUsers = new ArrayList<>();

    public User(StreetLeaf street, int consumerTypeKey, Map<GarbageType, Float> garbageQuantityMap) {
        id = usersCounter.incrementAndGet();
        this.street = street;
        this.consumerTypeKey = consumerTypeKey;
        this.garbageQuantityMap = garbageQuantityMap;
        allUsers.add(this);
    }

    public User() {
        id = usersCounter.incrementAndGet();
        allUsers.add(this);
    }

    public void setConsumerTypeKey(int type) {
        this.consumerTypeKey = type;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setContainers(Container container) {
        this.containers.add(container);
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Constants.ShareCategory> getConsumerType() {
        return consumerType;
    }

    public int getConsumerTypeKey() {
        return consumerTypeKey;
    }

    public int getGroup() {
        return group;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public StreetLeaf getStreet() {
        return street;
    }

    public Map<GarbageType, Float> getGarbageQuantityMap() {
        return garbageQuantityMap;
    }
}
