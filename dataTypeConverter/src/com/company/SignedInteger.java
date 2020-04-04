package com.company;

public class SignedInteger extends Number {
    private String row;
    private int number;
    private int endian ;
    private int[] binary = new int[16];
    private String hexString;

    SignedInteger(String row, int endian){
        super(row);
        this.row = row;
        this.endian = endian;
        convertToInt();
        convert();
        hexString = super.convertToHexa(binary);
        extendHextString();
        handleByteOrder();
    }
    private void handleByteOrder(){
        //if little endian
        if (endian==1)
            hexString = reverseHex(hexString);
        System.out.println(hexString);

    }

    private void extendHextString(){
        while (hexString.length() < 4 ){
            hexString = "0" + hexString;
        }
    }

    private void convertToInt(){
        number = Integer.parseInt(row);
    }


    private void convert(){
        int[] localBinary = new int[16];
        int index = 0;
        boolean isPositive;

        isPositive = number >= 0;

        if(isPositive) {
            while (number > 0) {
                localBinary[index++] = number % 2;
                number = number / 2;
            }
        }
        else {
            number *= -1;
            number--;
            while (number > 0) {
                localBinary[index++] = number % 2;
                number = number / 2;
            }
            for(int j=0; j< 16; j++) {
                if (localBinary[j] == 1)
                    localBinary[j] = 0;
                else
                    localBinary[j] = 1;
            }
            localBinary[15] = 1 ;
        }


        for(int i = 0; i < 16 ;i++) {
            binary[i] = localBinary[15 - i];
            //System.out.print(localBinary[15 - i]);
        }
        //System.out.println();
    }
}
