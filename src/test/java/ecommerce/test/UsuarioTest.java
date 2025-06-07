package ecommerce.test;

import ecommerce.usuario.UsuarioController;
import ecommerce.usuario.Usuario;

public class UsuarioTest {
    static UsuarioController controller = new UsuarioController();

    public static void main(String[] args) {
        try {
            //controller.registerUser("testUser", "testPassword");

            // Simular el inicio de sesión
            Usuario user = controller.loginUser("testUser", "testPassword");
            if (user != null) {
                System.out.println("✅ Usuario autenticado: " + user.getUsername());
            } else {
                System.out.println("❌ Error de autenticación");
            }

            // Obtener detalles del usuario
            org.bson.Document userDetails = controller.getUserDetails("testUser");
            if (userDetails != null) {
                System.out.println("Detalles del usuario: " + userDetails.toJson());
            } else {
                System.out.println("❌ Usuario no encontrado");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en UsuarioTest:");
            e.printStackTrace();
        }
    }
}
