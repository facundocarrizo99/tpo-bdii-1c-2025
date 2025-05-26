package com.ecommerce.catalog;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final MongoCollection<Document> products;

    public ProductService() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("ecommerce");
        products = database.getCollection("products");
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