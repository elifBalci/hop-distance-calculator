package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private String fileName;
    private Scanner scanner;
    private ArrayList<String> rowList;
    private String line;

    public InputHandler(String fileName) {
        this.fileName = fileName;
        rowList = new ArrayList<>();
        readFile();
    }

    private void readFile(){
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println( "Full path name should be provided. FileNotFoundException.\n");
        }
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            rowList.add(line);
        }
    }

    public void printRowList(){
        for (int i=0; i< rowList.size(); i++){
            System.out.println(rowList.get(i));
        }
    }

    public ArrayList<String> getRowList() {
        return rowList;
    }

    public void setRowList(ArrayList<String> rowList) {
        this.rowList = rowList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
