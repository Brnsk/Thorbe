package mainPackage;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
    	Product p = new Product();
    	FileCalls a = new FileCalls();
    	
    	p.setName("coso1");
    	p.setManufacturer("manufacturer-mod");
    	a.updateRegistry(p);
    }
}
