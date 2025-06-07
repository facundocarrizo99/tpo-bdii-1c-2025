package ecommerce.session;

import java.util.Scanner;

public class SessionController {
    private final SessionService service = new SessionService();
    private final Scanner scanner = new Scanner(System.in);

    public Cart getCart(String user) {
        return service.getCart(user);
    }

    public void clearCart(String user) {
        service.clearCart(user);
    }

    public void addToCart(String user, String productId) {
        service.addToCart(user, productId);
    }

    private void vaciar() {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        service.clearCart(user);
    }
}