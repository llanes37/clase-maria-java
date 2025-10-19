/**
 * =========================================================
 * CURSO: Java Swing (Biblioteca Gráfica) - 2º DAM
 * UNIDAD: UT4 - Modelo de eventos en Swing (acción, item, cambio, teclado, ratón)
 * =========================================================
 *
 * // * TEORÍA (resumen docente)
 *   Objetivo de UT4
 *   - Comprender cómo Swing gestiona la interacción del usuario mediante eventos.
 *   - Conectar "quién emite" (componente) con "quién escucha" (listener) y "qué hacer" (manejador).
 *   - Aplicar los listeners más comunes: ActionListener, ItemListener, ChangeListener,
 *     KeyListener y MouseListener. (Además: FocusListener y DocumentListener como nota).
 *
 *   Conceptos clave
 *   - Fuente (source): el componente que dispara el evento (ej. JButton).
 *   - Evento (event): el objeto que describe lo ocurrido (ej. ActionEvent, KeyEvent).
 *   - Listener: interfaz que define métodos a ejecutar cuando llega el evento (ej. ActionListener#actionPerformed).
 *   - Registro: alta del listener en el componente (ej. boton.addActionListener(...)).
 *   - Manejador: el código que se ejecuta dentro del método del listener.
 *
 *   Patrón mental básico
 *   - "Cuando el usuario haga X en Y, entonces Z".
 *   - Ejemplo: "Cuando haga clic en el botón (X) dentro de JButton (Y), mostrar un mensaje (Z)".
 *
 *   Buenas prácticas
 *   - Mantener los manejadores cortos: llamar a métodos auxiliares si crece la lógica.
 *   - Evitar lógica pesada en el EDT: si una tarea tarda, usar SwingWorker (UT10).
 *   - Usar nombres claros para variables y métodos de manejo (ej. manejarClickBoton()).
 *   - Quitar listeners si ya no se necesitan (casos avanzados).
 *
 *   Alcance de UT4
 *   ✔ ActionListener   ✔ ItemListener   ✔ ChangeListener   ✔ KeyListener   ✔ MouseListener
 *   ✔ Ejemplos didácticos separados y con comentarios línea a línea.
 *   ✖ No concurrencia (se verá en UT10), ✖ No menús (UT5), ✖ No MVC (UT8).
 *
 * =========================================================
 * Autor: Clases Online Joaquín — Año: 2025
 * =========================================================
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class UT4_ModeloEventos {

    // ! ========================================================
    // ! REGION 1) PUNTO DE ENTRADA — ARRANQUE EN EL EDT
    // ! ========================================================
    public static void main(String[] args) {
        // * Programamos la construcción de la interfaz en el EDT para seguridad de hilos.
        SwingUtilities.invokeLater(UT4_ModeloEventos::mostrarVentanaPrincipal);
    }

    // ! ========================================================
    // ! REGION 2) VENTANA PRINCIPAL Y CONMUTADOR DE DEMOS
    // ! ========================================================
    private static void mostrarVentanaPrincipal() {
        // * Creamos la ventana principal de UT4.
        JFrame f = new JFrame("UT4 · Modelo de eventos en Swing");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // * Usamos BorderLayout para colocar: cabecera (NORTH), lienzo (CENTER), estado (SOUTH).
        JPanel root = new JPanel(new BorderLayout(10,10));
        root.setBorder(new EmptyBorder(10,10,10,10));

        // -- Cabecera con botones para activar cada demo. FlowLayout por defecto.
        JPanel cabecera = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JLabel titulo = new JLabel("Selecciona demo de eventos:");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 14f));
        JButton bAction  = new JButton("ActionListener");
        JButton bItem    = new JButton("ItemListener");
        JButton bChange  = new JButton("ChangeListener");
        JButton bKey     = new JButton("KeyListener");
        JButton bMouse   = new JButton("MouseListener");

        cabecera.add(titulo);
        cabecera.add(bAction);
        cabecera.add(bItem);
        cabecera.add(bChange);
        cabecera.add(bKey);
        cabecera.add(bMouse);

        // -- Lienzo central (irá cambiando de panel según demo).
        JPanel lienzo = new JPanel(new BorderLayout());
        lienzo.add(panelActionDemo(), BorderLayout.CENTER); // demo por defecto al abrir

        // -- Barra de estado (mensaje corto de lo último ocurrido).
        JLabel status = new JLabel("Listo. Selecciona una demo.");
        status.setBorder(new EmptyBorder(4,2,4,2));

        // -- Conectar los botones de cabecera para cambiar el contenido del lienzo.
        bAction.addActionListener(e -> swap(lienzo, panelActionDemo()));
        bItem.addActionListener(e -> swap(lienzo, panelItemDemo()));
        bChange.addActionListener(e -> swap(lienzo, panelChangeDemo()));
        bKey.addActionListener(e -> swap(lienzo, panelKeyDemo(status)));
        bMouse.addActionListener(e -> swap(lienzo, panelMouseDemo(status)));

        // -- Ensamblado final.
        root.add(cabecera, BorderLayout.NORTH);
        root.add(lienzo, BorderLayout.CENTER);
        root.add(status, BorderLayout.SOUTH);

        f.setContentPane(root);
        f.setMinimumSize(new Dimension(820, 560));
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
    }

    // * Utilidad: cambia el panel del centro por uno nuevo (y refresca UI).
    private static void swap(JPanel lienzo, JPanel nuevoCentro) {
        lienzo.removeAll();                           // quita lo anterior
        lienzo.add(nuevoCentro, BorderLayout.CENTER); // pone lo nuevo
        lienzo.revalidate();                          // recalcula layouts
        lienzo.repaint();                             // fuerza repintado
    }

    // ! ========================================================
    // ! REGION 3) DEMOS DE EVENTOS (muy comentadas)
    // ! ========================================================

    // * 3.1) ActionListener: acciones de botón y campo de texto.
    private static JPanel panelActionDemo() {
        // * Panel raíz de esta demo con BorderLayout para separar secciones.
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("ActionListener · evento de acción (botón/campo)"));

        // -- Zona superior: explicación y campo de texto + botón.
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JLabel lbl = new JLabel("Escribe tu nombre y pulsa 'Saludar' o ENTER:");
        JTextField tfNombre = new JTextField(16);   // campo de texto de 16 columnas
        JButton btnSaludar  = new JButton("Saludar");
        JLabel out = new JLabel(" ");               // etiqueta donde mostraremos el saludo

        // * Conectar ActionListener al botón: se ejecuta al hacer clic o pulsar ALT+S si se configura mnemonic.
        btnSaludar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // * 'e' describe el evento; e.getSource() nos dice qué componente lo originó.
                String nombre = tfNombre.getText().trim();         // leemos el texto actual del campo
                if (nombre.isEmpty()) {                            // si no hay nombre
                    out.setText("Escribe un nombre antes de saludar."); // feedback claro
                } else {
                    out.setText("¡Hola, " + nombre + "! 👋");       // generamos el saludo
                }
            }
        });

        // * Conectar ActionListener al JTextField: ENTER dentro del campo equivale a "acción".
        tfNombre.addActionListener(e -> btnSaludar.doClick());  // delegamos al mismo botón (reutilizamos lógica)

        top.add(lbl);
        top.add(tfNombre);
        top.add(btnSaludar);

        // -- Centro: explicación breve con JTextArea de solo lectura.
        JTextArea info = new JTextArea(
            "ActionListener:\n" +
            "• Se usa para 'acciones' de componentes: botones, ENTER en textfields, etc.\n" +
            "• Alta típica: componente.addActionListener(listener).\n" +
            "• El método se llama actionPerformed(ActionEvent e).\n" +
            "• Mantén el manejador corto; si crece, extrae a un método auxiliar.\n"
        );
        info.setEditable(false);
        info.setBackground(new Color(248,248,248));
        info.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),
                                          new EmptyBorder(8,8,8,8)));

        // -- Pie: muestra del resultado.
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(new TitledBorder("Salida"));
        bottom.add(out, BorderLayout.CENTER);

        // -- Montaje final de la demo.
        p.add(top, BorderLayout.NORTH);
        p.add(info, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    // * 3.2) ItemListener: cambios de estado en checks, radios y combos.
    private static JPanel panelItemDemo() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("ItemListener · cambios de selección (check, radio, combo)"));

        // -- Panel de opciones: varios tipos de selección.
        JPanel opciones = new JPanel();
        opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
        opciones.setBorder(new EmptyBorder(8,8,8,8));

        // Checkbox: permite selección booleana.
        JCheckBox chkNoticias = new JCheckBox("Recibir noticias", true);
        // Radio buttons: elección exclusiva (ButtonGroup asegura 1 activo).
        JRadioButton rbA = new JRadioButton("Plan A (básico)", true);
        JRadioButton rbB = new JRadioButton("Plan B (pro)");
        ButtonGroup grupo = new ButtonGroup(); grupo.add(rbA); grupo.add(rbB);
        // Combo: lista desplegable.
        JComboBox<String> combo = new JComboBox<>(new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"});

        // -- Resultado acumulado
        JLabel out = new JLabel(" ");
        out.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),
                                         new EmptyBorder(6,6,6,6)));

        // * Listener compartido que recompone el estado actual cada vez que algo cambia.
        ItemListener refrescar = e -> {
            String plan = rbA.isSelected()? "Plan A" : "Plan B";        // leemos radio activo
            boolean noticias = chkNoticias.isSelected();                // leemos check
            String dia = (String) combo.getSelectedItem();              // leemos combo
            out.setText("Suscripción: " + plan + " | Noticias: " + (noticias? "Sí":"No") + " | Día: " + dia);
        };

        // * Registro del mismo listener en todos los componentes relevantes.
        chkNoticias.addItemListener(refrescar);
        rbA.addItemListener(refrescar);
        rbB.addItemListener(refrescar);
        combo.addItemListener(e -> { if (e.getStateChange()==ItemEvent.SELECTED) refrescar.itemStateChanged(e); });

        // -- Ensamblamos el panel de opciones
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        fila1.add(chkNoticias);
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        fila2.add(new JLabel("Plan:")); fila2.add(rbA); fila2.add(rbB);
        JPanel fila3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        fila3.add(new JLabel("Día preferente:")); fila3.add(combo);

        opciones.add(fila1);
        opciones.add(fila2);
        opciones.add(fila3);

        p.add(opciones, BorderLayout.CENTER);
        p.add(out, BorderLayout.SOUTH);
        return p;
    }

    // * 3.3) ChangeListener: cambios continuos (JSlider, JSpinner).
    private static JPanel panelChangeDemo() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("ChangeListener · cambios continuos (slider/spinner)"));

        // -- Creamos un slider de volumen 0..100 y un spinner de velocidad 1..10
        JSlider sliderVol = new JSlider(0, 100, 30);    // min, max, valor inicial
        sliderVol.setMajorTickSpacing(20);              // marcas principales
        sliderVol.setMinorTickSpacing(5);               // marcas menores
        sliderVol.setPaintTicks(true);                  // dibuja marcas
        sliderVol.setPaintLabels(true);                 // dibuja valores de marcas

        SpinnerNumberModel spModel = new SpinnerNumberModel(5, 1, 10, 1);
        JSpinner spinnerVel = new JSpinner(spModel);

        // -- Etiqueta de salida para ver los cambios.
        JLabel out = new JLabel("Volumen: 30 | Velocidad: 5x");
        out.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),
                                         new EmptyBorder(6,6,6,6)));

        // * ChangeListener: se dispara continuamente mientras mueves el control.
        ChangeListener refrescar = new ChangeListener() {
            @Override public void stateChanged(ChangeEvent e) {
                int vol = sliderVol.getValue();
                int vel = (Integer) spinnerVel.getValue();
                out.setText("Volumen: " + vol + " | Velocidad: " + vel + "x");
            }
        };
        sliderVol.addChangeListener(refrescar);
        spinnerVel.addChangeListener(refrescar);

        // -- Composición de la sección superior con FlowLayout
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        top.add(new JLabel("Volumen:")); top.add(sliderVol);
        top.add(Box.createHorizontalStrut(12));
        top.add(new JLabel("Velocidad:")); top.add(spinnerVel);

        p.add(top, BorderLayout.NORTH);
        p.add(out, BorderLayout.SOUTH);
        return p;
    }

    // * 3.4) KeyListener: pulsaciones de teclado en un JTextField.
    private static JPanel panelKeyDemo(JLabel status) {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("KeyListener · teclado (tecla presionada/liberada/typed)"));

        // -- Campo de entrada y área de historial.
        JTextField campo = new JTextField(20);
        JTextArea historial = new JTextArea(8, 40);
        historial.setEditable(false);
        JScrollPane sp = new JScrollPane(historial);

        // * Registramos un KeyListener que nos informa de cada fase de la tecla.
        campo.addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {
                // * keyTyped: carácter "interpretado" (tiene en cuenta mayúsculas, acentos, etc.)
                historial.append("typed: '" + e.getKeyChar() + "'\n");
            }
            @Override public void keyPressed(KeyEvent e) {
                // * keyPressed: pulsación física (código de tecla)
                historial.append("pressed: code=" + e.getKeyCode() + " (" + KeyEvent.getKeyText(e.getKeyCode()) + ")\n");
                status.setText("Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
            @Override public void keyReleased(KeyEvent e) {
                // * keyReleased: liberación física
                historial.append("released: code=" + e.getKeyCode() + " (" + KeyEvent.getKeyText(e.getKeyCode()) + ")\n");
            }
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        top.add(new JLabel("Escribe aquí y observa el historial de eventos:"));
        top.add(campo);

        p.add(top, BorderLayout.NORTH);
        p.add(sp, BorderLayout.CENTER);
        return p;
    }

    // * 3.5) MouseListener: clics y movimientos del ratón sobre un panel "patio de juegos".
    private static JPanel panelMouseDemo(JLabel status) {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("MouseListener · ratón (clic, entrar/salir, presionar/soltar)"));

        // -- Área central donde pintaremos puntos con clic izquierdo y limpiaremos con derecho.
        JPanel patio = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // * Dibujo mínimo didáctico: nada persistente; solo el fondo.
                //   (Para persistencia de figuras, ver UT9 - Dibujo 2D).
            }
        };
        patio.setPreferredSize(new Dimension(480, 340));
        patio.setBackground(new Color(250, 250, 255));
        patio.setBorder(new LineBorder(new Color(200,200,220)));

        JLabel out = new JLabel("Mueve el ratón por el área o haz clic.");
        out.setBorder(new EmptyBorder(6,6,6,6));

        // * MouseAdapter: clase de conveniencia (no obliga a implementar todos los métodos).
        patio.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                out.setText("Ratón dentro del área");
                status.setText("MouseEntered");
            }
            @Override public void mouseExited(MouseEvent e) {
                out.setText("Ratón fuera del área");
                status.setText("MouseExited");
            }
            @Override public void mousePressed(MouseEvent e) {
                // * mousePressed: botón presionado (antes de soltar)
                String boton = switch (e.getButton()) {
                    case MouseEvent.BUTTON1 -> "izquierdo";
                    case MouseEvent.BUTTON2 -> "medio";
                    case MouseEvent.BUTTON3 -> "derecho";
                    default -> "desconocido";
                };
                out.setText("Pressed (" + boton + ") en x=" + e.getX() + ", y=" + e.getY());
                status.setText("MousePressed");
            }
            @Override public void mouseReleased(MouseEvent e) {
                out.setText("Released en x=" + e.getX() + ", y=" + e.getY());
                status.setText("MouseReleased");
            }
            @Override public void mouseClicked(MouseEvent e) {
                // * mouseClicked: 'click' completo (presionar + soltar) en la misma posición
                if (e.getButton() == MouseEvent.BUTTON1) {
                    out.setText("Click izquierdo en x=" + e.getX() + ", y=" + e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    out.setText("Click derecho en x=" + e.getX() + ", y=" + e.getY());
                }
                status.setText("MouseClicked (" + e.getClickCount() + "x)");
            }
        });

        // * MouseMotionAdapter para movimiento/arrastre.
        patio.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseMoved(MouseEvent e) {
                status.setText("MouseMoved x=" + e.getX() + ", y=" + e.getY());
            }
            @Override public void mouseDragged(MouseEvent e) {
                status.setText("MouseDragged x=" + e.getX() + ", y=" + e.getY());
            }
        });

        p.add(patio, BorderLayout.CENTER);
        p.add(out, BorderLayout.SOUTH);
        return p;
    }

    // ! ========================================================
    // ! REGION 4) EJERCICIO GUIADO (PASO A PASO)
    // ! ========================================================
    // * Objetivo: consolidar lectura de estado y respuesta a distintos tipos de evento.
    //
    // TODO 1) ActionListener + Validación:
    //   - En panelActionDemo, desactiva el botón “Saludar” si el campo está vacío (usa un DocumentListener básico).
    //   - Si el nombre supera 20 caracteres, muestra un JOptionPane de advertencia.
    //
    // TODO 2) ItemListener + Dependencias:
    //   - En panelItemDemo, si se elige “Plan B (pro)”, habilita un JTextField extra para “Código promo”.
    //   - Incluye su valor en el resumen solo si no está vacío.
    //
    // TODO 3) ChangeListener + Formato:
    //   - En panelChangeDemo, añade un JProgressBar y sincronízalo con el slider (0..100).
    //   - Cuando la velocidad sea 10x, cambia el color de la etiqueta a rojo.
    //
    // TODO 4) KeyListener + Atajos:
    //   - En panelKeyDemo, si el usuario pulsa CTRL+L, limpia el área de historial.
    //   - Si pulsa ESC, borra el campo de texto.
    //
    // TODO 5) MouseListener + Herramienta:
    //   - En panelMouseDemo, dibuja un punto permanente donde se haga clic izquierdo
    //     (almacena pares (x,y) en una lista y repíntalos). Con clic derecho, limpia la lista.
    //   - (Pista: verás esto de forma más formal en UT9 Dibujo 2D).
    //
    // * Con estas tareas verás cómo cada listener encaja en un caso de uso típico.

    // ! ========================================================
    // ! REGION 5) PRÁCTICAS CON IA (ChatGPT / GitHub Copilot)
    // ! ========================================================
    // ? Prompt 1 — Validación reactiva del JTextField (DocumentListener)
    //   "Necesito que un JButton 'Saludar' esté habilitado solo cuando JTextField 'tfNombre'
    //    no esté vacío, y que muestre una advertencia si supero 20 caracteres. Dame un fragmento corto."
    //
    // ? Prompt 2 — Sincronizar JSlider con JProgressBar
    //   "Tengo un JSlider 0..100 y quiero que un JProgressBar refleje el mismo valor en tiempo real.
    //    Dame el ChangeListener necesario y el código de inicialización."
    //
    // ? Prompt 3 — Detectar CTRL+L y ESC con KeyListener
    //   "En un JTextField con un KeyListener, quiero que CTRL+L limpie un JTextArea 'historial'
    //    y que ESC borre el campo de texto. Dame el código del keyPressed."
    //

    // ! ========================================================
    // ! REGION 6) HELPERS VISUALES (BORDES, TITULARES)
    // ! ========================================================
    private static TitledBorder titulo(String t) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            t, TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.PLAIN, 12),
            new Color(60, 60, 60)
        );
    }
}
