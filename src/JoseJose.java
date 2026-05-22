import java.awt.*;
import javax.swing.*;

public class JoseJose extends JPanel implements TamagochiBase {
    // Variables de estadísticas (0 a 100)
    private double vida; 
    private double hambre, sueno, alcoholismo, suciedad, ganasDeCagar;
    private String nombre;
    private Timer timerVida;
    
    // Variables para la animación
    private JLabel spritePersonaje;
    private Timer timerAnimacion;
    private String estadoAnimacion = "idle"; 
    private int frameAnimacion = 1;

    // Componentes UI
    public JButton botonComer, botonDormir, botonTomar, botonBanarse, botonCagar;
    public JLabel etiquetaEstado;
    private JProgressBar barraVida; 

    public JoseJose(VentanaPrincipal app) {
        setLayout(null); 
        this.nombre = "Jose Jose";
        
        // Inicializar stats
        this.vida = 100.0;
        this.hambre = 0.0;
        this.sueno = 0.0;
        this.alcoholismo = 100.0;
        this.suciedad = 0.0;
        this.ganasDeCagar = 0.0;

        // Barra de Vida
        barraVida = new JProgressBar(0, 100);
        barraVida.setValue((int) vida);
        barraVida.setStringPainted(true); 
        barraVida.setForeground(new Color(46, 204, 113)); 
        barraVida.setBackground(Color.DARK_GRAY);
        barraVida.setBounds(440, 20, 400, 25); 
        add(barraVida);

        // Espacio para el Sprite del personaje
        spritePersonaje = new JLabel();
        spritePersonaje.setBounds(440, 110, 400, 350); 
        spritePersonaje.setHorizontalAlignment(SwingConstants.CENTER);
        add(spritePersonaje);

        // Botones e interfaz de texto
        botonComer = new JButton("Comer");
        botonDormir = new JButton("Dormir");
        botonTomar = new JButton("Tomar");
        botonBanarse = new JButton("Bañarse");
        botonCagar = new JButton("Cagar");
        
        etiquetaEstado = new JLabel("Jose Jose está bien", SwingConstants.CENTER);
        etiquetaEstado.setBounds(440, 65, 400, 30);
        etiquetaEstado.setFont(new Font("Arial", Font.BOLD, 18));
        add(etiquetaEstado);

        botonesMetodo(); 

        botonComer.addActionListener(e -> accionComer());
        botonDormir.addActionListener(e -> accionDormir());
        botonTomar.addActionListener(e -> accionFelicidad());
        botonBanarse.addActionListener(e -> accionBanarse());
        botonCagar.addActionListener(e -> accionCagar());

        JButton btnVolver = new JButton("Guardar y Salir");
        btnVolver.setBounds(540, 600, 200, 40);
        btnVolver.addActionListener(e -> app.mostrarPanel("Menu"));
        add(btnVolver);

        iniciarTimerVida();
        iniciarTimerAnimacion(); 
    }

    public void botonesMetodo() {
        botonComer.setBounds(300, 520, 120, 50);
        botonDormir.setBounds(430, 520, 120, 50);
        botonTomar.setBounds(560, 520, 120, 50);
        botonBanarse.setBounds(690, 520, 120, 50);
        botonCagar.setBounds(820, 520, 120, 50);
        
        add(botonComer);
        add(botonDormir);
        add(botonTomar);
        add(botonBanarse);
        add(botonCagar);
    }

    private void iniciarTimerAnimacion() {
        timerAnimacion = new Timer(250, e -> {
            
            // Evaluamos necesidades pasivas solo si está en "idle"
            if (estadoAnimacion.equals("idle")) {
                if (hambre > 70) estadoAnimacion = "hambre";
                else if (sueno > 70) estadoAnimacion = "sueno";
                else if (suciedad > 70) estadoAnimacion = "suciedad";
                else if (ganasDeCagar > 70) estadoAnimacion = "cagar";
                else if (alcoholismo < 20) estadoAnimacion = "tomar";
            } 
            // Retornos a idle...
            else if (estadoAnimacion.equals("hambre") && hambre <= 70) { estadoAnimacion = "idle"; } 
            else if (estadoAnimacion.equals("sueno") && sueno <= 70) { estadoAnimacion = "idle"; } 
            else if (estadoAnimacion.equals("suciedad") && suciedad <= 70) { estadoAnimacion = "idle"; } 
            else if (estadoAnimacion.equals("cagar") && ganasDeCagar <= 70) { estadoAnimacion = "idle"; } 
            else if (estadoAnimacion.equals("tomar") && alcoholismo >= 20) { estadoAnimacion = "idle"; }

            // Construimos la ruta
            String ruta = "/img/josejose" + estadoAnimacion + frameAnimacion + ".png";
            java.net.URL imgURL = getClass().getResource(ruta);
            
            if (imgURL != null) {
                ImageIcon icono = new ImageIcon(imgURL);
                Image img = icono.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
                spritePersonaje.setIcon(new ImageIcon(img));
            }

            // --- LÓGICA DE AVANCE DE FRAME ---
            if (estadoAnimacion.equals("morir")) {
                // Si está muriendo, avanza pero detente en el frame 4 (el fantasma/tumba)
                if (frameAnimacion < 4) {
                    frameAnimacion++;
                } else {
                    // Ya llegó al final de la muerte, congelamos el timer de animación
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

    private void cambiarEstadoTemporal(String nuevoEstado) {
        if (vida <= 0) return; // No hacer acciones si está muerto
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

public void iniciarTimerVida() {
        timerVida = new Timer(3000, e -> {
            hambre = Math.min(hambre + 5.0, 100.0);
            sueno = Math.min(sueno + 3.0, 100.0);
            suciedad = Math.min(suciedad + 2.0, 100.0);
            ganasDeCagar = Math.min(ganasDeCagar + 4.0, 100.0);
            alcoholismo = Math.max(alcoholismo - 4.0, 0.0);
            
            boolean sufriendo = false;
            
            // --- AJUSTE DE DIFICULTAD: DAÑO MÁS RÁPIDO ---
            if (hambre >= 90 || sueno >= 90 || suciedad >= 90 || ganasDeCagar >= 90 || alcoholismo <= 5) {
                vida = Math.max(0.0, vida - 20.0); // CAMBIO: Antes restaba 8, ahora resta 20 de golpe
                sufriendo = true;
            } else {
                vida = Math.min(100.0, vida + 1.0); // CAMBIO: Curación más lenta (antes 2, ahora 1)
            }

            barraVida.setValue((int) vida);
            
            if (vida < 30) barraVida.setForeground(Color.RED);
            else if (vida < 60) barraVida.setForeground(Color.ORANGE);
            else barraVida.setForeground(new Color(46, 204, 113));

            // Revisión de muerte
            if (vida <= 0) {
                etiquetaEstado.setText("¡José José ha fallecido! F en el chat.");
                vida = 0;
                
                if (!estadoAnimacion.equals("morir")) {
                    estadoAnimacion = "morir";
                    frameAnimacion = 1; 
                }
                
                timerVida.stop();
            } else if (sufriendo) {
                etiquetaEstado.setText("¡José está sufriendo DAÑO CRÍTICO por descuido!");
            } else if (hambre > 70) {
                etiquetaEstado.setText("¡Jose tiene mucha hambre!");
            } else if (ganasDeCagar > 70) {
                etiquetaEstado.setText("¡Jose necesita ir al baño urgentemente!");
            } else if (alcoholismo < 20) {
                etiquetaEstado.setText("¡Jose necesita alcohol!");
            } else {
                etiquetaEstado.setText("Jose Jose está tranquilo");
            }
            
            repaint();
        });
        timerVida.start();
    }

    // --- ACCIONES BLOQUEADAS SI ESTÁ MUERTO ---
    @Override public void accionComer() { 
        if (vida <= 0) { etiquetaEstado.setText("Demasiado tarde..."); return; }
        hambre = Math.max(0, hambre - 25); 
        cambiarEstadoTemporal("comer");
    }
    @Override public void accionDormir() { 
        if (vida <= 0) { etiquetaEstado.setText("Ya duerme eternamente."); return; }
        sueno = Math.max(0, sueno - 50); 
        cambiarEstadoTemporal("dormir");
    }
    @Override public void accionFelicidad() { 
        if (vida <= 0) { etiquetaEstado.setText("Ni el alcohol lo revive."); return; }
        alcoholismo = Math.min(100, alcoholismo + 20); 
        cambiarEstadoTemporal("tomar");
    }
    @Override public void accionBanarse() { 
        if (vida <= 0) { etiquetaEstado.setText("QEPD."); return; }
        suciedad = 0; 
        cambiarEstadoTemporal("banarse");
    }
    @Override public void accionCagar() { 
        if (vida <= 0) { return; }
        ganasDeCagar = 0; 
        cambiarEstadoTemporal("cagar");
    }

    // Métodos vacíos obligatorios de la interfaz
    @Override public void quejarseHambriento() {}
    @Override public void cansadoSueno() {}
    @Override public void llorar() {}
    @Override public void quejarseSuciedad() {}
    @Override public void quejarseCagar() {}
}