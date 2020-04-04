package com.company;

import java.util.ArrayList;

public class FloatingPoint extends Number {
    private String row;
    private String fraction_part;
    private int floatingPointSize;
    private int endian;
    private int partA;
    private float partB;
    private int sign;
    private int expSize;
    private int fractionSize;
    private int E;
    private int bias;
    private int exp;
    private int fractionLimit;
    int[] binary;
    String hexString;


    public FloatingPoint(String row, int floatingPointSize, int endian) {
        super(row);
        this.row = row;
        this.floatingPointSize = floatingPointSize;
        this.endian = endian;
        decideSize();
        convertToInt();
        convert();
        hexString = super.convertToHexa(binary);
        extendHextString();
        handleByteOrder();
    }

    private void handleByteOrder() {
        //if little endian
        if (endian == 1)
            hexString = reverseHex(hexString);
        System.out.println(hexString);
    }

    private void extendHextString() {
        while (hexString.length() < floatingPointSize) {
            hexString = "0" + hexString;
        }
    }

    private void decideSize() {
        switch (floatingPointSize) {
            case 1:
                expSize = 4;
                fractionSize = 3;
                fractionLimit = 3;
                break;
            case 2:
                expSize = 6;
                fractionSize = 9;
                fractionLimit = 9;
                break;
            case 3:
                expSize = 8;
                fractionSize = 15;
                fractionLimit = 13;
                break;
            case 4:
                expSize = 10;
                fractionSize = 21;
                fractionLimit = 13;
                break;
        }
        bias = (int) Math.pow(2, expSize - 1) - 1;
    }

    private void convertToInt() {
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == '.') {
                fraction_part = row.substring(i + 1, row.length());
                row = row.substring(0, i);
            }
        }
        fraction_part = "0." + fraction_part;
        partA = Integer.parseInt(row);

        if (partA > 0)
            sign = 0;
        else {
            sign = 1;
            partA *= -1;
        }
        partB = Float.parseFloat(fraction_part);
        System.out.println("got " + partA + partB);
    }

    private void convert() {
        binary = new int[floatingPointSize * 8];
        int[] expBinary = new int[expSize];
        int[] fractionBinary = new int[fractionSize];
        ArrayList<Integer> fractionArrayList = new ArrayList<>();
        ArrayList<Integer> partAList = new ArrayList<>();
        ArrayList<Integer> partBList = new ArrayList<>();

        // create part A
        int index = 0;
        while (partA > 0) {
            partAList.add(partA % 2);
            partA = partA / 2;
        }
//        for(int i=partAList.size()-1; i>=0; i--)
//            System.out.print(partAList.get(i));
//        System.out.println();

        E = partAList.size() - 1;

        // create part B
        for (int i = 0; i < 13; i++) {
            partB *= 2;
            if (partB >= 1)
                partBList.add(1);
            else
                partBList.add(0);
            partB = partB - (int) partB;
        }

        // calc exp
        exp = bias + E;

        int counter = 0;

        while (exp > 0) {
            expBinary[counter++] = exp % 2;
            exp = exp / 2;
        }

        for (int i = 0; i < expSize; i++) {
            if (expBinary[i] != 0 && expBinary[i] != 1)
                expBinary[i] = 0;
        }

        for (int i = 0; i < expBinary.length; i++)
            System.out.print(expBinary[expBinary.length - (i + 1)]);
        System.out.println();

        //calc mantissa

        //add partA
        counter = 0;
        for (int i = partAList.size() - 2; i >= 0; i--) {
            if (fractionBinary.length > counter) {
                fractionBinary[counter] = partAList.get(i);
                counter++;
            }
        }


//        System.out.print("Part b is: ");
//        for (Integer integer : partBList) {
//            System.out.print(integer);
//        }
//        System.out.println();

        int bSize = fractionSize - counter;

        //Round part B
        if (partBList.size() >= bSize + 1) {
            if (partBList.get(bSize) == 1) {
                //round
                int loop = counter;
                for (loop = counter + 1; loop > 0; loop--) {
                    if (partBList.get(loop) == 0) {
                        partBList.set(loop, 1);
                        loop = 0;
                    } else {
                        partBList.set(loop, 0);
                    }
                }
            }
        }

//        System.out.print("Part b is: ");
//        for (Integer integer : partBList) {
//            System.out.print(integer);
//        }

        System.out.println();
        // add part b to fraction
        for (int i = 0; i < counter; i++) {
            if (fractionBinary.length > counter + i)
                fractionBinary[counter + i] = partBList.get(i);
        }


//        System.out.print("fraction is : ");
//        for(int i = 0; i < fractionSize ;i++)
//            System.out.print(fractionBinary[i]);
//        System.out.println();

        this.binary[0] = sign;
        counter = 1;
        for (int i = 0; i < expBinary.length; i++) {
            this.binary[counter] = expBinary[expBinary.length - (i + 1)];
            counter++;
        }
        for (int item : fractionBinary) {
            this.binary[counter] = item;
            counter++;
        }

        for (int value : binary) System.out.print(value);
        System.out.println();
    }


    private void convert2() {
        binary = new int[floatingPointSize * 8];
        int[] expBinary = new int[expSize];
        int[] fractionBinary = new int[fractionSize];
        ArrayList<Integer> fractionArrayList = new ArrayList<>();
        ArrayList<Integer> partAList = new ArrayList<>();
        ArrayList<Integer> partBList = new ArrayList<>();

        // create part A
        int index = 0;
        while (partA > 0) {
            partAList.add(partA % 2);
            partA = partA / 2;
        }
//        for(int i=partAList.size()-1; i>=0; i--)
//            System.out.print(partAList.get(i));
//        System.out.println();

        E = partAList.size() - 1;

        // create part B
        for (int i = 0; i < 13; i++) {
            partB *= 2;
            if (partB >= 1)
                partBList.add(1);
            else
                partBList.add(0);
            partB = partB - (int) partB;
        }

        // calc exp
        exp = bias + E;

        int counter = 0;

        while (exp > 0) {
            expBinary[counter++] = exp % 2;
            exp = exp / 2;
        }

        for (int i = 0; i < expSize; i++) {
            if (expBinary[i] != 0 && expBinary[i] != 1)
                expBinary[i] = 0;
        }

        for (int i = 0; i < expBinary.length; i++)
            System.out.print(expBinary[expBinary.length - (i + 1)]);
        System.out.println();

        //calc mantissa

        //add partA
        counter = 0;
        for (int i = partAList.size() - 2; i >= 0; i--) {
            if (fractionBinary.length > counter) {
                fractionBinary[counter] = partAList.get(i);
                counter++;
            }
        }

        int counter2 = 0;
        for (int i = partAList.size() - 1; i > 0; i--) {
            if (fractionBinary.length > counter2) {
                fractionArrayList.add(partAList.get(i));
                counter2++;
            }
        }

        for (Integer i : fractionArrayList) {
            System.out.print(i);
        }
        System.out.println();

        fractionArrayList.addAll(partBList);
        //round fraction

        if (fractionArrayList.get(fractionLimit) == 1) {
            //round
            for (int loop= fractionLimit-1; loop > 0; loop--) {
                if (fractionArrayList.get(loop) == 0) {
                    fractionArrayList.set(loop, 1);
                    loop = 0;
                } else {
                    fractionArrayList.set(loop, 0);
                }
            }
        }
        System.out.println();
        for (int i=fractionLimit; i< fractionSize ; i++){
            fractionArrayList.set(i, 0);
        }

        for (int i=0; i<fractionSize; i++) {
            fractionBinary[i] = fractionArrayList.get(i);
            System.out.print(fractionArrayList.get(i));
        }
        System.out.println();


//        System.out.print("Part b is: ");
//        for (Integer integer : partBList) {
//            System.out.print(integer);
//        }
//        System.out.println();




//        System.out.print("Part b is: ");
//        for (Integer integer : partBList) {
//            System.out.print(integer);
//        }


        this.binary[0] = sign;
        counter = 1;
        for (int i = 0; i < expBinary.length; i++) {
            this.binary[counter] = expBinary[expBinary.length - (i + 1)];
            counter++;
        }
        for (int item : fractionBinary) {
            this.binary[counter] = item;
            counter++;
        }

        for (int value : binary) System.out.print(value);
        System.out.println();
    }
}

