package com.company;

public class UnsignedIneteger extends Number {
    private String row;
    private int number;

    public UnsignedIneteger(String row) {
        super(row);
        this.row = row;
        convertToInt();
        convert();
    }
    private void convertToInt(){
        for(int i=0; i< row.length(); i++){
            if (row.charAt(i) == 'u')
                row = row.substring(0, i);
        }
        number = Integer.parseInt(row);
        System.out.println("got " + number);
    }

    private void convert(){
        int binary[] = new int[16];
        int index = 0;

        while(number > 0){
            binary[index++] = number%2;
            number = number/2;
        }

        for(int i = 0; i < 16 ;i++)
            System.out.print(binary[15 - i]);
        System.out.println();
    }
}
