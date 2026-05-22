import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;

public class PantallaCrear extends JPanel {
    public PantallaCrear(VentanaPrincipal app) {
        setLayout(new GridLayout(1, 3, 20, 0));
        setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));

        // Opción 1: JoseJose
        JButton btnJose = new JButton("Jose Jose");
        btnJose.addActionListener(e -> {
            guardarNuevaPartida("Jose Jose"); // Crea el archivo .txt
            app.mostrarPanel("JoseJose");
        });

        // Opción 2 y 3: Proximamente
        JButton btnProx1 = new JButton("Próximamente...");
        btnProx1.setEnabled(false);
        JButton btnProx2 = new JButton("Próximamente...");
        btnProx2.setEnabled(false);

        add(btnJose);
        add(btnProx1);
        add(btnProx2);
    }

    // Función básica para crear el archivo TXT al elegir personaje
    private void guardarNuevaPartida(String nombre) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("partida.txt"))) {
            // Guardamos: Nombre, Hambre, Sueño, Alcohol, Suciedad, Caca
            writer.println(nombre + ",0.0,0.0,100.0,0.0,0.0");
        } catch (Exception e) {
            System.out.println("Error al crear partida");
        }
    }
}