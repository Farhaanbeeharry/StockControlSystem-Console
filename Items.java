package stockcontrolsystem;

public class Items {

    private String itemCode;
    private String itemName;
    private int quantity;
    private double itemPrice;

    //default constructor
    public Items() {
        this.itemCode = " ";
        this.itemName = " ";
        this.quantity = 0;
        this.itemPrice = 0.0;
    }

    //explicit constructor
    public Items(String itemCode, String itemName, int quantity, double itemPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    //method to display data
    public void display() {

        System.out.format("|  %30s %17s %21s %20s", itemName + "  |", itemCode + "  |", itemPrice + "  |", quantity + "  |");
        System.out.println(" ");
    }

}
