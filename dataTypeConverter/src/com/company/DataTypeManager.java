package com.company;

import java.util.ArrayList;

public class DataTypeManager {
    private ArrayList<String> rowList;
    private ArrayList<Number> numberList;
    private boolean isLittleEndian;
    private int floatingPointSize;

    DataTypeManager(int floatingPointSize){
        rowList = new ArrayList<>();
        numberList = new ArrayList<>();
        this.floatingPointSize = floatingPointSize;
    }
    public void convert(){

        //add to arraylist
        for (String row: rowList){
            //unsigned integer
            if (row.contains("u")){
                numberList.add(new UnsignedIneteger(row));
            }

            //floating point
            else if (row.contains(".")){
                numberList.add(new FloatingPoint(row, floatingPointSize));
            }

            //signed integer
            else {
                numberList.add(new SignedInteger(row));
            }

        }
    }

    public void addTorawList(String row){
        rowList.add(row);
    }

    public void printRawList(){
        for (String row: rowList){
            System.out.println(row);
        }
    }

    public void printNumberList(){
        for (Number raw: numberList){
            raw.printStr();
        }
    }

    public ArrayList<String> getRowList() {
        return rowList;
    }

    public void setRowList(ArrayList<String> rowList) {
        this.rowList = rowList;
    }
}
