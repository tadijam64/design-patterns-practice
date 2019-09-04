/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.utils;

import org.foi.uzdiz.tmalic.dz3.configuration.InitialConfiguration;

/**
 *
 * @author tadij
 */
public interface Constants {

    public interface Vehicles {

        public enum ConsumptionType {
            DISEL, ELECTRIC
        }

        public enum GarbageType {
            GLASS, PAPER, METAL, ORGANIC, MIXED
        }
    }

    public interface Containers {

        public enum ContainerType {
            CONTAINER, TRASH_CAN
        }
    }

    public enum ShareCategory {
        SMALL, MEDIUM, BIG
    }

    public interface Paths {

        public interface BasePaths {

            String STREETS_FILE = "\\" + InitialConfiguration.prop.getProperty("ulice");
            String CONTAINERS_FILE = "\\" + InitialConfiguration.prop.getProperty("spremnici");
            String VEHICLES_FILE = "\\" + InitialConfiguration.prop.getProperty("vozila");
            String AREA_FILE = "\\" + InitialConfiguration.prop.getProperty("područja");
            String DISPATCHER_FILE = "\\" + InitialConfiguration.prop.getProperty("dispečer");
        }
    }

    public interface Helpers {

        String CHARSET = "utf8";
        String NEEDED_CONT = "Number of needed containers for ";
    }

    public interface Properties {

        Integer PREUZIMANJE_PROPERTY = Integer.parseInt(InitialConfiguration.prop.get("preuzimanje").toString());
        Integer ISPIS_PROPERTY = Integer.parseInt(InitialConfiguration.prop.get("ispis").toString());
        Integer BROJ_RADNIH_CIKLUSA_ZA_ODVOZ = Integer.parseInt(InitialConfiguration.prop.get("brojRadnihCiklusaZaOdvoz").toString());
        Integer KAPACITET_DIZEL_VOZILA = Integer.parseInt(InitialConfiguration.prop.get("kapacitetDizelVozila").toString());
        Integer KAPACITET_ELEKTRO_VOZILA = Integer.parseInt(InitialConfiguration.prop.get("kapacitetElektroVozila").toString());
        Integer PUNJENJE_DIZEL_VOZILA = Integer.parseInt(InitialConfiguration.prop.get("punjenjeDizelVozila").toString());
        Integer PUNJENJE_ELEKTRO_VOZILA = Integer.parseInt(InitialConfiguration.prop.get("punjenjeElektroVozila").toString());
        String PARAMETERS_FILE = "DZ_2_parametri.txt";
        String PRIDRUZENO = "Pridružena količina otpada vrste: ";
        String UKUPNO = ", ukupna kolicina otpada na vozilu: ";
        String OTPADA = "Otpada kategorije ";
        String VOZILO = "Vozilo: ";
        String SLOBODNO = ", slobodno: ";
        String UKUPNO_OTPADA = "Ukupna količina otpada vrste GLASS na odlagalištu je ";
        String UKUPNO_CONTAINERA = "Ukupan broj containera koje je proslo vozilo ";
        String BROJ = "Broj puta kada je vozilo ";
        String BILO = " bilo na odlagalištu je ";
        String OBISLO = "Svako vozilo je obišlo isti broj mjesta (ulica), a to je: ";
    }

    public interface GarbageType {

        String GLASS = "GLASS";
        String PAPER = "PAPER";
        String MIXED = "MIXED";
        String METAL = "METAL";
        String ORGANIC = "ORGANIC";
    }

}
