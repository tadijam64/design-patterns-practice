/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.composite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.state.Vehicle;

/**
 *
 * @author tadij
 */
public class AreaComposite extends AreaComponent {

    Logger logger = Logger.getLogger(AreaComposite.class.getName());

    String id;
    String name;
    List<StreetLeaf> streets = new ArrayList<>();
    List<AreaComposite> subAreas = new LinkedList<>();
    public static List<AreaComposite> allAreas = new ArrayList<>();
    List<String> copyOfRowParts;
    List<String> rowParts = new ArrayList<>();
    private Float sumOfGarbageInStreet = 0f;
    public static ArrayList<String> allParts = new ArrayList<>();
    ArrayList<Vehicle> collectingVehicles = new ArrayList<>();

    public AreaComposite() {
    }

    public AreaComposite(String id, String naziv) {
        this.id = id;
        this.name = naziv;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<AreaComposite> getAllAreas() {
        return allAreas;
    }

    public void setAllAreas() {
        allAreas.add(this);
    }

    public List<StreetLeaf> getStreets() {
        return streets;
    }

    public List<String> getParts() {
        return rowParts;
    }

    public void setStreets(List<StreetLeaf> streets) {
        this.streets = streets;
    }

    public List<AreaComposite> getSubAreas() {
        return subAreas;
    }

    public Float getSumOfGarbageInStreet() {
        return sumOfGarbageInStreet;
    }

    public void setSumOfGarbageInStreet(Float sumOfGarbageInStreet) {
        this.sumOfGarbageInStreet += sumOfGarbageInStreet;
    }

    public void setSubAreas(List<AreaComposite> subAreas) {
        this.subAreas = subAreas;
    }

    public ArrayList<Vehicle> getCollectingVehicles() {
        return collectingVehicles;
    }

    public void addVehicleToCollection(Vehicle vehicle) {
        this.collectingVehicles.add(vehicle);
    }

    @Override
    public void makeAreas(String line) {
        String[] areaData = line.split(";|:|,");
        if (validate(areaData)) {
            this.setName(areaData[1]);
            this.setId(areaData[0]);
            for (int i = 2; i < areaData.length; i++) {
                this.rowParts.add(areaData[i]);
                allParts.add(areaData[i]);
            }
            copyOfRowParts = new ArrayList<>(rowParts);
            for (StreetLeaf currentStreet : StreetLeaf.allStreets) {
                if (currentStreet.getStreetName() != null) {
                    for (String part : rowParts) {
                        if (currentStreet.getId().equals(part)) {
                            streets.add(currentStreet);
                            copyOfRowParts.remove(part);
                        }
                    }
                    this.rowParts = new ArrayList<>(copyOfRowParts);
                }
            }
            setAllAreas();
        }
    }

    private boolean validate(String[] areaData) {
        if (areaData.length == 0) {
            return false;
        }
        for (String area : areaData) {
            if (area.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setSubAreas() {
        for (AreaComposite area : allAreas) {
            for (String part : area.getParts()) {
                for (AreaComposite currentArea : allAreas) {
                    if (currentArea.getId().equals(part)) {
                        area.subAreas.add(currentArea);
                    }
                }
            }
        }
    }

    @Override
    public Float getSumOfGarbage() {
        sumOfGarbageInArea = 0f;
        sumOfGarbageInStreet = 0f;
        if (streets.size() > 0) {
            for (StreetLeaf currentStreet : streets) {
                if (currentStreet.getStreetName() != null) {
                    sumOfGarbageInArea += currentStreet.getSumOfGarbage();
                    setSumOfGarbageInStreet(sumOfGarbageInArea);
                }
            }
        } else {
            for (AreaComposite area : subAreas) {

                sumOfGarbageInArea += area.getSumOfGarbage();
            }
            return sumOfGarbageInArea;
        }
        return sumOfGarbageInArea;
    }

    public static ArrayList<AreaComposite> getMainAreas() {
        ArrayList<AreaComposite> mainArea = new ArrayList<>();
        for (AreaComposite area : allAreas) {
            if (!allParts.contains(area.id)) {
                mainArea.add(area);
            }
        }
        return mainArea;
    }
}
