package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TexttestFixture {

    public static void main(String[] args) {
        String expectedFileName = "expected.txt";
        System.out.println("OMGHAI!");


        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6)
        };

        int days = 2;
        String fileName;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            fileName = args[1];
            writeToFile(items, days, fileName);
            Path expectedPath = Paths.get(expectedFileName);
            Path actualPath = Paths.get(fileName);
            compareFiles(expectedPath, actualPath);
        }
        else {
            writeSystemOut(items, days);
        }

    }

    private static void writeSystemOut(Item[] items, int days) {

        GildedRose app = new GildedRose(items);
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

    private static void writeToFile(Item[] items, int days, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            GildedRose app = new GildedRose(items);
            for (int i = 0; i < days; i++) {

                writer.println("-------- day " + i + " --------");
                writer.println("name, sellIn, quality");
                for (Item item : items) {
                    writer.println(item);
                }
                writer.println();
                app.updateQuality();
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void compareFiles(Path expected, Path actual) {
        try (BufferedReader bf1 = Files.newBufferedReader(expected);
             BufferedReader bf2 = Files.newBufferedReader(actual)) {

            long lineNumber = 1;
            String line1, line2;
            while ((line1 = bf1.readLine()) != null) {
                line2 = bf2.readLine();
                if(line2 == null) {
                    System.out.println("Actual file is short but they are same until lineNumber: " + lineNumber);
                    return;
                }
                else if (!line1.equals(line2)) {
                    //return lineNumber;
                    System.out.println("Files are not equal in line " + lineNumber );
                    System.out.println("Expected: " );
                    System.out.println(line1);
                    System.out.println("Actual: " );
                    System.out.println(line2);
                    return;
                }
                lineNumber++;
            }
            if(bf2.readLine() != null) {
                System.out.println("They are same until lineNumber: " + lineNumber + ", expected.txt is short.");
            }
            else {
                System.out.println("Files are equal");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
