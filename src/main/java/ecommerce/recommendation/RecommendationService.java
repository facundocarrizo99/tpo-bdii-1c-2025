package ecommerce.recommendation;

import ecommerce.config.Neo4jConfig;
import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService implements AutoCloseable {
    private final Driver driver;

    public RecommendationService() {
        driver = Neo4jConfig.getDriver(); // Cambiar usuario/clave
    }

    public void relateUserToProduct(String userId, String productId) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MERGE (u:User {id: $userId}) " +
                                "MERGE (p:Product {id: $productId}) " +
                                "MERGE (u)-[:VIEWED]->(p)",
                        Values.parameters("userId", userId, "productId", productId));
                return null;
            });
        }
    }

    public List<String> getRecommendations(String userId) {
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result result = tx.run(
                        "MATCH (other:User)-[:VIEWED]->(rec:Product) " +
                                "WHERE other.id <> $userId AND NOT EXISTS { " +
                                "  MATCH (:User {id: $userId})-[:VIEWED]->(rec) " +
                                "} " +
                                "RETURN DISTINCT rec.id AS recommended",
                        Values.parameters("userId", userId)
                );
                return result.list(r -> r.get("recommended").asString());
            });
        } catch (Exception e) {
            System.err.println("Error al obtener recomendaciones: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void close() {
        driver.close();
    }
}