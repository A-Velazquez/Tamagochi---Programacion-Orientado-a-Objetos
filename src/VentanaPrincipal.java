import java.awt.CardLayout;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
 
    private CardLayout cardLayout;
    private JPanel contenedor;

    public VentanaPrincipal() {
        this.setSize(600, 400); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("App de Adrian - Tamagochi"); 
        this.setLocationRelativeTo(null);
        
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        // Inicializamos el CardLayout y el contenedor principal
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Agregamos todas las pantallas al "mazo de cartas"
        // El primer panel que agregues será el que se muestre al iniciar la app.
        contenedor.add(new Menu(this), "Menu");
        contenedor.add(new JoseJose(this), "JoseJose");
        
        // Agregamos paneles de prueba para las nuevas opciones
        contenedor.add(crearPanelPlaceholder("Crear nueva mascota", this), "CrearMascota");
        contenedor.add(crearPanelPlaceholder("Mascotas guardadas", this), "Guardadas");
        contenedor.add(crearPanelPlaceholder("Ajustes", this), "Ajustes");

        // Añadimos el contenedor a la ventana
        this.getContentPane().add(contenedor);
    }

    // Método público para cambiar de pantalla desde cualquier otro panel
    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    // Método auxiliar SOLO para que puedas probar la navegación sin tener 
    // que crear todos los archivos de golpe. Luego crearás las clases reales.
    private JPanel crearPanelPlaceholder(String tituloTexto, VentanaPrincipal app) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(tituloTexto));
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> app.mostrarPanel("Menu"));
        panel.add(btnVolver);
        return panel;
    }
}