import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel {
    
    public Menu(VentanaPrincipal app) {
        // Un poco más de espacio a los lados ya que la pantalla ahora es más ancha
        setLayout(new GridLayout(5, 1, 20, 30));
        setBorder(BorderFactory.createEmptyBorder(60, 400, 60, 400));

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32)); // Letra un poco más grande para 720p

        // Agregamos el ancho (280) y el alto (200) al final de cada botón
        JButton btnCrear = crearBotonImagen("/img/btn_crearp.png", "Crear nueva mascota",500, 200);
        JButton btnGuardadas = crearBotonImagen("/img/btn_guardar.png", "Mascotas guardadas", 500, 200);
        JButton btnAjustes = crearBotonImagen("/img/btn_ajustes.png", "Ajustes", 500, 200);
        JButton btnJugarJose = crearBotonImagen("/img/btn_jugar.png", "Jugar con JoseJose", 500, 200);

        btnCrear.addActionListener(e -> app.mostrarPanel("CrearMascota"));
        btnGuardadas.addActionListener(e -> app.mostrarPanel("Guardadas"));
        btnAjustes.addActionListener(e -> app.mostrarPanel("Ajustes"));
        btnJugarJose.addActionListener(e -> app.mostrarPanel("JoseJose"));

        add(titulo);
        add(btnCrear);
        add(btnGuardadas);
        add(btnAjustes);
        add(btnJugarJose);
    }

    /**
     * Ahora recibe "ancho" y "alto" para encoger las imágenes gigantes automáticamente
     */
    private JButton crearBotonImagen(String ruta, String textoFallback, int ancho, int alto) {
        JButton boton = new JButton(textoFallback);
        java.net.URL imgURL = getClass().getResource(ruta);
        
        if (imgURL != null) {
            // 1. Cargamos la imagen original gigante
            ImageIcon iconoOriginal = new ImageIcon(imgURL);
            
            // 2. La redimensionamos (SCALE_SMOOTH hace que no se vea pixelada)
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            
            // 3. Le pasamos la imagen ya pequeña al botón
            boton.setIcon(new ImageIcon(imagenEscalada));
            boton.setText(""); 
            
            boton.setBorderPainted(false);       
            boton.setContentAreaFilled(false);   
            boton.setFocusPainted(false);        
            boton.setOpaque(false);              
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            System.err.println("No se encontró la imagen: " + ruta + " - Mostrando texto.");
        }
        
        return boton;
    }
}