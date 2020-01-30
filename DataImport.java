package stockcontrolsystem;

import java.io.*;

public class DataImport {

    public static int count() {

        int count = 0;

        try {
            FileReader inputFile = new FileReader("data/inventory.txt");

            BufferedReader inputBuffer = new BufferedReader(inputFile);
            String readItem = inputBuffer.readLine();

            while (readItem != null) {
                count++;
                readItem = inputBuffer.readLine();
            }
        } catch (IOException e) {
        }

        return count;

    }

    public static Items[] importInventory() throws IOException {

        Items[] items = new Items[count()];

        FileReader inputFile = new FileReader("data/inventory.txt");

        BufferedReader inputBuffer = new BufferedReader(inputFile);

        String data;
        data = inputBuffer.readLine();

        while (data != null) {
            for (int i = 0; i < count(); i++) {
                String[] inventory = data.split("--");
                items[i] = new Items();
                items[i].setItemCode(inventory[0]);
                items[i].setItemName(inventory[1]);
                items[i].setItemPrice(Double.parseDouble(inventory[2]));
                items[i].setQuantity(Integer.parseInt(inventory[3]));
                data = inputBuffer.readLine();
            }

        }

        items = StockControlSystem.sorting(items);

        return items;

    }

}
