import javax.swing.*;
import java.awt.BorderLayout;  
import java.awt.Font;  

public class JoseJose extends JFrame implements TamagochiBase{
    // Atributos que compartiran todas las mascotas
        private double hambre;
        private double sueno;
        private double felicidad;
        private String nombre;

    // Constructor de la mascota
        public JoseJose(VentanaPrincipal app){
            this.hambre = 100.0;
            this.sueno = 0.0;
            this.felicidad = 100.0;
            this.nombre = "Jose Jose";

            setLayout(new BorderLayout());

            JLabel titulo = new JLabel("Pantalla de Inicio", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 24));

            // Boton para volver al inicio
            JButton btnIrDashboard = new JButton("Volver al incio");

            // Al hacer clic, cambia de panel SIN abrir nueva ventana
            btnIrDashboard.addActionListener(e -> app.mostrarPanel("inicio"));

            add(titulo, BorderLayout.CENTER);
            add(btnIrDashboard, BorderLayout.SOUTH);
        }

        // Metodos con override

            // Metodos para inidicar que falta algo
            @Override
            public void quejarseHambriento(){
                
            }
            

}
