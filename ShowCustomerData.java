/**
 * Program description: Develop a Java application to manage the operations of a restaurant, focusing on customer orders and billing processes.
 *                      The system will handle customer information,menu items, and transaction records, including payment calculations
 * Programmer: NURATHRISHA ELISA BINTI YUSRI, MUHAMMAD NAQIB ASYRAAF BIN ARIFFIN, NUR FARHANA BINTI FAUZI
 * Date: 3 July 2024
 */

import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowCustomerData extends JFrame implements ActionListener {
    //declare variable
    private JButton closeWindowButton;
    private JFrame frame;

    public ShowCustomerData() {
        // Create the frame
        frame = new JFrame("Customer Data Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500); // Set the size of the frame
        frame.setLocationRelativeTo(null);

        // Read data from the file
        String data = "";
        try (BufferedReader br = new BufferedReader(new FileReader("customerList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                StringTokenizer tokenizer = new StringTokenizer(line,",");   
                String customerId = tokenizer.nextToken();
                String customerName = tokenizer.nextToken();
                int tableNo = Integer.parseInt(tokenizer.nextToken());
                String orderId = tokenizer.nextToken();
                String product = tokenizer.nextToken();
                double price = Double.parseDouble(tokenizer.nextToken());
                int quantity = Integer.parseInt(tokenizer.nextToken());
                String dateTime = tokenizer.nextToken();
                String formattedData =
                    "==============================================\n"+
                    "CUSTOMER INFORMATION\n"+
                    "==============================================\n"+
                    "Order Date & Time: " + dateTime +"\n" +
                    "Table No.: " + tableNo + "\n" +
                    "Customer ID: " + customerId + "\n" +
                    "Customer Name: " + customerName + "\n" +
                    "==============================================\n"+
                    "ORDER DETAILS\n"+
                    "==============================================\n"+
                    "Order ID: " + orderId + "\n" +
                    "Products: " + product + "\n" +
                    "Price: " + price + "\n" +
                    "Quantity: " + quantity + "\n" +
                    "==============================================\n\n";

                data+=formattedData;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a text area to display the data
        JTextArea textArea = new JTextArea(data.toString());
        textArea.setEditable(false); // Make the text area read-only

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        // Set the scroll pane to use vertical scrollbar
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Create a panel with a BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER); // Add text area scroll pane to the center

        // Create a panel for the button
        JPanel panelButton = new JPanel();
        closeWindowButton = new JButton("Close");
        closeWindowButton.addActionListener(this);
        panelButton.add(closeWindowButton);

        // Add the button panel to the bottom of the frame
        panel.add(panelButton, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {  
        if (e.getSource() == closeWindowButton) {
            // Close the window
            frame.dispose();
        }
    }
}
