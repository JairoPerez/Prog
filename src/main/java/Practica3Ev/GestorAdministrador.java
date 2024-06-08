package Practica3Ev;
import java.util.Scanner;
import Practica3Ev.clases.Evento;

public class GestorAdministrador extends Gestor{
    Scanner sc = new Scanner(System.in);

    public void insertarEvento(){
        System.out.println("Introduce el nombre del evento que quieres añadir");
        String nombreEvento = sc.nextLine();
        nombreEvento = Validaciones.validar_nombre(nombreEvento);
        
        System.out.println("Introduce el invitado especial");
        String invitado = sc.nextLine();
        invitado = Validaciones.validar_nombre(invitado);
        
        System.out.println("¿Que sala quieres escoger?");
        for(Salas s = listado_salas){

            
            System.out.println(s);
        }
        String sala = sc.nextLine();
        
        System.out.println("Seleccione fecha para el evento");
        LocalDate fechaEvento = sc.nextLine();
        fechaEvento = Validaciones.validar_fecha(fechaEvento);

        System.out.println("Seleccione hora para el evento");
        LocalTime horaEvento = sc.nextLine();
        horaEvento = Validaciones.validar_fecha(horaEvento);
        
        //NS COMO PONER EL EVENTO QUE SELECCIONA EL USUARIO
        listado_eventos.add(new Evento(nombreEvento, invitado, sala, fechaEvento, horaEvento));

    }
    public void eliminarEvento(){
        //NS COMO PONER EL EVENTO QUE SELECCIONA EL USUARIO
        System.out.println("¿Que evento quieres eliminar?");
        for(Eventos e : listado_eventos){
            System.out.println(e);
        }
        String eventoSeleccionado = sc.nextLine();
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
