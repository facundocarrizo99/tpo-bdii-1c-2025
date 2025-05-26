// com.ecommerce.config.MongoConfig.java
package ecommerce.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConfig {
    private static final MongoClient mongoClient = MongoClients.create(AppConfig.get("mongo.uri"));
    private static final MongoDatabase database = mongoClient.getDatabase(AppConfig.get("mongo.database"));

    public static MongoDatabase getDatabase() {
        return database;
    }
}
