package com.mycompany.libroconnect;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOBooks;
import com.mycompany.models.Libros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//La clase DAOBooksImpl extiende de Database, lo que significa 
//que hereda la funcionalidad de conexión y cierre de la base de datos de la clase Database.
public class DAOBooksImpl extends Database implements DAOBooks {
    
    /*
    Método registrar(Libros book):

    Inserta un nuevo libro en la base de datos utilizando los datos proporcionados en el objeto Libros pasado como parámetro.
    Prepara una sentencia SQL para la inserción y establece los valores de los parámetros.
    Ejecuta la sentencia y cierra los recursos.
    
    */

    @Override
    public void registrar(Libros book) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO books(title, date, author, category, edit, lang, pages, description, ejemplares, stock, available) VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            st.setString(1, book.getTitle());
            st.setString(2, book.getDate());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategory());
            st.setString(5, book.getEdit());
            st.setString(6, book.getLang());
            st.setString(7, book.getPages());
            st.setString(8, book.getDescription());
            st.setString(9, book.getEjemplares());
            st.setInt(10, book.getStock());
            st.setInt(11, book.getAvailable());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    
    /*
    Método modificar(Libros book):

    Actualiza los datos de un libro en la base de datos basado en el ID del libro proporcionado.
    Prepara una sentencia SQL para la actualización y establece los valores de los parámetros.
    Ejecuta la sentencia y cierra los recursos.
    */
    @Override
    public void modificar(Libros book) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE books SET title = ?, date = ?, author = ?, category = ?, edit = ?, lang = ?, pages = ?, description = ?, ejemplares = ?, stock = ?, available = ? WHERE id = ?");
            st.setString(1, book.getTitle());
            st.setString(2, book.getDate());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategory());
            st.setString(5, book.getEdit());
            st.setString(6, book.getLang());
            st.setString(7, book.getPages());
            st.setString(8, book.getDescription());
            st.setString(9, book.getEjemplares());
            st.setInt(10, book.getStock());
            st.setInt(11, book.getAvailable());
            st.setInt(12, book.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    /*
    Método eliminar(int bookId):

    Elimina un libro de la base de datos basado en el ID del libro proporcionado.
    Prepara una sentencia SQL para la eliminación y establece el valor del parámetro.
    Ejecuta la sentencia y cierra los recursos.
    */
    @Override
    public void eliminar(int bookId) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM books WHERE id = ?;");
            st.setInt(1, bookId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    /*
    Método listar(String title):

    Obtiene una lista de libros de la base de datos, opcionalmente filtrando por título.
    Prepara una consulta SQL dinámica en función del título proporcionado.
    Recupera los resultados, crea objetos Libros y los agrega a una lista.
    */
    @Override
    public List<Libros> listar(String title) throws Exception {
        List<Libros> lista = null;
        try {
            this.Conectar();
            String Query = title.isEmpty() ? "SELECT * FROM books;" : "SELECT * FROM books WHERE title LIKE '%" + title + "%';";
            PreparedStatement st = this.conexion.prepareStatement(Query);
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Libros book = new Libros();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("date"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
                lista.add(book);
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
    
    
    /*
    Método getBookById(int bookId):

    Obtiene un libro específico de la base de datos basado en el ID del libro proporcionado.
    Prepara una consulta SQL para obtener el libro por ID.
    Recupera el resultado, crea un objeto Libros y lo devuelve.
    */
    @Override
    public Libros getBookById(int bookId) throws Exception {
        Libros book = null;
        
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM books WHERE id = ? LIMIT 1;");
            st.setInt(1, bookId);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                book = new Libros();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("date"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return book;
    }   
}