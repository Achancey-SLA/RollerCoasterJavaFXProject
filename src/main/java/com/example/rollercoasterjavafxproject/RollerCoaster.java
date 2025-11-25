package com.example.rollercoasterjavafxproject;

import java.util.ArrayList;

public class RollerCoaster {
    protected String name;
    protected String park;
    protected int rank;

    public RollerCoaster(String name, String park, int rank) {
        this.name = name;
        this.park = park;
        this.rank = rank;
        coastersList.add(this);
    }
   private static ArrayList<RollerCoaster> coastersList = new ArrayList<RollerCoaster>();

    public static ArrayList<RollerCoaster> getCoastersList() {
        return coastersList;
    }

    public static void setCoastersList(ArrayList<RollerCoaster> coastersList) {
        RollerCoaster.coastersList = coastersList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
