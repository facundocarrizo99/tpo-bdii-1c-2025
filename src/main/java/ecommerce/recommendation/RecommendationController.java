package ecommerce.recommendation;

import ecommerce.catalog.Product;
import ecommerce.usuario.Usuario;

import java.util.List;
import java.util.Scanner;

public class RecommendationController {
    private final RecommendationService service = new RecommendationService();
    private final Scanner scanner = new Scanner(System.in);

    public void relacionarCompra(Usuario usuario, Product producto) {
        service.relateUserToProduct(usuario.getUsername(), producto.getName());
    }

    public List<String> recomendar(Usuario usuario) {
        return service.getRecommendations(usuario.getUsername());
    }
}