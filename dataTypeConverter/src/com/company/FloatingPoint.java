package com.company;

import java.util.ArrayList;

public class FloatingPoint extends Number {
    private String row;
    private String fraction_part;
    private int floatingPointSize;
    private int partA;
    private float partB;
    private int sign;
    private int expSize;
    private int fractionSize;
    private int E;
    private int bias;
    private int exp;


    public FloatingPoint(String row, int floatingPointSize) {
        super(row);
        this.row = row;
        this.floatingPointSize = floatingPointSize;
        decideSize();
        convertToInt();
        convert();
    }

    private void decideSize(){
        switch (floatingPointSize) {
            case 1:
                expSize = 4;
                fractionSize = 3;
                break;
            case 2:
                expSize = 6;
                fractionSize = 9;
                break;
            case 3:
                expSize = 8;
                fractionSize = 15;
                break;
            case 4:
                expSize = 10;
                fractionSize = 21;
                break;
        }
        bias = (int) Math.pow(2, expSize-1) -1;
    }
    private void convertToInt(){
        for(int i=0; i< row.length(); i++){
            if (row.charAt(i) == '.') {
                fraction_part = row.substring(i+1,row.length());
                row = row.substring(0, i);
            }
        }
        fraction_part = "0." + fraction_part;
        partA = Integer.parseInt(row);

        if (partA> 0)
            sign = 0;
        else
            sign = 1;

        partB = Float.parseFloat(fraction_part);
        System.out.println("got " + partA + partB);
    }

    private void convert() {
        int[] binary = new int[floatingPointSize];
        int[] expBinary = new int[expSize];
        int[] fractionBinary = new int[fractionSize];
        ArrayList<Integer> partAList = new ArrayList<>();
        ArrayList<Integer> partBList = new ArrayList<>();

        // create part A
        int index = 0;
        while(partA > 0){
            partAList.add(partA%2);
            partA = partA/2;
        }
        for(int i=partAList.size()-1; i>=0; i--)
            System.out.print(partAList.get(i));
        System.out.println();
        E = partAList.size()-1;

        // create part B
        for (int i=0; i<fractionSize; i++){
            partB *=2;
            if (partB >= 1)
                partBList.add(1);
            else
                partBList.add(0);
            partB = partB - (int)partB;
        }
        /*for (Integer integer : partBList) System.out.print(integer);
            System.out.println();
        */

        // calc exp
        exp = bias + E;

        int counter = 0;

        while(exp > 0){
            expBinary[counter++] = exp%2;
            exp = exp/2;
        }

        for(int i = 0; i < expSize ; i++) {
            if(expBinary[i] != 0 && expBinary[i] != 1)
                expBinary[i] = 0;
        }

        for(int i = 0; i < expBinary.length ;i++)
            System.out.print(expBinary[expBinary.length - (i + 1)]);
        System.out.println();

        //calc mantissa

        //add partA
        counter = 0;
        for(int i=partAList.size()-2; i>=0; i--) {
            fractionBinary[counter] = partAList.get(i);
            counter++;
        }

        //TODO:  add rounded part B


        for(int i = 0; i < fractionSize ;i++)
            System.out.print(fractionBinary[i]);
        System.out.println();

    }
}
