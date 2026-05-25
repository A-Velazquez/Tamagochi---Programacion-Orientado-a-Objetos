import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel {
    
    public Menu(VentanaPrincipal app) {
        // Redujimos a 4 filas porque quitamos la opción de jugar directamente
        setLayout(new GridLayout(4, 1, 20, 30));
        setBorder(BorderFactory.createEmptyBorder(60, 400, 60, 400));

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));

        JButton btnGuardadas = crearBotonImagen("/img/btn_guardar.png", "Mascotas guardadas", 280, 200);
        JButton btnCrear = crearBotonImagen("/img/btn_crear.png", "Crear nueva mascota", 280, 200);
        JButton btnAjustes = crearBotonImagen("/img/btn_ajustes.png", "Ajustes", 280, 200);

        btnCrear.addActionListener(e -> app.mostrarPanel("CrearMascota"));
        btnGuardadas.addActionListener(e -> app.mostrarPanel("Guardadas"));
        btnAjustes.addActionListener(e -> app.mostrarPanel("Ajustes"));

        add(titulo);
        add(btnGuardadas);
        add(btnCrear);
        add(btnAjustes);
    }

    private JButton crearBotonImagen(String ruta, String textoFallback, int ancho, int alto) {
        JButton boton = new JButton(textoFallback);
        java.net.URL imgURL = getClass().getResource(ruta);
        
        if (imgURL != null) {
            ImageIcon iconoOriginal = new ImageIcon(imgURL);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
            boton.setText(""); 
            boton.setBorderPainted(false);       
            boton.setContentAreaFilled(false);   
            boton.setFocusPainted(false);        
            boton.setOpaque(false);              
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            System.err.println("Falta imagen: " + ruta);
        }
        return boton;
    }
}