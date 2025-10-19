# Anexo I — Guía NetBeans: MySQL + JDBC (Agenda)

Guía práctica y súper explicada para preparar la base de datos "agenda" en MySQL y ejecutar el ejemplo JDBC del repositorio en NetBeans. Pensada para que el alumnado siga los pasos sin pérdidas y pueda realizar los ejercicios propuestos.

---

## 0) Qué vas a montar
- Una base de datos `agenda` con tres tablas: `CONTACTOS`, `CORREOS`, `TELEFONOS`.
- Un proyecto Java que se conecta por JDBC y lista los contactos.
- Ejercicios para extender el ejemplo (consultas por contacto, inserciones, integración con Swing).

Archivos clave del repo:
- `ANEXO1_MySQL_Agenda.sql` → crea la BD/tablas y carga datos de ejemplo.
- `ANEXO1_MySQL_Agenda.java` → ejemplo JDBC mínimo con regiones y TODOs.

Requisitos:
- NetBeans reciente (Apache NetBeans 12+ recomendado).
- MySQL instalado y en marcha (localhost:3306 por defecto).
- Driver JDBC de MySQL: `mysql-connector-j` (se añade al proyecto como JAR).

---

## 1) Importar el repositorio en NetBeans
1. Abre NetBeans.
2. Menú Archivo → Abrir proyecto…
3. Selecciona la carpeta del repositorio (donde ves los `.java` y `.sql`).
4. Acepta. Deberías ver los archivos en el panel de Proyectos.

Nota: Si no hay proyecto NetBeans preconfigurado, puedes crear un "Java with Ant/Gradle/Maven" vacío y añadir estos `.java` al `src` (o mantenerlos como archivos sueltos y usar "Run File").

---

## 2) Registrar MySQL en NetBeans (Servicios → Bases de datos)
1. Abre la pestaña "Servicios" (Window → Services si no la ves).
2. Expande "Bases de datos".
3. Clic derecho → "Registrar servidor MySQL" (o "New Connection" si ya está el servidor):
   - Host: `localhost`
   - Puerto: `3306`
   - Usuario admin: `root` (o el que uses)
   - Contraseña: tu contraseña de MySQL
4. Finaliza el asistente. En "Bases de datos" verás el servidor MySQL.
5. Clic derecho sobre el servidor → "Iniciar" si aparece detenido. Luego "Conectar".

Tip: Si ya tienes MySQL funcionando y solo quieres una conexión a `agenda`, puedes ir directo a "New Connection" y usar la URL `jdbc:mysql://localhost:3306/agenda`.

---

## 3) Crear la base de datos e importar el script SQL
Opción A) Con el SQL Editor de NetBeans
1. En Servicios → Bases de datos → tu conexión MySQL (root@localhost:3306) → Conectar.
2. Clic derecho → "Execute Command" para abrir el editor SQL.
3. Menú Archivo → Abrir… y elige `ANEXO1_MySQL_Agenda.sql` del repo.
4. Ejecuta el script completo (icono del rayo o F6). Debes ver mensajes de "Query executed" sin errores.

Opción B) Con MySQL Workbench o phpMyAdmin
- Abre la herramienta, conéctate a MySQL y ejecuta el contenido de `ANEXO1_MySQL_Agenda.sql`.

Verificación rápida (en cualquier herramienta):
- `SELECT * FROM CONTACTOS;`
- `SELECT * FROM CORREOS;`
- `SELECT * FROM TELEFONOS;`

---

## 4) Añadir el driver JDBC al proyecto de NetBeans
1. Clic derecho sobre tu proyecto → Propiedades → Libraries (o Classpath).
2. "Add JAR/Folder" → selecciona el JAR `mysql-connector-j-x.x.x.jar`.
   - Si no lo tienes, descárgalo de la web de MySQL (MySQL Connector/J) o de tu gestor de dependencias.
3. Aplica cambios. El proyecto ya sabe hablar con MySQL por JDBC.

Nota técnica: Con JDBC 4+ el driver se registra automáticamente al estar en el classpath. No hace falta `Class.forName(...)` en la mayoría de casos.

---

## 5) Configurar credenciales en el ejemplo JDBC
Abre `ANEXO1_MySQL_Agenda.java` y ajusta estos campos si hace falta:
- URL: `jdbc:mysql://localhost:3306/agenda?useSSL=false&serverTimezone=UTC`
- USER: `root` (o tu usuario)
- PASS: `""` (tu contraseña)

Guarda el archivo.

---

## 6) Ejecutar `ANEXO1_MySQL_Agenda.java`
- En el panel de Proyectos, clic derecho sobre `ANEXO1_MySQL_Agenda.java` → "Run File".
- En la consola de NetBeans deberías ver:
  - "Conexión OK a MySQL → agenda"
  - Un listado de contactos con sus ciudades.

Si sale error, revisa la sección de resolución de problemas.

---

## 7) Completar los ejercicios propuestos (región 4 del .java)
Sigue los TODO del archivo `ANEXO1_MySQL_Agenda.java`.

- TODO 1 — Correos por contacto (PreparedStatement)
  - SQL: `SELECT CORREO FROM CORREOS WHERE ID_CONTACTO = ? ORDER BY CORREO_ID`
  - Esqueleto orientativo:
    ```java
    String sql = "SELECT CORREO FROM CORREOS WHERE ID_CONTACTO=? ORDER BY CORREO_ID";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idContacto);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) System.out.println(rs.getString("CORREO"));
        }
    }
    ```

- TODO 2 — Teléfonos por contacto (PreparedStatement)
  - SQL: `SELECT TELEFONO FROM TELEFONOS WHERE ID_CONTACTO = ? ORDER BY TELEFONO_ID`

- TODO 3 — Insertar contacto con control de error
  - SQL: `INSERT INTO CONTACTOS(ID, NOMBRE, CIUDAD) VALUES(?, ?, ?)`
  - Captura duplicados (código de error 1062 en MySQL) y muestra mensaje claro al usuario.

- TODO 4 — Integración con Swing (opcional UT3/UT4)
  - Crea un `JFrame` con `JComboBox` de contactos y un `JTextArea` para mostrar correos/teléfonos.
  - Usa listeners (ActionListener/ItemListener) para recargar los datos al cambiar el contacto.

---

## 8) Resolución de problemas (FAQ)
- "Communications link failure" o "Connection refused"
  - MySQL no está arrancado o el puerto/host no es correcto. Inicia MySQL y verifica `localhost:3306`.

- "Access denied for user"
  - Usuario/contraseña incorrectos o el usuario no tiene permisos sobre `agenda`.

- "Unknown database 'agenda'"
  - No has ejecutado `ANEXO1_MySQL_Agenda.sql`. Vuelve al paso 3 y ejecútalo.

- Advertencias de zona horaria
  - Añade `serverTimezone=UTC` a la URL (ya viene por defecto en el ejemplo).

- "No suitable driver found" o ClassNotFound
  - Falta el JAR `mysql-connector-j` en Libraries del proyecto. Vuelve al paso 4.

---

## 9) Uso del panel Servicios → Bases de datos (NetBeans)
- Crea conexiones a `agenda` para explorar tablas y datos.
- Clic derecho en la conexión → "Connect" → "Execute Command" para abrir consultas.
- Puedes ejecutar sentencias SQL, crear índices, ver datos y copiar resultados.

---

## 10) Apéndice: esquema de tablas
- CONTACTOS(ID, NOMBRE, CIUDAD)
- CORREOS(CORREO_ID, ID_CONTACTO → CONTACTOS.ID, CORREO)
- TELEFONOS(TELEFONO_ID, ID_CONTACTO → CONTACTOS.ID, TELEFONO)

Integridad referencial: `CORREOS.ID_CONTACTO` y `TELEFONOS.ID_CONTACTO` referencian `CONTACTOS.ID`.

---

## 11) Siguientes pasos (sugerencias docente)
- Validación y diálogos (UT6): confirmar inserciones con `JOptionPane`.
- Persistencia ligera (UT12): guardar última conexión/usuario con `Preferences`.
- Integración con UT3/UT4: mini-CRUD visual (alta/baja/cambio de contacto) y manejo de eventos.

---

Hecho por: Clases Online Joaquín — 2025

---

## 12) URL JDBC recomendada para MySQL 8
Para evitar avisos de autenticación/SSL/zona horaria/UTF‑8, usa una URL como:

```
jdbc:mysql://localhost:3306/agenda?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8
```

- allowPublicKeyRetrieval=true: evita errores de clave pública en MySQL 8 sin SSL.
- serverTimezone=UTC: corta warnings de zona horaria.
- useUnicode/characterEncoding: asegura UTF‑8 real.

---

## 13) Descarga rápida del driver JDBC (mysql‑connector‑j)

Enlaces oficiales:
- Sitio MySQL: https://dev.mysql.com/downloads/connector/j/
- Maven Central: https://search.maven.org/artifact/mysql/mysql-connector-java

Ejemplo descarga directa (8.0.33) y guardarlo en `lib/` con PowerShell:

```powershell
New-Item -ItemType Directory -Path .\lib -Force
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar" -OutFile ".\lib\mysql-connector-java-8.0.33.jar"
```

Añadirlo al proyecto en NetBeans:
- Proyecto → botón derecho → Properties → Libraries → Add JAR/Folder → selecciona `lib/mysql-connector-java-8.0.33.jar` → OK.

Alternativas con gestor de dependencias:
- Maven (pom.xml):
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.33</version>
  <scope>runtime</scope>
  </dependency>
```

- Gradle (build.gradle):
```groovy
dependencies {
  runtimeOnly 'mysql:mysql-connector-java:8.0.33'
}
```

---

## 14) Ajustes útiles en NetBeans
- Main Class: Proyecto → Properties → Run → Main Class → `ANEXO1_MySQL_Agenda` (si vas a usar Run Project).
- Run File vs Run Project: “Run File” ejecuta el `.java` actual; “Run Project” usa la Main Class configurada.
- Encoding UTF‑8: Proyecto → Properties → Sources → Encoding = UTF‑8.
- Servicios → Drivers: si no aparece “MySQL (Connector/J)”, añade el JAR aquí y usa “Test Connection” en tu conexión a `agenda`.

---

## 15) Extras SQL para prácticas (opcional)
Si quieres facilitar inserciones/borrados en clase:

- AUTO_INCREMENT en claves primarias:
```sql
ALTER TABLE CONTACTOS  MODIFY ID INT PRIMARY KEY AUTO_INCREMENT;
ALTER TABLE CORREOS    MODIFY CORREO_ID INT PRIMARY KEY AUTO_INCREMENT;
ALTER TABLE TELEFONOS  MODIFY TELEFONO_ID INT PRIMARY KEY AUTO_INCREMENT;
```

- Borrado en cascada (revisa nombres reales de las FK con `SHOW CREATE TABLE`):
```sql
ALTER TABLE CORREOS
  DROP FOREIGN KEY CORREOS_ibfk_1,
  ADD CONSTRAINT CORREOS_FK
    FOREIGN KEY (ID_CONTACTO) REFERENCES CONTACTOS(ID)
    ON DELETE CASCADE;

ALTER TABLE TELEFONOS
  DROP FOREIGN KEY TELEFONOS_ibfk_1,
  ADD CONSTRAINT TELEFONOS_FK
    FOREIGN KEY (ID_CONTACTO) REFERENCES CONTACTOS(ID)
    ON DELETE CASCADE;
```

---

## 16) Opción rápida con Docker (opcional)
Si no tienes MySQL instalado localmente:

```powershell
docker run -d --name mysql8 -e MYSQL_ROOT_PASSWORD=pass -p 3306:3306 mysql:8
```

Conéctate con USER=`root`, PASS=`pass`, ejecuta el script SQL y sigue la guía.

---
