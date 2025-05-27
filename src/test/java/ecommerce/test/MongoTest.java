package ecommerce.test;

import ecommerce.config.MongoConfig;
import com.mongodb.client.MongoDatabase;

public class MongoTest {
    public static void main(String[] args) {
        try {
            MongoDatabase db = MongoConfig.getDatabase();
            System.out.println("✅ Conectado a MongoDB: " + db.getName());
        } catch (Exception e) {
            System.out.println("❌ Error conectando a MongoDB:");
            e.printStackTrace();
        }
    }
}
