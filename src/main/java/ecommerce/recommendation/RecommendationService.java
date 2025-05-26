package com.ecommerce.recommendation;

import org.neo4j.driver.*;

import java.util.List;

public class RecommendationService implements AutoCloseable {
    private final Driver driver;

    public RecommendationService() {
        driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j", "password")); // Cambiar usuario/clave
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
            return session.readTransaction(tx -> tx.run(
                    "MATCH (u:User {id: $userId})-[:VIEWED]->(:Product)<-[:VIEWED]-(other:User)-[:VIEWED]->(rec:Product) " +
                            "WHERE NOT (u)-[:VIEWED]->(rec) " +
                            "RETURN DISTINCT rec.id AS recommended",
                    Values.parameters("userId", userId))
            ).list(r -> r.get("recommended").asString()));
        }
    }

    public void close() {
        driver.close();
    }
}