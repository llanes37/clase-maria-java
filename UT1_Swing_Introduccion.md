# UT1 - Introducción a Java Swing (2º DAM)
**Curso Completo de Interfaces Gráficas en Java**

---

## 🎯 Objetivos de la Unidad
En esta primera unidad aprenderás los **conceptos esenciales de Swing**, la biblioteca gráfica de Java para crear interfaces de escritorio.

El objetivo principal es comprender cómo se estructura una aplicación con **ventanas (JFrame)**, **paneles (JPanel)** y **componentes básicos** como etiquetas, botones y campos de texto.  
También aprenderás a gestionar un **evento sencillo** (clic de botón) y a ejecutar la aplicación de forma segura en el hilo de interfaz (EDT).

---

## 🧠 ¿Qué es Swing?
**Swing** es la biblioteca gráfica estándar de Java, incluida en el propio **JDK**. Permite crear interfaces gráficas multiplataforma (funcionan igual en Windows, macOS y Linux).  
Forma parte del paquete `javax.swing` y se apoya en **AWT (Abstract Window Toolkit)**, la base del sistema gráfico de Java.

### 🔹 Ventajas principales
- Multiplataforma: funciona igual en cualquier sistema operativo.
- Incluido en el JDK: no requiere instalaciones externas.
- Altamente personalizable (layouts, estilos, componentes avanzados).
- Compatible con MVC (Modelo-Vista-Controlador).

### ⚙️ Relación entre Swing y AWT
AWT proporciona la base gráfica (ventanas, eventos, sistema de dibujo).  
Swing se construye sobre AWT, ofreciendo componentes más ricos (`JButton`, `JLabel`, `JTable`, etc.) y totalmente escritos en Java.

---

## 🧩 Componentes básicos de Swing

| Tipo de elemento | Clase | Función principal |
|------------------|--------|-------------------|
| Ventana | `JFrame` | Contenedor principal de la aplicación. |
| Panel | `JPanel` | Agrupa componentes dentro del `JFrame`. |
| Etiqueta | `JLabel` | Muestra texto o iconos. |
| Botón | `JButton` | Dispara una acción cuando se pulsa. |
| Campo de texto | `JTextField` | Permite al usuario introducir texto. |

---

## 🔄 El ciclo de vida de una aplicación Swing

1️⃣ **main()** — punto de entrada del programa.  
2️⃣ **invokeLater()** — garantiza que la interfaz se construye en el **EDT (Event Dispatch Thread)**.  
3️⃣ **JFrame** — creación de la ventana principal.  
4️⃣ **JPanel** — contenedor de los componentes.  
5️⃣ **Componentes** — etiquetas, botones, campos, etc.  
6️⃣ **Eventos** — se registran con listeners (ej. `ActionListener`).  
7️⃣ **pack()** — ajusta la ventana al contenido.  
8️⃣ **setVisible(true)** — muestra la ventana en pantalla.

---

## ⚡ Ejemplo básico (UT1_Swing_Introduccion.java)

```java
JFrame ventana = new JFrame("UT1 · Introducción a Swing");
ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

JPanel raiz = new JPanel();
JLabel etiqueta = new JLabel("Pulsa el botón para saludar");
JButton boton = new JButton("Saludar");

raiz.add(etiqueta);
raiz.add(boton);

boton.addActionListener(e -> etiqueta.setText("¡Hola, Swing!"));

ventana.setContentPane(raiz);
ventana.pack();
ventana.setLocationRelativeTo(null);
ventana.setVisible(true);
```

💬 Este programa crea una ventana con un botón.  
Al pulsarlo, cambia el texto de la etiqueta. Simple, pero contiene los **principios base de toda aplicación Swing**.

---

## 🧰 Propiedades importantes del JFrame

| Método | Descripción |
|---------|--------------|
| `setTitle("texto")` | Define el título de la ventana. |
| `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)` | Cierra la aplicación al cerrar la ventana. |
| `setContentPane(panel)` | Define el panel raíz que contendrá todos los componentes. |
| `pack()` | Ajusta la ventana al tamaño mínimo necesario. |
| `setLocationRelativeTo(null)` | Centra la ventana en pantalla. |
| `setVisible(true)` | Hace visible la ventana. |

---

## 🧭 Flujo correcto en Swing
Siempre usa:
```java
SwingUtilities.invokeLater(() -> {
    // Crear y mostrar la interfaz aquí
});
```
👉 Esto evita problemas de concurrencia, ya que Swing no es seguro para hilos fuera del EDT.

---

## 🧩 Eventos básicos

### ActionListener
Se usa para escuchar acciones del usuario, como hacer clic en un botón.

```java
boton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        etiqueta.setText("¡Botón pulsado!");
    }
});
```

O con una **lambda** (forma moderna):
```java
boton.addActionListener(e -> etiqueta.setText("¡Botón pulsado!"));
```

---

## 🧪 Ejercicios guiados
1️⃣ Añade un segundo botón “Reiniciar” que devuelva el texto original.  
2️⃣ Cambia el color del texto de la etiqueta (`setForeground(Color.BLUE)`).  
3️⃣ Agrega un campo `JTextField` para introducir el nombre del usuario y saludarle por su nombre.  
4️⃣ Prueba a sustituir `pack()` por `setSize(500,300)` y observa la diferencia.  

---

## 🤖 Prácticas con IA (ChatGPT / Copilot)
Utiliza la IA para **mejorar tu código sin romperlo**:

**Prompt 1 — Botón Reiniciar**
> Tengo un JFrame con un JLabel y un JButton. Quiero añadir un segundo botón “Reiniciar” que devuelva el texto original del JLabel y reactive el botón principal si estaba desactivado. Dame solo el fragmento necesario.

**Prompt 2 — Estilo básico**
> Quiero cambiar el color del texto al pulsar el botón “Saludar” y devolverlo al original al pulsar “Reiniciar”. Indícame dónde colocar cada línea de código.

**Prompt 3 — Campo de nombre**
> Quiero añadir un JTextField para introducir el nombre y que el saludo diga “¡Hola, [nombre]!” si no está vacío. Dame el ActionListener del botón con esa lógica.

---

## 🧩 Conclusiones
- Swing permite crear interfaces gráficas sencillas **con el JDK estándar**.  
- El uso correcto del **EDT** (Event Dispatch Thread) es clave para evitar errores.  
- Todo parte de un `JFrame` que contiene `JPanel` y componentes dentro.  
- En esta UT1 se sientan las bases para manejar eventos, contenedores y layouts que llegarán en **UT2 y UT3**.

---

## 📘 Próximo tema
**UT2 - Contenedores y Layouts**  
Aprenderás a organizar los componentes en pantalla con `FlowLayout`, `BorderLayout`, `GridLayout` y `BoxLayout`.

---

**Autor:** Clases Online Joaquín  
**Versión:** 1.0 — 2025  
**Licencia:** Uso docente y libre adaptación  
