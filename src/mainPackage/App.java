package mainPackage;

import java.util.Date;
import java.util.Scanner;

public class App {

    private static boolean printMenu(){
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        int resposta = -1;

        do {
            System.out.println("1. Registrar producte.");
            System.out.println("2. Listar tots els productes.");
            System.out.println("3. Comprar producte.");
            System.out.println("4. Vendre producte.");
            System.out.println("5. Cercar producte.");
            System.out.println("0. Sortir.");
            resposta = sc.nextInt();
        }while(resposta < 0 || resposta > 4);

        switch (resposta){
            case 0: exit = true;
            break;
            case 1: Product.createProduct();
            break;
            case 2: FileCalls.listProducts();
            break;
            case 3:
                System.out.println("Quin producte vols comprar?");
                String nom = sc.nextLine();
                System.out.println("Quina quantitat?");
                int preu = sc.nextInt();
                ;
            break;
            case 4: ;
            break;
            case 5: ;
        }

        return exit;
    }

    public static void main(String[] args) {
        boolean exit = false;

        while(!exit){
            exit = printMenu();
        }
    }
}
