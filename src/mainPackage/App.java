package mainPackage;


import java.util.Date;

public class App {
    public static void main(String[] args) {
        Product p = new Product("Samsung Galaxy 8","Smartphone","Samsung",400.26,10,new Date());
//        FileCalls.insertRegistry(p);
        FileCalls.deleteRegistry("Samsung Galaxy 8");
        FileCalls.listProducts();
        FileCalls.quantityRemaining();
    }
}
