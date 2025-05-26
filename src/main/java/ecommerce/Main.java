package ecommerce;

import ecommerce.catalog.ProductoController;
import ecommerce.session.SessionController;
import ecommerce.recommendation.RecommendationController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoController catalog = new ProductoController();
        SessionController session = new SessionController();
        RecommendationController recommendation = new RecommendationController();

        while (true) {
            System.out.println("\n=== E-Commerce CLI ===");
            System.out.println("1. Catálogo de productos");
            System.out.println("2. Carrito y sesiones");
            System.out.println("3. Recomendaciones");
            System.out.println("0. Salir");
            String op = scanner.nextLine();

            switch (op) {
                case "1" -> catalog.menu();
                case "2" -> session.menu();
                case "3" -> recommendation.menu();
                case "0" -> {
                    System.out.println("Chau pa 👋");
                    System.exit(0);
                }
            }
        }
    }
}