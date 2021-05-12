package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class HopCalculator {
    static int[][] vertexArray = new int[Laptop.laptops.size()][Laptop.laptops.size()];
    private ArrayList<LinkedList<Laptop>> linkedListsArray = new ArrayList<>();

    public HopCalculator(){
        //init linkedlists
        for (int i = 0; i < Laptop.laptops.size(); i++) {
            linkedListsArray.add(new LinkedList<Laptop>());
        }
        createMatrix();
    }

    private void createMatrix(){
        for (int i=0; i<Laptop.laptops.size(); i++){
            for (int j=0; j<Laptop.laptops.size(); j++){
                if (i != j)
                    if (isConnected(Laptop.laptops.get(i), Laptop.laptops.get(j)))
                        vertexArray[i][j] = 1;
                if ( i== j)
                    vertexArray[i][j] = -1;
            }
        }
    }


    private void printEdges(){
        for (int i=0; i< Laptop.laptops.size(); i++){
            for (int j=0; j<Laptop.laptops.size(); j++){
                System.out.print(vertexArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private int computeHopDistance(int laptopId1, int laptopId2){
        if (laptopId1 == laptopId2)
            return 0;

        int distance = 1 ;
        if (hop(laptopId1, laptopId2) == 1) {
            return distance;
        }

        int result = bfs(laptopId1, laptopId2);
        if (result == 0)
            return result;
        else
            return result + distance;
    }
    private int bfs(int laptopId1, int currentLaptopId){
        int distance = 0;
        int stage = 0;
        boolean notFound = true;

        while(stage < linkedListsArray.size()) {
            distance++;

            //add the neighbors of currentLaptop to the linked list queque
            for (int i = 0; i < Laptop.laptops.size(); i++) {
                if (vertexArray[currentLaptopId][i] == 1 && !Laptop.laptops.get(i).isVisited())
                    linkedListsArray.get(stage).add(Laptop.laptops.get(i));
            }

            for (int j = 0; j < linkedListsArray.get(stage).size() ; j++) {
                currentLaptopId = linkedListsArray.get(stage).get(j).getId();

                Laptop.laptops.get(currentLaptopId).setVisited(true);

                //check if the laptop found
                for (Laptop laptop : linkedListsArray.get(stage)) {
                    if (hop(laptopId1, laptop.getId()) == 1) {
                        return distance;
                    }
                }
            }
            stage++;

        }
        //print the queue
        return 0;
    }

    private int hop(int laptopId1, int laptopId2){
        if(vertexArray[laptopId1][laptopId2] == 1 )
            return 1;
        return 0;
    }

    private boolean isConnected(Laptop laptop1, Laptop laptop2){
        return distance(laptop1, laptop2) <= laptop1.getWirelessRange() + laptop2.getWirelessRange();
    }

    private float distance(Laptop laptop1, Laptop laptop2){
        return (float) Math.sqrt(Math.pow(laptop1.getPosition_x() - laptop2.getPosition_x(), 2) +
                Math.pow(laptop1.getPosition_y() - laptop2.getPosition_y(), 2));
    }

    public void computeAllHops(int laptopId){
        for(Laptop laptop: Laptop.laptops){
            Laptop.cleanVisited();
            System.out.println(computeHopDistance(laptopId, laptop.getId()));
        }
    }
}
