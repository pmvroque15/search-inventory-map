package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class StoreApp {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Product> inventoryMap = generateInventoryMap("src/main/resources/inventory.csv");

        System.out.println("What is the ID # of the product: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product findProductById = inventoryMap.get(id);
        System.out.println(findProductById);
    }

    static HashMap<Integer, Product> generateInventoryMap(String fileName) {
        HashMap<Integer, Product> idNumberMap = new HashMap<>();
        ArrayList<Product> inventory = generateInventory(fileName);

        for(Product p: inventory) {
            int idNumber = p.getId();

            idNumberMap.put(idNumber, p);
        }

        return idNumberMap;
    }

    static ArrayList<Product> generateInventory(String fileName) {
        ArrayList<Product> inventory = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] lineSplit = line.split("\\|");
                int id = Integer.parseInt(lineSplit[0]);
                String name = lineSplit[1];
                double price = Double.parseDouble(lineSplit[2]);

                Product p = new Product(id, name, price);
                inventory.add(p);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }

}






