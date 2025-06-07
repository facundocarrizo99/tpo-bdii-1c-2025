package ecommerce.catalog;

import org.bson.Document;
import java.util.Map;

public class Product {
    private String id;
    private String name;
    private Map<String, Object> attributes;
    private String description;
    private Float price;

    public Product(String name, Map<String, Object> attributes, String description, Float price, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.attributes = attributes;
        this.id = id;
    }

    public Document toDocument() {
        Document doc = new Document("name", name);
        doc.put("attributes", attributes);
        doc.put("description", description);
        doc.put("price", price);
        return doc;
    }

    public static Product fromDocument(Document doc) {
        return new Product(doc.getString("name"), doc.get("attributes", Map.class),
                doc.getString("description"), doc.getDouble("price").floatValue(), doc.getObjectId("_id").toHexString());
    }

    public String toString() {
        return "Producto: " + name + ", Atributos: " + attributes.toString() + ", Precio: " + price + ", Descripción: " + description;
    }

    public String getId() {
        return id;
    }
}