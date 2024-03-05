package com.mycompany.interfaces;

import com.mycompany.models.Libros;
import java.util.List;

/*Define la interfaz DAOBooks, que actúa como un contrato que especifica 
los métodos que deben ser implementados por las clases 
que actúan como DAO para la entidad de libros.

*/
public interface DAOBooks {
    public void registrar(Libros book) throws Exception; //Define un método para registrar un libro en la base de datos. 
    public void modificar(Libros book) throws Exception; //Define un método para modificar un libro en la base de datos. 
    public void eliminar(int bookId) throws Exception; //Define un método para eliminar un libro en la base de datos. 
    public List<Libros> listar(String title) throws Exception;//Define un método para listar un libro en la base de datos.
                                                              // Toma el título como parámetro y devuelve una lista de objetos
    public Libros getBookById(int bookId) throws Exception;//Define un método para obtener un libro basado en su ID. 
                                                           // Toma el ID del libro como parámetro y puede lanzar una excepción.
}
