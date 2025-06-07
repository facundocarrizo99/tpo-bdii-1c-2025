package ecommerce.usuario;
import com.mongodb.client.*;
import ecommerce.config.MongoConfig;
import org.bson.Document;


public class UsuarioService {
    private final MongoCollection<Document> usuarios;

    public UsuarioService() {
        MongoDatabase db = MongoConfig.getDatabase();
        usuarios = db.getCollection("usuarios");
    }

    public void addUsuario(Usuario usuario) {
        usuarios.insertOne(usuario.toDocument());
    }

    public Usuario getUsuario(String username) {
        Document doc = usuarios.find(new Document("user", username)).first();
        if (doc != null) {
            return Usuario.fromDocument(doc);
        }
        return null;
    }

}
