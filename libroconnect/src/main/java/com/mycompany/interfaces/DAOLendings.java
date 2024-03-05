package com.mycompany.interfaces;

import com.mycompany.models.Libros;
import com.mycompany.models.Prestamos;
import com.mycompany.models.Users;
import java.util.List;


//ES IGUAL AL DAOBooks pero solo muetra dometodos 
public interface DAOLendings {
    public void registrar(Prestamos lending) throws Exception;
    public void modificar(Prestamos lending) throws Exception;
    public Prestamos getLending(Users user, Libros book) throws Exception;
    // public void eliminar(Lendings user) throws Exception;
    public List<Prestamos> listar() throws Exception;
}
