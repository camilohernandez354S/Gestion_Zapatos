# Proyecto de Gestión de Zapatillas

Este proyecto es una aplicación de gestión de zapatillas en Java, que permite a los usuarios registrar, ver, actualizar y eliminar información de zapatillas en una base de datos MySQL. La aplicación incluye una interfaz gráfica con funcionalidades de gestión de datos mediante un dashboard moderno.

---

## **Instalación**

### **Requisitos previos:**
- **Java 8 o superior** instalado en tu máquina.
- **MySQL** instalado y configurado en tu entorno local.
- **IDE** recomendado: **IntelliJ IDEA** o **Eclipse**.
  
### **Pasos de instalación:**

1. **Clonar el repositorio:**

    ```bash
    git clone https://github.com/tu_usuario/proyecto_zapatillas.git
    ```

2. **Configurar la base de datos:**

    - Crear una base de datos llamada **`zapatillas`** en MySQL.
    - Ejecutar el siguiente script SQL para crear las tablas necesarias:

    ```sql
    -- Esquema de la base de datos para el proyecto

    -- Crear tabla parametros
    CREATE TABLE `parametros` (
        `id` int NOT NULL AUTO_INCREMENT,
        `nombre` varchar(100) DEFAULT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `nombre` (`nombre`)
    );

    -- Crear tabla tema
    CREATE TABLE `tema` (
        `id` int NOT NULL AUTO_INCREMENT,
        `nombre` varchar(50) DEFAULT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `nombre` (`nombre`)
    );

    -- Crear tabla tema_parametros
    CREATE TABLE `tema_parametros` (
        `id` int NOT NULL AUTO_INCREMENT,
        `id_tema` int DEFAULT NULL,
        `id_parametro` int DEFAULT NULL,
        PRIMARY KEY (`id`),
        KEY `id_tema` (`id_tema`),
        KEY `id_parametro` (`id_parametro`),
        CONSTRAINT `tema_parametros_ibfk_1` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id`),
        CONSTRAINT `tema_parametros_ibfk_2` FOREIGN KEY (`id_parametro`) REFERENCES `parametros` (`id`)
    );

    -- Crear tabla zapatillas
    CREATE TABLE `zapatillas` (
        `id` int NOT NULL AUTO_INCREMENT,
        `id_color` int DEFAULT NULL,
        `id_talla` int DEFAULT NULL,
        `id_genero` int DEFAULT NULL,
        `id_tipo` int DEFAULT NULL,
        `id_marca` int DEFAULT NULL,
        `foto` varchar(250) DEFAULT NULL,
        PRIMARY KEY (`id`),
        KEY `id_color` (`id_color`),
        KEY `id_talla` (`id_talla`),
        KEY `id_genero` (`id_genero`),
        KEY `id_tipo` (`id_tipo`),
        KEY `id_marca` (`id_marca`),
        CONSTRAINT `zapatillas_ibfk_1` FOREIGN KEY (`id_color`) REFERENCES `tema_parametros` (`id`),
        CONSTRAINT `zapatillas_ibfk_2` FOREIGN KEY (`id_talla`) REFERENCES `tema_parametros` (`id`),
        CONSTRAINT `zapatillas_ibfk_3` FOREIGN KEY (`id_genero`) REFERENCES `tema_parametros` (`id`),
        CONSTRAINT `zapatillas_ibfk_4` FOREIGN KEY (`id_tipo`) REFERENCES `tema_parametros` (`id`),
        CONSTRAINT `zapatillas_ibfk_5` FOREIGN KEY (`id_marca`) REFERENCES `tema_parametros` (`id`)
    );
    ```

3. **Configuración del proyecto en tu IDE:**

    - Abre tu IDE (por ejemplo, **IntelliJ IDEA** o **Eclipse**).
    - Importa el proyecto clonado.
    - Asegúrate de que el archivo **`ConexionDB.java`** tenga configurada correctamente la conexión a la base de datos (usuario, contraseña, URL).

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/zapatillas";
    private static final String USUARIO = "root";  // Reemplaza con tu usuario de MySQL
    private static final String CONTRASENA = "";  // Reemplaza con tu contraseña de MySQL
    ```

---

## **Uso**

1. **Ejecuta la aplicación** desde tu IDE.
2. La aplicación se abrirá con una interfaz gráfica donde podrás interactuar con los siguientes botones:
    - **Registrar Zapatilla**: Permite ingresar nueva información de zapatillas.
    - **Ver Zapatillas**: Muestra la lista de todas las zapatillas registradas.
    - **Eliminar Zapatilla**: Elimina una zapatilla seleccionada de la lista.
    - **Editar Zapatilla**: Permite editar la información de una zapatilla registrada.
   
---

## **Esquema de la Base de Datos**

El esquema de la base de datos para este proyecto se compone de 4 tablas principales:

1. **`parametros`**: Almacena los diferentes parámetros utilizados en las zapatillas, como talla, color, marca, etc.
2. **`tema`**: Define los temas de los parámetros (ej. Tallas, Colores, Marcas).
3. **`tema_parametros`**: Relaciona los parámetros con los temas. Esta tabla es una tabla intermedia.
4. **`zapatillas`**: Almacena la información de las zapatillas, incluyendo referencias a los parámetros de talla, color, marca, tipo y género.

El diagrama de la base de datos es el siguiente:

