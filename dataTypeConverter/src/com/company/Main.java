package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 0 -> big endian 1-> little endian
        Scanner scanner = new Scanner(System.in);
        System.out.println("Byte ordering:");
        String byteOrdering = scanner.nextLine();
        System.out.println("Floating point size: (bytes)");
        String floatingPointSize = scanner.nextLine();


        InputHandler inputHandler = new InputHandler("input.txt");

        DataTypeManager dataTypeManager = new DataTypeManager(processFloatingPointSize(floatingPointSize), processByteOrdering(byteOrdering));

        for(String row: inputHandler.getRowList()) {
            if(!row.isEmpty())
                dataTypeManager.addTorawList(row);
        }
        //dataTypeManager.printRawList();
        dataTypeManager.convert();
        //dataTypeManager.printNumberList();
    }
    public static int processFloatingPointSize(String floatingPointSize) {
        //left only digits
        floatingPointSize = floatingPointSize.replaceAll("[^.0123456789]","");
        return Integer.parseInt(floatingPointSize);
    }
    public static int processByteOrdering(String byteOrdering){
        // 0 -> big endian 1-> little endian
        if(byteOrdering.contains("big") || byteOrdering.contains("Big") || byteOrdering.contains("BIG") ){
            return 0;
        }
        else if (byteOrdering.contains("little") || byteOrdering.contains("Little") || byteOrdering.contains("LITTLE"))
            return 1;
        else
            System.out.println("Warning: Can't understand if input is big or little endian, computations will be performed based on big endian.");
        return 0;
    }
}
