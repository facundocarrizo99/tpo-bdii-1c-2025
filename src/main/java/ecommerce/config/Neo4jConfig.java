package ecommerce.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class Neo4jConfig {
    private static final Driver driver = GraphDatabase.driver(
            AppConfig.get("neo4j.uri"),
            AuthTokens.basic(
                    AppConfig.get("neo4j.user"),
                    AppConfig.get("neo4j.password")
            )
    );

    public static Driver getDriver() {
        return driver;
    }
    }
