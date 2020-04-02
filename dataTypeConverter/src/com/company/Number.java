package com.company;

public abstract class Number {
    public String row;

    public Number(String row){
        this.row  = row;
    }
    public void printStr(){
        System.out.println("number: "+ row);
    }
}
