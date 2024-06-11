package Practica3Ev;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Practica3Ev.clases.Asistente;
import Practica3Ev.clases.Evento;
import Practica3Ev.clases.Sala;
import Practica3Ev.clases.Usuario;

import javax.xml.stream.FactoryConfigurationError;

public class GestorAdministrador {
    Scanner sc = new Scanner(System.in);
    ArrayList<Evento> listado_eventos;
    Sala[] listado_salas;


    GestorAdministrador(ArrayList<Evento> lista_eventos,Sala[] listado_salas){
        this.listado_eventos=lista_eventos;
        this.listado_salas = listado_salas;
    }

    public void insertarEvento() {
        boolean salir = false;
        int numSala;
        String nombreEvento;
        String invitado;
        Sala sala = null;
        String fechaEvento;
        String horaEvento = null;
        String precioEvento;
        String tipoEvento;


        do {
            do {
                System.out.println("Introduce el nombre del evento que quieres añadir");
                nombreEvento = sc.nextLine();
                nombreEvento = Validaciones.validar_nombre(nombreEvento);
            } while (nombreEvento.isEmpty());

            do {
                System.out.println("Introduce el invitado especial que desea añadir al evento");
                invitado = sc.nextLine();
                invitado = Validaciones.validar_nombre(invitado);
            } while (invitado.isEmpty());

            do {
                System.out.println("¿Que sala quiere escoger, hay hasta un máximo de 5 salas?");
                try {
                    numSala = sc.nextInt();
                    sala = listado_salas[numSala - 1];
                    if (!(numSala >= 1 && numSala <= 5)) {
                        System.out.println("Introduce un número de sala en un rango de 1-5");
                        salir = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Tiene que introducir un valor númerico en un rango de 1-5");
                    sc.next();
                } catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("Introduce un número dentro del rango 1-5");
                }

            } while (sala==null);

            sc.nextLine();

            do {
                System.out.println("Seleccione fecha para el evento");
                fechaEvento = sc.nextLine();
                fechaEvento = Validaciones.validar_fecha(fechaEvento);
            } while (fechaEvento.isEmpty());

            System.out.println("Seleccione hora para el evento");
            sc.nextLine();
            
//            do {
//                System.out.println("Seleccione hora para el evento");
//                horaEvento = sc.nextLine();   //VALIDAR HORA CREAR VALIDACION
//                horaEvento = Validaciones.validar_hora(horaEvento);
//            } while (horaEvento.isEmpty());

            do {
                System.out.println("Introduzca precio del evento");
                precioEvento = sc.nextLine();
                precioEvento = Validaciones.validar_double(precioEvento);
            } while (precioEvento.isEmpty());

            do {
                System.out.println("Que tipo de evento desea crear");
                tipoEvento = sc.nextLine();
                tipoEvento = Validaciones.validar_nombre(tipoEvento);
            } while (tipoEvento.isEmpty());

            fechaEvento = LocalDate.EPOCH.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            LocalDate fecha = LocalDate.parse(fechaEvento);
            double precio = Double.parseDouble(precioEvento);

            listado_eventos.add(new Evento(nombreEvento, invitado, sala, fecha, LocalTime.of(12, 54), precio, tipoEvento, sala.getCapacidad_maxima(), new ArrayList<Usuario>()));

        } while (salir);

    }

    public void eliminarEvento() {
        //NS COMO PONER EL EVENTO QUE SELECCIONA EL USUARIO
        System.out.println("¿Que evento quieres eliminar?");
        int eventoEliminar = sc.nextInt();

        try {
            for (int i = 1; i < listado_eventos.size() - 1; i++) {
                System.out.println(i + ". " + listado_eventos.get(i).getNombre()+".");
                if (eventoEliminar != i) {
                    System.out.println("Introduce un número de evento válido");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Introduce un carácter númerico");
        }
    }

    public void listarEventos() {
        for (Evento e : listado_eventos) {
            e.mostrar_info_evento();
        }
    }

    public void listarEvento() {
        //INTENTAR COGER EL EVENTO DEL ID QUE ME PIDA EL USUARIO
    }

}
