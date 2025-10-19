-- =========================================================
-- ANEXO I — Creación de la base de datos "agenda" (MySQL)
-- Autor: Clases Online Joaquín — Año: 2025
-- =========================================================
-- 
-- Este script crea la base de datos y tablas necesarias para el ejemplo
-- de agenda, y carga datos iniciales. Úsalo en MySQL (CLI, MySQL Workbench,
-- phpMyAdmin o desde NetBeans) antes de ejecutar el ejemplo JDBC.
--
-- NOTA: Asegúrate de tener permisos para crear BD/tablas.

-- 1) Crear base de datos (si no existe) y seleccionarla
CREATE DATABASE IF NOT EXISTS agenda CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE agenda;

-- 2) Tabla CONTACTOS
--    Incluye un ID (PK), nombre y ciudad.
DROP TABLE IF EXISTS CONTACTOS;
CREATE TABLE CONTACTOS (
  ID INT PRIMARY KEY,
  NOMBRE VARCHAR(50) NOT NULL,
  CIUDAD VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

-- 3) Insertar contactos de ejemplo
INSERT INTO CONTACTOS VALUES (1, 'PABLO', 'ALMERIA');
INSERT INTO CONTACTOS VALUES (2, 'MARIO', 'GRANADA');
INSERT INTO CONTACTOS VALUES (3, 'LUCIA', 'MADRID');

-- 4) Tabla CORREOS
--    Varios correos por contacto. FOREIGN KEY a CONTACTOS(ID)
DROP TABLE IF EXISTS CORREOS;
CREATE TABLE CORREOS (
  CORREO_ID INT PRIMARY KEY,
  ID_CONTACTO INT NOT NULL,
  CORREO VARCHAR(100) NOT NULL,
  INDEX (ID_CONTACTO),
  FOREIGN KEY (ID_CONTACTO) REFERENCES CONTACTOS(ID)
) ENGINE=InnoDB;

-- 5) Insertar correos de ejemplo
INSERT INTO CORREOS VALUES (1, 1, 'pablo@yahoo.com');
INSERT INTO CORREOS VALUES (2, 1, 'pablo@gmail.com');
INSERT INTO CORREOS VALUES (3, 2, 'mario@yahoo.com');
INSERT INTO CORREOS VALUES (4, 2, 'mario@empresa.com');
INSERT INTO CORREOS VALUES (5, 2, 'mario@jaspersoft.com');
INSERT INTO CORREOS VALUES (6, 3, 'lmc@dominio.es');
INSERT INTO CORREOS VALUES (7, 3, 'lucy@algunemail.com');
INSERT INTO CORREOS VALUES (8, 3, 'luciamartos@organizacion.org');

-- 6) Tabla TELEFONOS
--    Varios teléfonos por contacto. FOREIGN KEY a CONTACTOS(ID)
DROP TABLE IF EXISTS TELEFONOS;
CREATE TABLE TELEFONOS (
  TELEFONO_ID INT PRIMARY KEY,
  ID_CONTACTO INT NOT NULL,
  TELEFONO VARCHAR(10) NOT NULL,
  INDEX (ID_CONTACTO),
  FOREIGN KEY (ID_CONTACTO) REFERENCES CONTACTOS(ID)
) ENGINE=InnoDB;

-- 7) Insertar teléfonos de ejemplo
INSERT INTO TELEFONOS VALUES (1, 1, '111111111');
INSERT INTO TELEFONOS VALUES (2, 1, '222222222');
INSERT INTO TELEFONOS VALUES (3, 1, '333333333');
INSERT INTO TELEFONOS VALUES (4, 2, '444444444');
INSERT INTO TELEFONOS VALUES (5, 3, '555555555');
INSERT INTO TELEFONOS VALUES (6, 3, '666666666');

-- Comprobaciones rápidas (opcionales)
-- SELECT * FROM CONTACTOS;
-- SELECT * FROM CORREOS;
-- SELECT * FROM TELEFONOS;
