import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;  

// CAMBIO CRÍTICO: Debe heredar de JPanel, no de JFrame
public class JoseJose extends JPanel implements TamagochiBase {
    
    private double hambre;
    private double sueno;
    private double felicidad;
    private String nombre;

    public JoseJose(VentanaPrincipal app) {
        this.hambre = 100.0;
        this.sueno = 0.0;
        this.felicidad = 100.0;
        this.nombre = "Jose Jose";

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Jugando con: " + this.nombre, SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnIrDashboard = new JButton("Volver al menú");

        // CAMBIO: Asegúrate de que el String coincida con el que pusimos en VentanaPrincipal ("Menu")
        btnIrDashboard.addActionListener(e -> app.mostrarPanel("Menu"));

        add(titulo, BorderLayout.CENTER);
        add(btnIrDashboard, BorderLayout.SOUTH);
    }

    @Override
    public void quejarseHambriento() {}
    
    @Override
    public void cansadoSueno() {}
    
    @Override
    public void llorar() {}

    @Override
    public void accionComer() {}

    @Override
    public void accionDormir() {}

    @Override
    public void accionFelicidad() {}
}