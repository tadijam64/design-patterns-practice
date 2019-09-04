/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.service.buider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.tmalic.dz3.mvc.model.Container;
import org.foi.uzdiz.tmalic.dz3.service.composite.StreetLeaf;
import org.foi.uzdiz.tmalic.dz3.mvc.model.User;
import org.foi.uzdiz.tmalic.dz3.service.buider.ContainerBuilder;
import org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory.BIG;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory.MEDIUM;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.ShareCategory.SMALL;

/**
 *
 * @author tadij
 */
public class ContainerBuilderImpl implements ContainerBuilder {

    Logger logger = Logger.getLogger(ContainerBuilderImpl.class.getName());

    private Container container;
    private List<Container> containers = new ArrayList<>();
    private static ContainerBuilderImpl containerBuilderImpl = null;
    private int numberOfNeededContainersForGlass;
    private int numberOfNeededContainersForPaper;
    private int numberOfNeededContainersForMetal;
    private int numberOfNeededContainersForBio;
    private int numberOfNeededContainersForMixed;
    private StreetBuilderImpl streetBuilderImpl = StreetBuilderImpl.getInstance();
    public static int numberOfAllContainers = 0;

    private static int usersSharingSmallContainer;
    private static int usersSharingMediumContainer;
    private static int usersSharingBigContainer;

    private ContainerBuilderImpl() {
        this.numberOfNeededContainersForMixed = 3;
        this.numberOfNeededContainersForBio = 3;
        this.numberOfNeededContainersForMetal = 3;
        this.numberOfNeededContainersForPaper = 3;
        this.numberOfNeededContainersForGlass = 3;
        this.container = new Container();
    }

    @Override
    public void buildName(String name) {
        container.setName(name);
    }

    @Override
    public void buildContainerType(int type) {
        container.setContainerType(type);
    }

    @Override
    public void buildShare(int smallShare, int mediumShare, int bigShare) {
        Map<ShareCategory, Integer> share = new HashMap<>();
        share.put(ShareCategory.BIG, bigShare);
        share.put(ShareCategory.MEDIUM, mediumShare);
        share.put(ShareCategory.SMALL, smallShare);
        container.setShare(share);
    }

    @Override
    public void buildCapacity(int capacity) {
        container.setCapacity(capacity);
    }

    @Override
    public Container getContainer() {
        return this.container;
    }

    public static ContainerBuilderImpl getInstance() {
        if (containerBuilderImpl == null) {
            containerBuilderImpl = new ContainerBuilderImpl();
        }
        return containerBuilderImpl;
    }

    @Override
    public void buildContainers() {
        containers.add(container);
    }

    @Override
    public List<Container> getContainers() {
        return this.containers;
    }

    @Override
    public void addContainerToUsers() {
        for (StreetLeaf currentStreet : StreetLeaf.allStreets) {
            if (currentStreet.getStreetName() != null) {
                List<User> users = currentStreet.getUsers();
                usersSharingSmallContainer = container.getShare().get(SMALL);
                usersSharingMediumContainer = container.getShare().get(MEDIUM);
                usersSharingBigContainer = container.getShare().get(BIG);
                checkForEmptyUserCategories();
                addContainersToUsersFromStreet(users);
                setContainers();
                printOutAndRestartContainerCounter(currentStreet.getStreetName());
            }
        }

        this.container = new Container();
    }

    private void addContainersToUsersFromStreet(List<User> users) {
        for (User user : users) {
            subscribeUserToContainer(user);
        }
    }

    private void checkForEmptyUserCategories() {
        if (usersSharingSmallContainer == 0) {
            numberOfNeededContainersForGlass--;
            numberOfNeededContainersForPaper--;
            numberOfNeededContainersForMetal--;
            numberOfNeededContainersForBio--;
            numberOfNeededContainersForMixed--;
        }
        if (usersSharingMediumContainer == 0) {
            numberOfNeededContainersForGlass--;
            numberOfNeededContainersForPaper--;
            numberOfNeededContainersForMetal--;
            numberOfNeededContainersForBio--;
            numberOfNeededContainersForMixed--;
        }
        if (usersSharingBigContainer == 0) {
            numberOfNeededContainersForGlass--;
            numberOfNeededContainersForPaper--;
            numberOfNeededContainersForMetal--;
            numberOfNeededContainersForBio--;
            numberOfNeededContainersForMixed--;
        }
    }

    private void subscribeUserToContainer(User user) {
        switch (user.getConsumerTypeKey()) {
            case 0:
                if (usersSharingSmallContainer == 0) {
                    usersSharingSmallContainer = container.getShare().get(SMALL);
                    incrementNumberOfNeededContainers();
                }
                user.setContainers(container);
                usersSharingSmallContainer--;
                break;
            case 1:
                if (usersSharingMediumContainer == 0) {
                    usersSharingMediumContainer = container.getShare().get(MEDIUM);
                    incrementNumberOfNeededContainers();
                }
                user.setContainers(container);
                usersSharingMediumContainer--;
                break;
            case 2:
                if (usersSharingBigContainer == 0) {
                    usersSharingBigContainer = container.getShare().get(BIG);
                    incrementNumberOfNeededContainers();
                }
                user.setContainers(container);
                usersSharingBigContainer--;
                break;
        }
    }

    private void incrementNumberOfNeededContainers() {
        switch (container.getName()) {
            case "staklo":
                numberOfNeededContainersForGlass++;
                break;
            case "papir":
                numberOfNeededContainersForPaper++;
                break;
            case "metal":
                numberOfNeededContainersForMetal++;
                break;
            case "bio":
                numberOfNeededContainersForBio++;
                break;
            case "mješano":
                numberOfNeededContainersForMixed++;
                break;
            default:
                logger.log(Level.WARNING, "Error on container's type");
                break;
        }
    }

    private void printOutAndRestartContainerCounter(String streetName) {
        switch (container.getName()) {
            case "staklo":
                numberOfNeededContainersForGlass = 3;
                break;
            case "papir":
                numberOfNeededContainersForPaper = 3;
                break;
            case "metal":
                numberOfNeededContainersForMetal = 3;
                break;
            case "bio":
                numberOfNeededContainersForBio = 3;
                break;
            case "mješano":
                numberOfNeededContainersForMixed = 3;
                break;
        }
    }

    @Override
    public int getNumberOfContainers() {
        return numberOfAllContainers;
    }

    private void setContainers() {
        int pom = numberOfNeededContainersForMetal + numberOfNeededContainersForBio + numberOfNeededContainersForMixed;
        numberOfAllContainers += numberOfNeededContainersForPaper + numberOfNeededContainersForGlass + pom;
    }

}
