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
    // Atributo para el timer
        private Timer timerVida;

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

        botonesMetodo();

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
                        etiquetaErrorMetodo();
                        etiquetaHambre.setText("Error: Jose no tiene hambre");
                        etiquetaHambre.setVisible(true);
                    }
                } else {
                    botonComer.addActionListener(e -> accionComer());
                }

            // Si intenta dormir cuando no tiene nada de sueno, da error
                if (sueno == 0.0){
                    try{
                        botonDormir.addActionListener(e -> accionComer());
                    } catch (Exception e) {
                        etiquetaErrorMetodo();
                        etiquetaError.setText("Error: Jose no quiere dormir");
                        etiquetaError.setVisible(true);
                    }
                } else {
                    botonDormir.addActionListener(e -> accionComer());
                }

            // Si intenta tomar cuando el alcoholismo esta al maximo, da error
                if (alcoholismo == 100.0){
                    try{
                        botonTomar.addActionListener(e -> accionFelicidad());
                    } catch (Exception e) {
                        etiquetaErrorMetodo();
                        etiquetaError.setText("Error: Jose no quiere dormir");
                        etiquetaError.setVisible(true);
                    }
                } else {
                    botonTomar.addActionListener(e -> accionFelicidad());
                }

            // Si intenta banarse cuando no esta nada sucio
                if (suciedad == 0.0){
                    try {
                        botonBanarse.addActionListener(e -> accionBanarse());
                    } catch (Exception e) {
                        etiquetaErrorMetodo();
                        etiquetaError.setText("Error: Se va a morir we");
                        etiquetaError.setVisible(true);
                    }
                } else {
                    botonBanarse.addActionListener(e -> accionBanarse());
                }

            // Si intenta cagar cuando no lele pancha, da error
                if (ganasDeCagar == 0.0){
                    try {
                        botonCagar.addActionListener(e -> accionCagar());
                    } catch (Exception e) {
                        etiquetaErrorMetodo();
                        etiquetaError.setText("Error: No lele pancha");
                        etiquetaError.setVisible(true);
                    }
                } else {
                    botonCagar.addActionListener(e -> accionCagar());
                }

        // Llamar la funcion del timer
        iniciarTimer();        

    }

    // Metodo de para el timer, para que los atributos vayan bajando con el tiempo
    public void iniciarTimer() {
        // Se ejecuta cada 3000ms = 3 segundos
        timerVida = new Timer(3000, e -> {
            // Hambre sube con el tiempo
            setHambre(Math.min(getHambre() + 5.0, 100.0));

            // Sueño sube con el tiempo
            setSueno(Math.min(getSueno() + 3.0, 100.0));

            // Alcoholismo baja con el tiempo
            setAlcoholismo(Math.max(getAlcoholismo() - 4.0, 0.0));

            // Suciedad sube con el tiempo
            setSuciedad(Math.min(getSuciedad() + 2.0, 100.0));

            // Ganas de cagar suben con el tiempo
            setGanasDeCagar(Math.min(getGanasDeCagar() + 3.0, 100.0));

            // Llamar los metodos de queja para que reaccionen al cambio
            quejarseHambriento();
            cansadoSueno();
            llorar();
            quejarseSuciedad();
            quejarseCagar();

            // Actualizar la pantalla
            revalidate();
            repaint();
        });
    timerVida.start();
    }

    // Metodo para los parametros de etiqueta error
    public void etiquetaErrorMetodo(){
        etiquetaError = new JLabel();
        etiquetaError.setForeground(Color.GREEN); // Le da el color de fondo
        etiquetaError.setBounds(10,80,50,50); // Dar el limite
        // Checar estas lineas de codigo de abajo por si no se queja de hambre
        add(etiquetaError);
    }

    // Metodo para los parametros de boton comer
    public void botonesMetodo(){
        botonComer.setBounds(100,100,100,100);
        botonDormir.setBounds(150,100,100,100);
        botonTomar.setBounds(200,100,100,100);
        botonBanarse.setBounds(250,100,100,100);
        botonCagar.setBounds(300,100,100,100);
        add(botonComer);
        add(botonDormir);
        add(botonTomar);
        add(botonBanarse);
        add(botonCagar);
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
            setHambre(getHambre()-20.0);
            // Este condicional es para asegurarnes que se quede en el rango de la variable
            if(getHambre() < 0){
                setHambre(0.0);
            }
        }

        @Override
        public void accionDormir() {
            setSueno(getSueno()-75.0);
            // Este condicional es para asegurarnes que se quede en el rango de la variable
            if(getSueno() < 0){
                setSueno(0.0);
            }
        }

        @Override
        public void accionFelicidad() {
            setAlcoholismo(getAlcoholismo()+5.0);
            // Este condicional es para asegurarnes que se quede en el rango de la variable
            if(getAlcoholismo() > 100.0){
                setAlcoholismo(100.0);
            }
        }

        @Override
        public void accionBanarse() {
            setSuciedad(100.0);
        }

        @Override
        public void accionCagar(){
            setGanasDeCagar(100.0);
        }
}