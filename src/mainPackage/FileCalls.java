package mainPackage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileCalls {
    final static File file = new File("./stock.dat");

    public static void insertRegistry(Product p){
        try{
            Writer wr = new FileWriter(file,true);
            StringBuilder line = new StringBuilder();

            line.append(System.lineSeparator());
            line.append(p.getName());
            line.append(",");
            line.append(p.getType());
            line.append(",");
            line.append(p.getManufacturer());
            line.append(",");
            line.append(p.getPrice());
            line.append(",");
            line.append(p.getQuantity());
            line.append(",");
            line.append(p.getRegistryDay());
            line.append(";");

            wr.append(line);
            wr.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void updateRegistry(Product p){

    }
    public void deleteRegistry(Product p){

    }
    public void listProducts(){

    }
}
