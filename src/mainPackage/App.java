package mainPackage;

public class App {
    public static void main(String[] args) {
    	Product p = new Product();
    	FileCalls a = new FileCalls();
    	
    	p.setName("coso1");
    	p.setManufacturer("manufacturer-mod");
    	a.updateRegistry(p);
    }
}
