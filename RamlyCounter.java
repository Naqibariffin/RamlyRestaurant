/**
 * Program description: Develop a Java application to manage the operations of a restaurant, focusing on customer orders and billing processes.
 *                      The system will handle customer information,menu items, and transaction records, including payment calculations
 * Programmer: NURATHRISHA ELISA BINTI YUSRI, MUHAMMAD NAQIB ASYRAAF BIN ARIFFIN, NUR FARHANA BINTI FAUZI
 * Date: 3 July 2024
 */

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RamlyCounter extends JFrame implements ActionListener {
    // Declare variables for the frame components and data structures
    private JFrame RamlyCounterFrame;
    private JLabel countLabel;
    private int count, i = 0;
    private JButton showDataButton, addNewCustomerButton, nextQueueButton;
    JTextArea queueContent1, queueContent2, queueContent3, receiptContent1, receiptContent2, receiptContent3, recordContent;
    JButton paymentButton1, paymentButton2, paymentButton3, receiptButton1, receiptButton2, receiptButton3, recordButton;
    JScrollPane scrollPane;
    
    // Queues for different counters and stack for completed orders
    Queue<CustomerOrderInformation> queueCounter1 = new LinkedList<>();
    Queue<CustomerOrderInformation> queueCounter2 = new LinkedList<>();
    Queue<CustomerOrderInformation> queueCounter3 = new LinkedList<>();
    Stack<CustomerOrderInformation> completeStack = new Stack<>();
    
    // ArrayList to hold customer order information
    ArrayList<CustomerOrderInformation> custOrderList = new ArrayList<>();
    CustomerOrderInformation custOrderItem, currentCheckoutCust;

    // Constructor to initialize the frame and components
    public RamlyCounter() {
        RamlyCounterFrame = new JFrame();
        // Set up the frame
        RamlyCounterFrame.setTitle("Ramly Restaurant System");
        RamlyCounterFrame.setSize(800, 400);
        RamlyCounterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RamlyCounterFrame.setLocationRelativeTo(null);
        RamlyCounterFrame.setVisible(true);

        // Set layout for the frame
        RamlyCounterFrame.setLayout(new BorderLayout());

        // North Panel for control buttons and count display
        JPanel northPanel = new JPanel();
        countLabel = new JLabel("Count: " + count);
        showDataButton = new JButton("Show All Customer Data");
        addNewCustomerButton = new JButton("Load All Customer Information");
        nextQueueButton = new JButton("Next Customer");
        northPanel.add(countLabel);
        addNewCustomerButton.addActionListener(this);
        northPanel.add(addNewCustomerButton);
        showDataButton.addActionListener(this);
        northPanel.add(showDataButton);
        nextQueueButton.addActionListener(this);
        northPanel.add(nextQueueButton);
        RamlyCounterFrame.add(northPanel, BorderLayout.NORTH);

        // Center Panel with counters
        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        RamlyCounterFrame.add(centerPanel, BorderLayout.CENTER);

        // Create panels for each counter
        JPanel counter1 = createCounterPanel("Counter 1", null);
        JPanel counter2 = createCounterPanel("Counter 2", null);
        JPanel counter3 = createCounterPanel("Counter 3", null);
        centerPanel.add(counter1);
        centerPanel.add(counter2);
        centerPanel.add(counter3);

        // South Panel for record button
        JPanel southPanel = new JPanel();
        recordButton = new JButton("Record");
        recordButton.addActionListener(this);
        southPanel.add(recordButton);
        RamlyCounterFrame.add(southPanel, BorderLayout.SOUTH);

        // Initialize JTextAreas for receipts and records
        receiptContent1 = new JTextArea();
        receiptContent1.setEditable(false);
        receiptContent2 = new JTextArea();
        receiptContent2.setEditable(false);
        receiptContent3 = new JTextArea();
        receiptContent3.setEditable(false);
        recordContent = new JTextArea();
        recordContent.setEditable(false);
    }

    // Action performed method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        // Display error message if no customer data is loaded
        if (count == 0 && e.getSource() != addNewCustomerButton) {
            JOptionPane.showMessageDialog(null, "Please reload customer data by clicking 'Load All Customer Information'", "No data exists!", JOptionPane.ERROR_MESSAGE);
        } else {
            // Show all customer data button action
            if (e.getSource() == showDataButton) {
                new ShowCustomerData();
            }
            // Load customer data from file
            else if (e.getSource() == addNewCustomerButton) {
                count = 0;
                try (BufferedReader br = new BufferedReader(new FileReader("customerList.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        // Parse each line of customer data
                        StringTokenizer tokenizer = new StringTokenizer(line, ",");
                        String customerId = tokenizer.nextToken();
                        String customerName = tokenizer.nextToken();
                        int tableNo = Integer.parseInt(tokenizer.nextToken());
                        String orderId = tokenizer.nextToken();
                        String product = tokenizer.nextToken();
                        double price = Double.parseDouble(tokenizer.nextToken());
                        int quantity = Integer.parseInt(tokenizer.nextToken());
                        String dateTime = tokenizer.nextToken();

                        // Create CustomerOrderInformation object and add to list
                        custOrderItem = new CustomerOrderInformation(customerId, customerName, tableNo, orderId, product, price, quantity, dateTime);
                        custOrderList.add(custOrderItem);
                        count++;
                        countLabel.setText("Count: " + count);
                    }
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
            // Process next customer button action
            else if (e.getSource() == nextQueueButton) {
                // Check if all counters are full
                if (queueCounter1.size() == 5 && queueCounter2.size() == 5 && queueCounter3.size() == 5) {
                    JOptionPane.showMessageDialog(null, "All 3 counters are full. Please checkout customers.", "Counter is full!", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Process the next customer based on conditions
                    custOrderItem = custOrderList.get(i);
                    // Adjust queue based on customer quantity and counter availability
                    if ((((queueCounter2.size() < 5 || queueCounter1.size() < 5) && queueCounter3.size() == 5) && custOrderList.get(i + 1).getItemQuantity() <= 5) || ((queueCounter3.size() < 5 && (queueCounter2.size() == 5 || queueCounter1.size() == 5)) && custOrderList.get(i + 1).getItemQuantity() > 5)) {

                        CustomerOrderInformation tempObj;
                        tempObj = custOrderList.get(i);
                        custOrderItem = custOrderList.get(i + 1);
                        custOrderList.set(i + 1, tempObj);
                    }
                    // Add customer to appropriate queue
                    if (custOrderItem.getItemQuantity() > 5) {
                        if (queueCounter3.size() < 5) {
                            i++;
                            queueCounter3.add(custOrderItem);
                        }
                    } else {
                        if (queueCounter2.size() < queueCounter1.size() && queueCounter2.size() < 5) {
                            i++;
                            queueCounter2.add(custOrderItem);
                        } else if (queueCounter1.size() < 5) {
                            i++;
                            queueCounter1.add(custOrderItem);
                        }
                    }
                }
            }
            // Process payment button actions for each counter
            else if (e.getSource() == paymentButton1 || e.getSource() == paymentButton2 || e.getSource() == paymentButton3) {
                if (e.getSource() == paymentButton1) {
                    currentCheckoutCust = queueCounter1.poll();
                    receiptContent1.append(currentCheckoutCust.getReceiptInfo("Counter 1"));
                } else if (e.getSource() == paymentButton2) {
                    currentCheckoutCust = queueCounter2.poll();
                    receiptContent2.append(currentCheckoutCust.getReceiptInfo("Counter 2"));
                } else if (e.getSource() == paymentButton3) {
                    currentCheckoutCust = queueCounter3.poll();
                    receiptContent3.append(currentCheckoutCust.getReceiptInfo("Counter 3"));
                }
                // Update count and push completed order to stack
                if (currentCheckoutCust != null) {
                    count--;
                    countLabel.setText("Count: " + count);
                    completeStack.push(currentCheckoutCust);
                } else {
                    JOptionPane.showMessageDialog(null, "Queue is empty", "No customer!", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Display receipts for each counter
            else if(e.getSource() == receiptButton1 || e.getSource() == receiptButton2 || e.getSource() == receiptButton3) {
                if (e.getSource() == receiptButton1) {
                    scrollPane = new JScrollPane(receiptContent1);
                } else if (e.getSource() == receiptButton2) {
                    scrollPane = new JScrollPane(receiptContent2);
                } else {
                    scrollPane = new JScrollPane(receiptContent3);
                }
                scrollPane.setPreferredSize(new Dimension(350, 450));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                JOptionPane.showMessageDialog(null, scrollPane, "Display Receipt", JOptionPane.INFORMATION_MESSAGE);
            }
            // Update JTextAreas with current queue contents
            setContentInTextArea(queueCounter1, queueCounter2, queueCounter3);
            // Display record of completed orders
            if (e.getSource() == recordButton) {
                while (!completeStack.isEmpty()) {
                    CustomerOrderInformation orderInfo = completeStack.pop();
                    recordContent.append(orderInfo.toString());
                }
                scrollPane = new JScrollPane(recordContent);
                scrollPane.setPreferredSize(new Dimension(350, 450));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                JOptionPane.showMessageDialog(null, scrollPane, "Display Record", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Method to create panels for each counter
    private JPanel createCounterPanel(String title, String data) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        JPanel buttonPanel = new JPanel();

        // Create JTextArea and buttons based on counter title
        if (title.equals("Counter 1")) {
            queueContent1 = new JTextArea();
            queueContent1.setEditable(false);
            queueContent1.setText(data);
            panel.add(new JScrollPane(queueContent1), BorderLayout.CENTER);
            paymentButton1 = new JButton("Checkout " + title);
            receiptButton1 = new JButton("Receipt ");
            paymentButton1.addActionListener(this);
            receiptButton1.addActionListener(this);
            buttonPanel.add(paymentButton1);
            buttonPanel.add(receiptButton1);
        } else if (title.equals("Counter 2")) {
            queueContent2 = new JTextArea();
            queueContent2.setEditable(false);
            queueContent2.setText(data);
            panel.add(new JScrollPane(queueContent2), BorderLayout.CENTER);
            paymentButton2 = new JButton("Checkout " + title);
            receiptButton2 = new JButton("Receipt ");
            paymentButton2.addActionListener(this);
            receiptButton2.addActionListener(this);
            buttonPanel.add(paymentButton2);
            buttonPanel.add(receiptButton2);
        } else {
            queueContent3 = new JTextArea();
            queueContent3.setEditable(false);
            queueContent3.setText(data);
            panel.add(new JScrollPane(queueContent3), BorderLayout.CENTER);
            paymentButton3 = new JButton("Checkout " + title);
            receiptButton3 = new JButton("Receipt ");
            paymentButton3.addActionListener(this);
            receiptButton3.addActionListener(this);
            buttonPanel.add(paymentButton3);
            buttonPanel.add(receiptButton3);
        }

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    // Method to update JTextAreas with current queue contents
    private void setContentInTextArea(Queue q1, Queue q2, Queue q3) {
        // Clear JTextAreas
        queueContent1.setText(null);
        queueContent2.setText(null);
        queueContent3.setText(null);

        // Append queue contents for each counter to respective JTextAreas
        queueContent1.append(
            "**********************************\n" +
            "Queue Customer For Counter 1\n" +
            "**********************************\n");

        queueContent2.append(
            "**********************************\n" +
            "Queue Customer For Counter 2\n" +
            "**********************************\n");

        queueContent3.append(
            "**********************************\n" +
            "Queue Customer For Counter 3\n" +
            "**********************************\n");

        // Convert Queues to ArrayLists for easier iteration
        ArrayList<CustomerOrderInformation> listQ1 = new ArrayList<>(q1);
        ArrayList<CustomerOrderInformation> listQ2 = new ArrayList<>(q2);
        ArrayList<CustomerOrderInformation> listQ3 = new ArrayList<>(q3);

        // Append customer information to respective JTextAreas
        for (int j = 0; j < listQ1.size(); j++) {
            queueContent1.append("=============== Customer " + (j + 1) + "===============\n");
            CustomerOrderInformation currentOrder = listQ1.get(j);
            queueContent1.append(currentOrder.getCustQueueInfo());
        }
        for (int j = 0; j < listQ2.size(); j++) {
            queueContent2.append("=============== Customer " + (j + 1) + "===============\n");
            CustomerOrderInformation currentOrder = listQ2.get(j);
            queueContent2.append(currentOrder.getCustQueueInfo());
        }
        for (int j = 0; j < listQ3.size(); j++) {
            queueContent3.append("=============== Customer " + (j + 1) + "===============\n");
            CustomerOrderInformation currentOrder = listQ3.get(j);
            queueContent3.append(currentOrder.getCustQueueInfo());
        }
    }
}
