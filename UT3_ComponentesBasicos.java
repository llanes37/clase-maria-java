/**
 * =========================================================
 * CURSO: Java Swing (Biblioteca Gráfica) - 2º DAM
 * UNIDAD: UT3 - Componentes básicos (nivel 1)
 * =========================================================
 *
 * // * TEORÍA (resumen docente)
 *   Objetivo de UT3
 *   - Conocer y usar los componentes más habituales de Swing para entrada y selección.
 *   - Entender la diferencia entre componentes de texto, selección única/múltiple
 *     y listas desplegables; y cómo leer/escribir su estado.
 *
 *   Componentes que veremos (nivel esencial):
 *   - Texto: JLabel, JTextField, JPasswordField, JTextArea (con JScrollPane).
 *   - Selección: JCheckBox (booleana), JRadioButton + ButtonGroup (exclusiva).
 *   - Elección: JComboBox (desplegable).
 *   - Lista: JList (simple), con JScrollPane.
 *
 *   Buenas prácticas:
 *   - Envolver componentes largos (JTextArea/JList) en JScrollPane.
 *   - Agrupar JRadioButton con ButtonGroup para exclusión.
 *   - Usar modelos de datos simples (DefaultComboBoxModel, DefaultListModel) si hace falta.
 *   - Mostrar el resultado en un JLabel/JTextArea aparte para confirmar al usuario.
 *
 *   Alcance de UT3:
 *   ✔ Lectura/escritura de valores de componentes.
 *   ✔ Eventos básicos (ActionListener, ItemListener, DocumentListener mínimo).
 *   ✖ Sin tablas/árboles (UT7), ✖ sin MVC (UT8), ✖ sin diálogos (UT6).
 *
 * =========================================================
 * Autor: Clases Online Joaquín — Año: 2025
 * =========================================================
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class UT3_ComponentesBasicos {

    // ! ========================================================
    // ! REGION 1) PUNTO DE ENTRADA — ARRANQUE EN EL EDT
    // ! ========================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UT3_ComponentesBasicos::mostrarVentanaPrincipal);
    }

    // ! ========================================================
    // ! REGION 2) VENTANA PRINCIPAL Y CONMUTADOR DE DEMOS
    // ! ========================================================
    private static void mostrarVentanaPrincipal() {
        JFrame f = new JFrame("UT3 · Componentes básicos");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(10,10));
        root.setBorder(new EmptyBorder(10,10,10,10));

        // Cabecera
        JPanel cabecera = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JLabel titulo = new JLabel("Selecciona demo:");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 14f));
        JButton bTexto     = new JButton("Texto");
        JButton bSeleccion = new JButton("Selección");
        JButton bCombo     = new JButton("Combo");
        JButton bLista     = new JButton("Lista + Área");

        cabecera.add(titulo);
        cabecera.add(bTexto);
        cabecera.add(bSeleccion);
        cabecera.add(bCombo);
        cabecera.add(bLista);

        // Lienzo central
        JPanel lienzo = new JPanel(new BorderLayout());
        lienzo.add(panelTextoDemo(), BorderLayout.CENTER); // demo por defecto

        // Eventos de conmutación
        bTexto.addActionListener(e -> swap(lienzo, panelTextoDemo()));
        bSeleccion.addActionListener(e -> swap(lienzo, panelSeleccionDemo()));
        bCombo.addActionListener(e -> swap(lienzo, panelComboDemo()));
        bLista.addActionListener(e -> swap(lienzo, panelListaAreaDemo()));

        // Barra de estado
        JLabel status = new JLabel("Listo.");
        status.setBorder(new EmptyBorder(4,2,4,2));

        root.add(cabecera, BorderLayout.NORTH);
        root.add(lienzo, BorderLayout.CENTER);
        root.add(status, BorderLayout.SOUTH);

        f.setContentPane(root);
        f.setMinimumSize(new Dimension(720, 520));
        f.setLocationRelativeTo(null);
        f.pack();
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
    // ! REGION 3) DEMOS DE COMPONENTES
    // ! ========================================================

    // * 3.1) Texto: JLabel, JTextField, JPasswordField, JTextArea + JScrollPane
    private static JPanel panelTextoDemo() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("Texto: JLabel, JTextField, JPasswordField, JTextArea"));

        // Form superior con grid 2x2
        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.setBorder(new EmptyBorder(10,10,10,10));

        JLabel lNombre = new JLabel("Nombre:");
        JTextField tfNombre = new JTextField(14);

        JLabel lClave = new JLabel("Contraseña:");
        JPasswordField pfClave = new JPasswordField(14);
        pfClave.setEchoChar('•'); // estética

        form.add(lNombre); form.add(tfNombre);
        form.add(lClave);  form.add(pfClave);

        // Área de notas con scroll
        JTextArea notas = new JTextArea(6, 30);
        notas.setLineWrap(true);
        notas.setWrapStyleWord(true);
        JScrollPane spNotas = new JScrollPane(notas);
        spNotas.setBorder(new TitledBorder("Notas (multilínea)"));

        // Panel inferior con botón y salida
        JPanel inferior = new JPanel(new BorderLayout(8,8));
        JButton btnLeer = new JButton("Leer valores");
        JLabel salida = new JLabel(" ");
        salida.setBorder(new EmptyBorder(4,4,4,4));

        // Habilitar/Deshabilitar botón según nombre (DocumentListener mínimo)
        tfNombre.getDocument().addDocumentListener(new DocumentListener() {
            private void actualizar() {
                btnLeer.setEnabled(!tfNombre.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) { actualizar(); }
            public void removeUpdate(DocumentEvent e) { actualizar(); }
            public void changedUpdate(DocumentEvent e) { actualizar(); }
        });
        btnLeer.setEnabled(false);

        btnLeer.addActionListener(e -> {
            String nombre = tfNombre.getText().trim();
            String pass = new String(pfClave.getPassword());
            String info = String.format("Nombre: %s | Clave: %s | Notas: %d chars",
                    nombre.isEmpty() ? "(vacío)" : nombre,
                    pass.isEmpty() ? "(vacía)" : "(oculta)",
                    notas.getText().length());
            salida.setText(info);
        });

        inferior.add(btnLeer, BorderLayout.WEST);
        inferior.add(salida, BorderLayout.CENTER);

        p.add(form, BorderLayout.NORTH);
        p.add(spNotas, BorderLayout.CENTER);
        p.add(inferior, BorderLayout.SOUTH);
        return p;
    }

    // * 3.2) Selección: JCheckBox, JRadioButton + ButtonGroup
    private static JPanel panelSeleccionDemo() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("Selección: JCheckBox, JRadioButton + ButtonGroup"));

        // Checkboxes (múltiple selección)
        JPanel checks = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checks.setBorder(new TitledBorder("Intereses (múltiple)"));
        JCheckBox chJava = new JCheckBox("Java", true);
        JCheckBox chPython = new JCheckBox("Python");
        JCheckBox chJS = new JCheckBox("JavaScript");
        JCheckBox chOtro = new JCheckBox("Otro");
        JTextField tfOtro = new JTextField(10);
        tfOtro.setEnabled(false);

        chOtro.addItemListener(e -> tfOtro.setEnabled(chOtro.isSelected()));

        checks.add(chJava); checks.add(chPython); checks.add(chJS);
        checks.add(chOtro); checks.add(tfOtro);

        // Radios (selección exclusiva)
        JPanel radios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radios.setBorder(new TitledBorder("Disponibilidad (exclusiva)"));
        JRadioButton rbM = new JRadioButton("Mañanas", true);
        JRadioButton rbT = new JRadioButton("Tardes");
        JRadioButton rbN = new JRadioButton("Noches");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbM); grupo.add(rbT); grupo.add(rbN);
        radios.add(rbM); radios.add(rbT); radios.add(rbN);

        // Resultado en tiempo real
        JLabel resultado = new JLabel("Selecciona opciones…");
        resultado.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),
                                               new EmptyBorder(6,6,6,6)));

        // Listeners
        ItemListener refrescar = e -> {
            StringBuilder sb = new StringBuilder("Intereses: ");
            boolean alguno = false;
            if (chJava.isSelected())  { sb.append("Java "); alguno = true; }
            if (chPython.isSelected()){ sb.append("Python "); alguno = true; }
            if (chJS.isSelected())    { sb.append("JavaScript "); alguno = true; }
            if (chOtro.isSelected() && !tfOtro.getText().trim().isEmpty()) {
                sb.append(tfOtro.getText().trim()).append(' '); alguno = true;
            }
            if (!alguno) sb.append("(ninguno)");
            String dispo = rbM.isSelected()? "Mañanas" : rbT.isSelected()? "Tardes" : "Noches";
            resultado.setText(sb.toString().trim() + " | Disponibilidad: " + dispo);
        };
        chJava.addItemListener(refrescar);
        chPython.addItemListener(refrescar);
        chJS.addItemListener(refrescar);
        chOtro.addItemListener(refrescar);
        tfOtro.getDocument().addDocumentListener(new DocumentListener() {
            void upd(){ refrescar.itemStateChanged(null); }
            public void insertUpdate(DocumentEvent e){ upd(); }
            public void removeUpdate(DocumentEvent e){ upd(); }
            public void changedUpdate(DocumentEvent e){ upd(); }
        });
        rbM.addItemListener(refrescar);
        rbT.addItemListener(refrescar);
        rbN.addItemListener(refrescar);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(checks);
        center.add(radios);

        p.add(center, BorderLayout.CENTER);
        p.add(resultado, BorderLayout.SOUTH);
        return p;
    }

    // * 3.3) Combo: JComboBox (cambio de selección + opción "Otro…")
    private static JPanel panelComboDemo() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("Elección: JComboBox (desplegable)"));

        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel l = new JLabel("Lenguaje favorito:");
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(new String[]{
                "Java", "Python", "JavaScript", "C#", "Kotlin", "Otro…"
        });
        JComboBox<String> combo = new JComboBox<>(model);
        combo.setSelectedItem("Java");
        fila.add(l); fila.add(combo);

        JLabel out = new JLabel("Seleccionado: Java");
        out.setBorder(new EmptyBorder(4,4,4,4));

        combo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String sel = (String) combo.getSelectedItem();
                if ("Otro…".equals(sel)) {
                    String nuevo = JOptionPane.showInputDialog(p, "Indica otro lenguaje:");
                    if (nuevo != null && !nuevo.trim().isEmpty()) {
                        model.insertElementAt(nuevo.trim(), model.getSize()-1);
                        combo.setSelectedItem(nuevo.trim());
                    } else {
                        combo.setSelectedIndex(0);
                    }
                } else {
                    out.setText("Seleccionado: " + sel);
                }
            }
        });

        p.add(fila, BorderLayout.NORTH);
        p.add(out, BorderLayout.SOUTH);
        return p;
    }

    // * 3.4) Lista + Área: JList y JTextArea (con scroll)
    private static JPanel panelListaAreaDemo() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(titulo("Lista simple + Área de contenido"));

        DefaultListModel<String> modelo = new DefaultListModel<>();
        modelo.addElement("Introducción");
        modelo.addElement("Variables");
        modelo.addElement("Control de flujo");
        modelo.addElement("POO");
        modelo.addElement("Colecciones");
        JList<String> lista = new JList<>(modelo);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setSelectedIndex(0);

        JScrollPane spLista = new JScrollPane(lista);
        spLista.setPreferredSize(new Dimension(160, 300));
        spLista.setBorder(new TitledBorder("Temas"));

        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JScrollPane spArea = new JScrollPane(area);
        spArea.setBorder(new TitledBorder("Descripción"));

        // Autocompletar descripción básica al cambiar selección
        lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String sel = lista.getSelectedValue();
                area.setText("Descripción de: " + sel + "\n\nEscribe aquí tus notas...");
            }
        });
        // Inicial
        area.setText("Descripción de: " + lista.getSelectedValue() + "\n\nEscribe aquí tus notas...");

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spLista, spArea);
        split.setResizeWeight(0.3);

        p.add(split, BorderLayout.CENTER);
        return p;
    }

    // ! ========================================================
    // ! REGION 4) EJERCICIO GUIADO (PASO A PASO)
    // ! ========================================================
    // * Practicar lectura de componentes y mostrar resumen en un JLabel/JTextArea.
    //
    // TODO 1) En la demo de Texto:
    //   - Desactiva el botón “Leer valores” si el nombre está vacío.
    //     Pista: usa DocumentListener sobre tfNombre.getDocument().
    //
    // TODO 2) En la demo de Selección:
    //   - Añade un JCheckBox “Otro (especificar)” y un JTextField a su lado.
    //     Si el check está activo, incluye el texto del campo en el resumen.
    //
    // TODO 3) En la demo de Combo:
    //   - Cambia el modelo por un DefaultComboBoxModel y añade la opción “Otro…”.
    //     Si se elige “Otro…”, muestra un JOptionPane para pedir un valor y añádelo.
    //
    // TODO 4) En la demo de Lista+Área:
    //   - Cambia a selección múltiple y muestra en el área una lista de seleccionados.
    //     Pista: usa getSelectedValuesList().
    //
    // * Con estas tareas reforzarás lectura/escritura de valores y eventos típicos.

    // ! ========================================================
    // ! REGION 5) PRÁCTICAS CON IA (ChatGPT / GitHub Copilot)
    // ! ========================================================
    // ? Prompt 1 — DocumentListener mínimo para habilitar botón
    //   "Tengo un JTextField tfNombre y un JButton btnLeer. Necesito un DocumentListener
    //    para habilitar btnLeer solo cuando tfNombre no esté vacío. Dame el fragmento."
    //
    // ? Prompt 2 — JRadioButton + ButtonGroup (exclusión) con lectura resumida
    //   "Quiero leer el valor activo de tres JRadioButton (mañana/tarde/noche) en un String
    //    'disponibilidad'. Dame un ejemplo claro y corto."
    //
    // ? Prompt 3 — JComboBox con opción 'Otro…' que abre JOptionPane
    //   "Añade a un JComboBox la opción 'Otro…'. Si el usuario la selecciona, abre un
    //    JOptionPane.showInputDialog para introducir un nuevo elemento y añádelo al combo."
    //

    // ! ========================================================
    // ! REGION 6) HELPERS VISUALES (BORDES, BOTONES)
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
