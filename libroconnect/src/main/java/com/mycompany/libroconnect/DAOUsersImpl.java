package com.mycompany.libroconnect;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOUsers;
import com.mycompany.models.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOUsersImpl extends Database implements DAOUsers {

    
    /*
    Método registrar(Users user):

    Inserta un nuevo usuario en la base de datos utilizando los datos proporcionados en el objeto Users pasado como parámetro.
    Prepara una sentencia SQL para la inserción y establece los valores de los parámetros.
    Ejecuta la sentencia y cierra los recursos.
    */
    @Override
    public void registrar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO users(name, last_name_p, last_name_m, domicilio, tel) VALUES(?,?,?,?,?);");
            st.setString(1, user.getName());
            st.setString(2, user.getLast_name_p());
            st.setString(3, user.getLast_name_m());
            st.setString(4, user.getDomicilio());
            st.setString(5, user.getTel());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    

    /*
    Método modificar(Users user):

    Actualiza los datos de un usuario en la base de datos basado en el ID del usuario proporcionado en el objeto Users.
    Prepara una sentencia SQL para la actualización y establece los valores de los parámetros.
    Ejecuta la sentencia y cierra los recursos.
    */
    @Override
    public void modificar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE users SET name = ?, last_name_p = ?, last_name_m = ?, domicilio = ?, tel = ? WHERE id = ?");
            st.setString(1, user.getName());
            st.setString(2, user.getLast_name_p());
            st.setString(3, user.getLast_name_m());
            st.setString(4, user.getDomicilio());
            st.setString(5, user.getTel());
            st.setInt(6, user.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    /*
    Método eliminar(int userId):

    Elimina un usuario de la base de datos basado en el ID del usuario proporcionado.
    Prepara una sentencia SQL para la eliminación y establece el valor del parámetro.
    Ejecuta la sentencia y cierra los recursos.
    */
    @Override
    public void eliminar(int userId) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM users WHERE id = ?;");
            st.setInt(1, userId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    /*
    Método listar(String name):

    Obtiene una lista de usuarios de la base de datos, opcionalmente filtrando por nombre.
    Prepara una consulta SQL dinámica en función del nombre proporcionado.
    Recupera los resultados, crea objetos Users y los agrega a una lista.

    */
    @Override
    public List<Users> listar(String name) throws Exception {
        List<Users> lista = null;
        try {
            this.Conectar();
            String Query = name.isEmpty() ? "SELECT * FROM users;" : "SELECT * FROM users WHERE name LIKE '%" + name + "%';";
            PreparedStatement st = this.conexion.prepareStatement(Query);
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name_p(rs.getString("last_name_p"));
                user.setLast_name_m(rs.getString("last_name_m"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
                lista.add(user);
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
    Método getUserById(int userId):

    Obtiene un usuario específico de la base de datos basado en el ID del usuario proporcionado.
    Prepara una consulta SQL con una condición que filtra por el ID del usuario.
    Recupera el resultado, crea un objeto Users y lo devuelve.
    */
    @Override
    public Users getUserById(int userId) throws Exception {
        Users user = null;
        
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM users WHERE id = ? LIMIT 1;");
            st.setInt(1, userId);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name_p(rs.getString("last_name_p"));
                user.setLast_name_m(rs.getString("last_name_m"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return user;
    }
/*
    Método sancionar(Users user):

    Actualiza las sanciones y el monto de sanción de un usuario en la base de datos basado en el ID del usuario proporcionado.
    Prepara una sentencia SQL para la actualización y establece los valores de los parámetros.
    Ejecuta la sentencia y cierra los recursos.
    
    */
    @Override
    public void sancionar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE users SET sanctions = ?, sanc_money = ? WHERE id = ?");
            st.setInt(1, user.getSanctions());
            st.setInt(2, user.getSanc_money());
            st.setInt(3, user.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
}
