/**
 * =========================================================
 * CURSO: Java Swing (Biblioteca Gráfica) - 2º DAM
 * UNIDAD: UT1 + UT2 — Repaso guiado (Ejercicio práctico)
 * =========================================================
 *
 * Objetivo del ejercicio
 * - Crear, desde cero, una pequeña ventana de "Ficha de contacto" que permita:
 *   1) Mostrar una cabecera simple (FlowLayout) con título y dos botones.
 *   2) Un formulario (GridLayout) con 3 campos básicos: Nombre, Email y Teléfono.
 *   3) Una botonera inferior (BoxLayout.X_AXIS) alineada a la derecha.
 *   4) Organizar todo dentro de un panel principal (BorderLayout) aplicando bordes y espacios.
 *
 * Importante
 * - Este archivo NO trae código hecho: solo la estructura, regiones y TODOs detallados.
 * - Debes escribir tú todo el código Java (imports, clases, métodos y listeners).
 * - Limítate a lo visto en UT1 y UT2: JFrame/JPanel, Flow/Border/Grid/Box, JButton,
 *   JLabel, JTextField, ActionListener (para el mínimo de interacción), pack() y centrado.
 * - Evita aún menús, diálogos avanzados, listas/tablas u otras bibliotecas.
 *
 * Qué se evalúa
 * - Comprensión del EDT, creación de JFrame y panel raíz.
 * - Elección correcta de layouts y anidación de paneles.
 * - Registro de eventos básicos (ActionListener) en botones.
 * - Organización limpia del código y lectura de los comentarios/TODOs.
 *
 * Sugerencias
 * - Trabaja por regiones en el orden indicado.
 * - Compila y prueba a menudo: primero que abra la ventana vacía, luego añade piezas.
 * - Apóyate en UT1_Swing_Introduccion.java y UT2_ContenedoresYLayouts.java como referencia.
 */

// TODO: 0) IMPORTS NECESARIOS (añádelos tú)
// - Pista (no lo copies literalmente): javax.swing.*, java.awt.*, java.awt.event.*

public class UT1_UT2_Repaso {

    // ========================================================
    // REGION 1) PUNTO DE ENTRADA — ARRANQUE EN EL EDT
    // ========================================================
    public static void main(String[] args) {
        // TODO 1.1) Arranca la construcción de la GUI en el EDT usando invokeLater
        // - Llama a un método estático que tú crearás (por ejemplo: crearYMostrarGUI)
        // - No implementes aquí toda la interfaz; solo delega.
    }

    // ========================================================
    // REGION 2) CONSTRUCCIÓN DE LA INTERFAZ (PASO A PASO)
    // ========================================================
    // TODO 2.1) Crea un método privado estático (p. ej. crearYMostrarGUI)
    // - Dentro, crea un JFrame con título "Repaso UT1+UT2 · Ficha de contacto".
    // - setDefaultCloseOperation(EXIT_ON_CLOSE)
    // - Crea un JPanel "root" con BorderLayout y aplica un EmptyBorder(10,10,10,10).
    // - Llama a helpers (región 5) para construir las 3 zonas: cabecera, centro (form) y pie.
    // - Añade esas zonas al root en NORTH, CENTER y SOUTH respectivamente.
    // - Monta el root en el frame, usa pack(), setMinimumSize(640x400), centrar y visible.

    // ========================================================
    // REGION 3) CABECERA (FlowLayout)
    // ========================================================
    // TODO 3.1) Crea un método privado estático que devuelva JPanel (p. ej. buildCabecera)
    // - Usa FlowLayout(FlowLayout.LEFT).
    // - Añade JLabel con texto "Ficha de contacto" (puedes ponerle font más grande si quieres).
    // - Añade dos JButton: "Guardar" y "Cancelar".
    // - Registra ActionListener mínimo:
    //     * Guardar: por ahora, solo cambia el título del frame o muestra un mensaje en consola.
    //     * Cancelar: limpia los campos del formulario (ver región 4) o cierra la app si lo prefieres.
    // - Sugerencia: si necesitas referenciar campos del form desde aquí, plantea devolver una
    //   estructura de datos simple o mantener referencias a nivel de clase (ver NOTA técnica abajo).

    // ========================================================
    // REGION 4) FORMULARIO (GridLayout 3x2)
    // ========================================================
    // TODO 4.1) Crea un método privado estático que devuelva JPanel (p. ej. buildFormulario)
    // - new JPanel(new GridLayout(3, 2, 8, 8))
    // - Pares etiqueta-campo:
    //     Nombre:  JLabel + JTextField(14)
    //     Email:   JLabel + JTextField(14)
    //     Teléfono: JLabel + JTextField(12)
    // - Aplica un EmptyBorder(10,10,10,10) para respirar.
    // - No uses validaciones aún; céntrate en la estructura.

    // ========================================================
    // REGION 5) BOTONERA (BoxLayout.X_AXIS)
    // ========================================================
    // TODO 5.1) Crea un método privado estático que devuelva JPanel (p. ej. buildBotonera)
    // - Crea JPanel y setLayout(new BoxLayout(panel, BoxLayout.X_AXIS))
    // - Añade Box.createHorizontalGlue() para empujar a la derecha.
    // - Añade JButton("Aceptar") y un espacio (RigidArea) y JButton("Salir").
    // - ActionListener mínimo:
    //     * Aceptar: lee los campos del form y muestra en consola un saludo con el nombre.
    //     * Salir: cierra la aplicación (dispose o System.exit(0)).
    // - Aplica EmptyBorder(10,10,10,10).

    // ========================================================
    // REGION 6) NOTA TÉCNICA SOBRE REFERENCIAS
    // ========================================================
    // Necesitarás acceder a los JTextField del formulario desde la cabecera/botonera.
    // Opciones válidas (elige UNA y sé coherente):
    //   A) Campos estáticos privados en la clase (JTextField nombreField, emailField, telField).
    //      - Los inicializas en buildFormulario() y luego los lees/modificas desde los listeners.
    //   B) Crear una pequeña clase interna "ModeloFormulario" con 3 JTextField y pasarla a los builders.
    //   C) Devolver desde buildFormulario() un JPanel y, además, guardar referencias en variables de clase.
    // Mantén el diseño simple (nivel UT1/UT2). Evita patrones avanzados por ahora.

    // ========================================================
    // REGION 7) HELPERS VISUALES OPCIONALES
    // ========================================================
    // TODO 7.1) Si quieres, crea helpers como:
    // - JPanel titledPanel(String titulo, JComponent contenido) → envuelve con TitledBorder.
    // - JButton boton(String texto, int ancho, int alto) → para tamaños consistentes.
    // No son obligatorios, pero ayudan a mantener el código limpio.

    // ========================================================
    // REGION 8) CHECKLIST DE ENTREGA (revísalo antes de probar)
    // ========================================================
    // [ ] Arranca en el EDT
    // [ ] Ventana principal con título, defaultCloseOperation, pack(), tamaño mínimo y centrado.
    // [ ] root con BorderLayout + márgenes (EmptyBorder)
    // [ ] Cabecera con FlowLayout.LEFT y 2 botones funcionales
    // [ ] Formulario con GridLayout 3x2 y 3 JTextField
    // [ ] Botonera con BoxLayout.X_AXIS alineada a la derecha
    // [ ] Eventos mínimos en Guardar/Cancelar/Aceptar/Salir
    // [ ] Código organizado por regiones y comentado

    // ========================================================
    // REGION 9) CÓMO COMPILAR Y EJECUTAR (Windows PowerShell)
    // ========================================================
    // 1) Compilar en la carpeta del archivo:
    //    javac UT1_UT2_Repaso.java
    // 2) Ejecutar:
    //    java UT1_UT2_Repaso
    // Si hay más .java en la carpeta, puedes compilar todos con:
    //    javac *.java
}
