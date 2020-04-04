package com.company;

import java.math.BigInteger;
import java.util.Arrays;

public abstract class Number {
    public String row;

    public Number(String row){
        this.row  = row;
    }
    public void printStr(){
        System.out.println("number: "+ row);
    }

    public String convertToHexa(int[] binary){
        String binaryString="";
        for(int i : binary) {
            binaryString+=Integer.toString(i);
        }
        return new BigInteger(binaryString, 2).toString(16);
    }
    public static String reverseHex(String originalHex) {
        // TODO: Validation that the length is even
        int lengthInBytes = originalHex.length() / 2;
        char[] chars = new char[lengthInBytes * 2];
        for (int index = 0; index < lengthInBytes; index++) {
            int reversedIndex = lengthInBytes - 1 - index;
            chars[reversedIndex * 2] = originalHex.charAt(index * 2);
            chars[reversedIndex * 2 + 1] = originalHex.charAt(index * 2 + 1);
        }
        return new String(chars);
    }

}
