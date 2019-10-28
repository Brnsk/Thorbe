package mainPackage;

import java.util.Scanner;

public class App {

    private static boolean printMenu() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        int resposta = -1;

        do {
            System.out.println("========= ThorbeEletrics =========");
            System.out.println("1. Register product.             #");
            System.out.println("2. List all products.            #");
            System.out.println("3. Buy products for the company. #");
            System.out.println("4. Sell product to a customer.   #");
            System.out.println("5. Find a product.               #");
            System.out.println("0. Exit.                         #");
            System.out.println("==================================");
            try{
                resposta = sc.nextInt();
            }catch(Exception e){
                System.out.println("Please, write a number from 0 to 5. Thank you.");
                sc.next();
            }
        } while (resposta < 0 || resposta > 5);

        switch (resposta) {
            case 0: exit = true;
                break;
            case 1: Product.createProduct();
                break;
            case 2: FileCalls.listProducts();
                break;
            case 3: Product.buyProduct(false, "");
                break;
            case 4: Product.buyProduct(true, "");
                break;
            case 5: FileCalls.searchProduct("");
                break;
        }

        return exit;
    }

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("                 ---------\t---------\t-----\t------|    \t --------");
        System.out.println("                 ---   ---\t||     ||\t|   |\t|     |    \t|--------");
        System.out.println("                    | |   \t||     ||\t|----\t|---------|\t| ====   ");
        System.out.println("                    | |   \t||     ||\t|  \\ \t|         |\t|---------");
        System.out.println("                    | |   \t---------\t|   \\\t|----------\t ---------");
        System.out.println();
        System.out.println(" -------- \t||      \t -------- \t---------\t---------\t-----\t||\t---------\t  ------");
        System.out.println("|-------- \t||      \t|-------- \t---------\t---   ---\t|   |\t||\t---------\t  -------");
        System.out.println("| ====    \t||      \t| ====    \t||       \t   | |   \t|----\t||\t||       \t   \\\\");
        System.out.println("|---------\t||      \t|---------\t---------\t   | |   \t|  \\ \t||\t---------\t-------");
        System.out.println(" ---------\t========\t ---------\t---------\t   | |   \t|   \\\t||\t---------\t-------");
        System.out.println();

        while (!exit) {
            exit = printMenu();
        }
    }
}
