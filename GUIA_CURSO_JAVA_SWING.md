# Curso Completo de Java Swing (2º DAM)
**Guía de introducción y plan docente**

> **Propósito**  
> Este documento es la guía oficial del curso. Explica **qué aprenderás** en cada unidad (UT), **cómo** trabajaremos (IDE, flujo de clase, evaluación) y **dónde** están los recursos (.java, ejercicios guiados y prácticas con IA). Está diseñado para usarse **dentro del IDE** (VS Code/NetBeans) y en tu **plataforma**.

---

## 0) Información general

**Requisitos previos**
- Java JDK 11+ (recomendado LTS).
- IDE: VS Code (con Extension Pack for Java) o Apache NetBeans.
- Conocimientos básicos de Java (variables, métodos, clases, objetos).

**Metodología**
- Cada UT incluye:  
  1) **Teoría breve** (en el propio `.java` y/o README).  
  2) **Demo mínima** (código que compila y muestra el concepto).  
  3) **Ejercicio guiado** con *Better Comments* (`// !`, `// *`, `// TODO`, `// NOTE`).  
  4) **Prácticas con IA** (prompts cortos para ChatGPT / Copilot).  
- Clase tipo: 5’ teoría → 15’ live-coding → 15’ práctica guiada → 10’ reto/IA → 5’ cierre.

**Estructura de carpetas (sugerida)**
```
CursoJavaSwing/
├─ UT1_Swing_Introduccion.java
├─ UT2_ContenedoresYLayouts.java
├─ UT3_ComponentesBasicos.java
├─ UT4_ModeloEventos.java
├─ UT5_MenusToolbarsEstado.java
├─ UT6_DialogosYValidacion.java
├─ UT7_ListasTablasArboles.java
├─ UT8_MVCyOrganizacion.java
├─ UT9_Dibujo2D_PaintComponent.java
├─ UT10_Concurrencia_SwingWorker.java
├─ UT11_LookAndFeel_i18n.java
└─ UT12_PersistenciaLigera_Empaquetado.java
```

**Leyenda Better Comments (en código)**
- `// !` Importante / alerta
- `// *` Idea clave / objetivo
- `// NOTE` Nota docente / contexto
- `// TODO` Tarea del alumno
- `// ?` Pregunta/experimento

---

## 1) Temario por Unidades (UT)

### UT1 — Introducción muy básica a Swing
- **Objetivo:** Entender EDT, crear `JFrame`, `JPanel`, `JLabel`, `JButton` y un único `ActionListener`.
- **Aprenderás:** ciclo de vida de una ventana, `pack()`, `setVisible`, `setDefaultCloseOperation`, FlowLayout por defecto.
- **Entrega:** `UT1_Swing_Introduccion.java` (teoría arriba + demo + ejercicios guiados + 3 prompts IA).
- **Resultado esperado:** app mínima que saluda y que el alumno puede extender con un botón *Reiniciar* y un `JTextField`.

### UT2 — Contenedores y Layouts esenciales
- **Objetivo:** Colocar componentes correctamente.
- **Contenidos:** `FlowLayout`, `BorderLayout`, `GridLayout`, `BoxLayout`; anidación de paneles, `setBorder()`.
- **Práctica:** maqueta de formulario simple con 2–3 layouts combinados; refactor del código de UT1 para separar panel raíz.

### UT3 — Componentes básicos (nivel 1)
- **Objetivo:** Conocer y usar los widgets más comunes.
- **Contenidos:** `JLabel`, `JTextField`, `JTextArea`, `JPasswordField`, `JButton`, `JCheckBox`, `JRadioButton`, `JComboBox`, `JList`.
- **Práctica:** pequeño formulario con validación y visualización de selección.

### UT4 — Modelo de eventos
- **Objetivo:** Comprender cómo Swing maneja la interacción.
- **Contenidos:** `ActionListener`, `ItemListener`, `KeyListener`, `MouseListener`, `ChangeListener`.
- **Práctica:** crear un panel interactivo con varios tipos de eventos.

### UT5 — Menús, Toolbars y barras de estado
- **Objetivo:** Añadir navegación y acciones de interfaz.
- **Contenidos:** `JMenuBar`, `JMenu`, `JMenuItem`, aceleradores, `JToolBar`, barra de estado.
- **Práctica:** añadir barra de menú a la app de UT3 con acciones básicas.

### UT6 — Diálogos y validación
- **Objetivo:** Mostrar información o pedir datos mediante diálogos.
- **Contenidos:** `JOptionPane`, `JFileChooser`, `JColorChooser`.
- **Práctica:** ampliar UT3 para guardar/cargar datos o seleccionar color.

### UT7 — Listas, Tablas y Árboles
- **Objetivo:** Mostrar colecciones de datos visualmente.
- **Contenidos:** `JList`, `JTable`, `JTree`, modelos (`ListModel`, `TableModel`), renderers básicos.
- **Práctica:** visualizador simple de registros o estructura jerárquica.

### UT8 — MVC y organización del código
- **Objetivo:** Separar lógica, vista y control.
- **Contenidos:** patrón MVC básico aplicado a Swing.
- **Práctica:** refactorizar UT5 o UT6 para aislar la lógica de la vista.

### UT9 — Dibujo 2D y eventos gráficos
- **Objetivo:** Dibujar formas y responder al ratón.
- **Contenidos:** `paintComponent(Graphics g)`, `Graphics2D`, colores, coordenadas, `MouseListener`.
- **Práctica:** mini-juego o pizarra simple con dibujo libre.

### UT10 — Concurrencia y tareas en segundo plano
- **Objetivo:** No bloquear el hilo de interfaz.
- **Contenidos:** `SwingWorker`, barras de progreso, cancelación, feedback visual.
- **Práctica:** simulador de descarga o tarea larga con progreso.

### UT11 — Look & Feel e internacionalización
- **Objetivo:** Personalizar el aspecto y traducir textos.
- **Contenidos:** `UIManager`, temas, propiedades, `ResourceBundle`.
- **Práctica:** app bilingüe con selector de idioma y cambio de tema.

### UT12 — Persistencia ligera y empaquetado
- **Objetivo:** Guardar configuraciones y crear ejecutables.
- **Contenidos:** `Preferences`, lectura/escritura de archivos, JSON simple, creación de `.jar` ejecutable.
- **Práctica:** guardar preferencias de usuario o datos de sesión.

---

## 2) Cómo usar este curso
1. Abre cada UT en tu IDE. Lee primero la teoría en los comentarios del inicio.  
2. Ejecuta el código base y observa el resultado.  
3. Resuelve los `// TODO` progresivamente.  
4. Usa los prompts de IA al final del archivo para extender el proyecto o mejorar el código.  
5. Guarda tus avances y añade comentarios personales (tu explicación).  
6. En cada UT, entiende primero el *por qué*, luego el *cómo*.  

---

## 3) Continuidad y ampliación
Este curso sienta las bases para:
- Desarrollo de herramientas de escritorio (CRUD, utilidades, mini-juegos).  
- Prácticas en **patrones de diseño (MVC, Observer, Singleton)**.  
- Transición natural a **JavaFX** o **frameworks modernos (Compose for Desktop)**.  
- Uso combinado de **IA educativa** para aprender programación visual guiada.  

---

> **Autor:** Clases Online Joaquín  
> **Versión:** 1.0 (2025)  
> **Licencia:** Uso docente libre y adaptativo  
