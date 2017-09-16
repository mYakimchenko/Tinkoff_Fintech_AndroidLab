package com.mihanjk.data.datasources;

import java.io.*;

public class FileDataSource {
    private static final String NAME_OF_FILE = "cache.txt";
    private final File file = new File(NAME_OF_FILE);

    public FileDataSource() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Can't create new file: " + NAME_OF_FILE);
        }
    }

    public void writeToFile(String input) {
        // TODO: 9/16/2017 rewrite older data in file to keep only last data from API
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAME_OF_FILE, true))) {
            if (!isFileContains(input)) {
                writer.write(input + System.lineSeparator());
            }
        } catch (java.io.IOException e) {
            System.err.println("Can't write into file: " + NAME_OF_FILE);
        }
    }

    private boolean isFileContains(String input) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(input)) {
                    return true;
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Can't read from file: " + NAME_OF_FILE);
        }
        return false;
    }

    public String readFromFile(String from, String to, String date) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(from) && line.contains(to) && line.contains(date)) {
                    return line;
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Can't read from file: " + NAME_OF_FILE);
        }
        return null;
    }
}
