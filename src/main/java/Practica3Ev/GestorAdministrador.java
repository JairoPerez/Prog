package Practica3Ev;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Practica3Ev.clases.Evento;
import Practica3Ev.clases.Sala;
import Practica3Ev.clases.Usuario;

public class GestorAdministrador {
    Scanner sc = new Scanner(System.in);
    ArrayList<Evento> listado_eventos;
    Sala[] listado_salas;


    GestorAdministrador(ArrayList<Evento> lista_eventos, Sala[] listado_salas) {
        this.listado_eventos = lista_eventos;
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
                nombreEvento = Validaciones.validarNombre(nombreEvento);
            } while (nombreEvento.isEmpty());

            do {
                System.out.println("Introduce el invitado especial que desea añadir al evento");
                invitado = sc.nextLine();
                invitado = Validaciones.validarNombre(invitado);
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
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Introduce un número dentro del rango 1-5");
                }

            } while (sala == null);

            sc.nextLine();

            do {
                System.out.println("Seleccione fecha para el evento");
                fechaEvento = sc.nextLine();
                fechaEvento = Validaciones.validarFechaEvento(fechaEvento);
            } while (fechaEvento.isEmpty());

            System.out.println("Seleccione hora para el evento");
            sc.nextLine();

            do {
                System.out.println("Seleccione hora para el evento");
                horaEvento = sc.nextLine();
                horaEvento = Validaciones.validarHora(horaEvento);
            } while (horaEvento.isEmpty());

            do {
                System.out.println("Introduzca precio del evento");
                precioEvento = sc.nextLine();
                precioEvento = Validaciones.validarDouble(precioEvento);
            } while (precioEvento.isEmpty());

            do {
                System.out.println("Que tipo de evento desea crear");
                tipoEvento = sc.nextLine();
                tipoEvento = Validaciones.validarNombre(tipoEvento);
            } while (tipoEvento.isEmpty());

            fechaEvento = LocalDate.EPOCH.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            LocalDate fecha = LocalDate.parse(fechaEvento);
            double precio = Double.parseDouble(precioEvento);

            listado_eventos.add(new Evento(nombreEvento, invitado, sala, fecha, LocalTime.of(12, 54), precio, tipoEvento, sala.getCapacidad_maxima(), new ArrayList<Usuario>()));

        } while (salir);

    }

    public void eliminarEvento() {
        int eventoEliminar = 0;

        do {
            try {
                for (int i = 0; i < listado_eventos.size(); i++) {
                    System.out.println(i + 1 + ". " + listado_eventos.get(i).getNombre() + ".");
                }
                System.out.println("¿Que evento quieres eliminar?");
                eventoEliminar = sc.nextInt();

                if (eventoEliminar >= 1 && eventoEliminar <= listado_eventos.size()) {
                    listado_eventos.remove(eventoEliminar - 1);
                    System.out.println("Evento eliminado correctamente");
                    break;
                } else {
                    System.out.println("Introduce un número válido");
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Introduce un carácter númerico");
                sc.next();
                break;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Introduce un número de evento válido");
                break;
            }
        } while (eventoEliminar != listado_eventos.size());
    }

    public void listarEventos() {
        for (Evento e : listado_eventos) {
            e.mostrar_info_evento();
        }
    }

    public void listarEvento() {
        int eventoEliminar = 0;
        boolean salir = true;

        do {
            try {
                for (int i = 0; i < listado_eventos.size(); i++) {
                    System.out.println(i + 1 + ". " + listado_eventos.get(i).getNombre() + ".");
                }
                System.out.println("¿De que evento quieres informarte?");
                eventoEliminar = sc.nextInt();

                if (eventoEliminar >= 1 && eventoEliminar <= listado_eventos.size()) {
                    listado_eventos.get(eventoEliminar - 1).mostrar_info_evento();
                    salir=true;
                } else {
                    System.out.println("Introduce un valor válido");
                    salir=false;

                }
            } catch (InputMismatchException ex) {
                System.out.println("Introduce un valor númerico");
                salir=false;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Introduce un número de evento válido");
                salir=false;
            }
        } while (!salir);
    }


}
