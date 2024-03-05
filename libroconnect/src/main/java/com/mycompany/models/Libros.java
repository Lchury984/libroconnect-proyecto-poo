package com.mycompany.models;

public class Libros {
    private int id;
    private String title;
    private String date;
    private String author;
    private String category;
    private String edit;
    private String lang;
    private String pages;
    private String description;
    private String ejemplares;
    private int stock;
    private int available;
    
    
    /*
    Métodos setters y getters:

    La clase Libros define métodos setters para establecer los valores de los atributos y getters para obtener los valores de los atributos.
    Los métodos setters (setXXX) permiten asignar valores a los atributos de la clase.
    Los métodos getters (getXXX) permiten obtener los valores de los atributos de la clase.
    */

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEjemplares(String ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getEdit() {
        return edit;
    }

    public String getLang() {
        return lang;
    }

    public String getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }

    public String getEjemplares() {
        return ejemplares;
    }

    public int getStock() {
        return stock;
    }

    public int getAvailable() {
        return available;
    }
}
