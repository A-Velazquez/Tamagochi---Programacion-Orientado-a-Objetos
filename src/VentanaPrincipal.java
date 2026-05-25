import java.awt.CardLayout;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel contenedor;

    public VentanaPrincipal() {
        this.setSize(1280, 720); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Agregamos las pantallas reales
        contenedor.add(new Menu(this), "Menu");
        contenedor.add(new PantallaCrear(this), "CrearMascota");
        contenedor.add(new JoseJose(this), "JoseJose");
        
        // El placeholder de Mascotas Guardadas (lógica de lectura TXT iría aquí)
        contenedor.add(new PantallaGuardadas(this), "Guardadas");
        contenedor.add(crearPanelSimple("Ajustes", this), "Ajustes");

        this.add(contenedor);
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    private JPanel crearPanelSimple(String texto, VentanaPrincipal app) {
        JPanel p = new JPanel();
        p.add(new JLabel(texto));
        JButton b = new JButton("Volver");
        b.addActionListener(e -> app.mostrarPanel("Menu"));
        p.add(b);
        return p;
    }
}