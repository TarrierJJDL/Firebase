package com.example.user.firebase1.model;

/**
 * Created by user on 06/01/2019.
 */

public class Productos {

    private String nombre;
    private String descripcion;
    private String catageoria;
    private String precio;
    private String nomuser;


    public Productos(){}
    public Productos(String nombre, String descripcion, String catageoria, String precio, String nomuser){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.catageoria=catageoria;
        this.precio=precio;
        this.nomuser=nomuser;


    }

    @Override
    public String toString() {
        return "Productos{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", catageoria='" + catageoria + '\'' +
                ", precio='" + precio + '\'' +
                ", nomuser='" + nomuser + '\'' +
                '}';
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCatageoria() {
        return catageoria;
    }

    public void setCatageoria(String catageoria) {
        this.catageoria = catageoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


}
