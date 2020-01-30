package stockcontrolsystem;

import java.io.*;
import javax.swing.JOptionPane;

public class StockControlSystem {

    //integer defined to keep reference about amount of space used in the array
    //private static int i = DataImport.count();

    public static void main(String[] args) throws Exception {
/*
        String userName = "Admin";
        String userPassword = "login";

        System.out.println("\n\n");
        System.out.print("                             L O A D I N G");
        for (int x = 0; x < 10; x++) {
            Thread.sleep(250); //allow a pause of 250 milliseconds
            System.out.print(" .");
            if (x == 9) {
                System.out.println("\n\n\n");
            }
        }

        logIn(userName, userPassword);
*/
        programStart();

    }

    public static void programStart() throws IOException, Exception {

        String app = "open";

        do {

            int returnMainPage = mainPage();

            if (returnMainPage == 1) {
                addNewItem();
            } else if (returnMainPage == 2) {
                changeStock();
            } else if (returnMainPage == 3) {
                removeItem();
            } else if (returnMainPage == 4) {
                viewAllItems();
            } else if (returnMainPage == 5) {
                searchItemCode();
            } else if (returnMainPage == 6) {
                similarityCheck();
            } else if (returnMainPage == 7) {
                exitSystem();
            }

        } while (app.equals("open"));

    }

    public static void logIn(String name, String pass) {

        String userInput;
        String userInputPass;

        do {
            userInput = JOptionPane.showInputDialog("Stock Control System\n\n"
                    + "Input is case sensitive\n"
                    + "Input username :\n\n"
                    + "Default username : Admin");
            if (userInput == null) {
                JOptionPane.showMessageDialog(null, "Exiting... Press OK !");
                System.exit(0);
            }

            if (!userInput.equals(name)) {
                JOptionPane.showMessageDialog(null, "Wrong username\n"
                        + "Account not found\n"
                        + "Press enter to try again !");
            }

        } while (!userInput.equals(name));

        do {
            userInputPass = JOptionPane.showInputDialog("Input is case sensitive\n"
                    + "Input password for " + userInput + " :"
                    + "\n\nDefault password : login");
            if (userInputPass == null) {
                JOptionPane.showMessageDialog(null, "Exiting... Press OK !");
                System.exit(0);
            }

            if (!userInputPass.equals(pass)) {
                JOptionPane.showMessageDialog(null, "Wrong password\n"
                        + "NOTE : PASSWORD IS CASE SENSITIVE\n"
                        + "Press enter to try again !");
            }
        } while (!userInputPass.equals(pass));

    }

    //method which displays a switch menu
    public static int mainPage() {

        int reply = 0;
        String choice = "";
        boolean condition = true;

        while (condition) {

            String message = "Stock Control System\n\n"
                    + "Choose what you want to do !\n\n"
                    + "1. Add new item\n"
                    + "2. Change stock quantity of an item\n"
                    + "3. Remove an item from the inventory\n"
                    + "4. Show all items in inventory\n"
                    + "5. Search by item code\n"
                    + "6. Check for similar item code\n"
                    + "7. Exit the program\n\n";

            int choiceCase = InputData.inputInt(message);

            if (choiceCase < 0) {
                exitSystem();
            }

            switch (choiceCase) {
                case 1:
                    reply = 1;
                    condition = false;
                    break;
                case 2:
                    reply = 2;
                    condition = false;
                    break;
                case 3:
                    reply = 3;
                    condition = false;
                    break;
                case 4:
                    reply = 4;
                    condition = false;
                    break;
                case 5:
                    reply = 5;
                    condition = false;
                    break;
                case 6:
                    reply = 6;
                    condition = false;
                    break;
                case 7:
                    reply = 7;
                    condition = false;
                    break;
                case 8:
                    reply = 8;
                    condition = false;
                    break;
                case 9:
                    reply = 9;
                    condition = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice\n" + "Try again !");
            }

        }

        return reply;
    }

    //method to add new item to the array
    public static void addNewItem() throws Exception {

        String messageItemName = "Input item's name : ";
        String itemName = InputData.inputString(messageItemName);
        if (itemName == null) {
            return;
        }

        String messageItemCode = "Input item's code : ";
        String itemCode = InputData.inputString(messageItemCode);
        if (itemCode == null) {
            return;
        }

        String messagePrice = "Input item's price : ";
        double itemPrice = InputData.inputDouble(messagePrice);
        if (itemPrice < 0) {
            return;
        }

        String messageQuantity = "Input item's quantity in stock : ";
        int quantity = InputData.inputInt(messageQuantity);
        if (quantity < 0) {
            return;
        }

        JOptionPane.showMessageDialog(null, "Item successfully added to inventory!\n"
                + "Press Enter to return to main page");
 
        DataExport.addItemToFile(itemName, itemCode, itemPrice, quantity);
    }

    //method to change the stock value of an item
    public static void changeStock() throws IOException {

        Items[] inventory = new Items[DataImport.count()];
        inventory = DataImport.importInventory();
        int newStock = 0;
        int searchResult = -1;
        int oldStock = 0;
        String search = JOptionPane.showInputDialog("Enter item code you want to change stock : ");
        for (int x = 0; x < DataImport.count(); x++) {
            if (inventory[x].getItemCode().equalsIgnoreCase(search)) {
                searchResult = x;
                oldStock = inventory[x].getQuantity();
            }
        }
        if (searchResult == -1) {
            JOptionPane.showMessageDialog(null, "No matching item !\nPress ENTER to return to main page !");
        } else {

            do {
                try {
                    newStock = Integer.parseInt(JOptionPane.showInputDialog(null, "The current stock is " + inventory[searchResult].getQuantity() + "\nWhat is the new value ?"));
                } catch (Exception e) {
                }
                if (newStock <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input !\nPress Enter to try again !");
                } else {
                    JOptionPane.showMessageDialog(null, "Item code : " + inventory[searchResult].getItemCode() + "\nStock successfully changed from " + oldStock + " to " + newStock);
                }
                inventory[searchResult].setQuantity(newStock);
            } while (newStock <= 0);

        }

        DataExport.writeToFile(inventory);

    }

    //method to delete all items in the array
    //method to remove only one item in the array
    public static void removeItem() throws IOException {

        Items[] inventory = new Items[DataImport.count()];
        inventory = DataImport.importInventory();

        int searchResult = -1;
        String search = JOptionPane.showInputDialog("Enter item code you want to remove : ");
        for (int x = 0; x < DataImport.count(); x++) {
            if (inventory[x].getItemCode().equalsIgnoreCase(search)) {
                searchResult = x;
            }
        }
        if (searchResult == -1) {
            JOptionPane.showMessageDialog(null, "No match found !\nPress ENTER to continue!");
        } else {
            JOptionPane.showMessageDialog(null, "Item removed successfully\nPress ENTER to continue...");
            inventory = removeTheElement(inventory, searchResult);
            }

        DataExport.updateFile(inventory);

    }

    public static Items[] removeTheElement(Items[] items, int index) {

        Items[] anotherItems = new Items[items.length - 1];

        for (int x = 0, k = 0; x < items.length; x++) {

            if (x == index) {
                continue;
            }

            anotherItems[k++] = items[x];
        }

        return anotherItems;
    }

    //method to check for similar item codes
    public static void similarityCheck() throws IOException {

        Items[] inventory = new Items[DataImport.count()];
        inventory = DataImport.importInventory();

        String choose = "";
        int outcome = 0;
        int similarity = 0;
        int choice = 0;
        int index = -1;
        int index1 = -1;
        int index2 = -1;
        String ask = "";
        for (int a = 0; a < DataImport.count(); a++) {
            for (int j = a + 1; j < DataImport.count(); j++) {
                outcome = equalsMethod(inventory[a].getItemCode(), inventory[j].getItemCode());
                if (outcome == -1) {
                    similarity = 1;
                    index1 = a;
                    index2 = j;
                }
            }
        }

        if (similarity == 0) {
            JOptionPane.showMessageDialog(null, "There is no similarity in item codes !");
        } else if (similarity == 1) {
            boolean deletion = true;
            while (deletion) {
                ask = JOptionPane.showInputDialog(null, "There is a similarity !\nTwo items found with the same item code\n"
                        + "Do you want to delete one of them ? (y/n)");

                if (ask == null) {
                    JOptionPane.showMessageDialog(null, "Cancelled... returning to main page! Press ENTER to continue !");
                    deletion = false;
                } else if (ask.equalsIgnoreCase("y")) {

                    while (choice != 1 && choice != 2) {
                        try {
                            choose = JOptionPane.showInputDialog(null, "Which item you want to remove ?\n"
                                    + "1. Item code : " + inventory[index1].getItemName()
                                    + "\n2. Item code : " + inventory[index2].getItemName());
                            if (choose == null) {

                                return;
                            }
                            choice = Integer.parseInt(choose);

                        } catch (Exception e) {
                        }
                        if (choice != 1 && choice != 2) {
                            JOptionPane.showMessageDialog(null, "Invalid Input\nPress ENTER to try again");
                        }
                    }

                    if (choice == 1) {
                        index = index1;
                    } else if (choice == 2) {
                        index = index2;
                    }

                    JOptionPane.showMessageDialog(null, inventory[index].getItemCode() + " - " + inventory[index].getItemName() + " has been successfully removed from inventory !");

                    inventory = removeItemSimilarity(index, inventory);
                    deletion = false;
                } else if (ask.equalsIgnoreCase("n")) {
                    JOptionPane.showMessageDialog(null, "No items removed !\nPress ENTER to return to main page !");
                    deletion = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Press ENTER to try again !");
                }

            }
        }

        DataExport.updateFile(inventory);

    }

    //method to remove similarity
    public static Items[] removeItemSimilarity(int del, Items[] items) {

        items = removeTheElement(items, del);

        return items;
    }

    public static int equalsMethod(String x, String y) {
        int answer = 0;
        if (x.equalsIgnoreCase(y)) {
            answer = -1;
        }
        return answer;
    }

    //method to display all items
    public static void viewAllItems() throws IOException {

        Items[] inventory = new Items[DataImport.count()];
        inventory = DataImport.importInventory();

        if (DataImport.count() == 0) {
            JOptionPane.showMessageDialog(null, "No item in inventory to display !");
        } else {
            System.out.printf("_____________________________________________________________________________________________\n");
            System.out.printf("| %31s %17s %21s %20s", "   Item Name   |", "Item Code   |", " Item Price (Rs)  |", "Quantity in stock  |\n");
            System.out.printf("=============================================================================================\n");
            for (int x = 0; x < DataImport.count(); x++) {
                inventory[x].display();
            }
            System.out.printf("---------------------------------------------------------------------------------------------");
            System.out.println("\n\n\n");
        }
    }

    //method to sort values in the array in alphabetical order
    public static Items[] sorting(Items[] items) {

        //creating temporary variables to allow swapping of data if not sorted
        String tempCode, tempName;
        int tempQuantity;
        double tempPrice;

        for (int x = 1; x < DataImport.count(); x++) {
            for (int j = x; j > 0; j--) {

                if (items[j].getItemName().compareToIgnoreCase(items[j - 1].getItemName()) < 0) {

                    tempCode = items[j].getItemCode();
                    tempName = items[j].getItemName();
                    tempQuantity = items[j].getQuantity();
                    tempPrice = items[j].getItemPrice();

                    items[j].setItemCode(items[j - 1].getItemCode());
                    items[j].setItemName(items[j - 1].getItemName());
                    items[j].setQuantity(items[j - 1].getQuantity());
                    items[j].setItemPrice(items[j - 1].getItemPrice());

                    items[j - 1].setItemCode(tempCode);
                    items[j - 1].setItemName(tempName);
                    items[j - 1].setQuantity(tempQuantity);
                    items[j - 1].setItemPrice(tempPrice);
                }
            }
        }

        return items;
    }

    //method to search for an item using item code
    public static void searchItemCode() throws IOException {

        Items[] inventory = new Items[DataImport.count()];
        inventory = DataImport.importInventory();

        int searchResult = -1;
        String search = JOptionPane.showInputDialog("Enter item code you want to search : ");
        for (int x = 0; x < DataImport.count(); x++) {
            if (inventory[x].getItemCode().equalsIgnoreCase(search)) {
                searchResult = x;
            }
        }

        if (searchResult == -1) {
            JOptionPane.showMessageDialog(null, "No result found !");
        } else {
            JOptionPane.showMessageDialog(null, "Item name : " + inventory[searchResult].getItemName() + "\n"
                    + "Item code : " + inventory[searchResult].getItemCode() + "\n"
                    + "Item Price : Rs " + inventory[searchResult].getItemPrice() + "\n"
                    + "Item Quantity : " + inventory[searchResult].getQuantity() + " units");
        }

    }

    //method to show the storage capacity of the array
    //method for system exit
    public static void exitSystem() {
        System.out.println("\n\nExit Successful !\n\n\n");

        System.out.println("   _____                 _   ____               _ ");
        System.out.println("  / ____|               | | |  _ \\             | |");
        System.out.println(" | |  __  ___   ___   __| | | |_) |_   _  ___  | |");
        System.out.println(" | | |_ |/ _ \\ / _ \\ / _` | |  _ <| | | |/ _ \\ | |");
        System.out.println(" | |__| | (_) | (_) | (_| | | |_) | |_| |  __/ |_|");
        System.out.println("  \\_____|\\___/ \\___/ \\__,_| |____/ \\__, |\\___| (_)");
        System.out.println("                                    __/ |         ");
        System.out.println("                                   |___/          ");

        System.exit(0);
    }

}
