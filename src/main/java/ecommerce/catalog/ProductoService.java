package ecommerce.catalog;

import com.mongodb.client.*;
import ecommerce.config.MongoConfig;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private final MongoCollection<Document> products;

    public ProductoService() {
        MongoDatabase db = MongoConfig.getDatabase();
        products = db.getCollection("products");
    }

    public void addProduct(Product product) {
        products.insertOne(product.toDocument());
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        for (Document doc : products.find()) {
            list.add(Product.fromDocument(doc));
        }
        return list;
    }
}