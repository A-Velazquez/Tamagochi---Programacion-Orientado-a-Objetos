import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    
    // Constructor
    public Menu(VentanaPrincipal app) {
        JLabel titulo = new JLabel("Menu", SwingConstants.CENTER);
        // Boton 
        JButton botonJose = new JButton("Cuidar a JoseJose");
        // EL boton al ser presionado, se activara el panel de la mascota
        botonJose.addActionListener(e -> app.mostrarPanel("JoseJose"));
        add(titulo);
        add(botonJose);
    }
}
