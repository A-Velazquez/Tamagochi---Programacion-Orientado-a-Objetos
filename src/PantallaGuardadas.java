import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.*;

public class PantallaGuardadas extends JPanel {
    public PantallaGuardadas(VentanaPrincipal app) {
        setLayout(new FlowLayout());
        
        try (BufferedReader br = new BufferedReader(new FileReader("partida.txt"))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] datos = linea.split(",");
                JButton btnPartida = new JButton("Cargar Partida: " + datos[0]);
                btnPartida.addActionListener(e -> app.mostrarPanel("JoseJose"));
                add(btnPartida);
            }
        } catch (Exception e) {
            add(new JLabel("No hay mascotas guardadas."));
        }

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> app.mostrarPanel("Menu"));
        add(btnVolver);
    }
}