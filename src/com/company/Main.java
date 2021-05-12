package com.company;
import java.util.ArrayList;

/*
*  To Run:
* javac com/company/Main.java com/company/Laptop.java com/company/InputHandler.java com/company/HopCalculator.java
* java com.company.Main /home/elif/IdeaProjects/algorithm_hw1/input.txt
*
*/

public class Main {
    public static void main(String[] args) {
        String fileName;
        if(args.length > 0) {
            fileName = args[0];
            //fileName = "/home/elif/IdeaProjects/algorithm_hw1/input.txt";
            InputHandler inputHandler = new InputHandler(fileName);
            generateLaptops(inputHandler);
            HopCalculator hp = new HopCalculator();
            hp.computeAllHops(0);
        }
        else{
            System.out.println("Input directory hasn't been provided as command line arg.");
            System.exit(0);
        }

    }
    public static void generateLaptops(InputHandler inputHandler){
        for (int i=1; i< inputHandler.getRowList().size(); i++){
            String[] values = inputHandler.getRowList().get(i).split("\t");
            Laptop laptop = new Laptop(Float.parseFloat(values[0]), Float.parseFloat(values[1]), Float.parseFloat(values[2]), i-1 );
            Laptop.laptops.add(laptop);
        }
    }
}
