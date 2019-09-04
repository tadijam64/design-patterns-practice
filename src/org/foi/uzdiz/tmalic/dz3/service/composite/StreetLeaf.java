/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.foi.uzdiz.tmalic.dz3.mvc.model.Container;
import org.foi.uzdiz.tmalic.dz3.mvc.model.User;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory;

/**
 *
 * @author tadij
 */
public class StreetLeaf extends AreaComponent {

    private String id;
    private String name;
    private int spotNumber;
    private Map<ShareCategory, Integer> share = new HashMap<>();
    private List<User> users = new ArrayList<>();
    private List<Container> containers = new ArrayList<>();
    Map<String, Float> garbageInStreet = new LinkedHashMap<>();
    private Float sumOfGarbageInStreet;
    public static List<StreetLeaf> allStreets = new ArrayList<>();
    private int containerNumber = 0;

    public StreetLeaf() {
        garbageInStreet.put("GLASS", 0f);
        garbageInStreet.put("PAPER", 0f);
        garbageInStreet.put("METAL", 0f);
        garbageInStreet.put("ORGANIC", 0f);
        garbageInStreet.put("MIXED", 0f);
        allStreets.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public void setShare(Map<ShareCategory, Integer> share) {
        this.share = share;
    }

    public String getStreetName() {
        return name;
    }

    public int getSpotNumbers() {
        return spotNumber;
    }

    public Map<ShareCategory, Integer> getShare() {
        return share;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(Container container) {
        this.containers.add(container);
        containerNumber++;
    }

    public Map<String, Float> getGarbageInStreet() {
        return garbageInStreet;
    }

    public void setGarbageInStreet(Map<String, Float> garbageInStreet) {
        this.garbageInStreet = garbageInStreet;
    }

    public Float getSumOfGarbage() {
        return sumOfGarbageInStreet;
    }

    public void setSumOfGarbageInStreet(Float sumOfGarbageInStreet) {
        this.sumOfGarbageInStreet = sumOfGarbageInStreet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(int containerNumber) {
        this.containerNumber = containerNumber;
    }

}
