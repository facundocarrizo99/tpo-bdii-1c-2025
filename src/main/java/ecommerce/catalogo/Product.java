package com.ecommerce.catalog;

import org.bson.Document;
import java.util.Map;

public class Product {
    private String id;
    private String name;
    private Map<String, Object> attributes;

    public Product(String name, Map<String, Object> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public Document toDocument() {
        Document doc = new Document("name", name);
        doc.put("attributes", attributes);
        return doc;
    }

    public static Product fromDocument(Document doc) {
        return new Product(doc.getString("name"), doc.get("attributes", Map.class));
    }

    public String toString() {
        return "Producto: " + name + ", Atributos: " + attributes.toString();
    }
}