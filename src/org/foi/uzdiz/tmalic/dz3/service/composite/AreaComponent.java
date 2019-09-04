/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.foi.uzdiz.tmalic.dz3.mvc.controller.OutputController.isThereEnoughSpaceOnScreen;
import static org.foi.uzdiz.tmalic.dz3.utils.BaseUtils.formatOutput;

/**
 *
 * @author tadij
 */
public abstract class AreaComponent {

    String id;
    String naziv;
    public static Float sumOfGarbageInArea = 0f;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void makeAreas(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSubAreas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Float getSumOfGarbage() {
        Float sumOfAllGarbage = 0f;
        for (AreaComposite area : AreaComposite.allAreas) {
            sumOfAllGarbage = area.getSumOfGarbage();
        }
        return sumOfAllGarbage;
    }

    public static void printAreasData() {
        String firstNumberAsString = "";
        formatOutput("");
        formatOutput("--------------------------------------------------------------------");
        isThereEnoughSpaceOnScreen();
        System.out.format("%17s%29s%10s", "Vrsta | ", "Naziv | ", " Smece ");
        formatOutput("");
        formatOutput("--------------------------------------------------------------------");
        List<AreaComposite> areaList = new ArrayList(AreaComposite.allAreas);
        for (AreaComposite area : areaList) {
            firstNumberAsString = String.format("%.3f", area.getSumOfGarbage());
            System.out.format("%17s%29s%10s", "Podrucje | ", area.getName() + " | ", firstNumberAsString);
            formatOutput("");
            for (AreaComposite subArea : area.getSubAreas()) {
                firstNumberAsString = String.format("%.3f", subArea.getSumOfGarbage());
                isThereEnoughSpaceOnScreen();
                System.out.format("%17s%29s%10s", "PodPodrucje | ", subArea.getId() + " | ", firstNumberAsString);
                formatOutput("");
            }
            for (StreetLeaf currentStreet : area.getStreets()) {
                firstNumberAsString = String.format("%.3f", currentStreet.getSumOfGarbage());
                isThereEnoughSpaceOnScreen();
                System.out.format("%17s%29s%10s", "Ulica | ", currentStreet.getStreetName() + " | ", firstNumberAsString);
                formatOutput("");
            }
        }
    }
}
