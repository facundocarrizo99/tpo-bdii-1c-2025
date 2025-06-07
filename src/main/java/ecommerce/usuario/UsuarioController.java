package ecommerce.usuario;
import org.bson.Document;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();

    public void registerUser(String username, String password) {
        Usuario usuario = new Usuario(username, password);
        usuarioService.addUsuario(usuario);
    }

    public Usuario loginUser(String username, String password) {
        Usuario usuario = usuarioService.getUsuario(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null; // Login failed
    }

    public Document getUserDetails(String username) {
        Usuario usuario = usuarioService.getUsuario(username);
        if (usuario != null) {
            return usuario.toDocument();
        }
        return null; // User not found
    }
}
