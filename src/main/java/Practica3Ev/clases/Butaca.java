package Practica3Ev.clases;

import java.io.Serializable;
import java.util.Scanner;

public class Butaca implements Serializable {
    private String posicion;
    private int id;
    private boolean accesibilidad, disponible;

    public Butaca() {
    }

    public Butaca(String posicion, int id, boolean accesibilidad, boolean disponible) {
        this.posicion = posicion;
        this.id = id;
        this.accesibilidad = accesibilidad;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public boolean isAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Butaca{" +
                "id='" + id + '\'' +
                ", accesibilidad=" + accesibilidad +
                ", disponible=" + disponible +
                '}';
    }
}
