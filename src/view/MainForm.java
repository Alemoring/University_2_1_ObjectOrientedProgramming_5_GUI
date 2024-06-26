package view;

import data.Txt;
import domain.entities.*;
import model.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class MainForm extends JFrame {
    private File path = new File("C:\\Users\\Алемор\\Desktop\\Саша\\Обучение\\ООП\\LW_5\\OOP_LW_5");
    // C:\Users\stud\Desktop\Alemor\LW_5_encode
    // C:\Users\Алемор\Desktop\Саша\Обучение\ООП\LW_5\OOP_LW_5
    private JFrame frame;
    private JButton btnAddRow;
    private JButton btnDeleteRow;
    private JMenuBar menuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenu aboutMenu;
    private JMenuItem aboutProgramItem;
    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JTable table;
    private MyTableModel tableModel;
    private JPanel mainPanel;
    private JPanel eastPanel;
    private JFileChooser fileChooser;
    private String[] types = {
            "Food",
            "Clothes",
            "Technic",
            "Milk"};
    private String[] qualities = {
            "Good",
            "Normal",
            "Bad"};
    ArrayList<Product> products = new ArrayList<>();
    public int readValue(int readedId, String message) {
        // readedID = 1 - readYear, 2 - readMonth, 3 - readDay
        int readed = -1;
        try {
            if (readedId == 1) {
                String line = JOptionPane.showInputDialog(
                        MainForm.this,
                        message);
                readed = Integer.parseInt(line);
                if (readed < 2000 || readed > 3000) {
                    readed = readValue(1, message);
                }
            } else if (readedId == 2) {
                String line = JOptionPane.showInputDialog(
                        MainForm.this,
                        message);
                readed = Integer.parseInt(line);
                if (readed < 1 || readed > 12) {
                    readed = readValue(2, message);
                }
            } else if (readedId == 3) {
                String line = JOptionPane.showInputDialog(
                        MainForm.this,
                        message);
                readed = Integer.parseInt(line);
                if (readed < 1 || readed > 31) {
                    readed = readValue(3, message);
                }
            }else if (readedId == 4) {
                String line = JOptionPane.showInputDialog(
                        MainForm.this,
                        message);
                readed = Integer.parseInt(line);
                if (readed < 1 || readed > 1000) {
                    readed = readValue(4, message);
                }
            }
        }catch (Exception e){
                if (readedId == 1) {
                    JOptionPane.showMessageDialog(MainForm.this, "Invalid value entered :(\n" +
                             "Enter the year from 2000 to 3000\n" + "Try again, you will succeed");
                    readed = readValue(1, message);
                }else if (readedId == 2) {
                    JOptionPane.showMessageDialog(MainForm.this, "Invalid value entered :(\n" +
                            "Enter month from 1 to 12\n" + "Try again, you will succeed");
                    readed = readValue(2, message);
                }else if (readedId == 3){
                    JOptionPane.showMessageDialog(MainForm.this, "Invalid value entered :(\n" +
                            "Enter day from 1 to 31\n" + "Try again, you will succeed");
                    readed = readValue(3, message);
                }else {
                    JOptionPane.showMessageDialog(MainForm.this, "Invalid value entered :(\n" +
                            "Enter the number of months from 1 to 1000\n" + "Try again, you will succeed");
                    readed = readValue(4, message);
                }
        }
        return readed;
    }
    public double readDoubleValue(String message){
        double readed = -1;
        try {
            String line = JOptionPane.showInputDialog(
                    MainForm.this,
                    message);
            readed = Double.parseDouble(line);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(MainForm.this, "Something went wrong :( \n" +
                    "Try again, you will succeed");
            readed = readDoubleValue(message);
        }
        return readed;
    }
    public String readLine(String message){
        String readed = "";
        try {
            readed = JOptionPane.showInputDialog(
                    MainForm.this,
                    message);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(MainForm.this, "Something went wrong :( \n" +
                    "Try again, you will succeed");
            readed = readLine(message);
        }
        return readed;
    }
    public class saveFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.setDialogTitle("Save File");
            // Определение режима - только файл
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(MainForm.this);
            // Если файл выбран, то представим его в сообщении
            if (result == JFileChooser.APPROVE_OPTION ) {
                JOptionPane.showMessageDialog(MainForm.this,
                        "File '" + fileChooser.getSelectedFile() +
                                " ) saved");
                Txt.setFilename(fileChooser.getSelectedFile(), fileChooser.getCurrentDirectory().toString());
                Txt.writeArrayToFile(tableModel.getData());
            }
        }
    }
    public void loadTable(){
        tableModel.changeData(products);
    }
    public class openFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.setDialogTitle("Open the file");
            // Определение режима - только файл
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(MainForm.this);
            // Если файл выбран, то представим его в сообщении
            if (result == JFileChooser.APPROVE_OPTION ) {
                JOptionPane.showMessageDialog(MainForm.this,
                        "File '" + fileChooser.getSelectedFile() +
                                " ) download");
                Txt.setFilename(fileChooser.getSelectedFile(), fileChooser.getCurrentDirectory().toString());
                if (Txt.nullFileOrNotNull()){
                    products = Txt.readProductsFromFile();
                    loadTable();
                }
            }
        }
    }
    public MainForm(){
        super();
        initialization();
        start();
    }
    public void initialization(){
        frame = new JFrame("App for Buy");
        frame.setSize(1200, 800);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setIconImage();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        menuBar = new JMenuBar();

        aboutMenu = new JMenu("About");
        aboutProgramItem = new JMenuItem("About program");
        aboutProgramItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainForm.this, "This program was created by 2nd year student " +
                        "Alexander Morgunov in the direction of ISTB-22-1.\n The program is designed to view, add, delete purchases in table form" +
                        "\n version 0.1");
            }
        });

        helpMenu = new JMenu("Help");
        helpItem = new JMenuItem("Help notes");
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainForm.this, "\"File\" => \"Save File\": Saves data to the selected file\n" +
                        "\"File\" => \"Open File\": Loads data from a file\n" +
                        "\"About Program\": Tells about the program\n" +
                        "\"Add Row\": Adds a new row to the table\n" +
                        "\"Delete Row\": Deletes the selected row in the table");
            }
        });

        fileMenu = new JMenu("File");

        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new saveFile());

        openItem = new JMenuItem("Open");
        openItem.addActionListener(new openFile());

        tableModel = new MyTableModel(products);

        table = new JTable(tableModel);

        btnAddRow = new JButton("Add new product");
        btnAddRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // type, name, purchaseDate, price, address, quality, comment, endDate(Food, Milk), guarantee(Technic),
                // fatContent(Milk)
                int[] typeId = {1, 2, 3, 4};
                String type = (String) JOptionPane.showInputDialog(
                        MainForm.this,
                        "Select product type: ",
                        "Adding a new product",
                        JOptionPane.QUESTION_MESSAGE,
                        null, types, types[0]);
                String name = readLine("Enter product name");
                Calendar purDate = Calendar.getInstance();
                int purDateYear = readValue(1, "Enter the year of purchase");
                int purDateMonth = readValue(2, "Enter the month of purchase") - 1;
                int purDateDay = readValue(3, "Enter the day of purchase");
                purDate.set(purDateYear, purDateMonth, purDateDay);
                double price = readDoubleValue("Enter the cost of the product");
                String address = readLine("Enter your purchase address");
                short realQuality;
                String quality = (String) JOptionPane.showInputDialog(
                        MainForm.this,
                        "Select product quality: ",
                        "Adding a new product",
                        JOptionPane.QUESTION_MESSAGE,
                        null, qualities, qualities[0]);
                if (quality.equals((String) "Good")){
                    realQuality = 0;
                }else if (quality.equals((String) "Normal")){
                    realQuality = 1;
                }else{
                    realQuality = 2;
                }
                String comment = readLine("Enter a comment if you wish.");
                if ("".equals(comment)){
                    comment = "0";
                }
                if (type.equals((String) "Food")){
                    int result = JOptionPane.showConfirmDialog(MainForm.this,
                            "Do you want to enter the expiration date of the product?",
                            "Desire to enter product expiration date",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (result == JOptionPane.YES_OPTION){
                        Calendar endDate = Calendar.getInstance();
                        int endDateYear = readValue(1, "Enter the expiration date year");
                        int endDateMonth = readValue(2, "Enter the expiration date month") - 1;
                        int endDateDay = readValue(3, "Enter the expiration date day");
                        endDate.set(endDateYear, endDateMonth, endDateDay);
                        products.add(new Food(name, purDate, price, address, realQuality, comment, endDate));
                    }else {
                        products.add(new Food(name, purDate, price, address, realQuality, comment));
                    }
                }else if (type.equals((String) "Clothes")){
                    products.add(new Clothes(name, purDate, price, address, realQuality, comment));
                }else if (type.equals((String) "Technic")){
                    int result = JOptionPane.showConfirmDialog(MainForm.this,
                            "Do you want to introduce a product guarantee?",
                            "Desire to introduce a product guarantee",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (result == JOptionPane.YES_OPTION){
                        int guarantee = readValue(4, "Enter the number of months of guarantee");
                        products.add(new Technic(name, purDate, price, address, realQuality, comment, guarantee));
                    }else {
                        products.add(new Technic(name, purDate, price, address, realQuality, comment));
                    }
                }else {
                    double fatContent = readDoubleValue("Enter milk fat content");
                    int result = JOptionPane.showConfirmDialog(MainForm.this,
                            "Do you want to enter the expiration date of the product?",
                            "Desire to enter product expiration date",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (result == JOptionPane.YES_OPTION){
                        Calendar endDate = Calendar.getInstance();
                        int endDateYear = readValue(1, "Enter the expiration date year");
                        int endDateMonth = readValue(2, "Enter the expiration date month") - 1;
                        int endDateDay = readValue(3, "Enter the expiration date day");
                        endDate.set(endDateYear, endDateMonth, endDateDay);
                        products.add(new Milk(name, purDate, price, address, realQuality, comment, endDate, fatContent));
                    }else {
                        products.add(new Milk(name, purDate, price, address, realQuality, comment, fatContent));
                    }
                }
                loadTable();
            }
        });

        btnDeleteRow = new JButton("Delete selected product");
        btnDeleteRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    tableModel.deleteRow(table.getSelectedRow());
                }catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(MainForm.this, "Unselected line");
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(MainForm.this, "Too many rows selected");
                }
            }
        });

        fileChooser = new JFileChooser(path);
    }
    public void start(){
        aboutMenu.add(aboutProgramItem);
        helpMenu.add(helpItem);
        fileMenu.add(saveItem);
        fileMenu.add(openItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);

        eastPanel.add(btnAddRow);
        eastPanel.add(btnDeleteRow);

        mainPanel.add(eastPanel, BorderLayout.WEST);
        mainPanel.add(menuBar, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table));

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
