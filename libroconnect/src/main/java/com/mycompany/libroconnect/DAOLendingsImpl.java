package com.mycompany.libroconnect;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOLendings;
import com.mycompany.models.Libros;
import com.mycompany.models.Prestamos;
import com.mycompany.models.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/*
la clase implementa la interfaz DAOLendings, 
por lo que debe proporcionar la implementación de todos los métodos definidos en la interfaz.
*/
public class DAOLendingsImpl extends Database implements DAOLendings {

    /*
    Método registrar(Prestamos lending):

    Inserta un nuevo préstamo en la base de datos utilizando los datos proporcionados en el objeto Prestamos pasado como parámetro.
    Prepara una sentencia SQL para la inserción y establece los valores de los parámetros.
    Ejecuta la sentencia y cierra los recursos.
    */
    @Override
    public void registrar(Prestamos lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO lendings(user_id, book_id, date_out) VALUES(?,?,?);");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    /*
    Método modificar(Prestamos lending):

    Actualiza los datos de un préstamo en la base de datos basado en el ID del préstamo proporcionado en el objeto Prestamos.
    Prepara una sentencia SQL para la actualización y establece los valores de los parámetros.
    Ejecuta la sentencia y cierra los recursos.
    */
    @Override
    public void modificar(Prestamos lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE lendings SET user_id = ?, book_id = ?, date_out = ?, date_return = ? WHERE id = ?");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.setString(4, lending.getDate_return());
            st.setInt(5, lending.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    /*
    Método getLending(Users user, Libros book):

    Obtiene el último préstamo activo de un usuario para un libro específico en la base de datos.
    Prepara una consulta SQL con una condición que filtra por el ID del usuario, el ID del libro y que el campo date_return sea nulo.
    Recupera los resultados, crea un objeto Prestamos y lo devuelve.
    */
    @Override
    public Prestamos getLending(Users user, Libros book) throws Exception {
        Prestamos lending = null;
        
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings WHERE user_id = ? AND book_id = ? AND date_return IS NULL ORDER BY id DESC LIMIT 1");
            st.setInt(1, user.getId());
            st.setInt(2, book.getId());
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lending = new Prestamos();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
            }
            
            st.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        
        return lending;
    }

    /*
    Método listar():

    Obtiene una lista de todos los préstamos registrados en la base de datos.
    Prepara una consulta SQL para seleccionar todos los préstamos ordenados por ID de forma descendente.
    Recupera los resultados, crea objetos Prestamos y los agrega a una lista.
    */
    @Override
    public List<Prestamos> listar() throws Exception {
        List<Prestamos> lista = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings ORDER BY id DESC");
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Prestamos lending = new Prestamos();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
                lista.add(lending);
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

}
