# UT3 - Componentes básicos de Swing (2º DAM)
Curso Completo de Interfaces Gráficas en Java

---

## 🎯 Objetivos de la Unidad
- Manejar los componentes habituales de entrada y selección en Swing.
- Leer y escribir el estado de los componentes de texto, checkboxes, radios, combos y listas.
- Usar modelos sencillos (`DefaultComboBoxModel`, `DefaultListModel`) y contenedores con scroll.

---

## 🧠 Componentes cubiertos
- Texto: `JLabel`, `JTextField`, `JPasswordField`, `JTextArea` (siempre con `JScrollPane`).
- Selección: `JCheckBox` (múltiple), `JRadioButton` + `ButtonGroup` (exclusiva).
- Elección: `JComboBox` (modelo + opción "Otro…").
- Lista: `JList` (con `DefaultListModel` y `JScrollPane`).

---

## ⚡ Ejemplo 1 — Texto y lectura de valores

```java
JLabel lNombre = new JLabel("Nombre:");
JTextField tfNombre = new JTextField(14);
JLabel lClave = new JLabel("Contraseña:");
JPasswordField pfClave = new JPasswordField(14);
pfClave.setEchoChar('•');

JTextArea notas = new JTextArea(6, 30);
notas.setLineWrap(true);
notas.setWrapStyleWord(true);
JScrollPane spNotas = new JScrollPane(notas);

JButton btnLeer = new JButton("Leer valores");
JLabel salida = new JLabel(" ");

btnLeer.addActionListener(e -> {
    String nombre = tfNombre.getText().trim();
    String pass = new String(pfClave.getPassword());
    String info = String.format("Nombre: %s | Clave: %s | Notas: %d chars",
            nombre.isEmpty()?"(vacío)":nombre,
            pass.isEmpty()?"(vacía)":"(oculta)",
            notas.getText().length());
    salida.setText(info);
});
```

Pista: habilita/deshabilita el botón con `DocumentListener` según si el nombre está vacío.

---

## ⚡ Ejemplo 2 — Selección: checks y radios

```java
// Checks múltiples
JCheckBox chJava = new JCheckBox("Java", true);
JCheckBox chPython = new JCheckBox("Python");
JCheckBox chJS = new JCheckBox("JavaScript");
JCheckBox chOtro = new JCheckBox("Otro");
JTextField tfOtro = new JTextField(10); tfOtro.setEnabled(false);
chOtro.addItemListener(e -> tfOtro.setEnabled(chOtro.isSelected()));

// Radios exclusivos
JRadioButton rbM = new JRadioButton("Mañanas", true);
JRadioButton rbT = new JRadioButton("Tardes");
JRadioButton rbN = new JRadioButton("Noches");
ButtonGroup g = new ButtonGroup(); g.add(rbM); g.add(rbT); g.add(rbN);

JLabel resultado = new JLabel("Selecciona opciones…");
ItemListener refrescar = e -> {
    StringBuilder sb = new StringBuilder("Intereses: ");
    boolean alguno = false;
    if (chJava.isSelected()) { sb.append("Java "); alguno = true; }
    if (chPython.isSelected()) { sb.append("Python "); alguno = true; }
    if (chJS.isSelected()) { sb.append("JavaScript "); alguno = true; }
    if (chOtro.isSelected() && !tfOtro.getText().trim().isEmpty()) { sb.append(tfOtro.getText().trim()).append(' '); alguno = true; }
    if (!alguno) sb.append("(ninguno)");
    String dispo = rbM.isSelected()?"Mañanas": rbT.isSelected()?"Tardes":"Noches";
    resultado.setText(sb.toString().trim() + " | Disponibilidad: " + dispo);
};
```

---

## ⚡ Ejemplo 3 — Combo con modelo y opción "Otro…"

```java
DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(new String[]{
    "Java", "Python", "JavaScript", "C#", "Kotlin", "Otro…"
});
JComboBox<String> combo = new JComboBox<>(model);
combo.addItemListener(e -> {
    if (e.getStateChange() == ItemEvent.SELECTED) {
        String sel = (String) combo.getSelectedItem();
        if ("Otro…".equals(sel)) {
            String nuevo = JOptionPane.showInputDialog(null, "Indica otro lenguaje:");
            if (nuevo != null && !nuevo.trim().isEmpty()) {
                model.insertElementAt(nuevo.trim(), model.getSize()-1); // antes de "Otro…"
                combo.setSelectedItem(nuevo.trim());
            } else {
                combo.setSelectedIndex(0);
            }
        }
    }
});
```

---

## ⚡ Ejemplo 4 — Lista + Área con `JSplitPane`

```java
DefaultListModel<String> modelo = new DefaultListModel<>();
modelo.addElement("Introducción");
modelo.addElement("Variables");
modelo.addElement("Control de flujo");
modelo.addElement("POO");
modelo.addElement("Colecciones");
JList<String> lista = new JList<>(modelo);
lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
lista.setSelectedIndex(0);

JTextArea area = new JTextArea();
area.setLineWrap(true); area.setWrapStyleWord(true);
lista.addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        String sel = lista.getSelectedValue();
        area.setText("Descripción de: " + sel + "\n\nEscribe aquí tus notas...");
    }
});
```

---

## 🧭 Buenas prácticas
- Envolver `JTextArea` y `JList` en `JScrollPane` siempre.
- Agrupar `JRadioButton` con `ButtonGroup` para exclusión.
- Usar modelos de datos simples cuando sea útil (`DefaultComboBoxModel`, `DefaultListModel`).
- Mostrar el resultado en una etiqueta o área de texto separada para confirmar al usuario.

---

## 🧪 Ejercicios guiados (alineados con el .java)
1) Texto: desactivar botón
- Desactiva "Leer valores" si el nombre está vacío. Usa `DocumentListener` sobre `tfNombre`.

2) Selección: campo "Otro"
- Añade `JCheckBox "Otro (especificar)"` y un `JTextField` a su lado. Si está activo y tiene texto, inclúyelo en el resumen.

3) Combo: opción "Otro…"
- Usa `DefaultComboBoxModel`. Si eligen "Otro…", pide un valor con `JOptionPane` y añádelo al combo.

4) Lista + Área: múltiple
- Cambia a selección múltiple y escribe todos los seleccionados en el área (`getSelectedValuesList()`).

---

## 🤖 Prácticas con IA
- Prompt 1 — `DocumentListener` mínimo para habilitar botón
  > Tengo un `JTextField tfNombre` y un `JButton btnLeer`. Necesito un `DocumentListener` para habilitar `btnLeer` solo cuando `tfNombre` no esté vacío. Dame el fragmento.

- Prompt 2 — `ButtonGroup` y lectura
  > Quiero leer el valor activo de tres `JRadioButton` (mañana/tarde/noche) en un `String disponibilidad`. Dame un ejemplo claro y corto.

- Prompt 3 — `JComboBox` con "Otro…"
  > Añade a un `JComboBox` la opción "Otro…". Si el usuario la selecciona, abre un `JOptionPane.showInputDialog` para introducir un nuevo elemento y añádelo al combo.

---

## 🧩 Conclusiones
- Con unos pocos componentes básicos se cubre la mayoría de formularios iniciales.
- Leer y escribir el estado es fundamental para la interacción.
- Los modelos (`DefaultXXXModel`) simplifican la gestión de datos en combos y listas.

---

## 📘 Próximo tema
UT4 — Modelo de eventos: Action, Item, Change, Key, Mouse. Cómo reaccionar a la interacción del usuario.

---

Autor: Clases Online Joaquín  
Versión: 1.0 — 2025  
Licencia: Uso docente y libre adaptación
