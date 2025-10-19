# UT4 - Modelo de eventos en Swing (2º DAM)
Curso Completo de Interfaces Gráficas en Java

---

## 🎯 Objetivos de la Unidad
- Comprender cómo Swing gestiona la interacción mediante eventos.
- Conectar fuente (componente), evento y listener para ejecutar lógica en respuesta al usuario.
- Practicar con los listeners más comunes: `ActionListener`, `ItemListener`, `ChangeListener`, `KeyListener`, `MouseListener`.

---

## 🧠 Conceptos clave
- Fuente (source): componente que dispara el evento (p. ej. `JButton`).
- Evento (event): objeto que describe lo sucedido (p. ej. `ActionEvent`, `KeyEvent`).
- Listener: interfaz con métodos que se ejecutan cuando llega el evento (p. ej. `ActionListener#actionPerformed`).
- Registro: alta del listener en el componente (`boton.addActionListener(...)`).
- Manejador: el código dentro del método del listener que realiza la acción.

Patrón mental: "Cuando el usuario haga X en Y, entonces Z".

---

## ⚡ ActionListener — acciones de botón y ENTER en campos

```java
JTextField tfNombre = new JTextField(16);
JButton btnSaludar = new JButton("Saludar");
JLabel out = new JLabel(" ");

btnSaludar.addActionListener(e -> {
    String nombre = tfNombre.getText().trim();
    if (nombre.isEmpty()) {
        out.setText("Escribe tu nombre");
    } else {
        out.setText("¡Hola, " + nombre + "!");
    }
});

// ENTER en el JTextField equivale a acción
tfNombre.addActionListener(e -> btnSaludar.doClick());
```

Consejo: mantén el manejador corto; si crece, extrae a un método.

---

## ⚡ ItemListener — cambios de selección

```java
JCheckBox chkNoticias = new JCheckBox("Recibir noticias", true);
JRadioButton rbA = new JRadioButton("Plan A", true);
JRadioButton rbB = new JRadioButton("Plan B");
ButtonGroup grupo = new ButtonGroup(); grupo.add(rbA); grupo.add(rbB);
JComboBox<String> combo = new JComboBox<>(new String[]{"Lunes","Martes","Miércoles"});

JLabel out = new JLabel(" ");
ItemListener refrescar = e -> {
    String plan = rbA.isSelected()?"A":"B";
    String dia = (String) combo.getSelectedItem();
    out.setText("Noticias: " + (chkNoticias.isSelected()?"sí":"no") + " | Plan " + plan + " | Día: " + dia);
};

chkNoticias.addItemListener(refrescar);
rbA.addItemListener(refrescar);
rbB.addItemListener(refrescar);
combo.addItemListener(e -> { if (e.getStateChange()==ItemEvent.SELECTED) refrescar.itemStateChanged(e); });
```

---

## ⚡ ChangeListener — cambios continuos (slider/spinner)

```java
JSlider sliderVol = new JSlider(0, 100, 30);
sliderVol.setMajorTickSpacing(20);
sliderVol.setMinorTickSpacing(5);
sliderVol.setPaintTicks(true);
sliderVol.setPaintLabels(true);

SpinnerNumberModel spModel = new SpinnerNumberModel(5, 1, 10, 1);
JSpinner spinnerVel = new JSpinner(spModel);

JLabel out = new JLabel("Volumen: 30 | Velocidad: 5x");
ChangeListener refrescar = e -> {
    int vol = sliderVol.getValue();
    int vel = (Integer) spinnerVel.getValue();
    out.setText("Volumen: " + vol + " | Velocidad: " + vel + "x");
};
sliderVol.addChangeListener(refrescar);
spinnerVel.addChangeListener(refrescar);
```

---

## ⚡ KeyListener — teclado

```java
JTextField campo = new JTextField(20);
JTextArea historial = new JTextArea(8, 40);
historial.setEditable(false);

campo.addKeyListener(new KeyAdapter() {
    @Override public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_L) {
            historial.setText(""); // CTRL+L limpia
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            campo.setText(""); // ESC borra campo
        }
    }
    @Override public void keyTyped(KeyEvent e) {
        historial.append("typed: " + e.getKeyChar() + "\n");
    }
});
```

---

## ⚡ MouseListener — clics y movimientos

```java
JPanel patio = new JPanel() {
    java.util.List<Point> puntos = new java.util.ArrayList<>();
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Point pt : puntos) g.fillOval(pt.x-3, pt.y-3, 6, 6);
    }
};
patio.setPreferredSize(new Dimension(400, 300));

patio.addMouseListener(new MouseAdapter() {
    @Override public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            patio.getGraphics().dispose();
            patio.puntos.clear(); patio.repaint();
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            patio.puntos.add(e.getPoint()); patio.repaint();
        }
    }
});
```

---

## 🧭 Buenas prácticas
- No bloquees el EDT con tareas largas; usa `SwingWorker` para operaciones pesadas.
- Quita listeners si ya no son necesarios en casos complejos.
- Usa nombres claros para variables y métodos manejadores.

---

## 🧪 Ejercicios guiados (alineados con el .java)
1) ActionListener + Validación
- Desactiva "Saludar" si el campo está vacío (usa `DocumentListener`). Si supera 20 caracteres, muestra una advertencia (`JOptionPane`).

2) ItemListener + Dependencias
- Si se elige "Plan B (pro)", habilita un `JTextField` "Código promo" e inclúyelo en el resumen solo si no está vacío.

3) ChangeListener + Formato
- Añade un `JProgressBar` sincronizado con el slider (0..100). Si la velocidad es 10x, pinta la etiqueta en rojo.

4) KeyListener + Atajos
- CTRL+L limpia el historial; ESC borra el campo de texto.

5) MouseListener + Herramienta
- Dibuja un punto permanente donde se haga clic izquierdo (almacena `(x,y)` y repíntalos). Con clic derecho, limpia la lista.

---

## 🤖 Prácticas con IA
- Prompt 1 — Validación reactiva del `JTextField`
  > Necesito que un `JButton "Saludar"` esté habilitado solo cuando `JTextField "tfNombre"` no esté vacío, y que muestre una advertencia si supero 20 caracteres. Dame un fragmento corto.

- Prompt 2 — Sincronizar `JSlider` con `JProgressBar`
  > Tengo un `JSlider 0..100` y quiero que un `JProgressBar` refleje el mismo valor en tiempo real. Dame el `ChangeListener` y la inicialización.

- Prompt 3 — Detectar `CTRL+L` y `ESC`
  > En un `JTextField` con un `KeyListener`, quiero que `CTRL+L` limpie un `JTextArea historial` y que `ESC` borre el campo de texto. Dame el código de `keyPressed`.

---

## 🧩 Conclusiones
- El modelo de eventos conecta interacción y lógica de forma clara.
- Diferentes listeners para diferentes tipos de interacción.
- Mantén los manejadores cortos y el EDT libre de cargas pesadas.

---

## 📘 Próximo tema
UT5 — Menús y barras de herramientas: `JMenuBar`, `JMenu`, `JToolBar`, aceleradores y mnemonics.

---

Autor: Clases Online Joaquín  
Versión: 1.0 — 2025  
Licencia: Uso docente y libre adaptación
