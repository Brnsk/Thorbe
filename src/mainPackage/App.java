package mainPackage;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        Product p = new Product("Samsung Galaxy 8","Smartphone-modificado","Samsung",400.26,10,new Date());
        FileCalls.updateRegistry(p);
       
    }
}
