package com.mihanjk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileUtils {
    private static final String NAME_OF_FILE = "cache.txt";

    public static void writeToFile(String input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAME_OF_FILE, true))) {
            writer.write(input);
        } catch (java.io.IOException e) {
            System.err.println("Can't write into file: " + NAME_OF_FILE);
        }
    }

    public static String readFromFile(String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(NAME_OF_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().contains(target)) return line;
            }
        } catch (java.io.IOException e) {
            System.err.println("Can't write into file: " + NAME_OF_FILE);
        }
        return null;
    }
}
