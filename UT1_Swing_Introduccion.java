/**
 * =========================================================
 * CURSO: Java Swing (Biblioteca Gráfica) - 2º DAM
 * UNIDAD: UT1 - Introducción MUY BÁSICA a Swing
 * =========================================================
 *
 * // * TEORÍA (resumen docente)
 *   ¿Qué es Swing?
 *   - Swing es la biblioteca gráfica estándar de Java (incluida en el JDK) para
 *     crear interfaces de escritorio: ventanas, botones, etiquetas...
 *
 *   ¿Qué es el EDT (Event Dispatch Thread)?
 *   - Es el hilo donde deben crearse/actualizarse los componentes Swing.
 *     Usamos SwingUtilities.invokeLater(...) para asegurar que todo el código
 *     de GUI se ejecute en ese hilo y evitar problemas de concurrencia.
 *
 *   Contenedor principal (JFrame)
 *   - Ventana con título y controles de sistema. Propiedades clave:
 *     • setTitle("...")                     → Título de la ventana
 *     • setDefaultCloseOperation(...)       → Comportamiento al cerrar
 *     • setContentPane(panelRaiz)           → Panel raíz de la UI
 *     • pack()                              → Tamaño óptimo según contenido
 *     • setLocationRelativeTo(null)         → Centrar en pantalla
 *     • setVisible(true)                    → Mostrar la ventana
 *
 *   Panel de contenido (JPanel)
 *   - Contenedor genérico para colocar componentes.
 *   - Por defecto usa FlowLayout (coloca componentes en fila).
 *   - En UT2 veremos layouts en detalle (Border, Grid, Box...).
 *
 *   Componentes usados en UT1
 *   - JLabel  → muestra texto.
 *   - JButton → botón que dispara una acción (ActionListener).
 *
 *   Modelo de eventos (UT1)
 *   - Un único evento: ActionListener en el botón para cambiar el texto del JLabel.
 *
 * // NOTE: Mantenerlo básico en UT1 (un panel, dos componentes, un evento).
 *          Nada de menús, diálogos ni layouts avanzados todavía.
 *
 * =========================================================
 * Autor: Clases Online Joaquín — Año: 2025
 * =========================================================
 */

import javax.swing.*;   // Swing: JFrame, JPanel, JLabel, JButton...
import java.awt.*;      // AWT: Dimension, Color (si lo usas), layouts básicos
import java.awt.event.*; // Eventos: ActionListener

public class UT1_Swing_Introduccion {

    // ! ========================================================
    // ! REGION 1) PUNTO DE ENTRADA — ARRANQUE EN EL EDT
    // ! ========================================================
    public static void main(String[] args) {
        // * Ejecutamos la construcción de la interfaz en el EDT.
        SwingUtilities.invokeLater(UT1_Swing_Introduccion::crearYMostrarGUI);
    }

    // ! ========================================================
    // ! REGION 2) CONSTRUCCIÓN PASO A PASO DE LA GUI
    // ! ========================================================
    private static void crearYMostrarGUI() {
        // * PASO 1) Crear la ventana principal (JFrame)
        JFrame ventana = new JFrame("UT1 · Introducción a Swing");

        // ! IMPORTANTE: Cerrar la app completa al cerrar esta ventana.
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // * PASO 2) Crear el panel raíz (contenedor principal)
        // NOTE: FlowLayout por defecto — suficiente para UT1.
        JPanel raiz = new JPanel();

        // * PASO 3) Crear componentes mínimos (etiqueta + botón)
        JLabel etiqueta = new JLabel("Pulsa el botón para saludar");
        JButton boton  = new JButton("Saludar");

        // * Mejora UX básica (opcional en UT1, no complica conceptos):
        boton.setToolTipText("Haz clic para mostrar el saludo");
        boton.setMnemonic('S'); // Alt+S activa el botón

        // * PASO 4) Añadir componentes al panel raíz (en orden)
        raiz.add(etiqueta);
        raiz.add(boton);

        // * PASO 5) Registrar UN evento sencillo en el botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiqueta.setText("¡Hola, Swing!");
                // TODO (experimento rápido): desactiva el botón y observa:
                // boton.setEnabled(false);
            }
        });

        // * PASO 6) Montar el panel en la ventana
        ventana.setContentPane(raiz);

        // * PASO 7) Ajustar tamaño, centrar y mostrar
        ventana.pack();                                // Cálculo óptimo según contenido
        ventana.setMinimumSize(new Dimension(360,120)); // Evita tamaños ridículos
        ventana.setLocationRelativeTo(null);           // Centrar
        // NOTE: Puedes activar el botón por defecto si añades un JTextField en los ejercicios:
        // ventana.getRootPane().setDefaultButton(boton);
        ventana.setVisible(true);
    }

    // ! ========================================================
    // ! REGION 3) EJERCICIO GUIADO (PASO A PASO DENTRO DEL ARCHIVO)
    // ! ========================================================
    // * Objetivo: practicar sobre este mismo .java sin nuevas clases/proyectos.
    //
    // TODO 1) Añadir botón "Reiniciar"
    //   a) Crea: JButton btnReiniciar = new JButton("Reiniciar");
    //   b) Añádelo al panel 'raiz' junto al resto (antes de pack()).
    //   c) En su ActionListener, devuelve el texto original:
    //        etiqueta.setText("Pulsa el botón para saludar");
    //      (Si desactivaste "Saludar" antes, vuelve a activarlo con boton.setEnabled(true);)
    //
    // TODO 2) Personalizar estilo básico
    //   a) Al saludar, cambia el color del texto: etiqueta.setForeground(Color.BLUE);
    //   b) En "Reiniciar", vuelve a negro: etiqueta.setForeground(Color.BLACK);
    //   c) Cambia el título de la ventana: ventana.setTitle("UT1 · Mi primera app Swing");
    //
    // TODO 3) Entrada de nombre (mínimo)
    //   a) Crea JTextField nombre = new JTextField(12);
    //   b) Añádelo al panel 'raiz' ANTES del botón "Saludar".
    //   c) En el ActionListener de "Saludar":
    //        String n = nombre.getText().trim();
    //        etiqueta.setText(n.isEmpty() ? "¡Hola, Swing!" : "¡Hola, " + n + "!");
    //
    // TODO 4) pack() vs setSize()
    //   a) Sustituye pack() por setSize(500,300) y mira la diferencia.
    //   b) Vuelve a pack() (recomendado) para el comportamiento estándar.

    // ! ========================================================
    // ! REGION 4) PRÁCTICAS CON IA (ChatGPT / GitHub Copilot)
    // ! ========================================================
    // * Usa estos prompts cortos para mejorar tu código SIN salir de UT1:
    //
    // ? Prompt 1 — Botón Reiniciar (mínimo necesario)
    //   "Tengo un JFrame con JPanel, JLabel 'etiqueta' y JButton 'boton' que saluda.
    //    Dame SOLO el fragmento para crear un JButton 'Reiniciar', añadirlo al panel
    //    y su ActionListener para devolver el texto original y reactivar 'boton' si hiciera falta."
    //
    // ? Prompt 2 — Estilo simple del JLabel
    //   "Indícame dónde colocar setForeground(Color.BLUE) al pulsar 'Saludar' y
    //    dónde volver a Color.BLACK al pulsar 'Reiniciar' en este ejemplo básico de Swing."
    //
    // ? Prompt 3 — Nombre desde JTextField
    //   "Quiero añadir JTextField 'nombre' y que, si no está vacío, muestre '¡Hola, <nombre>!'.
    //    Dame el ActionListener del botón 'Saludar' con esa lógica, manteniendo el resto igual."
}
