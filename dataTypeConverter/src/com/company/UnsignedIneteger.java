package com.company;

public class UnsignedIneteger extends Number {
    private String row;
    private int number;
    private int endian;
    private int[] binary = new int[16];
    private String hexString;

    public UnsignedIneteger(String row, int endian) {
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
        for(int i=0; i< row.length(); i++){
            if (row.charAt(i) == 'u')
                row = row.substring(0, i);
        }
        number = Integer.parseInt(row);
    }

    private void convert(){
        int[] localBinary = new int[16];
        int index = 0;

        while(number > 0){
            localBinary[index++] = number%2;
            number = number/2;
        }

        for(int i = 0; i < 16 ;i++){
            binary[i] = localBinary[15 - i];
            //System.out.print(localBinary[15 - i]);
        }
        //System.out.println();
    }
}
