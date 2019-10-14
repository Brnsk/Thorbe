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

    protected static void insertRegistry(Product p){
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

    protected static void updateRegistry(Product p){
    	deleteRegistry(p.getName());
    	insertRegistry(p);
    }

    protected static void deleteRegistry(String name) {
        try{
            Scanner sc = new Scanner(FileCalls.file);
            List<String> copy = new ArrayList<String>();
            int len = 0;
            String line = "";

            while(sc.hasNext()){
                copy.add(sc.nextLine());
            }

            len = copy.size()-2;
            Writer wr = new FileWriter(FileCalls.file);

            for (int i = 0; i < copy.size(); i++) {
                line = copy.get(i);
                if(i < len){
                    if(!line.split(",")[0].toUpperCase().equals(name.toUpperCase())){
                        wr.write(line + System.lineSeparator());
                    }
                }else{
                    if(!line.split(",")[0].toUpperCase().equals(name.toUpperCase())){
                        wr.write(line);
                    }
                }
            }

            wr.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    protected static void listProducts(){
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

    protected static void quantityRemaining() {
    	try{
            Scanner sc = new Scanner(FileCalls.file);
            Scanner user = new Scanner(System.in);
            int comprar = 0;
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
                	
                    System.out.println(aviso);
                }else if(Integer.parseInt(line.split(",")[4]) <= 0) {
                	StringBuilder aviso = new StringBuilder();
                	aviso.append("There is no more ");
                	aviso.append(line.split(",")[0]);
                	aviso.append(" left.");
                	aviso.append(System.lineSeparator());
                	System.out.println(aviso);
                	do {
                		System.out.println("Do you want to buy more? (1 = S o 2 = N) ");
                		comprar = user.nextInt();
                	}while(comprar != 1 || comprar != 2);
                	if (comprar == 1) {
                		System.out.println("buying more"+line.split(",")[0]);
                	}
                }
            }

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    protected static void buyProduct(){

    }
}
