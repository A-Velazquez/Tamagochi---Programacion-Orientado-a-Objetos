import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel {
    
    public Menu(VentanaPrincipal app) {
        // Usamos un GridLayout de 5 filas y 1 columna, con espacios de 20px
        setLayout(new GridLayout(5, 1, 20, 20));
        // Agregamos un borde vacío para que los botones no toquen los bordes de la ventana
        setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Crear los botones
        JButton btnCrear = new JButton("Crear nueva mascota");
        JButton btnGuardadas = new JButton("Mascotas guardadas");
        JButton btnAjustes = new JButton("Ajustes");
        JButton btnJugarJose = new JButton("Jugar con JoseJose (Demo)");

        // Asignar las acciones a los botones para cambiar de panel
        btnCrear.addActionListener(e -> app.mostrarPanel("CrearMascota"));
        btnGuardadas.addActionListener(e -> app.mostrarPanel("Guardadas"));
        btnAjustes.addActionListener(e -> app.mostrarPanel("Ajustes"));
        btnJugarJose.addActionListener(e -> app.mostrarPanel("JoseJose"));

        // Añadir todo al panel
        add(titulo);
        add(btnCrear);
        add(btnGuardadas);
        add(btnAjustes);
        add(btnJugarJose);
    }
}