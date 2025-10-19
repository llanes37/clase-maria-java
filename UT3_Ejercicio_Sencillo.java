/**
 * =========================================================
 * CURSO: Java Swing (Biblioteca Gráfica) - 2º DAM
 * UNIDAD: UT3 — Componentes básicos (ejercicio muy sencillo)
 * =========================================================
 *
 * Objetivo del ejercicio
 * - Construir paso a paso una pequeña ventana de "Preferencias" para practicar
 *   lectura/escritura de componentes básicos: JTextField, JCheckBox, JRadioButton,
 *   ButtonGroup, JComboBox, JTextArea (con JScrollPane) y JButton.
 *
 * Filosofía
 * - Todo está súper explicado y dividido por REGIONES y TODOs. El alumno escribe el código.
 * - Mantenerse en lo visto en UT1/UT2/UT3 nivel inicial (sin tablas, diálogos complejos, ni MVC).
 * - Validación mínima; prioriza comprender cómo montar y leer componentes.
 */

// PISTA (no lo copies literal):
// import javax.swing.*; import javax.swing.border.*; import javax.swing.event.*;
// import java.awt.*; import java.awt.event.*;

public class UT3_Ejercicio_Sencillo {

    // ========================================================
    // REGION 1) PUNTO DE ENTRADA — ARRANQUE EN EL EDT
    // ========================================================
    public static void main(String[] args) {
        // TODO 1.1) Arrancar SIEMPRE la construcción de la UI en el EDT
        // - Usa SwingUtilities.invokeLater(() -> crearYMostrarGUI());
        // - ¿Por qué? Swing NO es thread-safe; evitamos condiciones de carrera.
    }

    // ========================================================
    // REGION 2) CREAR Y MOSTRAR LA VENTANA PRINCIPAL
    // ========================================================
    // TODO 2.1) Crea un método privado estático crearYMostrarGUI()
    // - Crea un JFrame con título "UT3 · Preferencias (sencillo)".
    // - f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    // - Crea un JPanel root con BorderLayout(10,10) y EmptyBorder(10,10,10,10)
    // - Llama a constructores de paneles (regiones 3-6) y colócalos en NORTH/CENTER/SOUTH.
    // - f.setContentPane(root); f.pack(); f.setMinimumSize(new Dimension(640, 420));
    //   f.setLocationRelativeTo(null); f.setVisible(true);

    // ========================================================
    // REGION 3) PANEL SUPERIOR — DATOS BÁSICOS (GridLayout 2x2)
    // ========================================================
    // TODO 3.1) buildDatosBasicos(): JPanel
    // - new JPanel(new GridLayout(2,2,8,8)) + EmptyBorder(10,10,10,10)
    // - JLabel("Nombre:"), JTextField(14)
    // - JLabel("Email:"), JTextField(18)
    // - Devuelve el panel con los pares añadidos (label-campo, label-campo)
    // - NOTA: guarda referencias a los JTextField en variables de clase si los leerás después.

    // ========================================================
    // REGION 4) PANEL CENTRAL — PREFERENCIAS (Checks + Radios + Combo)
    // ========================================================
    // TODO 4.1) buildPreferencias(): JPanel (usa BoxLayout en Y o BorderLayout)
    // - Subpanel A (FlowLayout.LEFT): JCheckBox "Newsletter" y "Promos"
    // - Subpanel B (FlowLayout.LEFT): JRadioButton "Básico" | "Intermedio" | "Avanzado"
    //   agrupados con ButtonGroup (selección exclusiva). Marca uno por defecto.
    // - Subpanel C (FlowLayout.LEFT): JLabel("Tema:"), JComboBox<String> con "Claro", "Oscuro", "Sistema"
    // - Añade A, B, C a un contenedor vertical con espaciado.

    // ========================================================
    // REGION 5) PANEL INFERIOR — RESUMEN + ACCIONES (BorderLayout)
    // ========================================================
    // TODO 5.1) buildAcciones(): JPanel
    // - Crea un JTextArea (5, 40) dentro de un JScrollPane con TitledBorder("Resumen")
    // - Crea una botonera (FlowLayout.RIGHT) con JButton("Generar resumen") y JButton("Limpiar")
    // - Al pulsar "Generar resumen":
    //     * Lee todos los valores de las regiones 3 y 4
    //     * Construye un String multilinea y muéstralo en el JTextArea
    // - Al pulsar "Limpiar":
    //     * Vacía el JTextArea y restablece valores por defecto (texto vacío, radios por defecto, etc.)

    // ========================================================
    // REGION 6) NOTA TÉCNICA: CÓMO COMPARTIR CAMPOS
    // ========================================================
    // - Necesitarás acceder a JTextField/JCheckBox/JRadioButton/JComboBox desde varios métodos.
    // - Opciones:
    //   A) Declara campos estáticos privados en esta clase y asígnalos en los buildXxx().
    //   B) Crea una clase interna "Modelo" con las referencias y pásala entre métodos.
    // - Para este ejercicio, la opción A es suficiente y sencilla.

    // ========================================================
    // REGION 7) CHECKLIST ANTES DE PROBAR
    // ========================================================
    // [ ] Arranca en el EDT
    // [ ] Ventana principal con BorderLayout y márgenes
    // [ ] Panel superior (datos) con 2x2
    // [ ] Panel central (preferencias): checks + radios + combo
    // [ ] Panel inferior con JTextArea (scroll) + botones
    // [ ] Botones funcionales: "Generar resumen" y "Limpiar"
    // [ ] Código organizado por regiones y comentado

    // ========================================================
    // REGION 8) COMPILAR Y EJECUTAR (Windows PowerShell)
    // ========================================================
    // Compilar:
    //   javac UT3_Ejercicio_Sencillo.java
    // Ejecutar:
    //   java UT3_Ejercicio_Sencillo
}
