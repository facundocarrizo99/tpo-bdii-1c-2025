package ecommerce.usuario;

import org.bson.Document;

public class Usuario {
    private String username;
    private String password;

    public Usuario(String user, String password) {
        this.username = user;
        this.password = password;
    }

    public Document toDocument() {
        Document doc = new Document("user", username);
        doc.put("password", password);
        return doc;
    }

    public static Usuario fromDocument(Document doc) {
        return new Usuario(doc.getString("user"), doc.getString("password"));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
