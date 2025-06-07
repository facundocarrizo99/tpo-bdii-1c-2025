package ecommerce.recommendation;

import java.util.Scanner;

public class RecommendationController {
    private final RecommendationService service = new RecommendationService();
    private final Scanner scanner = new Scanner(System.in);

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