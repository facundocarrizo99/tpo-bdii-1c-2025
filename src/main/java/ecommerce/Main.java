package ecommerce;

import ecommerce.catalog.*;
import ecommerce.session.*;
import ecommerce.recommendation.*;
import ecommerce.usuario.*;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ProductoController catalog = new ProductoController();
    static UsuarioController usuarioController = new UsuarioController();
    static SessionController session = new SessionController();
    static RecommendationController recommendation = new RecommendationController();
    private Usuario usuario;

    public static void main(String[] args) {
        Main instancia = new Main();
        instancia.menuInicio();
    }

    private void menuInicio() {
        System.out.println("\n--- Bienvenido ---");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión");
        System.out.println("0. Salir");
        String op = scanner.nextLine();

        switch (op) {
            case "1" -> this.registrar();
            case "2" -> this.iniciarSesion();
            case "0" -> System.exit(0);
            default -> System.out.println("Opción inválida.");
        }
    }

    private void registrar() {
        System.out.print("Nombre de usuario: ");
        String user = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();
        usuarioController.registerUser(user, pass);
        System.out.println("¡Usuario registrado!");
        this.menuInicio();
    }

    private void iniciarSesion() {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();
        this.usuario = usuarioController.loginUser(user, pass);
        if (this.usuario != null) {
            String nombre = this.usuario.getUsername();
            System.out.println("¡Bienvenido " + nombre + "!");
            menuUsuario();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
            menuInicio();
        }
    }

    private void menuUsuario() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Ver catálogo");
        System.out.println("2. Ver carrito");
        System.out.println("3. Agregar producto");
        System.out.println("4. Cerrar sesión");
        System.out.println("5. Ver recomendaciones");
        String op = scanner.nextLine();

        switch (op) {
            case "1" -> verCatalogo();
            case "2" -> verCarrito();
            case "3" -> agregarProducto();
            case "4" -> menuInicio();
            case "5" -> menuRecomendados();
            default -> System.out.println("Opción inválida.");
        }
    }

    private void menuProducto(Product p) {
        System.out.println("\n" + p);
        System.out.println("1. Agregar al carrito");
        System.out.println("2. Comprar ahora");
        System.out.println("0. Volver");

        switch (scanner.nextLine()) {
            case "1" -> {
                session.addToCart(this.usuario.getUsername(), p.getId());
                System.out.println("Producto agregado al carrito.");
                verCatalogo();
            }
            case "2" -> {
                recommendation.relacionarCompra(this.usuario, p);
                System.out.println("Producto comprado y agregado a tus recomendaciones.");
                menuUsuario();
            }
        }
    }

    private void verCarrito() {
        List<Product> productos = new ArrayList<Product>();
        Cart cart = session.getCart(this.usuario.getUsername());
        List<String> ids = cart.getProductIds();
        if (ids.isEmpty()) {
            System.out.println("Tu carrito está vacío.");
            menuUsuario();
        }

        System.out.println("\n--- Carrito ---");
        for (String id : ids) {
            Product p = catalog.getProdctoById(id);
            productos.add(p);
            if (p != null) System.out.println(p);
        }

        System.out.println("1. Comprar todo\n2. Vaciar carrito\n3. Volver");
        switch (scanner.nextLine()) {
            case "1" -> {
                for (Product p : productos) {
                    recommendation.relacionarCompra(this.usuario, p);
                }
                System.out.println("Productos comprados.");
                session.clearCart(this.usuario.getUsername());
                menuUsuario();
            }
            case "2" -> {
                session.clearCart(this.usuario.getUsername());
                System.out.println("Carrito vaciado.");
                menuUsuario();
            }
            case "3" -> {
                menuUsuario();
            }
        }
    }

    private void verCatalogo() {
        List<Product> productos = new ArrayList<>(catalog.listarTodos());
        int pagina = 0;

        while (true) {
            int inicio = pagina * 10;
            int fin = Math.min(inicio + 10, productos.size());
            Dictionary<String, Object> diccionario = new Hashtable<>();

            System.out.println("\n--- Catálogo (página " + (pagina + 1) + ") ---");
            for (int i = inicio; i < fin; i++) {
                Product p = productos.get(i);
                diccionario.put(String.valueOf(i + 1), p);
                System.out.println((i + 1) + ". " + p);
            }

            System.out.println("\nN - Siguiente | P - Anterior | E - Elegir producto | V - Volver");
            String op = scanner.nextLine().toUpperCase();

            switch (op) {
                case "N" -> {
                    if (fin < productos.size()) pagina++;
                    else System.out.println("Ya estás en la última página.");
                }
                case "P" -> {
                    if (pagina > 0) pagina--;
                    else System.out.println("Ya estás en la primera página.");
                }
                case "E" -> {
                    System.out.print("Ingrese ID del producto: ");
                    String id = scanner.nextLine();
                    if (!((Hashtable<String, Object>) diccionario).containsKey(id)) {
                        System.out.println("ID inválido. Por favor, intente de nuevo.");
                        continue;
                    }
                    Product p = (Product) diccionario.get(String.valueOf(id));
                    if (p != null) {
                        this.menuProducto(p);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                }
                case "V" -> {
                    menuUsuario();
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    public void agregarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("descripcion del producto: ");
        String descripcion = scanner.nextLine();
        System.out.print("precio del producto: ");
        Float precio = Float.valueOf(scanner.nextLine());
        Map<String, Object> atributos = new HashMap<>();
        while (true) {
            System.out.print("Clave de atributo (enter para terminar): ");
            String clave = scanner.nextLine();
            if (clave.isEmpty()) break;
            System.out.print("Valor: ");
            String valor = scanner.nextLine();
            atributos.put(clave, valor);
        }
        catalog.agregarProducto(nombre, descripcion, precio, atributos);
        menuUsuario();
    }

    public void menuRecomendados() {
        List<String> recomendados = recommendation.recomendar(this.usuario);
        if (recomendados.isEmpty()) {
            System.out.println("No hay recomendaciones disponibles.");
            menuUsuario();
            return;
        }

        System.out.println("\n--- Recomendaciones ---");
        for (String p : recomendados) {
            System.out.println(p);
        }

        System.out.println("\n1. Volver al menú principal");
        String op = scanner.nextLine();

        switch (op) {
            case "1" -> menuUsuario();
            default -> System.out.println("Opción inválida.");
        }
    }
}
