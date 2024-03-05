package com.mycompany.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Declaración de la clase Database:

Se define la clase Database que se encarga de manejar la conexión y cierre de la base de datos.
*/
public class Database {
    
    /*
    Atributos de la clase:

    protected Connection conexion;: Declara un atributo de tipo Connection para representar la conexión a la base de datos.
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";: Define la clase del controlador JDBC para MySQL.
    private final String DB_URL = "jdbc:mysql://localhost:3306/libroconnect";: Establece la URL de conexión a la base de datos MySQL.
    private final String USER = "root"; y private final String PASS = "";: Especifica el usuario 
    y contraseña para la conexión a la base de datos.
    
    */
    
    protected Connection conexion;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/libroconnect";
    
    private final String USER = "root";
    private final String PASS = "";
    
    /*
    Método Conectar():

    Este método se encarga de establecer la conexión con la base de datos.
    Dentro del método, se intenta establecer la conexión utilizando DriverManager.getConnection(DB_URL, USER, PASS) 
    y se carga el controlador JDBC con Class.forName(JDBC_DRIVER).
    En caso de que ocurra una excepción SQLException, se captura y se registra en el log.
    
    */
    
    public void Conectar() throws ClassNotFoundException {
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    Método Cerrar():

    Este método se encarga de cerrar la conexión con la base de datos.
    Verifica si la conexión no es nula y si no está cerrada, entonces cierra la conexión.
    */
    public void Cerrar() throws SQLException{
        if (conexion != null) {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        }
    }
}