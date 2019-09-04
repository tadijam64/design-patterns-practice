/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.buider;

import java.util.List;
import java.util.Map;
import org.foi.uzdiz.tmalic.dz3.service.composite.StreetLeaf;

/**
 *
 * @author tadij
 */
public interface StreetBuilder {

    public void buildName(String name);

    public void buildSpotNumber(int spotsNumber);

    public void buildShare(int smallShare, int mediumShare, int bigShare);

    public void buildStreets();

    public StreetLeaf getStreet();

    public List<StreetLeaf> getStreets();

    public void buildUsers();

    public void setGarbagePerStreetAndCategoryInHashMap();

    public Map<String, Float> getGarbagePerStreet();

    public Float[] getGarbageForAllStreets();

    public void setGarbageForAllStreets();

    public void buildId(String id);

}
