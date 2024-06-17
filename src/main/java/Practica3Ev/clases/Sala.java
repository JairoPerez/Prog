package Practica3Ev.clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable {
    String nombre;
    int capacidad_maxima;
    ArrayList<Butaca> lista_butacas;
    double dimension;
    boolean accesibilidad;
    String caracteristicas_acusticas;
    String observaciones;

    public Sala(String nombre, int capacidad_maxima, ArrayList<Butaca> lista_butacas, double dimension) {
        this.nombre = nombre;
        this.capacidad_maxima = capacidad_maxima;
        this.lista_butacas = lista_butacas;
        this.dimension = dimension;
    }

    public Sala(String nombre, int capacidad_maxima, ArrayList<Butaca> lista_butacas, double dimension, boolean accesibilidad, String caracteristicas_acusticas, String observaciones) {
        this.nombre = nombre;
        this.capacidad_maxima = capacidad_maxima;
        this.lista_butacas = lista_butacas;
        this.dimension = dimension;
        this.accesibilidad = accesibilidad;
        this.caracteristicas_acusticas = caracteristicas_acusticas;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public ArrayList<Butaca> getLista_butacas() {
        return lista_butacas;
    }

    public void setLista_butacas(ArrayList<Butaca> lista_butacas) {
        this.lista_butacas = lista_butacas;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public boolean isAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public String getCaracteristicas_acusticas() {
        return caracteristicas_acusticas;
    }

    public void setCaracteristicas_acusticas(String caracteristicas_acusticas) {
        this.caracteristicas_acusticas = caracteristicas_acusticas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int total_butacas() {
        return lista_butacas.size();
    }
}
