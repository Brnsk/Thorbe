package mainPackage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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

        System.out.println("Nom del producte:");
        p.setName(sc.nextLine());
        System.out.println("Tipus de producte:");
        p.setType(sc.nextLine());
        System.out.println("Fabricant:");
        p.setManufacturer(sc.nextLine());
        System.out.println("Preu unitari:");
        p.setPrice(sc.nextDouble());
        System.out.println("Quantitat:");
        p.setQuantity(sc.nextInt());
        p.setRegistryDay(d);

        FileCalls.insertRegistry(p,true);
    }

    public static void buyProduct(boolean sell) {
        int quantity = 0;
        Product p = null;

        Scanner sc = new Scanner(System.in);

        do {
            p = FileCalls.searchProduct();
        } while (p == null);

        if(sell){
            System.out.println("How many " + p.getName() + " do you want to buy dear custemer?");
            quantity = sc.nextInt();
            p.setQuantity(p.getQuantity() - quantity);
        }else{
            System.out.println("How many " + p.getName() + " do you want? There are actually " + p.getQuantity() + " in stock.");
            quantity = sc.nextInt();
            p.setQuantity(p.getQuantity() + quantity);
        }

        quantity = sc.nextInt();

        p.setQuantity(p.getQuantity() + quantity);

        FileCalls.updateRegistry(p);
    }
}
