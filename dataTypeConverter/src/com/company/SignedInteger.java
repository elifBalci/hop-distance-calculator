package com.company;

public class SignedInteger extends Number {
    private String row;
    private int number;

    SignedInteger(String row){
        super(row);
        this.row = row;
        convertToInt();
        convert();
    }

    private void convertToInt(){
        number = Integer.parseInt(row);
        System.out.println("got " + number);
    }

    private void convert(){
        int[] binary = new int[16];
        int index = 0;
        boolean isPositive;

        isPositive = number >= 0;

        if(isPositive) {
            while (number > 0) {
                binary[index++] = number % 2;
                number = number / 2;
            }
        }
        else {
            number *= -1;
            number--;
            while (number > 0) {
                binary[index++] = number % 2;
                number = number / 2;
            }
            for(int j=0; j< 16; j++) {
                if (binary[j] == 1)
                    binary[j] = 0;
                else
                    binary[j] = 1;
            }
            binary[15] = 1 ;
        }


        for(int i = 0; i < 16 ;i++)
            System.out.print(binary[15 - i]);
        System.out.println();
    }
}
