package Practica3Ev.clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Evento implements Serializable {
    String nombre;
    String invitado;
    String fecha_formateada;
    Sala sala;
    LocalDate fecha;
    LocalTime hora;
    double precio;
    String tipo_evento;
    int asistentes_maximos;
    ArrayList<Usuario> lista_asistentes;

    public Evento(){
    }

    public Evento(String nombre, String invitado, Sala sala, LocalDate fecha, LocalTime hora, double precio, String tipo_evento, int asistentes_maximos, ArrayList<Usuario> lista_asistentes) {
        this.nombre = nombre;
        this.invitado = invitado;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.tipo_evento = tipo_evento;
        this.asistentes_maximos = asistentes_maximos;
        this.lista_asistentes = lista_asistentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInvitado() {
        return invitado;
    }

    public void setInvitado(String invitado) {
        this.invitado = invitado;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public int getAsistentes_maximos() {
        return asistentes_maximos;
    }

    public void setAsistentes_maximos(int asistentes_maximos) {
        this.asistentes_maximos = asistentes_maximos;
    }

    public ArrayList<Usuario> getLista_asistentes() {
        return lista_asistentes;
    }

    public void setLista_asistentes(ArrayList<Usuario> lista_asistentes) {
        this.lista_asistentes = lista_asistentes;
    }

    public void mostrar_butacas() {
        for (Butaca b : this.sala.getLista_butacas()) {
            System.out.print(b.getPosicion() + " ");
        }
    }

    public void mostrar_info_evento() {

        fecha_formateada = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.println("\nTipo de evento: " + tipo_evento);
        System.out.println("Nombre del evento: " + nombre);
        System.out.println("Invitado especial: " + invitado);
        System.out.println("Sala del evento: " + sala.getNombre());
        System.out.println("Fecha y hora del evento: " + fecha_formateada + " a las " + hora);
        System.out.println("Precio del evento: " + precio + "€");
        System.out.println("Capacidad de la sala: " + asistentes_maximos);
    }


}
