import java.awt.*;
import javax.swing.*;  


// Debe heredar de JPanel y ser interfaz de Tamagochi base
public class JoseJose extends JPanel implements TamagochiBase {
    
    // Atributos del personaje
        private double hambre;
        private double sueno;
        private double alcoholismo;
        private double suciedad;
        private double ganasDeCagar;
        private String nombre;
    // Atributos de etiqueta
        public JLabel etiquetaHambre;
        public JLabel etiquetaSueno;
        public JLabel etiquetaLlorar;
        public JLabel etiquetaSucio;
        public JLabel etiquetaCagar;
        public JLabel etiquetaError;
    // Atributos de boton
        public JButton botonComer;
        public JButton botonDormir;
        public JButton botonTomar;
        public JButton botonBanarse;
        public JButton botonCagar;

    public JoseJose(VentanaPrincipal app) {
        this.hambre = 0.0;
        this.sueno = 0.0;
        this.alcoholismo = 100.0;
        this.suciedad = 0.0;
        this.ganasDeCagar = 0.0;
        this.nombre = "Jose Jose";

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Jugando con: " + this.nombre, SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnIrDashboard = new JButton("Volver al menú");

        // Boton para volver al menu de inicio
        btnIrDashboard.addActionListener(e -> app.mostrarPanel("Menu"));

        add(titulo, BorderLayout.CENTER);
        add(btnIrDashboard, BorderLayout.SOUTH);

        // Crear los botones  e indicar su accion con sus exceptions

            // Accion comer
            botonComer = new JButton();
                // Si le intenta dar de comer cuando no tiene hambre, da error
                if (hambre == 0.0){
                    try{
                        botonComer.addActionListener(e -> accionComer());
                    } catch (Exception e) {
                        etiquetaError = new JLabel();
                        etiquetaHambre.setText("Error: Jose no tiene hambre");
                        etiquetaHambre.setForeground(Color.GREEN); // Le da el color de fondo
                        etiquetaHambre.setBounds(10,80,50,50); // Dar el limite
                        // Checar estas lineas de codigo de abajo por si no se queja de hambre
                        add(etiquetaHambre);
                        etiquetaHambre.setVisible(true);
                    }
                } else {
                    botonComer.addActionListener(e -> accionComer());
                }

            // 


        
    }

    // Setters y Getters

        // Getter de la variable hambre
        public double getHambre() {
            return hambre;
        }

        // Setter de la variable hambre
        public void setHambre(double hambre) {
            this.hambre = hambre;
        }

        // Getter de la variable sueno
        public double getSueno() {
            return sueno;
        }
        
        // Setter de la variable sueno
        public void setSueno(double sueno) {
            this.sueno = sueno;
        }

        // Getter de la variable alcoholismo
        public double getAlcoholismo() {
            return alcoholismo;
        }

        // Setter de la variable alcoholismo
        public void setAlcoholismo(double alcoholismo) {
            this.alcoholismo = alcoholismo;
        }

        // Getter de la variable nombre
        public String getNombre() {
            return nombre;
        }
        
        // Setter de la variable nombre
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        // Getter de la variable suciedad
        public double getSuciedad() {
            return suciedad;
        }

        // Setter de la variable suciedad
        public void setSuciedad(double suciedad) {
            this.suciedad = suciedad;
        }

        // Getter de la variable ganas de cagar
        public double getGanasDeCagar() {
            return ganasDeCagar;
        }

        // Setter de la variable ganas de cagar
        public void setGanasDeCagar(double ganasDeCagar) {
            this.ganasDeCagar = ganasDeCagar;
        }

    // Acciones de quejarse

        @Override
        public void quejarseHambriento() {
            // Si el hambre sube de 75, mostrar el mensaje
            if (getHambre() > 75 ){
                etiquetaHambre = new JLabel();
                etiquetaHambre.setText("Tengo hambre we");
                etiquetaHambre.setForeground(Color.GREEN); // Le da el color de fondo
                etiquetaHambre.setBounds(10,10,50,50); // Dar el limite
                // Checar estas lineas de codigo de abajo por si no se queja de hambre
                add(etiquetaHambre);
                etiquetaHambre.setVisible(true);
            } else {
                // Checar esta linea de codigo por si no desaparece
                etiquetaHambre.setVisible(false);
            }
        }
        
        @Override
        public void cansadoSueno() {
            // Si el sueno sube de 80, mostrar el mensaje
            if (getSueno() > 80){
                etiquetaSueno = new JLabel();
                etiquetaSueno.setText("Tengo ganas de mimir we");
                etiquetaSueno.setForeground(Color.GREEN); // Le da el color de fondo
                etiquetaSueno.setBounds(10,20,50,50); // Dar el limite
                // Checar estas lineas de codigo de abajo por si no se queja de hambre
                add(etiquetaSueno);
                etiquetaSueno.setVisible(true);
            } else {
                // Checar esta linea de codigo por si no desaparece
                etiquetaSueno.setVisible(false);
            }
        }
        
        @Override
        public void llorar() {
            // Si el alcoholismo baja de 90, mostrar el mensaje
            if (getAlcoholismo() < 90){
                etiquetaLlorar = new JLabel();
                etiquetaLlorar.setText("DAME ALCOHOL");
                etiquetaLlorar.setForeground(Color.GREEN); // Le da el color de fondo
                etiquetaLlorar.setBounds(10,30,50,50); // Dar el limite
                // Checar estas lineas de codigo de abajo por si no se queja de hambre
                add(etiquetaLlorar);
                etiquetaLlorar.setVisible(true);
            } else {
                // Checar esta linea de codigo por si no desaparece
                etiquetaLlorar.setVisible(false);
            }
        }

        @Override
        public void quejarseSuciedad() {
            // Si la suciedad sube de 85, quejarse
            if (getSuciedad() > 85){
                etiquetaSucio = new JLabel();
                etiquetaSucio.setText("Necesito un bano por favor");
                etiquetaSucio.setForeground(Color.GREEN); // Le da el color de fondo
                etiquetaSucio.setBounds(10,40,50,50); // Dar el limite
                // Checar estas lineas de codigo de abajo por si no se queja de hambre
                add(etiquetaSucio);
                etiquetaSucio.setVisible(true);
            } else {
                // Checar esta linea de codigo por si no desaparece
                etiquetaSucio.setVisible(false);
            }
        }

        @Override
        public void quejarseCagar() {
            // Si las ganas de cagar sube de 90, quejarse
            if (getSuciedad() > 90){
                etiquetaCagar = new JLabel();
                etiquetaCagar.setText("SE ME SALE LA CACA");
                etiquetaCagar.setForeground(Color.GREEN); // Le da el color de fondo
                etiquetaCagar.setBounds(10,50,50,50); // Dar el limite
                // Checar estas lineas de codigo de abajo por si no se queja de hambre
                add(etiquetaCagar);
                etiquetaCagar.setVisible(true);
            } else {
                // Checar esta linea de codigo por si no desaparece
                etiquetaCagar.setVisible(false);
            }
        }


        
    // Acciones de calmar    

        @Override
        public void accionComer() {
            
        }

        @Override
        public void accionDormir() {}

        @Override
        public void accionFelicidad() {}

        @Override
        public void accionBanarse() {

        }

        @Override
        public void accionCagar(){

        }
}