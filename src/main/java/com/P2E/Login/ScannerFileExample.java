package com.P2E.Login;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileExample {
    public static void main(String[] args) {
        try {
            // Create a Scanner object to read from a file
//            File file = new File("example.txt");
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Reads each line
                System.out.println(line);
            }

            // Close the scanner
            scanner.close();
        } catch (Exception e) {
            System.out.println("File not found!");
        }
    }
}
