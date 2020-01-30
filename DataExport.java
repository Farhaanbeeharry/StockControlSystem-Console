package stockcontrolsystem;

import java.io.*;

public class DataExport {

    public static void updateFile(Items[] inventory) {

        try {
            File outputFile = new File("data/inventory.txt");//create a file
            PrintStream writer = new PrintStream(outputFile);
            //create the writer
            //loop through the array and saves its elements

            for (int i = 0; i < inventory.length; i++) {
                if (!(inventory[i].getItemCode() == null)) {
                    writer.print(inventory[i].getItemCode());
                    writer.print("--");
                    writer.print(inventory[i].getItemName());
                    writer.print("--");
                    writer.print(inventory[i].getItemPrice());
                    writer.print("--");
                    if (i == inventory.length-1) {
                        writer.print(inventory[i].getQuantity());
                    }
                    else { 
                        writer.println(inventory[i].getQuantity());
                    }
                }

            }
            writer.close();//always close file connection
        } catch (FileNotFoundException e) {
            System.err.println("file not found!");
        } // exception handling
    }

    public static void addItemToFile(String itemName, String itemCode, double itemPrice, int quantity) {

        try {
            File inputFile = new File("data/inventory.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile, true));

            bw.newLine();
            bw.write(itemCode + "--" + itemName + "--" + itemPrice + "--" + quantity);

            bw.close();

        } catch (IOException e) {

        }
    }
}
