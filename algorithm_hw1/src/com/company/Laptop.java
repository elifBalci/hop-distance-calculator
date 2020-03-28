package com.company;

import java.util.ArrayList;

public class Laptop {
    protected static ArrayList<Laptop> laptops = new ArrayList<>();
    private int id;
    private float position_x;
    private float position_y;
    private float wirelessRange;
    private boolean visited;

    public Laptop(float position_x, float position_y, float wirelessRange, int id ){
        this.id = id;
        this.position_x = position_x;
        this.position_y = position_y;
        this.wirelessRange = wirelessRange;
        visited = false;
    }

    public static void printLaptops(){
        for (int i=0; i<laptops.size(); i++){
            System.out.println("laptop id: " + i + "\nlaptop position x: " +
                    laptops.get(i).position_x + "\nlaptop position y: " + laptops.get(i).position_y + "\nlaptop range: " + laptops.get(i).wirelessRange + "\n");
        }
    }

    public float getPosition_x() {
        return position_x;
    }

    public void setPosition_x(float position_x) {
        this.position_x = position_x;
    }

    public float getPosition_y() {
        return position_y;
    }

    public void setPosition_y(float position_y) {
        this.position_y = position_y;
    }

    public float getWirelessRange() {
        return wirelessRange;
    }

    public void setWirelessRange(float wirelessRange) {
        this.wirelessRange = wirelessRange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public static void cleanVisited(){
        for (Laptop laptop :laptops){
            laptop.setVisited(false);
        }
    }
}
