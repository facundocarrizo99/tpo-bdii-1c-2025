package ecommerce.catalog;

import com.mongodb.client.*;
import ecommerce.config.MongoConfig;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private final MongoCollection<Document> products;

    public ProductoService() {
        MongoDatabase db = MongoConfig.getDatabase();
        products = db.getCollection("products"); //todo usuario
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

    public List<Product> getAmountOfProducts(int amount) {
        List<Product> list = new ArrayList<>();
        FindIterable<Document> iterable = products.find().limit(amount);
        for (Document doc : iterable) {
            list.add(Product.fromDocument(doc));
        }
        return list;
    }

    public Product getProductById(ObjectId id) {
        Document doc = products.find(new Document("_id", id)).first();
        if (doc != null) {
            return Product.fromDocument(doc);
        }
        return null;
    }

}