package Practica3Ev;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Practica3Ev.clases.Evento;
import Practica3Ev.clases.Sala;
import Practica3Ev.clases.Usuario;

import javax.xml.stream.FactoryConfigurationError;

public class GestorAdministrador {
    Scanner sc = new Scanner(System.in);

    public void insertarEvento(ArrayList<Evento> listado_eventos, Sala[] listado_salas, ArrayList<Usuario> asistentes) {
        boolean salir = false;
        int numSala;
        String nombreEvento;
        String invitado;
        Sala sala = null;
        String fechaEvento;
        String horaEvento;
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
                    salir = false;
                }

            } while (sala.equals(""));


            do {
                System.out.println("Seleccione fecha para el evento");
                fechaEvento = sc.nextLine();
                fechaEvento = Validaciones.validar_fecha(fechaEvento);
            } while (fechaEvento.isEmpty());

            do {
                System.out.println("Seleccione hora para el evento");
                horaEvento = sc.nextLine();   //VALIDAR HORA CREAR VALIDACION
                horaEvento = Validaciones.validar_fecha(horaEvento);
            } while (horaEvento.isEmpty());

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

            LocalDate fecha = LocalDate.parse(fechaEvento);
            LocalTime hora = LocalTime.parse(horaEvento);
            double precio = Double.parseDouble(precioEvento);

            listado_eventos.add(new Evento(nombreEvento, invitado, sala, fecha, hora, precio, tipoEvento, sala.getCapacidad_maxima(), null));

        } while (salir);

    }

    public void eliminarEvento(ArrayList<Evento> listado_eventos) {
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

    public void listarEventos(ArrayList<Evento> listado_eventos) {
        for (Evento e : listado_eventos) {
            System.out.println(e);
        }
    }

    public void listarEvento() {
        //INTENTAR COGER EL EVENTO DEL ID QUE ME PIDA EL USUARIO
    }

}
