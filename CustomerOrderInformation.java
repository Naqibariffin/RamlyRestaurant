/**
 * Program description: Develop a Java application to manage the operations of a restaurant, focusing on customer orders and billing processes.
 *                      The system will handle customer information,menu items, and transaction records, including payment calculations
 * Programmer:NURATHRISHA ELISA BINTI YUSRI, MUHAMMAD NAQIB ASYRAAF BIN ARIFFIN, NUR FARHANA BINTI FAUZI
 * Date: 3 July 2024
 */

public class CustomerOrderInformation {
    // Declaration of attributes
    private String customerId;
    private String customerName;
    private int tableNumber;
    private String orderId;
    private String itemsName;
    private double itemPrice;
    private int quantity;
    private String ordersTime;

    // Constructor w/o parameter
    public CustomerOrderInformation() {
        customerId = null;
        customerName = null;
        tableNumber = 0;
        orderId = null;
        itemsName = null;
        itemPrice = 0.0;
        quantity = 0;
        ordersTime = null;
    }

    // Constructor with parameter
    public CustomerOrderInformation(String custId, String custName, int tableNum,String orderID, String itemName, double itemTotalPrice, int itemQuantity, String orderTime) {
        customerId = custId;
        customerName = custName;
        tableNumber = tableNum;
        orderId = orderID;
        itemsName = itemName;
        itemPrice = itemTotalPrice;
        quantity = itemQuantity;
        ordersTime = orderTime;
    }

    // Setter for customer Id
    public void setCustomerId(String custId) {
        customerId = custId;
    }

    // Setter for customer name
    public void setCustomerName(String custName) {
        customerName = custName;
    }

    // Setter for table number
    public void setTableNumber(int tableNum) {
        tableNumber = tableNum;
    }

    // Mutator for order id
    public void setOrderId(String orderID) {
        orderId = orderID;
    }

    // Mutator for item name
    public void setItemsName(String itemName) {
        itemsName = itemName;
    }

    // Mutator for item price
    public void setItemPrice(double itemTotalPrice) {
        itemPrice = itemTotalPrice;
    }

    // Mutator for quantity
    public void setQuantity(int itemQuantity) {
        quantity = itemQuantity;
    }

    // Mutator for time
    public void setOrdersTime(String orderTime) {
        ordersTime = orderTime;
    }

    // Setter for all
    public void setAll(String custId, String custName, int tableNum,String orderID, String itemName, double itemTotalPrice, int itemQuantity, String orderTime) {
        customerId = custId;
        customerName = custName;
        tableNumber = tableNum;
        orderId = orderID;
        itemsName = itemName;
        itemPrice = itemTotalPrice;
        quantity = itemQuantity;
        ordersTime = orderTime;
    }

    // Accessor for customer id
    public String getCustId() {
        return customerId;
    }
    // Accessor for customer name
    public String getCustName() {
        return customerName;
    }
    // Accessor for table number
    public int getTableNum() {
        return tableNumber;
    }
    // Accessor for order id
    public String getOrderID() {
        return orderId;
    }
    // Accessor for item name
    public String getItemName() {
        return itemsName;
    }

    // Accessor for item price
    public double getItemTotalPrice() {
        return itemPrice;
    }

    // Accessor for quantity
    public int getItemQuantity() {
        return quantity;
    }

    // Accessor for order time
    public String getOrderTime() {
        return ordersTime;
    }
    
    // Accessor for customer queue information
    public String getCustQueueInfo(){
        return "Customer ID: " + customerId + 
        "\nName: " + customerName + 
        "\nOrder ID: " + orderId+
        "\nQuantity: " + quantity+
        "\nPrice: " +(itemPrice*quantity)+"\n";
    }
    
    // Accessor for receipt informatio
    public String getReceiptInfo(String counter) {

        String data = 
            "=============================================="+
            "\nRECEIPT FOR RAMLY BURGER RESTAURANT"+
            "\nOrder Time: " + ordersTime ; 
        if(counter != null){
            data+= "\nCounter: "+counter; 
        }
        data+="\n=============================================="+
        "\nCUSTOMER INFORMATION"+
        "\n=============================================="+
        "\nCustomer ID: " + customerId + 
        "\nName: " + customerName +
        "\nTable Number: " + tableNumber +
        "\n=============================================="+
        "\nORDER DETAILS"+
        "\n=============================================="+
        "\nOrder ID: " + orderId +
        "\nItem Name: " + itemsName + 
        "\nItem Price Per Each: RM " + itemPrice + 
        "\nQuantity: " + quantity + 
        "\n=============================================="+
        "\nTotal Amount: RM " + (itemPrice*quantity) + 
        "\n=============================================="+
        "\n                     ♡ NO REFUND ♡                  "+
        "\n==============================================\n";
        return data;
    }

    @Override
    public String toString() {

        String data = 
            "=============================================="+
            "\nRECEIPT FOR RAMLY BURGER RESTAURANT"+
            "\nOrder Time: " + ordersTime +"\n=============================================="+
            "\nCUSTOMER INFORMATION"+
            "\n=============================================="+
            "\nCustomer ID: " + customerId + 
            "\nName: " + customerName +
            "\nTable Number: " + tableNumber +
            "\n=============================================="+
            "\nORDER DETAILS"+
            "\n=============================================="+
            "\nOrder ID: " + orderId +
            "\nItem Name: " + itemsName + 
            "\nItem Price Per Each: RM " + itemPrice + 
            "\nQuantity: " + quantity + 
            "\n=============================================="+
            "\nTotal Amount: RM " + (itemPrice*quantity) + 
            "\n=============================================="+
            "\n                     ♡ NO REFUND ♡                  "+
            "\n==============================================\n";
        return data;
    }
}
