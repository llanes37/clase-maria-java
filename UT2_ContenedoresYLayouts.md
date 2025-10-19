# UT2 - Contenedores y Layouts (2º DAM)
Curso Completo de Interfaces Gráficas en Java

---

## 🎯 Objetivos de la Unidad
- Entender el papel de un contenedor (`JFrame`, `JPanel`) y su gestor de disposición (Layout Manager).
- Conocer y practicar los layouts esenciales: `FlowLayout`, `BorderLayout`, `GridLayout` y `BoxLayout`.
- Aprender a anidar paneles y a separar visualmente con bordes y márgenes.

---

## 🧠 ¿Qué es un Layout Manager?
Es la estrategia que usa un contenedor (como un `JPanel`) para colocar y dimensionar sus componentes hijos. Cada layout decide cómo se reparte el espacio y qué tamaño tiene cada componente.

Relación clave:
- Contenedor → quién alberga (p. ej. `JPanel`).
- Layout → cómo coloca a sus hijos (p. ej. `new FlowLayout(...)`).

---

## 🧩 Layouts esenciales

- FlowLayout
  - Coloca componentes en fila (izquierda → derecha) respetando el orden de añadido.
  - Alineación: `LEFT`, `CENTER` (por defecto), `RIGHT`.
  - Ideal para tiras de botones o cabeceras simples.

- BorderLayout
  - 5 regiones: `NORTH`, `SOUTH`, `EAST`, `WEST`, `CENTER`.
  - `CENTER` ocupa el espacio restante.
  - Ideal para estructura principal de una ventana.

- GridLayout
  - Rejilla F x C. Todas las celdas con el mismo tamaño.
  - Útil para formularios sencillos, teclados o tableros.

- BoxLayout
  - Apila componentes en columna (`Y_AXIS`) o fila (`X_AXIS`).
  - Permite espaciadores: `RigidArea` (fijo) y `Glue` (flexible).
  - Muy práctico para alinear botones a un lado.

---

## ⚡ Ejemplo base (estructura)

```java
JFrame f = new JFrame("UT2 · Contenedores y Layouts");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

JPanel root = new JPanel(new BorderLayout(10, 10));
root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

// Cabecera con FlowLayout (por defecto)
JPanel cabecera = new JPanel();
cabecera.add(new JLabel("Selecciona demo:"));
JButton bFlow = new JButton("FlowLayout");
JButton bBorder = new JButton("BorderLayout");
JButton bGrid = new JButton("GridLayout");
JButton bBox = new JButton("BoxLayout");
cabecera.add(bFlow); cabecera.add(bBorder); cabecera.add(bGrid); cabecera.add(bBox);

// Lienzo central donde cargamos cada demo
JPanel lienzo = new JPanel(new BorderLayout());
lienzo.add(panelFlowDemo(), BorderLayout.CENTER);

// Acciones para conmutar demos
bFlow.addActionListener(e -> swap(lienzo, panelFlowDemo()));
bBorder.addActionListener(e -> swap(lienzo, panelBorderDemo()));
bGrid.addActionListener(e -> swap(lienzo, panelGridDemo()));
bBox.addActionListener(e -> swap(lienzo, panelBoxDemo()));

root.add(cabecera, BorderLayout.NORTH);
root.add(lienzo, BorderLayout.CENTER);

f.setContentPane(root);
f.pack();
f.setLocationRelativeTo(null);
f.setVisible(true);
```

---

## 🔎 Mini-demos (ideas)

- FlowLayout
```java
JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
for (int i = 1; i <= 6; i++) fila.add(new JButton("B" + i));
```

- BorderLayout
```java
JPanel p = new JPanel(new BorderLayout(8, 8));
p.add(new JLabel("NORTH"), BorderLayout.NORTH);
p.add(new JLabel("SOUTH"), BorderLayout.SOUTH);
p.add(new JLabel("EAST"), BorderLayout.EAST);
p.add(new JLabel("WEST"), BorderLayout.WEST);
p.add(new JLabel("CENTER"), BorderLayout.CENTER);
```

- GridLayout
```java
JPanel grid = new JPanel(new GridLayout(2, 3, 8, 8));
for (int i = 1; i <= 6; i++) grid.add(new JButton("C" + i));
```

- BoxLayout
```java
JPanel col = new JPanel();
col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
col.add(new JButton("Arriba"));
col.add(Box.createRigidArea(new Dimension(0,10)));
col.add(new JButton("Centro"));
col.add(Box.createVerticalGlue());
col.add(new JButton("Abajo"));
```

---

## 🧭 Buenas prácticas
- Anida paneles: estructura principal con `BorderLayout` y dentro paneles con otros layouts.
- Usa `EmptyBorder` para dar márgenes internos (respira mejor).
- Llama a `pack()` y `setLocationRelativeTo(null)` al final.

---

## 🧪 Ejercicios guiados (alineados con el .java)
1) Cabecera con FlowLayout
- En `panelBorderDemo()`, sustituye la caja `NORTH` por un panel con `FlowLayout(LEFT)` y añade: `JLabel("Formulario")`, `JButton("Guardar")`, `JButton("Cancelar")`.

2) Formulario con GridLayout
- Crea `JPanel form = new JPanel(new GridLayout(3,2,6,6));`
- Pares `JLabel/JTextField`: Nombre, Email, Teléfono. Colócalo en `CENTER`.

3) Botonera inferior con BoxLayout
- `JPanel botonera = new JPanel(); botonera.setLayout(new BoxLayout(botonera, BoxLayout.X_AXIS));`
- Añade `Box.createHorizontalGlue() + JButton("Aceptar") + espacio + JButton("Salir")`. Ponlo en `SOUTH`.

4) Espaciado y bordes
- Aplica `new EmptyBorder(10,10,10,10)` al formulario y botonera.

5) Extra (opcional)
- Crea un “panel tarjeta” con `BorderLayout` y anida cabecera (Flow), formulario (Grid) y botonera (Box). Usa ese panel como `CENTER` en la demo Border.

---

## 🤖 Prácticas con IA
- Prompt 1 — Formulario 3x2 con GridLayout
  > En un `JPanel` con `BorderLayout` quiero poner en `CENTER` un formulario con `GridLayout 3x2` (labels + textfields) para Nombre, Email y Teléfono. Dame solo el fragmento.

- Prompt 2 — Botonera a la derecha con BoxLayout
  > Necesito una botonera al pie con `BoxLayout.X_AXIS`, con botones Aceptar y Salir pegados a la derecha y con un espacio entre ellos. Dame el panel listo para `BorderLayout.SOUTH`.

- Prompt 3 — Cabecera con FlowLayout y título
  > Crea un panel de cabecera con `FlowLayout.LEFT` que incluya un `JLabel` grande "Formulario" y dos botones "Guardar" y "Cancelar", con un borde inferior sutil.

---

## 🧩 Conclusiones
- Los Layout Managers determinan cómo se reparten y dimensionan los componentes.
- Con cuatro layouts básicos puedes construir casi cualquier diseño inicial.
- La anidación de paneles es la técnica clave para interfaces claras y mantenibles.

---

## 📘 Próximo tema
UT3 — Componentes básicos: texto, selección, combos y listas, con lectura/escritura de valores y eventos elementales.

---

Autor: Clases Online Joaquín  
Versión: 1.0 — 2025  
Licencia: Uso docente y libre adaptación
