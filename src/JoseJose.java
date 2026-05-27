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
        private double vida;
        private String nombre;
    // Atributos de etiqueta
        public JLabel etiquetaHambre;
        public JLabel etiquetaSueno;
        public JLabel etiquetaLlorar;
        public JLabel etiquetaSucio;
        public JLabel etiquetaCagar;
        public JLabel etiquetaError;
        public JLabel etiquetaEstado;
    // Atributos de boton
        public JButton botonComer;
        public JButton botonDormir;
        public JButton botonTomar;
        public JButton botonBanarse;
        public JButton botonCagar;
    // Atributo para el timer
        private Timer timerVida;
    // Atributos de animacion
        private JLabel spritePersonaje;
        private Timer timerAnimacion;
        private String estadoAnimacion = "idle";
        private int frameAnimacion = 1;
    // Barra de vida
        private JProgressBar barraVida;
    // Atributos de etiqueta para mostrar las estadisticas cambiantes al usuario
        private JLabel statHambre;
        private JLabel statSueno;
        private JLabel statAlcoholismo;
        private JLabel statSuciedad;
        private JLabel statCagar;

    public JoseJose(VentanaPrincipal app) {
        this.hambre = 0.0;
        this.sueno = 0.0;
        this.alcoholismo = 100.0;
        this.suciedad = 0.0;
        this.ganasDeCagar = 0.0;
        this.vida = 100.0;
        this.nombre = "Jose Jose";

        setLayout(null);

        JLabel titulo = new JLabel("Jugando con: " + this.nombre, SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(400,10,400,30);

        JButton btnIrDashboard = new JButton("Volver al menú");

        // Boton para volver al menu de inicio
        btnIrDashboard.addActionListener(e -> {
            timerVida.stop();
            app.mostrarPanel("Menu");
        });

        // Barra de vida
        barraVida = new JProgressBar(0, 100);
        barraVida.setValue((int) vida);
        barraVida.setStringPainted(true);
        barraVida.setForeground(new Color(46, 204, 113));
        barraVida.setBackground(Color.DARK_GRAY);
        barraVida.setBounds(400, 50, 400, 25);
        add(barraVida);

        // Espacio para el sprite del personaje
        spritePersonaje = new JLabel();
        spritePersonaje.setHorizontalAlignment(SwingConstants.CENTER);
        spritePersonaje.setBounds(400, 90, 400, 350);
        add(spritePersonaje);

        // Etiqueta de estado general
        etiquetaEstado = new JLabel("Jose Jose esta bien", SwingConstants.CENTER);
        etiquetaEstado.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaEstado.setBounds(400, 450, 400, 30);
        add(etiquetaEstado);
        

        add(titulo);
        btnIrDashboard.setBounds(500, 600, 200, 40);
        add(btnIrDashboard);

        // Crear los botones e indicar su accion con sus exceptions
            botonesMetodo();

        // Lo necesario para el error
        etiquetaError = new JLabel();
        etiquetaError.setForeground(Color.RED);
        add(etiquetaError);

                // Si le intenta dar de comer cuando no tiene hambre, da error
                botonComer.addActionListener(e -> {
                    try {
                        accionComer();
                    } catch (Exception ex) {
                        etiquetaError.setBounds(800,80,300,50); // Dar el limite
                        etiquetaError.setText("Error: Jose no tiene hambre");
                        etiquetaError.setVisible(true);
                        // Desaparece despues de 3 segundos
                        Timer t = new Timer(3000, ev -> etiquetaError.setVisible(false));
                        t.setRepeats(false);
                        t.start();
                    }
                });

            // Si intenta dormir cuando no tiene nada de sueno, da error
                botonDormir.addActionListener(e -> {
                    try {
                        accionDormir();
                    } catch (Exception ex) {
                        etiquetaError.setBounds(800,130,300,50); // Dar el limite
                        etiquetaError.setText("Error: Jose no quiere dormir");
                        etiquetaError.setVisible(true);
                        // Desaparece despues de 3 segundos
                        Timer t = new Timer(3000, ev -> etiquetaError.setVisible(false));
                        t.setRepeats(false);
                        t.start();
                    }
                });

            // Si intenta tomar cuando el alcoholismo esta al maximo, da error
                botonTomar.addActionListener(e -> {
                    try {
                        accionFelicidad();
                    } catch (Exception ex) {
                        etiquetaError.setBounds(800,180,300,50); // Dar el limite
                        etiquetaError.setText("Error: Jose no quiere tomar");
                        etiquetaError.setVisible(true);
                        // Desaparece despues de 3 segundos
                        Timer t = new Timer(3000, ev -> etiquetaError.setVisible(false));
                        t.setRepeats(false);
                        t.start();
                    }
                });

            // Si intenta banarse cuando no esta nada sucio
                botonBanarse.addActionListener(e -> {
                    try {
                        accionBanarse();
                    } catch (Exception ex) {
                        etiquetaError.setBounds(800,230,300,50); // Dar el limite
                        etiquetaError.setText("Error: Jose Jose no esta sucio");
                        etiquetaError.setVisible(true);
                        // Desaparece despues de 3 segundos
                        Timer t = new Timer(3000, ev -> etiquetaError.setVisible(false));
                        t.setRepeats(false);
                        t.start();
                    }
                });

            // Si intenta cagar cuando no lele pancha, da error
                botonCagar.addActionListener(e -> {
                    try {
                        accionCagar();
                    } catch (Exception ex) {
                        etiquetaError.setBounds(800,280,300,50); // Dar el limite
                        etiquetaError.setText("Error: No lele pancha");
                        etiquetaError.setVisible(true);
                        // Desaparece despues de 3 segundos
                        Timer t = new Timer(3000, ev -> etiquetaError.setVisible(false));
                        t.setRepeats(false);
                        t.start();
                    }
                });

        // Inicializar las estadisticas para que el usuario pueda verlo
                statHambre = new JLabel("Hambre: " + hambre + "%");
                statHambre.setBounds(10,100,200,25);

                statSueno = new JLabel("Sueno: " + sueno + "%");
                statSueno.setBounds(10,130,200,25);

                statAlcoholismo = new JLabel("Alcoholismo: " + alcoholismo + "%");
                statAlcoholismo.setBounds(10,160,200,25);

                statSuciedad = new JLabel("Suciedad: " + suciedad + "%");
                statSuciedad.setBounds(10,190,200,25);

                statCagar = new JLabel("Caca: " + ganasDeCagar + "%");
                statCagar.setBounds(10,220,200,25);

                add(statHambre);
                add(statSueno);
                add(statAlcoholismo);
                add(statSuciedad);
                add(statCagar);

        // Añadir las etiquetas de quejarse
    
            etiquetaHambre = new JLabel();
            etiquetaHambre.setForeground(Color.GREEN); // Le da el color de fondo
            etiquetaHambre.setBounds(175,50,150,50); // Dar el limite

            etiquetaSueno = new JLabel();
            etiquetaSueno.setForeground(Color.GREEN); // Le da el color de fondo
            etiquetaSueno.setBounds(175,100,200,50); // Dar el limite

            etiquetaLlorar = new JLabel();
            etiquetaLlorar.setForeground(Color.GREEN); // Le da el color de fondo
            etiquetaLlorar.setBounds(175,150,150,50); // Dar el limite

            etiquetaSucio = new JLabel();
            etiquetaSucio.setForeground(Color.GREEN); // Le da el color de fondo
            etiquetaSucio.setBounds(175,200,200,50); // Dar el limite

            etiquetaCagar = new JLabel();
            etiquetaCagar.setForeground(Color.GREEN); // Le da el color de fondo
            etiquetaCagar.setBounds(175,250,200,50); // Dar el limite

            add(etiquetaHambre);
            add(etiquetaSueno);
            add(etiquetaLlorar);
            add(etiquetaSucio);
            add(etiquetaCagar);

        // Llamar la funcion del timer
        iniciarTimer();
        iniciarTimerAnimacion();

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

            // Si algun atributo esta en nivel critico, bajar vida
            if (hambre >= 90 || sueno >= 90 || suciedad >= 90 || ganasDeCagar >= 90 || alcoholismo <= 5) {
                vida = Math.max(0.0, vida - 20.0);
            } else {
                vida = Math.min(100.0, vida + 1.0);
            }

            // Actualizar barra de vida y su color
            barraVida.setValue((int) vida);
            if (vida < 30) barraVida.setForeground(Color.RED);
            else if (vida < 60) barraVida.setForeground(Color.ORANGE);
            else barraVida.setForeground(new Color(46, 204, 113));

            // Actualizar los porcentajes que ve el usuario de las estadisticas
            statHambre.setText("Hambre: " + getHambre() + "%");
            statSueno.setText("Sueno: " + getSueno() + "%");
            statAlcoholismo.setText("Alcoholismo: " + getAlcoholismo() + "%");
            statSuciedad.setText("Suciedad: " + getSuciedad() + "%");
            statCagar.setText("Caca: " + getGanasDeCagar() + "%");
            statHambre.revalidate();
            statSueno.revalidate();
            statAlcoholismo.revalidate();
            statSuciedad.revalidate();
            statCagar.revalidate();

            // Revision de muerte
            if (vida <= 0) {
                etiquetaEstado.setText("¡Jose Jose ha fallecido! F en el chat.");
                vida = 0;
                if (!estadoAnimacion.equals("morir")) {
                    estadoAnimacion = "morir";
                    frameAnimacion = 1;
                }
                timerVida.stop();
            }

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

    // Metodo para manejar la animacion del sprite
    private void iniciarTimerAnimacion() {
        timerAnimacion = new Timer(250, e -> {
            // Evaluamos necesidades pasivas solo si esta en idle
            if (estadoAnimacion.equals("idle")) {
                if (hambre > 70) {
                    estadoAnimacion = "hambre";
                } else if (sueno > 70) {
                    estadoAnimacion = "sueno";
                } else if (suciedad > 70) {
                    estadoAnimacion = "suciedad";
                } else if (ganasDeCagar > 70) {
                    estadoAnimacion = "cagar";
                } else if (alcoholismo < 20) {
                    estadoAnimacion = "tomar";
                }
            // Retornos a idle 
            } else if (estadoAnimacion.equals("hambre") && hambre <= 70) { 
                estadoAnimacion = "idle";
            } else if (estadoAnimacion.equals("sueno") && sueno <= 70) { 
                estadoAnimacion = "idle";
            } else if (estadoAnimacion.equals("suciedad") && suciedad <= 70) { 
                estadoAnimacion = "idle"; 
            } else if (estadoAnimacion.equals("cagar") && ganasDeCagar <= 70) { 
                estadoAnimacion = "idle"; 
            } else if (estadoAnimacion.equals("tomar") && alcoholismo >= 20) { 
                estadoAnimacion = "idle";
            }

            // Construimos la ruta del sprite
            String ruta = "/img/josejose" + estadoAnimacion + frameAnimacion + ".png";
            java.net.URL imgURL = getClass().getResource(ruta);

            if (imgURL != null) {
                ImageIcon icono = new ImageIcon(imgURL);
                Image img = icono.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
                spritePersonaje.setIcon(new ImageIcon(img));
            }

            // Logica de avance de frame
            if (estadoAnimacion.equals("morir")) {
                // Si esta muriendo, avanza pero detente en el frame 4
                if (frameAnimacion < 4) {
                    frameAnimacion++;
                } else {
                    // Ya llego al final de la muerte, congelamos el timer de animacion
                    timerAnimacion.stop();
                }
            } else {
                // Ciclo normal para idle y acciones (1-2-3-4-1...)
                frameAnimacion++;
                if (frameAnimacion > 4) {
                    frameAnimacion = 1;
                }
            }
        });
        timerAnimacion.start();
    }

    // Metodo para cambiar el estado de animacion temporalmente al hacer una accion
    private void cambiarEstadoTemporal(String nuevoEstado) {
        if (vida <= 0) return; // No hacer acciones si esta muerto
        estadoAnimacion = nuevoEstado;
        frameAnimacion = 1;

        Timer t = new Timer(1500, e -> {
            if (vida > 0) { // Solo volver a idle si sigue vivo
                estadoAnimacion = "idle";
                frameAnimacion = 1;
            }
        });
        t.setRepeats(false);
        t.start();
    }

    // Metodo para los parametros de los botones
    public void botonesMetodo(){
        botonComer = new JButton("Comer");
        botonDormir = new JButton("Dormir");
        botonTomar = new JButton("Tomar");
        botonBanarse = new JButton("Bañarse");
        botonCagar = new JButton("Cagar");
        botonComer.setBounds(175,475,100,100);
        botonDormir.setBounds(375,475,100,100);
        botonTomar.setBounds(575,475,100,100);
        botonBanarse.setBounds(775,475,100,100);
        botonCagar.setBounds(975,475,100,100);
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
                etiquetaHambre.setText("Tengo hambre we");
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
                etiquetaSueno.setText("Tengo ganas de mimir we");
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
                etiquetaLlorar.setText("DAME ALCOHOL");
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
                etiquetaSucio.setText("Necesito un bano por favor");
                etiquetaSucio.setVisible(true);
            } else {
                // Checar esta linea de codigo por si no desaparece
                etiquetaSucio.setVisible(false);
            }
        }

        @Override
        public void quejarseCagar() {
            // Si las ganas de cagar sube de 90, quejarse
            if (getGanasDeCagar() > 90){
                etiquetaCagar.setText("SE ME SALE LA CACA");
                etiquetaCagar.setVisible(true);
            } else {
                // Checar esta linea de codigo por si no desaparece
                etiquetaCagar.setVisible(false);
            }
        }

    // Acciones de calmar    

        @Override
        public void accionComer() throws Exception { // La funcion tiene capacidad de lanzar una exception
            if (vida <= 0) { 
                etiquetaEstado.setText("Demasiado tarde..."); 
                return; 
            }
            if (getHambre() == 0.0){
                throw new Exception("Jose no tiene hambre"); // La exception se ejecuta si NO tiene hambre
            }
            setHambre(getHambre()-20.0);
            // Este condicional es para asegurarnes que se quede en el rango de la variable
            if(getHambre() < 0){
                setHambre(0.0);
            }
            cambiarEstadoTemporal("comer");
        }

        @Override
        public void accionDormir() throws Exception { // La funcion tiene capacidad de lanzar una exception
            if (vida <= 0) { 
                etiquetaEstado.setText("Ya duerme eternamente."); 
                return; 
            }
            if (getSueno() == 0.0){
                throw new Exception("Jose no tiene sueno"); // La exception se ejecuta si NO tiene sueno
            }
            setSueno(getSueno()-75.0);
            // Este condicional es para asegurarnes que se quede en el rango de la variable
            if(getSueno() < 0){
                setSueno(0.0);
            }
            cambiarEstadoTemporal("dormir");
        }

        @Override
        public void accionFelicidad() throws Exception{ // La funcion tiene capacidad de lanzar una exception
            if (vida <= 0) { 
                etiquetaEstado.setText("Ni el alcohol lo revive."); 
                return; 
            }
            if (getAlcoholismo() == 100.0){
                throw new Exception("Se va a morir otra vez wey"); // La exception se ejecuta si el alcoholismo esta al maximo
            }
            setAlcoholismo(getAlcoholismo()+5.0);
            // Este condicional es para asegurarnes que se quede en el rango de la variable
            if(getAlcoholismo() > 100.0){
                setAlcoholismo(100.0);
            }
            cambiarEstadoTemporal("tomar");
        }

        @Override
        public void accionBanarse() throws Exception { // La funcion tiene capacidad de lanzar una exception
            if (vida <= 0) { 
                etiquetaEstado.setText("No intentes limpiar un cadaver."); 
                return; 
            }
            if (getSuciedad() == 0.0){
                throw new Exception("Jose esta completamente limpio"); // La exception se ejecuta si Jose no esta sucio
            }
            setSuciedad(0.0);
            cambiarEstadoTemporal("banarse");
        }

        @Override
        public void accionCagar() throws Exception{ // La funcion tiene capacidad de lanzar una exception
            if (vida <= 0){
                etiquetaEstado.setText("Los cadaveres no cagan");
                return;
            } 
            if (getGanasDeCagar() == 0.0){
                throw new Exception("Jose no necesita cagar"); // La exception se ejecuta si Jose no necesita cagar
            }
            setGanasDeCagar(0.0);
            cambiarEstadoTemporal("cagar");
        }
}