package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Byte ordering:");
        String byteOrdering = scanner.nextLine();
        System.out.println("Floating point size: (bytes)");
        String floatingPointSize = scanner.nextLine();


        InputHandler inputHandler = new InputHandler("input.txt");

        DataTypeManager dataTypeManager = new DataTypeManager(processFloatingPointSize(floatingPointSize));

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
}
