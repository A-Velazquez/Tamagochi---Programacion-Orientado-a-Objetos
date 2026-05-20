import java.awt.CardLayout;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
 
    private CardLayout cardLayout;
    private JPanel contenedor;

    public VentanaPrincipal() {
        // CAMBIO: Resolución a 720p (1280 x 720)
        this.setSize(1280, 720); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("App de Adrian - Tamagochi"); 
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        contenedor.add(new Menu(this), "Menu");
        contenedor.add(new JoseJose(this), "JoseJose");
        
        contenedor.add(crearPanelPlaceholder("Crear nueva mascota", this), "CrearMascota");
        contenedor.add(crearPanelPlaceholder("Mascotas guardadas", this), "Guardadas");
        contenedor.add(crearPanelPlaceholder("Ajustes", this), "Ajustes");

        this.getContentPane().add(contenedor);
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    private JPanel crearPanelPlaceholder(String tituloTexto, VentanaPrincipal app) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(tituloTexto));
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> app.mostrarPanel("Menu"));
        panel.add(btnVolver);
        return panel;
    }
}