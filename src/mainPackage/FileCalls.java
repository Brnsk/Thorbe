package mainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCalls {
    final static File file = new File("./stock.dat");

    public static void insertRegistry(Product p, boolean lineSep) {
        try {
            Writer wr = new FileWriter(FileCalls.file, true);
            StringBuilder line = new StringBuilder();

            if (lineSep) {
                line.append(System.lineSeparator());
            }
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateRegistry(Product p) {
        deleteRegistry(p.getName());
        insertRegistry(p, false);
    }

    public static void deleteRegistry(String name) {
        try {
            Scanner file = new Scanner(FileCalls.file);
            List<String> copy = new ArrayList<String>();
            
            while (file.hasNext()) {
                copy.add(file.nextLine());
            }
            file.close();

            Writer wr = new FileWriter(FileCalls.file);

            for (String line : copy) {
                if (!line.split(",")[0].toUpperCase().equals(name.toUpperCase())) {
                    wr.write(line + System.lineSeparator());
                }
            }

            wr.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listProducts() {
        try {
            StringBuilder sb = new StringBuilder();
            Scanner file = new Scanner(FileCalls.file);
            String line = "";
            file.nextLine();

            while (file.hasNext()) {
                line = file.nextLine();
                sb.append("=================");
                sb.append(System.lineSeparator());
                sb.append("Product: ");
                sb.append(line.split(",")[0]);
                sb.append(System.lineSeparator());
                sb.append("Unity Price: ");
                sb.append(line.split(",")[3]);
                sb.append(System.lineSeparator());
                sb.append("Quantity: ");
                sb.append(line.split(",")[4]);
                sb.append(System.lineSeparator());
                sb.append("=================");
                sb.append(System.lineSeparator());
            }
            System.out.println(sb);
            file.close();

            FileCalls.quantityRemaining();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Product searchProduct(String name) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String line = "";
        Product product = null;
        String date = "";
        boolean found = false;

        if(name.equals("")){
            System.out.println("Which product are you looking for?");
            FileCalls.listProducts();
            name = sc.nextLine();
        }

        try {
            Scanner file = new Scanner(FileCalls.file);

            file.nextLine();

            while (file.hasNext() && !found) {
                line = file.nextLine();

                if (line.split(",")[0].toLowerCase().equals(name.toLowerCase())) {
                    found = true;
                    date = line.split(",")[5];
                    date = date.replace(";", "");
                    product = new Product(line.split(",")[0], line.split(",")[1], line.split(",")[2],
                            Double.parseDouble(line.split(",")[3]), Integer.parseInt(line.split(",")[4]), date);

                    sb.append("=================");
                    sb.append(System.lineSeparator());
                    sb.append("Product: ");
                    sb.append(product.getName());
                    sb.append(System.lineSeparator());
                    sb.append("Unity Price: ");
                    sb.append(product.getPrice());
                    sb.append(System.lineSeparator());
                    sb.append("Quantity: ");
                    sb.append(product.getQuantity());
                    sb.append(System.lineSeparator());
                    sb.append("=================");
                    sb.append(System.lineSeparator());
                    System.out.println(sb);
                }
            }

            if (!found) {
                System.out.println("Product not found");
            }

            file.close();
            return product;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static void quantityRemaining() {
        try {
            Scanner file = new Scanner(FileCalls.file);
            Scanner sc = new Scanner(System.in);
            List<String> copy = new ArrayList<String>();
            boolean out = false;

            while (file.hasNext()) {
                copy.add(file.nextLine());
            }
            copy.remove(0);

            for (String line : copy) {
                if (Integer.parseInt(line.split(",")[4]) <= 5 && Integer.parseInt(line.split(",")[4]) >= 0) {
                    StringBuilder warning = new StringBuilder();
                    warning.append("The product ");
                    warning.append(line.split(",")[0]);
                    warning.append(" is running out of stock. Only ");
                    warning.append(line.split(",")[4]);
                    warning.append(" left.");
                    out = true;

                    System.out.println(warning);
                }

                if (out) {
                    String buy;
                    do {
                        System.out.println("Do you want to buy more? (Yes / No)");
                        buy = sc.nextLine();
                    } while (!buy.toUpperCase().equals("YES") && !buy.toUpperCase().contentEquals("NO"));

                    if (buy.toUpperCase().equals("YES")) {
                        Product.buyProduct(false, line.split(",")[0]);
                        System.out.println("Buying more " + line.split(",")[0]);
                    }
                }
                out = false;
            }

            file.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createBill(List<Product> products, String clientName, List<Integer> quantity) {
        try {
            Date day = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy'at'HH-mm'h-'");

            File f = new File("./bills/" + dateFormat.format(day) + clientName + ".dat");

            BufferedWriter wr = new BufferedWriter(new FileWriter(f));
            StringBuilder line = new StringBuilder();
            int i = 0;
            double pfinal = 0;

            line.append("===========ThorbeElectrics===========");
            line.append(System.lineSeparator());
            line.append(System.lineSeparator());
            line.append("  <<<<<CLIENTE>>>>>");
            line.append(System.lineSeparator());
            line.append("     · Nombre: ");
            line.append(clientName);
            line.append(System.lineSeparator());
            line.append(System.lineSeparator());
            line.append("  <<<<<PRODUCTOS>>>>>");
            line.append(System.lineSeparator());

            for (Product p : products) {
                line.append("    · Producto: ");
                line.append(p.getName());
                line.append(System.lineSeparator());
                line.append("    · Cantidad: ");
                line.append(quantity.get(i));
                line.append(System.lineSeparator());
                line.append("    · Precio /u: ");
                line.append(p.getPrice());
                line.append(System.lineSeparator());
                line.append("    · Total: ");
                line.append(p.getPrice() * quantity.get(i));
                line.append(System.lineSeparator());
                line.append("     ------------------");
                line.append(System.lineSeparator());
                pfinal = pfinal + (p.getPrice() * quantity.get(i));
                i += 1;
            }
            line.append(System.lineSeparator());
            line.append(">>>>>>>>PRECIO FINAL :");
            line.append(pfinal);
            line.append(System.lineSeparator());

            wr.append(line);
            wr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
