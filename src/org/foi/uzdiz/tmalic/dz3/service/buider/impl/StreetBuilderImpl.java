/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.buider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.configuration.InitialConfiguration;
import org.foi.uzdiz.tmalic.dz3.service.buider.StreetBuilder;
import org.foi.uzdiz.tmalic.dz3.service.composite.StreetLeaf;
import org.foi.uzdiz.tmalic.dz3.mvc.model.User;
import org.foi.uzdiz.tmalic.dz3.utils.BaseUtils;
import org.foi.uzdiz.tmalic.dz3.utils.Constants;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory.BIG;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory.MEDIUM;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory.SMALL;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.Vehicles.GarbageType;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Vehicles.GarbageType.GLASS;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Vehicles.GarbageType.METAL;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Vehicles.GarbageType.PAPER;

/**
 *
 * @author tadij
 */
public class StreetBuilderImpl implements StreetBuilder {

    Logger logger = Logger.getLogger(StreetBuilderImpl.class.getName());

    private StreetLeaf street;
    private List<StreetLeaf> streets = new ArrayList<>();
    private static StreetBuilderImpl streetBuilderImpl = null;
    Map<Constants.Vehicles.GarbageType, Float> garbageQuantityMap = new HashMap<>();
    int seed = Integer.parseInt(InitialConfiguration.prop.get("sjemeGeneratora").toString());
    Float smallMin = Float.parseFloat((String) InitialConfiguration.prop.get("maliMin"));
    Float mediumMin = Float.parseFloat((String) InitialConfiguration.prop.get("srednjiMin"));
    Float bigMin = Float.parseFloat((String) InitialConfiguration.prop.get("velikiMin"));
    Map<String, Float> garbagePerStreet = new LinkedHashMap<>();
    int counter = 0;

    private Float sum = 0f;
    private Float sumGlass = 0f;
    private Float sumMetal = 0f;
    private Float sumPaper = 0f;
    private Float sumOrganic = 0f;
    private Float sumMixed = 0f;

    public static Float[] garbageSum = {0f, 0f, 0f, 0f, 0f};

    String[] smallGarbageTypes = {"maliStaklo", "maliPapir", "maliMetal", "maliBio", "maliMješano"};
    String[] mediumGarbageTypes = {"srednjiStaklo", "srednjiPapir", "srednjiMetal", "srednjiBio", "srednjiMješano"};
    String[] bigGarbageTypes = {"velikiStaklo", "velikiPapir", "velikiMetal", "velikiBio", "velikiMješano"};

    int smallCounter = 0;
    int mediumCounter = 0;
    int bigCounter = 0;

    private StreetBuilderImpl() {
        this.street = new StreetLeaf();
        garbagePerStreet.clear();
    }

    @Override
    public void buildName(String name) {
        street.setName(name);
    }

    @Override
    public void buildSpotNumber(int spotsNumber) {
        street.setSpotNumber(spotsNumber);
    }

    @Override
    public void buildShare(int smallShare, int mediumShare, int bigShare) {
        Map<ShareCategory, Integer> share = new HashMap<>();
        share.put(ShareCategory.BIG, bigShare);
        share.put(ShareCategory.MEDIUM, mediumShare);
        share.put(ShareCategory.SMALL, smallShare);
        street.setShare(share);
    }

    @Override
    public StreetLeaf getStreet() {
        return this.street;
    }

    @Override
    public List<StreetLeaf> getStreets() {
        return this.streets;
    }

    @Override
    public void buildStreets() {
        streets.add(street);
        setGarbagePerStreetAndCategoryInHashMap();
        this.street = new StreetLeaf();
    }

    public static StreetBuilderImpl getInstance() {
        if (streetBuilderImpl == null) {
            streetBuilderImpl = new StreetBuilderImpl();
        }
        return streetBuilderImpl;
    }

    @Override
    public void buildUsers() {
        int small = street.getSpotNumbers() * street.getShare().get(SMALL) / 100;
        int medium = street.getSpotNumbers() * street.getShare().get(MEDIUM) / 100;
        int big = street.getSpotNumbers() * street.getShare().get(BIG) / 100;

        while (street.getSpotNumbers() > small + medium + big) {
            small++;
        }
        for (int i = 0; i < small; i++) {
            User user = new User(street, 0, generateUserGarbage(0));
            street.setUsers(user);
        }
        for (int i = 0; i < medium; i++) {
            User user = new User(street, 1, generateUserGarbage(1));
            street.setUsers(user);
        }
        for (int i = 0; i < big; i++) {
            User user = new User(street, 2, generateUserGarbage(2));
            street.setUsers(user);
        }
        int zbroj = small + medium + big;
    }

    private Map<GarbageType, Float> generateUserGarbage(int type) {
        garbageQuantityMap.clear();
        smallCounter = mediumCounter = bigCounter = 0;
        switch (type) {
            case 0:
                for (GarbageType value : GarbageType.values()) {
                    setMapObject(value, smallGarbageTypes[smallCounter], smallMin);
                    smallCounter++;
                }
                return garbageQuantityMap;
            case 1:
                for (GarbageType pom : GarbageType.values()) {
                    setMapObject(pom, mediumGarbageTypes[mediumCounter], mediumMin);
                    mediumCounter++;
                }
                return garbageQuantityMap;
            case 2:
                for (GarbageType pom : GarbageType.values()) {
                    setMapObject(pom, bigGarbageTypes[bigCounter], bigMin);
                    bigCounter++;
                }
                return garbageQuantityMap;
            default:
                logger.log(Level.WARNING, "Error in user type (expected: 0, 1 or 2, got: {})", type);
                return garbageQuantityMap;
        }
    }

    private void setMapObject(GarbageType garbageType, String brojnik, Float nazivnik) {
        float from = getFloatProperty(brojnik) / nazivnik;
        float to = getFloatProperty(brojnik);

        garbageQuantityMap.put(garbageType, BaseUtils.generateRandom(seed, from, to));
    }

    private Float getFloatProperty(String propertyKey) {
        return Float.parseFloat((String) InitialConfiguration.prop.get(propertyKey));
    }

    @Override
    public void setGarbagePerStreetAndCategoryInHashMap() {
        for (User user : street.getUsers()) {
            for (GarbageType type : user.getGarbageQuantityMap().keySet()) {
                switch (type) {
                    case GLASS:
                        sumGlass += user.getGarbageQuantityMap().get(type);
                        break;
                    case PAPER:
                        sumPaper += user.getGarbageQuantityMap().get(type);
                        break;
                    case METAL:
                        sumMetal += user.getGarbageQuantityMap().get(type);
                        break;
                    case ORGANIC:
                        sumOrganic += user.getGarbageQuantityMap().get(type);
                        break;
                    case MIXED:
                        sumMixed += user.getGarbageQuantityMap().get(type);
                        break;
                }
                sum += user.getGarbageQuantityMap().get(type);
            }
        }
        modifyGarbageInStreet(street);
    }

    private void modifyGarbageInStreet(StreetLeaf street) {
        Map<String, Float> garbageInStreet = street.getGarbageInStreet();
        garbageInStreet.put("GLASS", garbageInStreet.get("GLASS") + sumGlass);
        garbageInStreet.put("PAPER", garbageInStreet.get("PAPER") + sumPaper);
        garbageInStreet.put("METAL", garbageInStreet.get("METAL") + sumMetal);
        garbageInStreet.put("ORGANIC", garbageInStreet.get("ORGANIC") + sumOrganic);
        garbageInStreet.put("MIXED", garbageInStreet.get("MIXED") + sumMixed);
        street.setSumOfGarbageInStreet(sum);
        street.setGarbageInStreet(garbageInStreet);
    }

    @Override
    public Map<String, Float> getGarbagePerStreet() {
        return garbagePerStreet;
    }

    @Override
    public void setGarbageForAllStreets() {
        garbageSum[0] += sumGlass;
        garbageSum[1] += sumPaper;
        garbageSum[2] += sumMetal;
        garbageSum[3] += sumOrganic;
        garbageSum[4] += sumMixed;
    }

    @Override
    public Float[] getGarbageForAllStreets() {
        return garbageSum;
    }

    @Override
    public void buildId(String id) {
        street.setId(id);
    }
}
