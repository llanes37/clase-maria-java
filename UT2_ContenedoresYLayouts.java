/**
 * =========================================================
 * CURSO: Java Swing (Biblioteca Gráfica) - 2º DAM
 * UNIDAD: UT2 - Contenedores y Layouts esenciales
 * =========================================================
 *
 * // * TEORÍA (resumen docente)
 *   Objetivo de UT2
 *   - Entender cómo organizar componentes en pantalla usando los layouts básicos.
 *   - Diferenciar contenedores (JFrame/JPanel) de gestores de disposición (Layout Managers).
 *
 *   ¿Qué es un Layout?
 *   - Es la estrategia que usa un contenedor (p. ej. un JPanel) para colocar y dimensionar
 *     sus componentes hijos.
 *
 *   Layouts que veremos (nivel esencial):
 *   - FlowLayout  → Coloca en fila (izq→der). Respeta el orden de añadido. Sencillo.
 *   - BorderLayout → 5 zonas: NORTH, SOUTH, EAST, WEST, CENTER. Ideal para estructura principal.
 *   - GridLayout  → Rejilla F x C. Todas las celdas del mismo tamaño. Rápido para formularios simples.
 *   - BoxLayout   → Fila o columna. Permite “espaciadores” (rigid areas) y “glue” para separar.
 *
 *   Contenedores:
 *   - JFrame: ventana principal.
 *   - JPanel: contenedor genérico al que le asignamos un Layout concreto.
 *
 *   Buenas prácticas:
 *   - Anidar paneles: un panel principal con BorderLayout y dentro paneles con otros layouts.
 *   - Usar bordes (TitledBorder/EmptyBorder) para separar visualmente.
 *   - Llamar a pack() y centrar la ventana.
 *
 *   Alcance de UT2:
 *   ✔ Mostrar y comparar Flow, Border, Grid y Box.
 *   ✔ Anidación mínima de paneles.
 *   ✖ Sin menús (UT5), ✖ sin tablas/listas (UT7), ✖ sin MVC (UT8).
 *
 * =========================================================
 * Autor: Clases Online Joaquín — Año: 2025
 * =========================================================
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class UT2_ContenedoresYLayouts {

    // ! ========================================================
    // ! REGION 1) PUNTO DE ENTRADA — ARRANQUE EN EL EDT
    // ! ========================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UT2_ContenedoresYLayouts::mostrarVentanaPrincipal);
    }

    // ! ========================================================
    // ! REGION 2) VENTANA PRINCIPAL Y CONMUTADOR DE DEMOS
    // ! ========================================================
    private static void mostrarVentanaPrincipal() {
        // * Estructura base: BorderLayout en el root → arriba botones, centro “lienzo”
        JFrame f = new JFrame("UT2 · Contenedores y Layouts");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // -- Cabecera con botones para cambiar de demo (usa FlowLayout por defecto)
        JPanel cabecera = new JPanel(); // FlowLayout (izquierda→derecha)
        JLabel lbl = new JLabel("Selecciona demo: ");
        JButton bFlow   = new JButton("FlowLayout");
        JButton bBorder = new JButton("BorderLayout");
        JButton bGrid   = new JButton("GridLayout");
        JButton bBox    = new JButton("BoxLayout");

        cabecera.add(lbl);
        cabecera.add(bFlow);
        cabecera.add(bBorder);
        cabecera.add(bGrid);
        cabecera.add(bBox);

        // -- Lienzo central donde “montamos” cada demo de layout
        JPanel lienzo = new JPanel(new BorderLayout());
        lienzo.add(panelFlowDemo(), BorderLayout.CENTER); // Demo por defecto

        // -- Conectar botones
        bFlow.addActionListener(e -> swap(lienzo, panelFlowDemo()));
        bBorder.addActionListener(e -> swap(lienzo, panelBorderDemo()));
        bGrid.addActionListener(e -> swap(lienzo, panelGridDemo()));
        bBox.addActionListener(e -> swap(lienzo, panelBoxDemo()));

        // -- Colocar en el root
        root.add(cabecera, BorderLayout.NORTH);
        root.add(lienzo, BorderLayout.CENTER);

        // -- Finalizar ventana
        f.setContentPane(root);
        f.pack();
        f.setMinimumSize(new Dimension(640, 420));
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // * Utilidad para cambiar el contenido del “lienzo”
    private static void swap(JPanel lienzo, JPanel nuevoCentro) {
        lienzo.removeAll();
        lienzo.add(nuevoCentro, BorderLayout.CENTER);
        lienzo.revalidate();
        lienzo.repaint();
    }

    // ! ========================================================
    // ! REGION 3) DEMOS DE LAYOUTS
    // ! ========================================================

    // * 3.1) FlowLayout: coloca componentes en fila (respeta orden de añadido)
    private static JPanel panelFlowDemo() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(titulo("FlowLayout · Fila simple (izquierda→derecha)"));

        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        for (int i = 1; i <= 6; i++) {
            JButton b = new JButton("B" + i);
            fila.add(b);
        }

        JTextArea info = new JTextArea(
            "FlowLayout:\n" +
            "• Coloca componentes en fila, respetando el orden de inserción.\n" +
            "• Alineación típica: LEFT/CENTER/RIGHT.\n" +
            "• Útil para tiras de botones o cabeceras sencillas.\n"
        );
        info.setEditable(false);
        info.setBackground(new Color(248, 248, 248));
        info.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),
                                          new EmptyBorder(8,8,8,8)));

        p.add(fila, BorderLayout.CENTER);
        p.add(info, BorderLayout.SOUTH);
        return p;
    }

    // * 3.2) BorderLayout: NORTH/SOUTH/EAST/WEST/CENTER
    private static JPanel panelBorderDemo() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(titulo("BorderLayout · 5 regiones"));

        JPanel north = cajaColor("NORTH");
        JPanel south = cajaColor("SOUTH");
        JPanel east  = cajaColor("EAST");
        JPanel west  = cajaColor("WEST");
        JPanel center= cajaColor("CENTER");

        p.add(north, BorderLayout.NORTH);
        p.add(south, BorderLayout.SOUTH);
        p.add(east,  BorderLayout.EAST);
        p.add(west,  BorderLayout.WEST);
        p.add(center,BorderLayout.CENTER);

        return p;
    }

    // * 3.3) GridLayout: rejilla F x C (todas las celdas mismo tamaño)
    private static JPanel panelGridDemo() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(titulo("GridLayout · 2 filas x 3 columnas"));

        JPanel grid = new JPanel(new GridLayout(2, 3, 8, 8)); // gaps H,V
        for (int i = 1; i <= 6; i++) {
            grid.add(botonGrande("C" + i));
        }

        JTextArea info = new JTextArea(
            "GridLayout:\n" +
            "• Rejilla regular (todas las celdas del mismo tamaño).\n" +
            "• Ideal para formularios sencillos, teclados numéricos, tableros, etc.\n" +
            "• Ajusta automáticamente al tamaño del contenedor.\n"
        );
        info.setEditable(false);
        info.setBackground(new Color(248, 248, 248));
        info.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),
                                          new EmptyBorder(8,8,8,8)));

        p.add(grid, BorderLayout.CENTER);
        p.add(info, BorderLayout.SOUTH);
        return p;
    }

    // * 3.4) BoxLayout: apila en columna o fila, con espaciadores
    private static JPanel panelBoxDemo() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(titulo("BoxLayout · Columna con espaciadores"));

        JPanel columna = new JPanel();
        columna.setLayout(new BoxLayout(columna, BoxLayout.Y_AXIS));
        columna.setBorder(new EmptyBorder(10,10,10,10));

        // Elementos + espaciadores:
        columna.add(botonAnchura("Arriba"));
        columna.add(Box.createRigidArea(new Dimension(0, 10))); // espacio fijo vertical
        columna.add(botonAnchura("Centro"));
        columna.add(Box.createVerticalGlue()); // empuja hacia abajo
        columna.add(botonAnchura("Abajo"));

        JTextArea info = new JTextArea(
            "BoxLayout:\n" +
            "• Apila en columna (Y_AXIS) o fila (X_AXIS).\n" +
            "• Permite espaciadores fijos (RigidArea) y flexibles (Glue).\n" +
            "• Muy útil para paneles con alineación vertical u horizontal.\n"
        );
        info.setEditable(false);
        info.setBackground(new Color(248, 248, 248));
        info.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),
                                          new EmptyBorder(8,8,8,8)));

        p.add(columna, BorderLayout.CENTER);
        p.add(info, BorderLayout.SOUTH);
        return p;
    }

    // ! ========================================================
    // ! REGION 4) EJERCICIO GUIADO (PASO A PASO)
    // ! ========================================================
    // * Objetivo: practicar anidación de paneles y elección de layout adecuado.
    //
    // TODO 1) Cabecera con FlowLayout:
    //   - En el panel de BorderLayout (panelBorderDemo), reemplaza la caja NORTH
    //     por un JPanel con FlowLayout(FlowLayout.LEFT), añade: JLabel("Formulario"),
    //     JButton("Guardar"), JButton("Cancelar"). Observa cómo se adapta.
    //
    // TODO 2) Formulario con GridLayout:
    //   - Crea un JPanel form = new JPanel(new GridLayout(3,2,6,6));
    //   - Añade pares etiqueta-campo (JLabel/JTextField) para: Nombre, Email, Teléfono.
    //   - Coloca form en BorderLayout.CENTER (sustituyendo la caja CENTER).
    //
    // TODO 3) Botonera inferior con BoxLayout:
    //   - Crea JPanel botonera = new JPanel(); setLayout(new BoxLayout(botonera, BoxLayout.X_AXIS));
    //   - Añade Box.createHorizontalGlue() + JButton("Aceptar") + espacio + JButton("Salir").
    //   - Coloca botonera en BorderLayout.SOUTH.
    //
    // TODO 4) Espaciado y bordes:
    //   - Aplica EmptyBorder(10,10,10,10) al “form” y a la “botonera”.
    //   - Comprueba cómo cambia el aspecto general (respira mejor).
    //
    // TODO 5) Extra (opcional):
    //   - Crea un “panel tarjeta” (JPanel con BorderLayout) y dentro añade
    //     arriba una cabecera (FlowLayout), en el centro el “form” (GridLayout)
    //     y abajo la “botonera” (BoxLayout). Anida ese panel dentro de panelBorderDemo.
    //
    // * Con estos pasos verás cómo la anidación de layouts te da control total
    //   de la colocación sin introducir aún menús ni componentes avanzados.

    // ! ========================================================
    // ! REGION 5) PRÁCTICAS CON IA (ChatGPT / GitHub Copilot)
    // ! ========================================================
    // ? Prompt 1 — Crear “formulario clásico” con GridLayout
    //   "En un JPanel con BorderLayout quiero poner en CENTER un formulario con GridLayout 3x2
    //    (labels + textfields) para Nombre, Email y Teléfono. Dame solo el fragmento."
    //
    // ? Prompt 2 — Botonera alineada a la derecha con BoxLayout
    //   "Necesito una botonera al pie con BoxLayout.X_AXIS, con botones Aceptar y Salir
    //    pegados a la derecha y con un espacio entre ellos. Dame el panel listo para añadir
    //    en BorderLayout.SOUTH."
    //
    // ? Prompt 3 — Cabecera con FlowLayout y título
    //   "Crea un panel de cabecera con FlowLayout.LEFT que incluya un JLabel grande
    //    'Formulario' y dos botones 'Guardar' y 'Cancelar', con un borde inferior sutil."
    //

    // ! ========================================================
    // ! REGION 6) HELPERS VISUALES (BORDES, BOTONES DE DEMO)
    // ! ========================================================
    private static TitledBorder titulo(String t) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            t,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("SansSerif", Font.PLAIN, 12),
            new Color(60, 60, 60)
        );
    }

    private static JPanel cajaColor(String texto) {
        JPanel p = new JPanel(new GridBagLayout()); // centra el label
        p.setBackground(new Color(240, 244, 248));
        p.setBorder(new LineBorder(new Color(200, 200, 200)));
        JLabel l = new JLabel(texto);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 12f));
        p.add(l);
        return p;
    }

    private static JButton botonGrande(String t) {
        JButton b = new JButton(t);
        b.setPreferredSize(new Dimension(96, 48));
        return b;
    }

    private static JButton botonAnchura(String t) {
        JButton b = new JButton(t);
        b.setAlignmentX(Component.CENTER_ALIGNMENT); // útil para BoxLayout en columna
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36)); // que ocupe ancho disponible
        return b;
    }
}
