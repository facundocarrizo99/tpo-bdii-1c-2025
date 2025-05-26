package ecommerce.session;

import java.util.Scanner;

public class SessionController {
    private final SessionService service = new SessionService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- Sesión / Carrito ---");
            System.out.println("1. Agregar producto al carrito");
            System.out.println("2. Ver carrito");
            System.out.println("3. Vaciar carrito");
            System.out.println("0. Volver");
            String op = scanner.nextLine();

            switch (op) {
                case "1" -> agregar();
                case "2" -> ver();
                case "3" -> vaciar();
                case "0" -> { return; }
            }
        }
    }

    private void agregar() {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("ID producto: ");
        String id = scanner.nextLine();
        service.addToCart(user, id);
    }

    private void ver() {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.println(service.getCart(user));
    }

    private void vaciar() {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        service.clearCart(user);
    }
}