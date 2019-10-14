package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCalls {
    final static File file = new File("./stock.dat");

    public static void insertRegistry(Product p){
        try{
            Writer wr = new FileWriter(FileCalls.file,true);
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
    
    public static void updateRegistry(Product p){
    	deleteRegistry(p.getName());
    	insertRegistry(p);
    }	
    
    public static void deleteRegistry(String name) {
        try{
            Scanner sc = new Scanner(FileCalls.file);
            List<String> copy = new ArrayList<String>();
            while(sc.hasNext()){
                copy.add(sc.nextLine());
            }

            Writer wr = new FileWriter(FileCalls.file);

            for (String line : copy) {
                if(!line.split(",")[0].toUpperCase().equals(name.toUpperCase())){
                    wr.write(line + System.lineSeparator());
                }
            }

            wr.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void listProducts(){
        try{
            Scanner sc = new Scanner(FileCalls.file);
            sc.nextLine();

            while(sc.hasNext()){
                System.out.println(sc.nextLine());
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void quantityRemaining() {
    	try{
            Scanner sc = new Scanner(FileCalls.file);
            Scanner user = new Scanner(System.in);
            boolean out = true;
            
            List<String> copy = new ArrayList<String>();
            while(sc.hasNext()){
                copy.add(sc.nextLine());
            }
            copy.remove(0);
            for (String line : copy) {
                if(Integer.parseInt(line.split(",")[4]) <= 5 && Integer.parseInt(line.split(",")[4]) > 0){
                	StringBuilder aviso = new StringBuilder();
                	aviso.append("The product ");
                	aviso.append(line.split(",")[0]);
                	aviso.append(" is running out of stock. Only ");
                	aviso.append(line.split(",")[4]);
                	aviso.append(" left.");
                	out = true;
                	
                    System.out.println(aviso);
                }else if(Integer.parseInt(line.split(",")[4]) <= 0) {
                	StringBuilder aviso = new StringBuilder();
                	aviso.append("There is no more ");
                	aviso.append(line.split(",")[0]);
                	aviso.append(" left.");
                	aviso.append(System.lineSeparator());
                	System.out.println(aviso);
                	out = true;
                }
                if (out) {
                	String comprar;
                	do {
                		System.out.println("Do you want to buy more? (S o N) ");
                		comprar = user.nextLine();
                	}while(!comprar.toUpperCase().equals("S") && comprar.toUpperCase().contentEquals("N"));
                	if (comprar.toUpperCase().equals("S")) {
                		System.out.println("buying more "+line.split(",")[0]);
                	}
                }
                out = false;
            	sc.close();
            }

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
