package mainPackage;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

public class Product {
    private String name;
    private String type;
    private String manufacturer;
    private double price;
    private int quantity;
    private String registryDay;

    public Product() {
        this.name = null;
        this.type = null;
        this.manufacturer = null;
        this.price = 0.0;
        this.quantity = 0;
        this.registryDay = null;
    }

    public Product(String name, String type, String manufacturer, double price, int quantity, String registryDay) {
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.registryDay = registryDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRegistryDay() {
        return registryDay;
    }

    public void setRegistryDay(String registryDay) {
        this.registryDay = registryDay;
    }


    public static void createProduct() {
        Product p = new Product();
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy' at 'HH:mm");
        String d = sdf.format(new Date());
        String name = "";

        try {
            Scanner file = new Scanner(FileCalls.file);

            System.out.println("Product name:");
            name = sc.nextLine();
            System.out.println("Checking if the product already exists...");

            if (FileCalls.searchProduct(name) == null) {
                p.setName(name);
                System.out.println("Type of product:");
                p.setType(sc.nextLine());
                System.out.println("Manufacturer");
                p.setManufacturer(sc.nextLine());
                System.out.println("Price /u:");
                p.setPrice(sc.nextDouble());
                System.out.println("Quantity:");
                p.setQuantity(sc.nextInt());
                p.setRegistryDay(d);
                FileCalls.insertRegistry(p, true);
            } else {
                System.out.println("The product already exists.");
            }

            file.close();
            FileCalls.quantityRemaining();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
            sc.next();
        }
    }

    public static void buyProduct(boolean sell, String name) throws DocumentException {
        int quantity = 0;
        Product p = null;
        String customer = "";
        List<Product> pList = new ArrayList<Product>();
        List<Integer> qList = new ArrayList<Integer>();
        String more = "";
        Scanner sc = new Scanner(System.in);
        int i = 0;

        if (sell) {
            if (i == 0) {
                System.out.println("What is your name dear customer?");
                customer = sc.nextLine();
                i++;
            }
            do {
                do {
                    p = FileCalls.searchProduct("");
                } while (p == null);

                System.out.println("How many " + p.getName() + " do you want to buy " + customer + "?");
                quantity = sc.nextInt();

                if (p.getQuantity() > quantity) {
                    p.setQuantity(p.getQuantity() - quantity);
                    pList.add(p);
                    qList.add(quantity);
                    FileCalls.updateRegistry(p);

                    do {
                        System.out.println("Do you want anything else (Yes / No)?");
                        sc.nextLine();
                        more = sc.nextLine();
                    } while (!more.toUpperCase().equals("YES") && !more.toUpperCase().contentEquals("NO"));
                } else {
                    System.out.println("Sorry, we only have " + p.getQuantity() + " " + p.getName());
                }
            } while (more.toLowerCase().equals("yes"));

            FileCalls.createBill(pList, customer, qList);
        } else {
            do {
                if(name.equals("")){
                    p = FileCalls.searchProduct("");
                }else{
                    p = FileCalls.searchProduct(name);
                }
            } while (p == null);
            System.out.println("How many " + p.getName() + " do you want? There are actually " + p.getQuantity() + " in stock.");
            quantity = sc.nextInt();
            p.setQuantity(p.getQuantity() + quantity);
        }

        FileCalls.quantityRemaining();
    }
}
