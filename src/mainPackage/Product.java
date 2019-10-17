package mainPackage;

import java.util.Date;
import java.util.Scanner;

public class Product {
    private String name;
    private String type;
    private String manufacturer;
    private double price;
    private int quantity;
    private Date registryDay;

    public Product(){
        this.name = null;
        this.type = null;
        this.manufacturer = null;
        this.price = 0.0;
        this.quantity = 0;
        this.registryDay = null;
    }

    public Product(String name, String type, String manufacturer, double price, int quantity, Date registryDay) {
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

    public Date getRegistryDay() {
        return registryDay;
    }

    public void setRegistryDay(Date registryDay) {
        this.registryDay = registryDay;
    }

    public static void createProduct(){
        Product p = new Product();
        Scanner sc = new Scanner(System.in);

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
        p.setRegistryDay(new Date());

        FileCalls.insertRegistry(p);
    }
}
