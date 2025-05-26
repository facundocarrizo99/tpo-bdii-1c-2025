package ecommerce.recommendation;

import java.util.Scanner;

public class RecommendationController {
    private final RecommendationService service = new RecommendationService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- Recomendaciones ---");
            System.out.println("1. Relacionar usuario con producto");
            System.out.println("2. Obtener recomendaciones");
            System.out.println("0. Volver");
            String op = scanner.nextLine();

            switch (op) {
                case "1" -> relacionar();
                case "2" -> recomendar();
                case "0" -> {
                    service.close();
                    return;
                }
            }
        }
    }

    private void relacionar() {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("ID producto: ");
        String id = scanner.nextLine();
        service.relateUserToProduct(user, id);
    }

    private void recomendar() {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        service.getRecommendations(user).forEach(System.out::println);
    }
}