package ecommerce.test;

import ecommerce.config.Neo4jConfig;
import org.neo4j.driver.*;

public class Neo4jTest {
    public static void main(String[] args) {
        try (Driver driver = Neo4jConfig.getDriver();
             Session session = driver.session()) {
            String greeting = session.readTransaction(tx ->
                    tx.run("RETURN 'Neo4j OK' AS result").single().get("result").asString()
            );
            System.out.println("✅ Neo4j respondió: " + greeting);
        } catch (Exception e) {
            System.out.println("❌ Error conectando a Neo4j:");
            e.printStackTrace();
        }
    }
}
