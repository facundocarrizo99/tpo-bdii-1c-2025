package ecommerce.catalog;

import java.util.*;


public class ProductoController {
    private final ProductoService service = new ProductoService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- Catálogo ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver productos");
            System.out.println("0. Volver");
            String op = scanner.nextLine();

            switch (op) {
                case "1" -> agregar();
                case "2" -> listar();
                case "0" -> { return; }
            }
        }
    }

    private void agregar() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        Map<String, Object> atributos = new HashMap<>();
        while (true) {
            System.out.print("Clave de atributo (enter para terminar): ");
            String clave = scanner.nextLine();
            if (clave.isEmpty()) break;
            System.out.print("Valor: ");
            String valor = scanner.nextLine();
            atributos.put(clave, valor);
        }
        service.addProduct(new Product(nombre, atributos));
    }

    private void listar() {
        service.getAllProducts().forEach(System.out::println);
    }
}