package ecommerce.catalog;

import org.bson.types.ObjectId;

import java.util.*;


public class ProductoController {
    private final ProductoService service = new ProductoService();
    private final Scanner scanner = new Scanner(System.in);

    public List<Product> listarTodos() {
        return service.getAllProducts();
    }

    public List<Product> listarConLimite(int cantidad) {
        return service.getAmountOfProducts(cantidad);
    }

    public Product getProdctoById(String id) {
        ObjectId objectId = new ObjectId(id);
        return service.getProductById(objectId);
    }

    public void agregarProducto(String nombre, String descripcion, Float precio, Map<String, Object> attributes) {
        Product nuevoProducto = new Product(nombre, attributes, descripcion, precio, null);
        service.addProduct(nuevoProducto);
        System.out.println("Producto agregado exitosamente.");
    }
}