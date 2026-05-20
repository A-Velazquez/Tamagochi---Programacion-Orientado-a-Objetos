import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
 
    private CardLayout cardLayout;
    private JPanel contenedor;

    public VentanaPrincipal() {
        this.setSize(1280, 720); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("App de Adrian - Tamagochi"); 
        this.setLocationRelativeTo(null);
        
        iniciarComponentes();
        
        // Llamamos al método para cambiar el cursor al final
        configurarCursor();
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

    /**
     * Método para cargar y establecer un cursor personalizado
     */
    private void configurarCursor() {
        // Asegúrate de tener tu imagen 'cursor.png' en la carpeta img
        java.net.URL imgURL = getClass().getResource("/img/cursor.png");
        
        if (imgURL != null) {
            ImageIcon iconoCursor = new ImageIcon(imgURL);
            Image imagenCursor = iconoCursor.getImage();
            
            // El hotspot es el píxel que hace el clic. (0, 0) es la punta superior izquierda.
            Point hotspot = new Point(0, 0);
            
            // Creamos el cursor a través del Toolkit de Java
            Cursor cursorPersonalizado = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, hotspot, "Cursor Propio");
            
            // Se lo aplicamos a la ventana (JFrame)
            this.setCursor(cursorPersonalizado);
        } else {
            System.err.println("No se encontró la imagen del cursor en /img/cursor.png");
        }
    }
}