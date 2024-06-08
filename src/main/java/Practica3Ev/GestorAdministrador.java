package Practica3Ev;

import Practica3Ev.clases.Evento;

public class GestorAdministrador extends Gestor{

    public void insertarEvento(){
        //NS COMO PONER EL EVENTO QUE SELECCIONA EL USUARIO
        listado_eventos.add(new Evento());

    }
    public void eliminarEvento(){
        //NS COMO PONER EL EVENTO QUE SELECCIONA EL USUARIO
        listado_eventos.remove(new Evento());
    }
    public void listarEventos(){
        for (Evento e : listado_eventos){
            System.out.println(e);
        }
    }

    public void listarEvento(){
        //INTENTAR COGER EL EVENTO DEL ID QUE ME PIDA EL USUARIO
    }

}
