/**
 * =========================================================
 * ANEXO I — MySQL: Creación BD "agenda" y conexión JDBC desde Java
 * =========================================================
 *
 * Objetivo
 * - Preparar una base de datos MySQL (script SQL aparte) y mostrar un ejemplo
 *   mínimo de conexión JDBC desde Java para consultar contactos.
 *
 * Requisitos previos
 * - Tener un servidor MySQL en marcha (localhost:3306 por defecto).
 * - Ejecutar previamente el script: ANEXO1_MySQL_Agenda.sql (crea BD y datos).
 * - Tener el driver JDBC de MySQL disponible (mysql-connector-j). En NetBeans:
 *   1) Botón derecho sobre el proyecto → Propiedades → Libraries/Classpath.
 *   2) Añadir JAR/Folder → seleccionar mysql-connector-j-x.x.x.jar.
 *
 * Notas para NetBeans
 * - Puedes registrar el servidor y crear la conexión en la pestaña "Servicios" → Bases de datos.
 * - Usuario típico: root | Puerto: 3306 | Host: localhost | Base: agenda | Charset: UTF-8.
 *
 * Aviso
 * - Este archivo es didáctico. Manejo de errores simplificado y credenciales en claro.
 *   Para proyectos reales, usa configuración externa y nunca subas contraseñas.
 */

import java.sql.*; // JDBC

public class ANEXO1_MySQL_Agenda {

    // ========================================================
    // REGION 1) CONFIGURACIÓN (ADAPTA A TU ENTORNO)
    // ========================================================
    // TODO: Ajusta usuario y contraseña a tu instalación de MySQL.
    private static final String URL = "jdbc:mysql://localhost:3306/agenda?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";      // ← cambia si no es root
    private static final String PASS = "";          // ← pon tu contraseña

    // ========================================================
    // REGION 2) PUNTO DE ENTRADA
    // ========================================================
    public static void main(String[] args) {
        // Objetivo: conectar y listar CONTACTOS por consola.
        // En caso de error, mostrar mensaje comprensible para el alumno.
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Conexión OK a MySQL → agenda");
            listarContactos(con);
        } catch (SQLException ex) {
            System.err.println("No se pudo conectar o consultar. Revisa:");
            System.err.println("- ¿Está MySQL levantado? (localhost:3306)");
            System.err.println("- ¿Importaste ANEXO1_MySQL_Agenda.sql?");
            System.err.println("- ¿Usuario/contraseña correctos? (USER/PASS)");
            ex.printStackTrace();
        }
    }

    // ========================================================
    // REGION 3) CONSULTA BÁSICA: CONTACTOS
    // ========================================================
    private static void listarContactos(Connection con) throws SQLException {
        // Ejemplo mínimo de JDBC: Statement + ResultSet (solo lectura)
        String sql = "SELECT ID, NOMBRE, CIUDAD FROM CONTACTOS ORDER BY ID";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("ID | NOMBRE | CIUDAD");
            System.out.println("---------------------");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                String ciudad = rs.getString("CIUDAD");
                System.out.printf("%d | %s | %s%n", id, nombre, ciudad);
            }
        }
    }

    // ========================================================
    // REGION 4) EJERCICIOS PROPUESTOS (para practicar)
    // ========================================================
    // TODO 1) Crear método listarCorreosPorContacto(Connection con, int idContacto)
    //   - SELECT CORREO FROM CORREOS WHERE ID_CONTACTO = ? ORDER BY CORREO_ID
    //   - Usa PreparedStatement, establece el parámetro y muestra resultados.
    //
    // TODO 2) Crear método listarTelefonosPorContacto(Connection con, int idContacto)
    //   - SELECT TELEFONO FROM TELEFONOS WHERE ID_CONTACTO = ? ORDER BY TELEFONO_ID
    //   - También con PreparedStatement.
    //
    // TODO 3) Añadir inserción segura de un nuevo contacto
    //   - Método insertarContacto(Connection con, int id, String nombre, String ciudad)
    //   - Maneja duplicados de PK con try-catch y mensajes claros.
    //
    // TODO 4) Integración con Swing (opcional, si estás en UT3/UT4)
    //   - Crea una pequeña ventana con un JComboBox de contactos y un JTextArea
    //     que muestre correos y teléfonos del contacto seleccionado.
}
