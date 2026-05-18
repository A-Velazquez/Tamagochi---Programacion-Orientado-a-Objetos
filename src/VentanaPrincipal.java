import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame{
 
    // Atributos
    public JPanel panel;
    // JButton los pongo de atributos porque tambien los va a usar actionlistener
    public JButton botonComer;
    public JButton botonDormir;
    public JButton botonFelicidad;
    // Card Layout va a proporcionar la habilidad de cambiar entre diferentes paneles
    private CardLayout cardLayout;
    // Un panel va a ser el que contenga todos los paneles, pero que solo este activa una 
    private JPanel contenedor;

    // Inicializar la ventana y llamar al metodo principal
    public VentanaPrincipal(){

        this.setSize(600,400); // Cambia el tamano de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("App␣de␣Adrian"); // Cambia el titulo
        this.setLocationRelativeTo(null);
        componentes();

    }

    // Organiza la construccion de la interfaz llamando a otros metodos.
    private void componentes(){
        // Es mejor separarlo para la identificacion de errores y la legibilidad del codigo
        paneles();
        etiquetas();
        // botones();
        // opciones();
        cardLayoutFuncion();
       
    }

    // Crea el contenedor principal de la interfaz
    private void paneles(){
        panel = new JPanel();
        panel.setBackground(Color.WHITE); // Cambia el color del panel
        this.getContentPane().add(panel);
        panel.setLayout(null); // Establece el gestor de diseno para organizar los componentes dentro del panel.
    }

    // Agrega textos a la interfaz
    private void etiquetas(){
        JLabel e1 = new JLabel();
        JLabel e2 = new JLabel();
        e1.setText("Tamagochi");
        e1.setForeground(Color.GREEN); // Le da el color de fondo
        e1.setBounds(10,10, 50,50); // Dar el limite
        panel.add(e1);

        e2.setText("Adrian"); // Agrega una etiqueta con tu nombre
        e2.setBounds(50, 40, 50, 50); // Pone el limite
        e2.setForeground(Color.RED); // Cambiar el color del texto
        panel.add(e2);
    }

    // Metodo para crear el contenedor necesario para la libreria CardLayout
    private void cardLayoutFuncion(){
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Los diferentes paneles que se identifican con un nombre clave
        contenedor.add("Menu", new Menu(this));
        contenedor.add("JoseJose", new JoseJose(this));

        add(contenedor);
        setVisible(true);
    }

    // Metodo para cambiar de pantalla
    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

}
